package test.boot.core.redis;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;

public class WrappedJedisCluster implements JedisExecutor {
	
	protected JedisCluster jedisCluster;

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public WrappedJedisCluster(JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}

	public Set<String> scan(String pattern) {
		return scan(pattern, 10000);
	}
	
	public Set<String> scan(String pattern, int count) {
		Set<String> result = new HashSet<>();
		
		for (JedisPool jedisPool : jedisCluster.getClusterNodes().values()) {
			String cursor = "0";
			do {
				Jedis jedis = jedisPool.getResource();
				ScanResult<String> scanResult = jedis.scan(cursor, 
						new ScanParams().match(pattern).count(count));
				result.addAll(scanResult.getResult());
				cursor = scanResult.getStringCursor();
				jedis.close();
			} while (!cursor.equals("0"));
		}
		
		return result;
	}
	
	@Override
	public String set(final String key, final String value) {
		return jedisCluster.set(key, value);
	}

	@Override
	public String get(final String key) {

		return jedisCluster.get(key);

	}

	@Override
	public Boolean exists(final String key) {

		return jedisCluster.exists(key);

	}

	@Override
	public String type(final String key) {

		return jedisCluster.type(key);

	}

	@Override
	public Long setrange(final String key, final long offset, final String value) {
		return jedisCluster.setrange(key, offset, value);
	}
	
	@Override
	public String getrange(final String key, final long startOffset,
			final long endOffset) {

		return jedisCluster.getrange(key, startOffset, endOffset);

	}

	@Override
	public String getSet(final String key, final String value) {

		return jedisCluster.getSet(key, value);

	}

	@Override
	public Long setnx(final String key, final String value) {

		return jedisCluster.setnx(key, value);

	}

	@Override
	public String setex(final String key, final int seconds, final String value) {

		return jedisCluster.setex(key, seconds, value);

	}

	@Override
	public String psetex(final String key, final long milliseconds,
			final String value) {

		return jedisCluster.psetex(key, milliseconds, value);

	}

	@Override
	public Long decrBy(final String key, final long integer) {

		return jedisCluster.decrBy(key, integer);

	}

	@Override
	public Long decr(final String key) {

		return jedisCluster.decr(key);

	}

	@Override
	public Long incrBy(final String key, final long integer) {

		return jedisCluster.incrBy(key, integer);

	}

	@Override
	public Double incrByFloat(final String key, final double value) {

		return jedisCluster.incrByFloat(key, value);

	}

	@Override
	public Long incr(final String key) {

		return jedisCluster.incr(key);

	}

	@Override
	public Long append(final String key, final String value) {

		return jedisCluster.append(key, value);

	}

	@Override
	public String substr(final String key, final int start, final int end) {

		return jedisCluster.substr(key, start, end);

	}

	@Override
	public Long hset(final String key, final String field, final String value) {
		Long result = jedisCluster.hset(key, field, value);
		logger.debug("hset key[{}] field[{}] value[{}]", key, field, value);
		return result;

	}

	@Override
	public String hget(final String key, final String field) {
		String result = jedisCluster.hget(key, field);
		logger.debug("hget key[{}] field[{}] value[{}]", key, field, result);
		return result;

	}

	@Override
	public Long hsetnx(final String key, final String field, final String value) {

		return jedisCluster.hsetnx(key, field, value);

	}

	@Override
	public String hmset(final String key, final Map<String, String> hash) {
		String result = jedisCluster.hmset(key, hash);
		logger.debug("hmset key[{}] hash[{}]", key, hash);
		return result;

	}

	@Override
	public List<String> hmget(final String key, final String... fields) {

		return jedisCluster.hmget(key, fields);

	}

	@Override
	public Long hincrBy(final String key, final String field, final long value) {

		return jedisCluster.hincrBy(key, field, value);

	}

	@Override
	public Double hincrByFloat(final String key, final String field,
			final double value) {

		return jedisCluster.hincrByFloat(key, field, value);

	}

	@Override
	public Boolean hexists(final String key, final String field) {

		return jedisCluster.hexists(key, field);

	}

	@Override
	public Long hdel(final String key, final String... field) {

		return jedisCluster.hdel(key, field);

	}

	@Override
	public Long hlen(final String key) {

		return jedisCluster.hlen(key);

	}

	@Override
	public Set<String> hkeys(final String key) {

		return jedisCluster.hkeys(key);

	}

	@Override
	public List<String> hvals(final String key) {

		return jedisCluster.hvals(key);

	}

	@Override
	public Map<String, String> hgetall(final String key) {

		return jedisCluster.hgetAll(key);

	}

	@Override
	public Long rpush(final String key, final String... string) {

		return jedisCluster.rpush(key, string);

	}

	@Override
	public Long lpush(final String key, final String... string) {

		return jedisCluster.lpush(key, string);

	}
	
	@Override
	public String rpoplpush(final String srckey, final String dstkey) {
		
		return jedisCluster.rpoplpush(srckey, dstkey);
		
	}

	@Override
	public Long llen(final String key) {

		return jedisCluster.llen(key);

	}

	@Override
	public List<String> lrange(final String key, final long start,
			final long end) {
		List<String> result = jedisCluster.lrange(key, start, end);
		logger.debug("lrange key[{}] start[{}] end[{}] result[{}]", key, start, end, result);
		return result;

	}

	@Override
	public String ltrim(final String key, final long start, final long end) {
		String result = jedisCluster.ltrim(key, start, end);
		logger.debug("ltrim key[{}] start[{}] end[{}] result[{}]", key, start, end, result);
		return result;

	}

	@Override
	public String lindex(final String key, final long index) {

		return jedisCluster.lindex(key, index);

	}

	@Override
	public String lset(final String key, final long index, final String value) {

		return jedisCluster.lset(key, index, value);

	}

	@Override
	public Long lrem(final String key, final long count, final String value) {

		return jedisCluster.lrem(key, count, value);

	}

	@Override
	public String lpop(final String key) {

		return jedisCluster.lpop(key);

	}

	@Override
	public String rpop(final String key) {

		return jedisCluster.rpop(key);

	}

	@Override
	public Long sadd(final String key, final String... member) {

		return jedisCluster.sadd(key, member);

	}

	@Override
	public Set<String> smembers(final String key) {

		return jedisCluster.smembers(key);

	}

	@Override
	public Long srem(final String key, final String... member) {

		return jedisCluster.srem(key, member);

	}

	@Override
	public String spop(final String key) {

		return jedisCluster.spop(key);

	}

	@Override
	public Set<String> spop(final String key, final long count) {

		return jedisCluster.spop(key, count);

	}

	@Override
	public Long scard(final String key) {

		return jedisCluster.scard(key);

	}

	@Override
	public Boolean sismember(final String key, final String member) {

		return jedisCluster.sismember(key, member);

	}

	@Override
	public String srandmember(final String key) {

		return jedisCluster.srandmember(key);

	}

	@Override
	public List<String> srandmember(final String key, final int count) {

		return jedisCluster.srandmember(key, count);

	}

	@Override
	public Long strlen(final String key) {

		return jedisCluster.strlen(key);

	}

	@Override
	public Long zadd(final String key, final Map<String, Double> scoreMembers) {

		return jedisCluster.zadd(key, scoreMembers);

	}

	@Override
	public Set<String> zrange(final String key, final long start, final long end) {

		return jedisCluster.zrange(key, start, end);

	}

	@Override
	public Long zrem(final String key, final String... member) {
		Long result = jedisCluster.zrem(key, member);
		logger.debug("zrem key[{}] member[{}] total[{}]", key, member, result);
		return result;

	}

	@Override
	public Double zincrby(final String key, final double score,
			final String member) {

		return jedisCluster.zincrby(key, score, member);

	}

	@Override
	public Long zrank(final String key, final String member) {

		return jedisCluster.zrank(key, member);

	}

	@Override
	public Long zrevrank(final String key, final String member) {

		return jedisCluster.zrevrank(key, member);

	}

	@Override
	public Set<String> zrevrange(final String key, final long start,
			final long end) {

		return jedisCluster.zrevrange(key, start, end);

	}

	@Override
	public Set<Tuple> zrangeWithScores(final String key, final long start,
			final long end) {

		return jedisCluster.zrangeWithScores(key, start, end);

	}

	@Override
	public Set<Tuple> zrevrangeWithScores(final String key, final long start,
			final long end) {

		return jedisCluster.zrevrangeWithScores(key, start, end);

	}

	@Override
	public Long zcard(final String key) {

		return jedisCluster.zcard(key);

	}

	@Override
	public Double zscore(final String key, final String member) {

		return jedisCluster.zscore(key, member);

	}

	@Override
	public List<String> sort(final String key) {

		return jedisCluster.sort(key);

	}

	@Override
	public Long zcount(final String key, final double min, final double max) {

		return jedisCluster.zcount(key, min, max);

	}

	@Override
	public Set<String> zrangeByScore(final String key, final double min,
			final double max) {

		return jedisCluster.zrangeByScore(key, min, max);

	}

	@Override
	public Set<String> zrevrangeByScore(final String key, final double max,
			final double min) {

		return jedisCluster.zrevrangeByScore(key, max, min);

	}

	@Override
	public Set<String> zrangeByScore(final String key, final double min,
			final double max, final int offset, final int count) {

		return jedisCluster.zrangeByScore(key, min, max, offset, count);

	}

	@Override
	public Set<String> zrevrangeByScore(final String key, final String max,
			final String min) {

		return jedisCluster.zrevrangeByScore(key, max, min);

	}

	@Override
	public Set<String> zrangeByScore(final String key, final String min,
			final String max, final int offset, final int count) {

		return jedisCluster.zrangeByScore(key, min, max, offset, count);

	}

	@Override
	public Set<String> zrevrangeByScore(final String key, final double max,
			final double min, final int offset, final int count) {

		return jedisCluster.zrevrangeByScore(key, max, min, offset, count);

	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(final String key,
			final double min, final double max, final int offset,
			final int count) {

		return jedisCluster.zrangeByScoreWithScores(key, min, max, offset,
				count);

	}

	@Override
	public Set<String> zrevrangeByScore(final String key, final String max,
			final String min, final int offset, final int count) {

		return jedisCluster.zrevrangeByScore(key, max, min, offset, count);

	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(final String key,
			final double max, final double min, final int offset,
			final int count) {

		return jedisCluster.zrevrangeByScoreWithScores(key, max, min, offset,
				count);

	}

	@Override
	public Long zremrangeByRank(final String key, final long start,
			final long end) {

		return jedisCluster.zremrangeByRank(key, start, end);

	}

	@Override
	public Long zremrangeByScore(final String key, final double start,
			final double end) {

		return jedisCluster.zremrangeByScore(key, start, end);

	}

	@Override
	public Long zremrangeByScore(final String key, final String start,
			final String end) {
		Long result = jedisCluster.zremrangeByScore(key, start, end);
		logger.debug("zremrangebyscore key[{}] start[{}] end[{}] total[{}]",
				key, start, end, result);
		return result;

	}

	@Override
	public Long zlexcount(final String key, final String min, final String max) {

		return jedisCluster.zlexcount(key, min, max);

	}

	@Override
	public Set<String> zrangeByLex(final String key, final String min,
			final String max) {

		return jedisCluster.zrangeByLex(key, min, max);

	}

	@Override
	public Set<String> zrangeByLex(final String key, final String min,
			final String max, final int offset, final int count) {

		return jedisCluster.zrangeByLex(key, min, max, offset, count);

	}

	@Override
	public Set<String> zrevrangeByLex(final String key, final String max,
			final String min) {

		return jedisCluster.zrevrangeByLex(key, max, min);

	}

	@Override
	public Set<String> zrevrangeByLex(final String key, final String max,
			final String min, final int offset, final int count) {

		return jedisCluster.zrevrangeByLex(key, max, min, offset, count);

	}

	@Override
	public Long zremrangeByLex(final String key, final String min,
			final String max) {

		return jedisCluster.zremrangeByLex(key, min, max);

	}

	@Override
	public Long linsert(final String key, final LIST_POSITION where,
			final String pivot, final String value) {

		return jedisCluster.linsert(key, where, pivot, value);

	}

	@Override
	public Long lpushx(final String key, final String... string) {

		return jedisCluster.lpushx(key, string);

	}

	@Override
	public Long rpushx(final String key, final String... string) {

		return jedisCluster.rpushx(key, string);

	}

	@Override
	public String echo(final String string) {

		return jedisCluster.echo(string);

	}


	@Override
	public Long del(final String... keys) {

		return jedisCluster.del(keys);

	}

	@Override
	public List<String> mget(final String... keys) {

		return jedisCluster.mget(keys);

	}

	@Override
	public String mset(final String... keysvalues) {
		String[] keys = new String[keysvalues.length / 2];

		for (int keyIdx = 0; keyIdx < keys.length; keyIdx++) {
			keys[keyIdx] = keysvalues[keyIdx * 2];
		}

		return jedisCluster.mset(keysvalues);

	}

	@Override
	public Long msetnx(final String... keysvalues) {
		String[] keys = new String[keysvalues.length / 2];

		for (int keyIdx = 0; keyIdx < keys.length; keyIdx++) {
			keys[keyIdx] = keysvalues[keyIdx * 2];
		}

		return jedisCluster.msetnx(keysvalues);

	}

	@Override
	public String rename(final String oldkey, final String newkey) {

		return jedisCluster.rename(oldkey, newkey);

	}

	@Override
	public Long renamenx(final String oldkey, final String newkey) {

		return jedisCluster.renamenx(oldkey, newkey);

	}

	@Override
	public Long sdiffstore(final String dstkey, final String... keys) {
		return jedisCluster.sdiffstore(dstkey, keys);

	}

	@Override
	public Set<String> sinter(final String... keys) {

		return jedisCluster.sinter(keys);

	}

	@Override
	public Long sinterstore(final String dstkey, final String... keys) {
		return jedisCluster.sinterstore(dstkey, keys);

	}

	@Override
	public Long smove(final String srckey, final String dstkey,
			final String member) {

		return jedisCluster.smove(srckey, dstkey, member);

	}

	@Override
	public Long sort(final String key, final SortingParams sortingParameters,
			final String dstkey) {

		return jedisCluster.sort(key, sortingParameters, dstkey);

	}

	@Override
	public Long sort(final String key, final String dstkey) {

		return jedisCluster.sort(key, dstkey);

	}

}
