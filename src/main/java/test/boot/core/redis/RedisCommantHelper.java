package test.boot.core.redis;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Tuple;
import test.boot.common.exception.BusinessException;

@Component
public class RedisCommantHelper {
	
	/**
	 * 定义可用的命令
	 */
	private Map<String, RedisCommand> supportedCommands = new HashMap<>();

	@PostConstruct  
    public void init() {
		// TODO scan
		// Key
		supportedCommands.put("DEL", new RedisCommand("DEL", "DEL key"));
		//supportedCommands.put("DUMP", new RedisCommand("DUMP", "DUMP key"));
		supportedCommands.put("EXISTS", new RedisCommand("EXISTS", "EXISTS key"));
		supportedCommands.put("EXPIRE", new RedisCommand("EXPIRE", "EXPIRE key seconds"));
		supportedCommands.put("EXPIREAT", new RedisCommand("EXPIREAT", "EXPIREAT key timestamp"));
		supportedCommands.put("PEXPIRE", new RedisCommand("PEXPIRE", "PEXPIRE key milliseconds"));
		supportedCommands.put("PEXPIREAT", new RedisCommand("PEXPIREAT", "PEXPIREAT key milliseconds-timestamp"));
		//supportedCommands.put("KEYS", new RedisCommand("KEYS", "KEYS pattern"));
		supportedCommands.put("MOVE", new RedisCommand("MOVE", "MOVE key db"));
		supportedCommands.put("PERSIST", new RedisCommand("PERSIST", "PERSIST key"));
		//supportedCommands.put("PTTL", new RedisCommand("PTTL", "PTTL key"));
		//supportedCommands.put("TTL", new RedisCommand("TTL", "TTL key"));
		supportedCommands.put("RANDOM", new RedisCommand("RANDOM", "RANDOM KEY "));
		supportedCommands.put("RENAME", new RedisCommand("RENAME", "RENAME key newkey"));
		supportedCommands.put("RENAMENX", new RedisCommand("RENAMENX", "RENAMENX key newkey"));
		supportedCommands.put("TYPE", new RedisCommand("TYPE", "TYPE key"));
		// String
		supportedCommands.put("SET", new RedisCommand("SET", "SET key value"));
		supportedCommands.put("GET", new RedisCommand("GET", "GET key"));
		supportedCommands.put("GETRANGE", new RedisCommand("GETRANGE", "GETRANGE key start end"));
		supportedCommands.put("GETSET", new RedisCommand("GETSET", "GETSET key value"));
		supportedCommands.put("GETBIT", new RedisCommand("GETBIT", "GETBIT key offset"));
		supportedCommands.put("MGET", new RedisCommand("MGET", "MGET key1 [key2..]"));
		supportedCommands.put("SETBIT", new RedisCommand("SETBIT", "SETBIT key offset value"));
		supportedCommands.put("SETEX", new RedisCommand("SETEX", "SETEX key seconds value"));
		supportedCommands.put("SETNX", new RedisCommand("SETNX", "SETNX key value"));
		supportedCommands.put("SETRANGE", new RedisCommand("SETRANGE", "SETRANGE key offset value"));
		supportedCommands.put("STRLEN", new RedisCommand("STRLEN", "STRLEN key"));
		supportedCommands.put("MSET", new RedisCommand("MSET", "MSET key value [key value ...]"));
		supportedCommands.put("MSETNX", new RedisCommand("MSETNX", "MSETNX key value [key value ...]"));
		supportedCommands.put("PSETEX", new RedisCommand("PSETEX", "PSETEX key milliseconds value"));
		supportedCommands.put("INCR", new RedisCommand("INCR", "INCR key"));
		supportedCommands.put("INCRBY", new RedisCommand("INCRBY", "INCRBY key increment"));
		supportedCommands.put("INCRBYFLOAT", new RedisCommand("INCRBYFLOAT", "INCRBYFLOAT key increment"));
		supportedCommands.put("DECR", new RedisCommand("DECR", "DECR key"));
		supportedCommands.put("DECRBY", new RedisCommand("DECRBY", "DECRBY key decrement"));
		supportedCommands.put("APPEND", new RedisCommand("APPEND", "APPEND key value"));
		// List
		supportedCommands.put("BLPOP", new RedisCommand("BLPOP", "BLPOP key1 [key2 ] timeout"));
		supportedCommands.put("BRPOP", new RedisCommand("BRPOP", "BRPOP key1 [key2 ] timeout"));
		supportedCommands.put("BRPOPLPUSH", new RedisCommand("BRPOPLPUSH", "BRPOPLPUSH source destination timeout"));
		supportedCommands.put("LINDEX", new RedisCommand("LINDEX", "LINDEX key index"));
		supportedCommands.put("LINSERT", new RedisCommand("LINSERT", "LINSERT key BEFORE|AFTER pivot value"));
		supportedCommands.put("LLEN", new RedisCommand("LLEN", "LLEN key"));
		supportedCommands.put("LPOP", new RedisCommand("LPOP", "LPOP key"));
		supportedCommands.put("LPUSH", new RedisCommand("LPUSH", "LPUSH key value1 [value2]"));
		supportedCommands.put("LPUSHX", new RedisCommand("LPUSHX", "LPUSHX key value"));
		supportedCommands.put("LRANGE", new RedisCommand("LRANGE", "LRANGE key start stop"));
		supportedCommands.put("LREM", new RedisCommand("LREM", "LREM key count value"));
		supportedCommands.put("LSET", new RedisCommand("LSET", "LSET key index value"));
		supportedCommands.put("LTRIM", new RedisCommand("LTRIM", "LTRIM key start stop"));
		supportedCommands.put("RPOP", new RedisCommand("RPOP", "RPOP key"));
		supportedCommands.put("RPOPLPUSH", new RedisCommand("RPOPLPUSH", "RPOPLPUSH source destination"));
		supportedCommands.put("RPUSH", new RedisCommand("RPUSH", "RPUSH key value1 [value2]"));
		supportedCommands.put("RPUSHX", new RedisCommand("RPUSHX", "RPUSHX key value"));
		// HASH
		supportedCommands.put("HDEL", new RedisCommand("HDEL", "HDEL key field1 [field2]"));
		supportedCommands.put("HEXISTS", new RedisCommand("HEXISTS", "HEXISTS key field"));
		supportedCommands.put("HGET", new RedisCommand("HGET", "HGET key field"));
		supportedCommands.put("HGETALL", new RedisCommand("HGETALL", "HGETALL key"));
		supportedCommands.put("HINCRBY", new RedisCommand("HINCRBY", "HINCRBY key field increment"));
		supportedCommands.put("HINCRBYFLOAT", new RedisCommand("HINCRBYFLOAT", "HINCRBYFLOAT key field increment"));
		supportedCommands.put("HKEYS", new RedisCommand("HKEYS", "HKEYS key"));
		supportedCommands.put("HLEN", new RedisCommand("HLEN", "HLEN key"));
		supportedCommands.put("HMGET", new RedisCommand("HMGET", "HMGET key field1 [field2]"));
		supportedCommands.put("HMSET", new RedisCommand("HMSET", "HMSET key field1 value1 [field2 value2 ]"));
		supportedCommands.put("HSET", new RedisCommand("HSET", "HSET key field value"));
		supportedCommands.put("HSETNX", new RedisCommand("HSETNX", "HSETNX key field value"));
		supportedCommands.put("HVALS", new RedisCommand("HVALS", "HVALS key"));
		// Sorted Set
		supportedCommands.put("ZADD", new RedisCommand("ZADD", "ZADD key score1 member1 [score2 member2]"));
		supportedCommands.put("ZCARD", new RedisCommand("ZCARD", "ZCARD key"));
		supportedCommands.put("ZCOUNT", new RedisCommand("ZCOUNT", "ZCOUNT key min max"));
		supportedCommands.put("ZINCRBY", new RedisCommand("ZINCRBY", "ZINCRBY key increment member"));
		supportedCommands.put("ZINTERSTORE", new RedisCommand("ZINTERSTORE", "ZINTERSTORE destination numkeys key [key ...]"));
		supportedCommands.put("ZLEXCOUNT", new RedisCommand("ZLEXCOUNT", "ZLEXCOUNT key min max"));
		supportedCommands.put("ZRANGE", new RedisCommand("ZRANGE", "ZRANGE key start stop [WITHSCORES]"));
		supportedCommands.put("ZRANGEBYLEX", new RedisCommand("ZRANGEBYLEX", "ZRANGEBYLEX key min max [LIMIT offset count]"));
		supportedCommands.put("ZRANGEBYSCORE", new RedisCommand("ZRANGEBYSCORE", "ZRANGEBYSCORE key min max [WITHSCORES] [LIMIT]"));
		supportedCommands.put("ZRANK", new RedisCommand("ZRANK", "ZRANK key member"));
		supportedCommands.put("ZREM", new RedisCommand("ZREM", "ZREM key member [member ...]"));
		supportedCommands.put("ZREMRANGEBYLEX", new RedisCommand("ZREMRANGEBYLEX", "ZREMRANGEBYLEX key min max"));
		supportedCommands.put("ZREMRANGEBYRANK", new RedisCommand("ZREMRANGEBYRANK", "ZREMRANGEBYRANK key start stop"));
		supportedCommands.put("ZREMRANGEBYSCORE", new RedisCommand("ZREMRANGEBYSCORE", "ZREMRANGEBYSCORE key min max"));
		supportedCommands.put("ZREVRANGE", new RedisCommand("ZREVRANGE", "ZREVRANGE key start stop [WITHSCORES]"));
		supportedCommands.put("ZREVRANGEBYSCORE", new RedisCommand("ZREVRANGEBYSCORE", "ZREVRANGEBYSCORE key max min [WITHSCORES]"));
		supportedCommands.put("ZREVRANK", new RedisCommand("ZREVRANK", "ZREVRANK key member"));
		supportedCommands.put("ZSCORE", new RedisCommand("ZSCORE", "ZSCORE key member"));
		supportedCommands.put("ZUNIONSTORE", new RedisCommand("ZUNIONSTORE", "ZUNIONSTORE destination numkeys key [key ...]"));
		// 自定义
		supportedCommands.put("ZRANGEWITHSCORES", new RedisCommand("ZRANGE", "ZRANGEWITHSCORES", "ZRANGE key start stop [WITHSCORES]"));
		supportedCommands.put("ZRANGEBYSCOREWITHSCORES", new RedisCommand("ZRANGEBYSCORE", "ZRANGEBYSCOREWITHSCORES", "ZRANGEBYSCORE key min max [WITHSCORES] [LIMIT]"));
		supportedCommands.put("ZREVRANGEWITHSCORES", new RedisCommand("ZREVRANGE", "ZREVRANGEWITHSCORES", "ZREVRANGE key start stop [WITHSCORES]"));
		supportedCommands.put("ZREVRANGEBYSCOREWITHSCORES", new RedisCommand("ZREVRANGEBYSCORE", "ZREVRANGEBYSCOREWITHSCORES", "ZREVRANGEBYSCORE key max min [WITHSCORES]"));
    }
	
	public Map<String, String> getSupportedCommands() {
		Map<String, String> result = new HashMap<>();
		supportedCommands.forEach((k, c) -> {
			result.put(c.getCommand(), c.getDesc());
		});
		return result;
	}
	
	public List<String> executeCommand(JedisExecutor jedisExecutor, String commandStr) throws Exception {
		if (commandStr == null || commandStr.trim().equals("")) {
			throw new BusinessException("please enter a command");
		}
		
		String command = getCommand(commandStr);
		if (!supportedCommands.containsKey(command)) {
			throw new BusinessException("unsupported command '" + command + "'");
		}
		
		if (commandStr.trim().split(" +").length == 1) {
			throw new BusinessException("wrong number of arguments for '" + command + "' command");
		}
		
		String processedCommandStr = processCommand(commandStr);
		String processedCommand = getCommand(processedCommandStr);
		RedisCommand redisCommand = getOrCreateCommand(processedCommand);
		String[] parameters = getParameters(processedCommandStr);
		validateCommandParameters(redisCommand, parameters);
		
		String key = getKey(commandStr);
		return getCommandResult(jedisExecutor, redisCommand, key, parameters);
	}

	private String getCommand(String commandStr) {
		return commandStr.trim().split(" +")[0].toUpperCase();
	}
	
	private String[] getParameters(String commandStr) {
		String[] keywords = commandStr.trim().split(" +");
		return keywords.length > 2 ? 
				Arrays.copyOfRange(keywords, 2, keywords.length) : new String[]{};
	}

	private String getKey(String commandStr) {
		return commandStr.trim().split(" +")[1];
	}
	
	/**
	 * WITHSCORES和LIMIT处理
	 * @param commandStr
	 * @return
	 */
	private String processCommand(String commandStr) {
		String result = commandStr.trim();
		// WITHSCORES
		result = result
				.replaceAll("(?i)^(ZRANGE|ZREVRANGE|ZRANGEBYSCORE|ZREVRANGEBYSCORE) +([^ ]+?) +(\\S+?) +(\\S+?) +WITHSCORES( .*)*$", 
						"$1WITHSCORES $2 $3 $4$5");
		result = result
				.replaceAll("(?i)^(ZRANGE|ZREVRANGE|ZRANGEBYSCORE|ZREVRANGEBYSCORE) +([^ ]+?) +(\\S+?) +(\\S+?) +LIMIT +(\\S+?) +(\\S+?) +WITHSCORES( .*)*$", 
						"$1WITHSCORES $2 $3 $4 LIMIT $5 $6$7");
		// LIMIT
		result = result
				.replaceAll("(?i)^(ZRANGEBYLEX|ZRANGEBYSCORE|ZRANGEBYSCOREWITHSCORES) +([^ ]+?) +(\\S+?) +(\\S+?)$", 
						"$1 $2 $3 $4 LIMIT 0 -1");
		result = result
				.replaceAll("(?i)^(ZRANGEBYLEX|ZRANGEBYSCORE|ZRANGEBYSCOREWITHSCORES) +([^ ]+?) +(\\S+?) +(\\S+?) +LIMIT +(\\S+?) +(\\S+?)$", 
						"$1 $2 $3 $4 $5 $6");
		
		return result;
	}
	
	private RedisCommand getOrCreateCommand(String command) {
		RedisCommand result = supportedCommands.get(command);
		if (result.getJedisMethod() == null) {
			result.setJedisMethod(createRedisCommandMethod(command));
			result.setParameterTypes(createRedisCommandParameterTypes(command));
		}
		
		return result;
	}

	private Method createRedisCommandMethod(String command) {
		return Stream.of(JedisExecutor.class.getMethods()).
				filter(m -> m.getName().toUpperCase().equals(command)).
				map(m -> m)
				.collect(Collectors.toList()).get(0);
	}
	
	private Class<?>[] createRedisCommandParameterTypes(String command) {
		return Stream.of(WrappedJedisCluster.class.getMethods()).
				filter(m -> m.getName().toUpperCase().equals(command)).
				map(m -> m.getParameterTypes().length > 1 ? 
						Arrays.copyOfRange(m.getParameterTypes(), 1, m.getParameterTypes().length)
						: new Class[] {})
				.collect(Collectors.toList()).get(0);
	}
	
	private void validateCommandParameters(RedisCommand redisCommand, String[] parameters) {
		if (!redisCommand.numberMatchingValidate(parameters)) {
			throw new BusinessException("wrong number of arguments for '" + redisCommand.getCommand() + "' command");
		}
		
		if (!redisCommand.typeMatchingValidate(parameters)) {
			throw new BusinessException("wrong type of arguments for '" + redisCommand.getCommand() + "' command");
		}
	}

	private List<String> getCommandResult(JedisExecutor jedisExecutor,
			RedisCommand redisCommand, String key, String[] parameters) throws Exception {
		Object commandResult = redisCommand.execute(jedisExecutor, key, parameters);
		if (commandResult == null) {
			return Arrays.asList(new String[]{"NULL"});
		}
		
		if (commandResult instanceof String || commandResult instanceof Boolean || commandResult instanceof Long) {
			return Arrays.asList(new String[]{String.valueOf(commandResult)});
		}
		
		if (commandResult instanceof Map) {
			List<String> result = new ArrayList<>();
			((Map<String, String>) commandResult).forEach((k, v) -> {
				result.add(k);
				result.add(v);
			});
			return result;
		}
		
		if (commandResult instanceof Set) {
			List<String> result = new ArrayList<>();
			((Set) commandResult).forEach(e -> {
				if (e instanceof Tuple) {
					result.add(((Tuple) e).getElement());
					result.add(String.valueOf(((Tuple) e).getScore()));
				} else {
					result.add(String.valueOf(e));
				}
			});
			return result;
		}
		
		return (List<String>) commandResult;
	}
}
