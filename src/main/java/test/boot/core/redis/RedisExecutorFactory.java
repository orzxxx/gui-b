package test.boot.core.redis;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import test.boot.dao.RedisConnectionDao;
import test.boot.dao.RedisNodeDao;
import test.boot.entity.tables.pojos.RedisConnection;
import test.boot.entity.tables.pojos.RedisNode;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Component
public class RedisExecutorFactory {
	
	@Autowired
	private RedisConnectionDao autowiredRedisConnectionDao;
	
	@Autowired
	private RedisNodeDao autowiredRedisNodeDao;
	
	private static RedisConnectionDao redisConnectionDao;
	
	private static RedisNodeDao redisNodeDao;

	private static LoadingCache<Integer, JedisExecutor> cache;

	@PostConstruct  
    public void init() {  
		cache = CacheBuilder.newBuilder()
				.maximumSize(100)
				.expireAfterWrite(3600, TimeUnit.SECONDS)
				.build(new CacheLoader<Integer, JedisExecutor>() {
					@Override
					public JedisExecutor load(Integer key) throws ExecutionException {
						return null;
					}
				});
		
		this.redisConnectionDao = this.autowiredRedisConnectionDao;
		this.redisNodeDao = this.autowiredRedisNodeDao;
    }  
	
    public static JedisExecutor createRedisExecutor(int connId) {
    	int cacheKey = connId;
    	JedisExecutor result = cache.getIfPresent(cacheKey);
    	
		if (result == null) {
			RedisConnection conn = redisConnectionDao.get(connId);
			List<RedisNode> nodes = redisNodeDao.listByCid(conn.getId());
			if (nodes.size() >= 3) {
				result = new WrappedJedisCluster(
						new JedisCluster(
								buildHostAndPort(redisNodeDao.listByCid(conn.getId())),
								Optional.ofNullable(conn.getConnectionTimeout()).orElse(5000), 
								Optional.ofNullable(conn.getSoTimeout()).orElse(5000), 
								5,
								conn.getAuth(), 
								new GenericObjectPoolConfig()
							)
						);
			} else {
				RedisNode node = nodes.get(0);
				result = new WrappedJedis(
						new JedisPool(
								new GenericObjectPoolConfig(), 
								node.getHost(), 
								node.getPort(), 
								conn.getConnectionTimeout(),
								conn.getAuth()));
			}
        	
    		cache.put(cacheKey, result);
		}
		
        return result;
    }
    
    private static Set<HostAndPort> buildHostAndPort(List<RedisNode> nodes) {
		return nodes.stream()
				.map(c -> new HostAndPort(c.getHost(), c.getPort()))
				.collect(Collectors.toSet());
    }

}
