package test.boot.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import test.boot.core.redis.JedisExecutor;
import test.boot.core.redis.RedisCommantHelper;
import test.boot.core.redis.RedisExecutorFactory;
import test.boot.service.RedisCommantService;

@Service
@Transactional
public class RedisCommantServiceImpl implements RedisCommantService {
	
	@Autowired
	private RedisCommantHelper redisCommentHelper;
	
	@Override
	public List<String> getRedisCommantResult(int connId, String command) throws Exception {
		JedisExecutor jedisExecutor = RedisExecutorFactory.createRedisExecutor(connId);
		return redisCommentHelper.executeCommand(jedisExecutor, command);
	}

	@Override
	public Map<String, String> getSupportedRedisCommant() {
		return redisCommentHelper.getSupportedCommands();
	}
	
}
