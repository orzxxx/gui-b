package test.boot.core.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public abstract class JedisCommand<T> {

  private Jedis jedis;

  public JedisCommand(JedisPool jedisPool) {
	  this.jedis = jedisPool.getResource();
  }

  public abstract T execute(Jedis jedis);

  public T run() {
      try {
          return execute(jedis);
      } finally {
          jedis.close();
      }
  }
}
