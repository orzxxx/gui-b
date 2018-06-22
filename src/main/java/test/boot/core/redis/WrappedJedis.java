package test.boot.core.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;

public class WrappedJedis implements JedisExecutor {

	private static Logger logger = LoggerFactory.getLogger(WrappedJedis.class);

	private JedisPool jedisPool;

	public WrappedJedis(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	@Override
	public String set(final String key, String value) {

		return new JedisCommand<String>(jedisPool) {
			@Override
			public String execute(Jedis jedis) {
				String result = jedis.set(key, value);
				return result;
			}
		}.run();

	}

	/**
	 * Get the value of the specified key. If the key does not exist null is
	 * returned. If the value stored at key is not a string an error is returned
	 * because GET can only handle string values.
	 * <p>
	 * Time complexity: O(1)
	 * 
	 * @param key
	 * @return Bulk reply
	 */
	@Override
	public String get(final String key) {

		return new JedisCommand<String>(jedisPool) {
			@Override
			public String execute(Jedis jedis) {
				String result = jedis.get(key);
				return result;
			}
		}.run();

	}

	/**
	 * Test if the specified key exists. The command returns "1" if the key
	 * exists, otherwise "0" is returned. Note that even keys set with an empty
	 * string as value will return "1". Time complexity: O(1)
	 * 
	 * @param key
	 * @return Boolean reply, true if the key exists, otherwise false
	 */
	@Override
	public Boolean exists(final String key) {

		return new JedisCommand<Boolean>(jedisPool) {
			@Override
			public Boolean execute(Jedis jedis) {
				Boolean result = jedis.exists(key);
				return result;
			}
		}.run();

	}

	/**
	 * Remove the specified keys. If a given key does not exist no operation is
	 * performed for this key. The command returns the number of keys removed.
	 * Time complexity: O(1)
	 * 
	 * @param keys
	 * @return Integer reply, specifically: an integer greater than 0 if one or
	 *         more keys were removed 0 if none of the specified key existed
	 */
	@Override
	public Long del(final String... keys) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.del(keys);
				return result;
			}
		}.run();

	}

	/**
	 * Return the type of the value stored at key in form of a string. The type
	 * can be one of "none", "string", "list", "set". "none" is returned if the
	 * key does not exist. Time complexity: O(1)
	 * 
	 * @param key
	 * @return Status code reply, specifically: "none" if the key does not exist
	 *         "string" if the key contains a String value "list" if the key
	 *         contains a List value "set" if the key contains a Set value
	 *         "zset" if the key contains a Sorted Set value "hash" if the key
	 *         contains a Hash value
	 */
	@Override
	public String type(final String key) {

		return new JedisCommand<String>(jedisPool) {
			@Override
			public String execute(Jedis jedis) {
				String result = jedis.type(key);
				return result;
			}
		}.run();

	}

	/**
	 * Atomically renames the key oldkey to newkey. If the source and
	 * destination name are the same an error is returned. If newkey already
	 * exists it is overwritten.
	 * <p>
	 * Time complexity: O(1)
	 * 
	 * @param oldkey
	 * @param newkey
	 * @return Status code repy
	 */
	@Override
	public String rename(final String oldkey, final String newkey) {

		return new JedisCommand<String>(jedisPool) {
			@Override
			public String execute(Jedis jedis) {
				String result = jedis.rename(oldkey, newkey);
				return result;
			}
		}.run();

	}

	/**
	 * Rename oldkey into newkey but fails if the destination key newkey already
	 * exists.
	 * <p>
	 * Time complexity: O(1)
	 * 
	 * @param oldkey
	 * @param newkey
	 * @return Integer reply, specifically: 1 if the key was renamed 0 if the
	 *         target key already exist
	 */
	@Override
	public Long renamenx(final String oldkey, final String newkey) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.renamenx(oldkey, newkey);
				return result;
			}
		}.run();

	}

	/**
	 * GETSET is an atomic set this value and return the old value command. Set
	 * key to the string value and return the old value stored at key. The
	 * string can't be longer than 1073741824 bytes (1 GB).
	 * <p>
	 * Time complexity: O(1)
	 * 
	 * @param key
	 * @param value
	 * @return Bulk reply
	 */@Override
	public String getSet(final String key, final String value) {

		return new JedisCommand<String>(jedisPool) {
			@Override
			public String execute(Jedis jedis) {
				String result = jedis.getSet(key, value);
				return result;
			}
		}.run();

	}

	/**
	 * Get the values of all the specified keys. If one or more keys dont exist
	 * or is not of type String, a 'nil' value is returned instead of the value
	 * of the specified key, but the operation never fails.
	 * <p>
	 * Time complexity: O(1) for every key
	 * 
	 * @param keys
	 * @return Multi bulk reply
	 */@Override
	public List<String> mget(final String... keys) {

		return new JedisCommand<List<String>>(jedisPool) {
			@Override
			public List<String> execute(Jedis jedis) {
				List<String> result = jedis.mget(keys);
				return result;
			}
		}.run();

	}

	/**
	 * SETNX works exactly like {@link #set(String, String) SET} with the only
	 * difference that if the key already exists no operation is performed.
	 * SETNX actually means "SET if Not eXists".
	 * <p>
	 * Time complexity: O(1)
	 * 
	 * @param key
	 * @param value
	 * @return Integer reply, specifically: 1 if the key was set 0 if the key
	 *         was not set
	 */@Override
	public Long setnx(final String key, final String value) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.setnx(key, value);
				return result;
			}
		}.run();

	}

	/**
	 * The command is exactly equivalent to the following group of commands:
	 * {@link #set(String, String) SET} + {@link #expire(String, int) EXPIRE}.
	 * The operation is atomic.
	 * <p>
	 * Time complexity: O(1)
	 * 
	 * @param key
	 * @param seconds
	 * @param value
	 * @return Status code reply
	 */@Override
	public String setex(final String key, final int seconds, final String value) {

		return new JedisCommand<String>(jedisPool) {
			@Override
			public String execute(Jedis jedis) {
				String result = jedis.setex(key, seconds, value);
				return result;
			}
		}.run();

	}

	/**
	 * Set the the respective keys to the respective values. MSET will replace
	 * old values with new values, while {@link #msetnx(String...) MSETNX} will
	 * not perform any operation at all even if just a single key already
	 * exists.
	 * <p>
	 * Because of this semantic MSETNX can be used in order to set different
	 * keys representing different fields of an unique logic object in a way
	 * that ensures that either all the fields or none at all are set.
	 * <p>
	 * Both MSET and MSETNX are atomic operations. This means that for instance
	 * if the keys A and B are modified, another client talking to Redis can
	 * either see the changes to both A and B at once, or no modification at
	 * all.
	 * 
	 * @see #msetnx(String...)
	 * @param keysvalues
	 * @return Status code reply Basically +OK as MSET can't fail
	 */@Override
	public String mset(final String... keysvalues) {

		return new JedisCommand<String>(jedisPool) {
			@Override
			public String execute(Jedis jedis) {
				String result = jedis.mset(keysvalues);
				return result;
			}
		}.run();

	}

	/**
	 * Set the the respective keys to the respective values.
	 * {@link #mset(String...) MSET} will replace old values with new values,
	 * while MSETNX will not perform any operation at all even if just a single
	 * key already exists.
	 * <p>
	 * Because of this semantic MSETNX can be used in order to set different
	 * keys representing different fields of an unique logic object in a way
	 * that ensures that either all the fields or none at all are set.
	 * <p>
	 * Both MSET and MSETNX are atomic operations. This means that for instance
	 * if the keys A and B are modified, another client talking to Redis can
	 * either see the changes to both A and B at once, or no modification at
	 * all.
	 * 
	 * @see #mset(String...)
	 * @param keysvalues
	 * @return Integer reply, specifically: 1 if the all the keys were set 0 if
	 *         no key was set (at least one key already existed)
	 */@Override
	public Long msetnx(final String... keysvalues) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.msetnx(keysvalues);
				return result;
			}
		}.run();

	}

	/**
	 * IDECRBY work just like {@link #decr(String) INCR} but instead to
	 * decrement by 1 the decrement is integer.
	 * <p>
	 * INCR commands are limited to 64 bit signed integers.
	 * <p>
	 * Note: this is actually a string operation, that is, in Redis there are
	 * not "integer" types. Simply the string stored at the key is parsed as a
	 * base 10 64 bit signed integer, incremented, and then converted back as a
	 * string.
	 * <p>
	 * Time complexity: O(1)
	 * 
	 * @see #incr(String)
	 * @see #decr(String)
	 * @see #incrBy(String, long)
	 * @param key
	 * @param integer
	 * @return Integer reply, this commands will reply with the new value of key
	 *         after the increment.
	 */@Override
	public Long decrBy(final String key, final long integer) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.decrBy(key, integer);
				return result;
			}
		}.run();

	}

	/**
	 * Decrement the number stored at key by one. If the key does not exist or
	 * contains a value of a wrong type, set the key to the value of "0" before
	 * to perform the decrement operation.
	 * <p>
	 * INCR commands are limited to 64 bit signed integers.
	 * <p>
	 * Note: this is actually a string operation, that is, in Redis there are
	 * not "integer" types. Simply the string stored at the key is parsed as a
	 * base 10 64 bit signed integer, incremented, and then converted back as a
	 * string.
	 * <p>
	 * Time complexity: O(1)
	 * 
	 * @see #incr(String)
	 * @see #incrBy(String, long)
	 * @see #decrBy(String, long)
	 * @param key
	 * @return Integer reply, this commands will reply with the new value of key
	 *         after the increment.
	 */@Override
	public Long decr(final String key) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.decr(key);
				return result;
			}
		}.run();

	}

	/**
	 * INCRBY work just like {@link #incr(String) INCR} but instead to increment
	 * by 1 the increment is integer.
	 * <p>
	 * INCR commands are limited to 64 bit signed integers.
	 * <p>
	 * Note: this is actually a string operation, that is, in Redis there are
	 * not "integer" types. Simply the string stored at the key is parsed as a
	 * base 10 64 bit signed integer, incremented, and then converted back as a
	 * string.
	 * <p>
	 * Time complexity: O(1)
	 * 
	 * @see #incr(String)
	 * @see #decr(String)
	 * @see #decrBy(String, long)
	 * @param key
	 * @param integer
	 * @return Integer reply, this commands will reply with the new value of key
	 *         after the increment.
	 */@Override
	public Long incrBy(final String key, final long integer) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.incrBy(key, integer);
				return result;
			}
		}.run();

	}

	/**
	 * INCRBYFLOAT
	 * <p>
	 * INCRBYFLOAT commands are limited to double precision floating point
	 * values.
	 * <p>
	 * Note: this is actually a string operation, that is, in Redis there are
	 * not "double" types. Simply the string stored at the key is parsed as a
	 * base double precision floating point value, incremented, and then
	 * converted back as a string. There is no DECRYBYFLOAT but providing a
	 * negative value will work as expected.
	 * <p>
	 * Time complexity: O(1)
	 * 
	 * @param key
	 * @param value
	 * @return Double reply, this commands will reply with the new value of key
	 *         after the increment.
	 */@Override
	public Double incrByFloat(final String key, final double value) {

		return new JedisCommand<Double>(jedisPool) {
			@Override
			public Double execute(Jedis jedis) {
				Double result = jedis.incrByFloat(key, value);
				return result;
			}
		}.run();
	}

	/**
	 * Increment the number stored at key by one. If the key does not exist or
	 * contains a value of a wrong type, set the key to the value of "0" before
	 * to perform the increment operation.
	 * <p>
	 * INCR commands are limited to 64 bit signed integers.
	 * <p>
	 * Note: this is actually a string operation, that is, in Redis there are
	 * not "integer" types. Simply the string stored at the key is parsed as a
	 * base 10 64 bit signed integer, incremented, and then converted back as a
	 * string.
	 * <p>
	 * Time complexity: O(1)
	 * 
	 * @see #incrBy(String, long)
	 * @see #decr(String)
	 * @see #decrBy(String, long)
	 * @param key
	 * @return Integer reply, this commands will reply with the new value of key
	 *         after the increment.
	 */@Override
	public Long incr(final String key) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.incr(key);
				return result;
			}
		}.run();

	}

	/**
	 * If the key already exists and is a string, this command appends the
	 * provided value at the end of the string. If the key does not exist it is
	 * created and set as an empty string, so APPEND will be very similar to SET
	 * in this special case.
	 * <p>
	 * Time complexity: O(1). The amortized time complexity is O(1) assuming the
	 * appended value is small and the already present value is of any size,
	 * since the dynamic string library used by Redis will double the free space
	 * available on every reallocation.
	 * 
	 * @param key
	 * @param value
	 * @return Integer reply, specifically the total length of the string after
	 *         the append operation.
	 */@Override
	public Long append(final String key, final String value) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.append(key, value);
				return result;
			}
		}.run();

	}

	/**
	 * Return a subset of the string from offset start to offset end (both
	 * offsets are inclusive). Negative offsets can be used in order to provide
	 * an offset starting from the end of the string. So -1 means the last char,
	 * -2 the penultimate and so forth.
	 * <p>
	 * The function handles out of range requests without raising an error, but
	 * just limiting the resulting range to the actual length of the string.
	 * <p>
	 * Time complexity: O(start+n) (with start being the start index and n the
	 * total length of the requested range). Note that the lookup part of this
	 * command is O(1) so for small strings this is actually an O(1) command.
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return Bulk reply
	 */@Override
	public String substr(final String key, final int start, final int end) {

		return new JedisCommand<String>(jedisPool) {
			@Override
			public String execute(Jedis jedis) {
				String result = jedis.substr(key, start, end);
				return result;
			}
		}.run();

	}

	/**
	 * Set the specified hash field to the specified value.
	 * <p>
	 * If key does not exist, a new key holding a hash is created.
	 * <p>
	 * <b>Time complexity:</b> O(1)
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return If the field already exists, and the HSET just produced an update
	 *         of the value, 0 is returned, otherwise if a new field is created
	 *         1 is returned.
	 */@Override
	public Long hset(final String key, final String field, final String value) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.hset(key, field, value);
				return result;
			}
		}.run();

	}

	/**
	 * If key holds a hash, retrieve the value associated to the specified
	 * field.
	 * <p>
	 * If the field is not found or the key does not exist, a special 'nil'
	 * value is returned.
	 * <p>
	 * <b>Time complexity:</b> O(1)
	 * 
	 * @param key
	 * @param field
	 * @return Bulk reply
	 */@Override
	public String hget(final String key, final String field) {

		return new JedisCommand<String>(jedisPool) {
			@Override
			public String execute(Jedis jedis) {
				String result = jedis.hget(key, field);
				return result;
			}
		}.run();

	}

	/**
	 * Set the specified hash field to the specified value if the field not
	 * exists. <b>Time complexity:</b> O(1)
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return If the field already exists, 0 is returned, otherwise if a new
	 *         field is created 1 is returned.
	 */@Override
	public Long hsetnx(final String key, final String field, final String value) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.hsetnx(key, field, value);
				return result;
			}
		}.run();

	}

	/**
	 * Set the respective fields to the respective values. HMSET replaces old
	 * values with new values.
	 * <p>
	 * If key does not exist, a new key holding a hash is created.
	 * <p>
	 * <b>Time complexity:</b> O(N) (with N being the number of fields)
	 * 
	 * @param key
	 * @param hash
	 * @return Return OK or Exception if hash is empty
	 */@Override
	public String hmset(final String key, final Map<String, String> hash) {

		return new JedisCommand<String>(jedisPool) {
			@Override
			public String execute(Jedis jedis) {
				String result = jedis.hmset(key, hash);
				return result;
			}
		}.run();

	}

	/**
	 * Retrieve the values associated to the specified fields.
	 * <p>
	 * If some of the specified fields do not exist, nil values are returned.
	 * Non existing keys are considered like empty hashes.
	 * <p>
	 * <b>Time complexity:</b> O(N) (with N being the number of fields)
	 * 
	 * @param key
	 * @param fields
	 * @return Multi Bulk Reply specifically a list of all the values associated
	 *         with the specified fields, in the same order of the request.
	 */@Override
	public List<String> hmget(final String key, final String... fields) {

		return new JedisCommand<List<String>>(jedisPool) {
			@Override
			public List<String> execute(Jedis jedis) {
				List<String> result = jedis.hmget(key, fields);
				return result;
			}
		}.run();

	}

	/**
	 * Increment the number stored at field in the hash at key by value. If key
	 * does not exist, a new key holding a hash is created. If field does not
	 * exist or holds a string, the value is set to 0 before applying the
	 * operation. Since the value argument is signed you can use this command to
	 * perform both increments and decrements.
	 * <p>
	 * The range of values supported by HINCRBY is limited to 64 bit signed
	 * integers.
	 * <p>
	 * <b>Time complexity:</b> O(1)
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return Integer reply The new value at field after the increment
	 *         operation.
	 */@Override
	public Long hincrBy(final String key, final String field, final long value) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.hincrBy(key, field, value);
				return result;
			}
		}.run();

	}

	/**
	 * Increment the number stored at field in the hash at key by a double
	 * precision floating point value. If key does not exist, a new key holding
	 * a hash is created. If field does not exist or holds a string, the value
	 * is set to 0 before applying the operation. Since the value argument is
	 * signed you can use this command to perform both increments and
	 * decrements.
	 * <p>
	 * The range of values supported by HINCRBYFLOAT is limited to double
	 * precision floating point values.
	 * <p>
	 * <b>Time complexity:</b> O(1)
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return Double precision floating point reply The new value at field
	 *         after the increment operation.
	 */@Override
	public Double hincrByFloat(final String key, final String field,
			final double value) {

		return new JedisCommand<Double>(jedisPool) {
			@Override
			public Double execute(Jedis jedis) {
				Double result = jedis.hincrByFloat(key, field, value);
				return result;
			}
		}.run();
	}

	/**
	 * Test for existence of a specified field in a hash. <b>Time
	 * complexity:</b> O(1)
	 * 
	 * @param key
	 * @param field
	 * @return Return 1 if the hash stored at key contains the specified field.
	 *         Return 0 if the key is not found or the field is not present.
	 */@Override
	public Boolean hexists(final String key, final String field) {

		return new JedisCommand<Boolean>(jedisPool) {
			@Override
			public Boolean execute(Jedis jedis) {
				Boolean result = jedis.hexists(key, field);
				return result;
			}
		}.run();

	}

	/**
	 * Remove the specified field from an hash stored at key.
	 * <p>
	 * <b>Time complexity:</b> O(1)
	 * 
	 * @param key
	 * @param fields
	 * @return If the field was present in the hash it is deleted and 1 is
	 *         returned, otherwise 0 is returned and no operation is performed.
	 */@Override
	public Long hdel(final String key, final String... fields) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.hdel(key, fields);
				return result;
			}
		}.run();

	}

	/**
	 * Return the number of items in a hash.
	 * <p>
	 * <b>Time complexity:</b> O(1)
	 * 
	 * @param key
	 * @return The number of entries (fields) contained in the hash stored at
	 *         key. If the specified key does not exist, 0 is returned assuming
	 *         an empty hash.
	 */@Override
	public Long hlen(final String key) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.hlen(key);
				return result;
			}
		}.run();

	}

	/**
	 * Return all the fields in a hash.
	 * <p>
	 * <b>Time complexity:</b> O(N), where N is the total number of entries
	 * 
	 * @param key
	 * @return All the fields names contained into a hash.
	 */@Override
	public Set<String> hkeys(final String key) {

		return new JedisCommand<Set<String>>(jedisPool) {
			@Override
			public Set<String> execute(Jedis jedis) {
				Set<String> result = jedis.hkeys(key);
				return result;
			}
		}.run();
	}

	/**
	 * Return all the values in a hash.
	 * <p>
	 * <b>Time complexity:</b> O(N), where N is the total number of entries
	 * 
	 * @param key
	 * @return All the fields values contained into a hash.
	 */@Override
	public List<String> hvals(final String key) {

		return new JedisCommand<List<String>>(jedisPool) {
			@Override
			public List<String> execute(Jedis jedis) {
				List<String> result = jedis.hvals(key);
				return result;
			}
		}.run();
	}

	/**
	 * Return all the fields and associated values in a hash.
	 * <p>
	 * <b>Time complexity:</b> O(N), where N is the total number of entries
	 * 
	 * @param key
	 * @return All the fields and values contained into a hash.
	 */
	@Override
	public Map<String, String> hgetall(final String key) {

		return new JedisCommand<Map<String, String>>(jedisPool) {
			@Override
			public Map<String, String> execute(Jedis jedis) {
				Map<String, String> result = jedis.hgetAll(key);
				return result;
			}
		}.run();
	}

	/**
	 * Add the string value to the head (LPUSH) or tail (RPUSH) of the list
	 * stored at key. If the key does not exist an empty list is created just
	 * before the append operation. If the key exists but is not a List an error
	 * is returned.
	 * <p>
	 * Time complexity: O(1)
	 * 
	 * @param key
	 * @param strings
	 * @return Integer reply, specifically, the number of elements inside the
	 *         list after the push operation.
	 */@Override
	public Long rpush(final String key, final String... strings) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.rpush(key, strings);
				return result;
			}
		}.run();

	}

	/**
	 * Add the string value to the head (LPUSH) or tail (RPUSH) of the list
	 * stored at key. If the key does not exist an empty list is created just
	 * before the append operation. If the key exists but is not a List an error
	 * is returned.
	 * <p>
	 * Time complexity: O(1)
	 * 
	 * @param key
	 * @param strings
	 * @return Integer reply, specifically, the number of elements inside the
	 *         list after the push operation.
	 */@Override
	public Long lpush(final String key, final String... strings) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.lpush(key, strings);
				return result;
			}
		}.run();

	}

	/**
	 * Return the length of the list stored at the specified key. If the key
	 * does not exist zero is returned (the same behaviour as for empty lists).
	 * If the value stored at key is not a list an error is returned.
	 * <p>
	 * Time complexity: O(1)
	 * 
	 * @param key
	 * @return The length of the list.
	 */@Override
	public Long llen(final String key) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.llen(key);
				return result;
			}
		}.run();

	}

	/**
	 * Return the specified elements of the list stored at the specified key.
	 * Start and end are zero-based indexes. 0 is the first element of the list
	 * (the list head), 1 the next element and so on.
	 * <p>
	 * For example LRANGE foobar 0 2 will return the first three elements of the
	 * list.
	 * <p>
	 * start and end can also be negative numbers indicating offsets from the
	 * end of the list. For example -1 is the last element of the list, -2 the
	 * penultimate element and so on.
	 * <p>
	 * <b>Consistency with range functions in various programming languages</b>
	 * <p>
	 * Note that if you have a list of numbers from 0 to 100, LRANGE 0 10 will
	 * return 11 elements, that is, rightmost item is included. This may or may
	 * not be consistent with behavior of range-related functions in your
	 * programming language of choice (think Ruby's Range.new, Array#slice or
	 * Python's range() function).
	 * <p>
	 * LRANGE behavior is consistent with one of Tcl.
	 * <p>
	 * <b>Out-of-range indexes</b>
	 * <p>
	 * Indexes out of range will not produce an error: if start is over the end
	 * of the list, or start &gt; end, an empty list is returned. If end is over
	 * the end of the list Redis will threat it just like the last element of
	 * the list.
	 * <p>
	 * Time complexity: O(start+n) (with n being the length of the range and
	 * start being the start offset)
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return Multi bulk reply, specifically a list of elements in the
	 *         specified range.
	 */@Override
	public List<String> lrange(final String key, final long start,
			final long end) {
		return new JedisCommand<List<String>>(jedisPool) {
			@Override
			public List<String> execute(Jedis jedis) {
				List<String> result = jedis.lrange(key, start, end);
				logger.debug("lrange key[{}] start[{}] end[{}] result[{}]", key, start, end, result);
				return result;
			}
		}.run();

	}

	/**
	 * Trim an existing list so that it will contain only the specified range of
	 * elements specified. Start and end are zero-based indexes. 0 is the first
	 * element of the list (the list head), 1 the next element and so on.
	 * <p>
	 * For example LTRIM foobar 0 2 will modify the list stored at foobar key so
	 * that only the first three elements of the list will remain.
	 * <p>
	 * start and end can also be negative numbers indicating offsets from the
	 * end of the list. For example -1 is the last element of the list, -2 the
	 * penultimate element and so on.
	 * <p>
	 * Indexes out of range will not produce an error: if start is over the end
	 * of the list, or start &gt; end, an empty list is left as value. If end
	 * over the end of the list Redis will threat it just like the last element
	 * of the list.
	 * <p>
	 * Hint: the obvious use of LTRIM is together with LPUSH/RPUSH. For example:
	 * <p>
	 * {@code lpush("mylist", "someelement"); ltrim("mylist", 0, 99); * }
	 * <p>
	 * The above two commands will push elements in the list taking care that
	 * the list will not grow without limits. This is very useful when using
	 * Redis to store logs for example. It is important to note that when used
	 * in this way LTRIM is an O(1) operation because in the average case just
	 * one element is removed from the tail of the list.
	 * <p>
	 * Time complexity: O(n) (with n being len of list - len of range)
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return Status code reply
	 */@Override
	public String ltrim(final String key, final long start, final long end) {

		return new JedisCommand<String>(jedisPool) {
			@Override
			public String execute(Jedis jedis) {
				String result = jedis.ltrim(key, start, end);
				logger.debug("ltrim key[{}] start[{}] end[{}] result[{}]", key, start, end, result);
				return result;
			}
		}.run();

	}

	/**
	 * Return the specified element of the list stored at the specified key. 0
	 * is the first element, 1 the second and so on. Negative indexes are
	 * supported, for example -1 is the last element, -2 the penultimate and so
	 * on.
	 * <p>
	 * If the value stored at key is not of list type an error is returned. If
	 * the index is out of range a 'nil' reply is returned.
	 * <p>
	 * Note that even if the average time complexity is O(n) asking for the
	 * first or the last element of the list is O(1).
	 * <p>
	 * Time complexity: O(n) (with n being the length of the list)
	 * 
	 * @param key
	 * @param index
	 * @return Bulk reply, specifically the requested element
	 */@Override
	public String lindex(final String key, final long index) {

		return new JedisCommand<String>(jedisPool) {
			@Override
			public String execute(Jedis jedis) {
				String result = jedis.lindex(key, index);
				return result;
			}
		}.run();

	}

	/**
	 * Set a new value as the element at index position of the List at key.
	 * <p>
	 * Out of range indexes will generate an error.
	 * <p>
	 * Similarly to other list commands accepting indexes, the index can be
	 * negative to access elements starting from the end of the list. So -1 is
	 * the last element, -2 is the penultimate, and so forth.
	 * <p>
	 * <b>Time complexity:</b>
	 * <p>
	 * O(N) (with N being the length of the list), setting the first or last
	 * elements of the list is O(1).
	 * 
	 * @see #lindex(String, long)
	 * @param key
	 * @param index
	 * @param value
	 * @return Status code reply
	 */@Override
	public String lset(final String key, final long index, final String value) {

		return new JedisCommand<String>(jedisPool) {
			@Override
			public String execute(Jedis jedis) {
				String result = jedis.lset(key, index, value);
				return result;
			}
		}.run();

	}

	/**
	 * Remove the first count occurrences of the value element from the list. If
	 * count is zero all the elements are removed. If count is negative elements
	 * are removed from tail to head, instead to go from head to tail that is
	 * the normal behaviour. So for example LREM with count -2 and hello as
	 * value to remove against the list (a,b,c,hello,x,hello,hello) will lave
	 * the list (a,b,c,hello,x). The number of removed elements is returned as
	 * an integer, see below for more information about the returned value. Note
	 * that non existing keys are considered like empty lists by LREM, so LREM
	 * against non existing keys will always return 0.
	 * <p>
	 * Time complexity: O(N) (with N being the length of the list)
	 * 
	 * @param key
	 * @param count
	 * @param value
	 * @return Integer Reply, specifically: The number of removed elements if
	 *         the operation succeeded
	 */@Override
	public Long lrem(final String key, final long count, final String value) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.lrem(key, count, value);
				return result;
			}
		}.run();

	}

	/**
	 * Atomically return and remove the first (LPOP) or last (RPOP) element of
	 * the list. For example if the list contains the elements "a","b","c" LPOP
	 * will return "a" and the list will become "b","c".
	 * <p>
	 * If the key does not exist or the list is already empty the special value
	 * 'nil' is returned.
	 * 
	 * @see #rpop(String)
	 * @param key
	 * @return Bulk reply
	 */@Override
	public String lpop(final String key) {

		return new JedisCommand<String>(jedisPool) {
			@Override
			public String execute(Jedis jedis) {
				String result = jedis.lpop(key);
				return result;
			}
		}.run();

	}

	/**
	 * Atomically return and remove the first (LPOP) or last (RPOP) element of
	 * the list. For example if the list contains the elements "a","b","c" RPOP
	 * will return "c" and the list will become "a","b".
	 * <p>
	 * If the key does not exist or the list is already empty the special value
	 * 'nil' is returned.
	 * 
	 * @see #lpop(String)
	 * @param key
	 * @return Bulk reply
	 */@Override
	public String rpop(final String key) {

		return new JedisCommand<String>(jedisPool) {
			@Override
			public String execute(Jedis jedis) {
				String result = jedis.rpop(key);
				return result;
			}
		}.run();

	}

	/**
	 * Atomically return and remove the last (tail) element of the srckey list,
	 * and push the element as the first (head) element of the dstkey list. For
	 * example if the source list contains the elements "a","b","c" and the
	 * destination list contains the elements "foo","bar" after an RPOPLPUSH
	 * command the content of the two lists will be "a","b" and "c","foo","bar".
	 * <p>
	 * If the key does not exist or the list is already empty the special value
	 * 'nil' is returned. If the srckey and dstkey are the same the operation is
	 * equivalent to removing the last element from the list and pusing it as
	 * first element of the list, so it's a "list rotation" command.
	 * <p>
	 * Time complexity: O(1)
	 * 
	 * @param srckey
	 * @param dstkey
	 * @return Bulk reply
	 */
	@Override
	public String rpoplpush(final String srckey, final String dstkey) {

		return new JedisCommand<String>(jedisPool) {
			@Override
			public String execute(Jedis jedis) {
				String result = jedis.rpoplpush(srckey, dstkey);
				return result;
			}
		}.run();

	}

	/**
	 * Add the specified member to the set value stored at key. If member is
	 * already a member of the set no operation is performed. If key does not
	 * exist a new set with the specified member as sole member is created. If
	 * the key exists but does not hold a set value an error is returned.
	 * <p>
	 * Time complexity O(1)
	 * 
	 * @param key
	 * @param members
	 * @return Integer reply, specifically: 1 if the new element was added 0 if
	 *         the element was already a member of the set
	 */@Override
	public Long sadd(final String key, final String... members) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.sadd(key, members);
				return result;
			}
		}.run();

	}

	/**
	 * Return all the members (elements) of the set value stored at key. This is
	 * just syntax glue for {@link #sinter(String...) SINTER}.
	 * <p>
	 * Time complexity O(N)
	 * 
	 * @param key
	 * @return Multi bulk reply
	 */@Override
	public Set<String> smembers(final String key) {

		return new JedisCommand<Set<String>>(jedisPool) {
			@Override
			public Set<String> execute(Jedis jedis) {
				Set<String> result = jedis.smembers(key);
				return result;
			}
		}.run();
	}

	/**
	 * Remove the specified member from the set value stored at key. If member
	 * was not a member of the set no operation is performed. If key does not
	 * hold a set value an error is returned.
	 * <p>
	 * Time complexity O(1)
	 * 
	 * @param key
	 * @param members
	 * @return Integer reply, specifically: 1 if the new element was removed 0
	 *         if the new element was not a member of the set
	 */@Override
	public Long srem(final String key, final String... members) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.srem(key, members);
				return result;
			}
		}.run();

	}

	/**
	 * Remove a random element from a Set returning it as return value. If the
	 * Set is empty or the key does not exist, a nil object is returned.
	 * <p>
	 * The {@link #srandmember(String)} command does a similar work but the
	 * returned element is not removed from the Set.
	 * <p>
	 * Time complexity O(1)
	 * 
	 * @param key
	 * @return Bulk reply
	 */@Override
	public String spop(final String key) {

		return new JedisCommand<String>(jedisPool) {
			@Override
			public String execute(Jedis jedis) {
				String result = jedis.spop(key);
				return result;
			}
		}.run();

	}
@Override
	public Set<String> spop(final String key, final long count) {

		return new JedisCommand<Set<String>>(jedisPool) {
			@Override
			public Set<String> execute(Jedis jedis) {
				Set<String> result = jedis.spop(key, count);
				return result;
			}
		}.run();
	}

	/**
	 * Move the specifided member from the set at srckey to the set at dstkey.
	 * This operation is atomic, in every given moment the element will appear
	 * to be in the source or destination set for accessing clients.
	 * <p>
	 * If the source set does not exist or does not contain the specified
	 * element no operation is performed and zero is returned, otherwise the
	 * element is removed from the source set and added to the destination set.
	 * On success one is returned, even if the element was already present in
	 * the destination set.
	 * <p>
	 * An error is raised if the source or destination keys contain a non Set
	 * value.
	 * <p>
	 * Time complexity O(1)
	 * 
	 * @param srckey
	 * @param dstkey
	 * @param member
	 * @return Integer reply, specifically: 1 if the element was moved 0 if the
	 *         element was not found on the first set and no operation was
	 *         performed
	 */@Override
	public Long smove(final String srckey, final String dstkey,
			final String member) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.smove(srckey, dstkey, member);
				return result;
			}
		}.run();

	}

	/**
	 * Return the set cardinality (number of elements). If the key does not
	 * exist 0 is returned, like for empty sets.
	 * 
	 * @param key
	 * @return Integer reply, specifically: the cardinality (number of elements)
	 *         of the set as an integer.
	 */@Override
	public Long scard(final String key) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.scard(key);
				return result;
			}
		}.run();

	}

	/**
	 * Return 1 if member is a member of the set stored at key, otherwise 0 is
	 * returned.
	 * <p>
	 * Time complexity O(1)
	 * 
	 * @param key
	 * @param member
	 * @return Integer reply, specifically: 1 if the element is a member of the
	 *         set 0 if the element is not a member of the set OR if the key
	 *         does not exist
	 */@Override
	public Boolean sismember(final String key, final String member) {

		return new JedisCommand<Boolean>(jedisPool) {
			@Override
			public Boolean execute(Jedis jedis) {
				Boolean result = jedis.sismember(key, member);
				return result;
			}
		}.run();

	}

	/**
	 * Return the members of a set resulting from the intersection of all the
	 * sets hold at the specified keys. Like in
	 * {@link #lrange(String, long, long) LRANGE} the result is sent to the
	 * client as a multi-bulk reply (see the protocol specification for more
	 * information). If just a single key is specified, then this command
	 * produces the same result as {@link #smembers(String) SMEMBERS}. Actually
	 * SMEMBERS is just syntax sugar for SINTER.
	 * <p>
	 * Non existing keys are considered like empty sets, so if one of the keys
	 * is missing an empty set is returned (since the intersection with an empty
	 * set always is an empty set).
	 * <p>
	 * Time complexity O(N*M) worst case where N is the cardinality of the
	 * smallest set and M the number of sets
	 * 
	 * @param keys
	 * @return Multi bulk reply, specifically the list of common elements.
	 */@Override
	public Set<String> sinter(final String... keys) {

		return new JedisCommand<Set<String>>(jedisPool) {
			@Override
			public Set<String> execute(Jedis jedis) {
				Set<String> result = jedis.sinter(keys);
				return result;
			}
		}.run();
	}

	/**
	 * This commnad works exactly like {@link #sinter(String...) SINTER} but
	 * instead of being returned the resulting set is sotred as dstkey.
	 * <p>
	 * Time complexity O(N*M) worst case where N is the cardinality of the
	 * smallest set and M the number of sets
	 * 
	 * @param dstkey
	 * @param keys
	 * @return Status code reply
	 */@Override
	public Long sinterstore(final String dstkey, final String... keys) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.sinterstore(dstkey, keys);
				return result;
			}
		}.run();

	}


	/**
	 * This command works exactly like {@link #sdiff(String...) SDIFF} but
	 * instead of being returned the resulting set is stored in dstkey.
	 * 
	 * @param dstkey
	 * @param keys
	 * @return Status code reply
	 */@Override
	public Long sdiffstore(final String dstkey, final String... keys) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.sdiffstore(dstkey, keys);
				return result;
			}
		}.run();

	}

	/**
	 * Return a random element from a Set, without removing the element. If the
	 * Set is empty or the key does not exist, a nil object is returned.
	 * <p>
	 * The SPOP command does a similar work but the returned element is popped
	 * (removed) from the Set.
	 * <p>
	 * Time complexity O(1)
	 * 
	 * @param key
	 * @return Bulk reply
	 */
	@Override
	public String srandmember(final String key) {

		return new JedisCommand<String>(jedisPool) {
			@Override
			public String execute(Jedis jedis) {
				String result = jedis.srandmember(key);
				return result;
			}
		}.run();

	}
	
	@Override
	public List<String> srandmember(final String key, final int count) {

		return new JedisCommand<List<String>>(jedisPool) {
			@Override
			public List<String> execute(Jedis jedis) {
				List<String> result = jedis.srandmember(key, count);
				return result;
			}
		}.run();

	}

	/**
	 * Add the specified member having the specifeid score to the sorted set
	 * stored at key. If member is already a member of the sorted set the score
	 * is updated, and the element reinserted in the right position to ensure
	 * sorting. If key does not exist a new sorted set with the specified member
	 * as sole member is crated. If the key exists but does not hold a sorted
	 * set value an error is returned.
	 * <p>
	 * The score value can be the string representation of a double precision
	 * floating point number.
	 * <p>
	 * Time complexity O(log(N)) with N being the number of elements in the
	 * sorted set
	 * 
	 * @param key
	 * @param score
	 * @param member
	 * @return Integer reply, specifically: 1 if the new element was added 0 if
	 *         the element was already a member of the sorted set and the score
	 *         was updated
	 */

	@Override
	public Long zadd(final String key, final Map<String, Double> scoreMembers) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.zadd(key, scoreMembers);
				return result;
			}
		}.run();

	}

	@Override
	public Set<String> zrange(final String key, final long start, final long end) {

		return new JedisCommand<Set<String>>(jedisPool) {
			@Override
			public Set<String> execute(Jedis jedis) {
				Set<String> result = jedis.zrange(key, start, end);
				return result;
			}
		}.run();
	}

	/**
	 * Remove the specified member from the sorted set value stored at key. If
	 * member was not a member of the set no operation is performed. If key does
	 * not not hold a set value an error is returned.
	 * <p>
	 * Time complexity O(log(N)) with N being the number of elements in the
	 * sorted set
	 * 
	 * @param key
	 * @param members
	 * @return Integer reply, specifically: 1 if the new element was removed 0
	 *         if the new element was not a member of the set
	 */@Override
	public Long zrem(final String key, final String... members) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.zrem(key, members);
				return result;
			}
		}.run();

	}

	/**
	 * If member already exists in the sorted set adds the increment to its
	 * score and updates the position of the element in the sorted set
	 * accordingly. If member does not already exist in the sorted set it is
	 * added with increment as score (that is, like if the previous score was
	 * virtually zero). If key does not exist a new sorted set with the
	 * specified member as sole member is crated. If the key exists but does not
	 * hold a sorted set value an error is returned.
	 * <p>
	 * The score value can be the string representation of a double precision
	 * floating point number. It's possible to provide a negative value to
	 * perform a decrement.
	 * <p>
	 * For an introduction to sorted sets check the Introduction to Redis data
	 * types page.
	 * <p>
	 * Time complexity O(log(N)) with N being the number of elements in the
	 * sorted set
	 * 
	 * @param key
	 * @param score
	 * @param member
	 * @return The new score
	 */@Override
	public Double zincrby(final String key, final double score,
			final String member) {

		return new JedisCommand<Double>(jedisPool) {
			@Override
			public Double execute(Jedis jedis) {
				Double result = jedis.zincrby(key, score, member);
				return result;
			}
		}.run();
	}

	/**
	 * Return the rank (or index) or member in the sorted set at key, with
	 * scores being ordered from low to high.
	 * <p>
	 * When the given member does not exist in the sorted set, the special value
	 * 'nil' is returned. The returned rank (or index) of the member is 0-based
	 * for both commands.
	 * <p>
	 * <b>Time complexity:</b>
	 * <p>
	 * O(log(N))
	 * 
	 * @see #zrevrank(String, String)
	 * @param key
	 * @param member
	 * @return Integer reply or a nil bulk reply, specifically: the rank of the
	 *         element as an integer reply if the element exists. A nil bulk
	 *         reply if there is no such element.
	 */@Override
	public Long zrank(final String key, final String member) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.zrank(key, member);
				return result;
			}
		}.run();

	}

	/**
	 * Return the rank (or index) or member in the sorted set at key, with
	 * scores being ordered from high to low.
	 * <p>
	 * When the given member does not exist in the sorted set, the special value
	 * 'nil' is returned. The returned rank (or index) of the member is 0-based
	 * for both commands.
	 * <p>
	 * <b>Time complexity:</b>
	 * <p>
	 * O(log(N))
	 * 
	 * @see #zrank(String, String)
	 * @param key
	 * @param member
	 * @return Integer reply or a nil bulk reply, specifically: the rank of the
	 *         element as an integer reply if the element exists. A nil bulk
	 *         reply if there is no such element.
	 */@Override
	public Long zrevrank(final String key, final String member) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.zrevrank(key, member);
				return result;
			}
		}.run();

	}
@Override
	public Set<String> zrevrange(final String key, final long start,
			final long end) {

		return new JedisCommand<Set<String>>(jedisPool) {
			@Override
			public Set<String> execute(Jedis jedis) {
				Set<String> result = jedis.zrevrange(key, start, end);
				return result;
			}
		}.run();
	}
@Override
	public Set<Tuple> zrangeWithScores(final String key, final long start,
			final long end) {

		return new JedisCommand<Set<Tuple>>(jedisPool) {
			@Override
			public Set<Tuple> execute(Jedis jedis) {
				Set<Tuple> result = jedis.zrangeWithScores(key, start, end);
				return result;
			}
		}.run();
	}
@Override
	public Set<Tuple> zrevrangeWithScores(final String key, final long start,
			final long end) {

		return new JedisCommand<Set<Tuple>>(jedisPool) {
			@Override
			public Set<Tuple> execute(Jedis jedis) {
				Set<Tuple> result = jedis.zrevrangeWithScores(key, start, end);
				return result;
			}
		}.run();
	}

	/**
	 * Return the sorted set cardinality (number of elements). If the key does
	 * not exist 0 is returned, like for empty sorted sets.
	 * <p>
	 * Time complexity O(1)
	 * 
	 * @param key
	 * @return the cardinality (number of elements) of the set as an integer.
	 */@Override
	public Long zcard(final String key) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.zcard(key);
				return result;
			}
		}.run();

	}

	/**
	 * Return the score of the specified element of the sorted set at key. If
	 * the specified element does not exist in the sorted set, or the key does
	 * not exist at all, a special 'nil' value is returned.
	 * <p>
	 * <b>Time complexity:</b> O(1)
	 * 
	 * @param key
	 * @param member
	 * @return the score
	 */@Override
	public Double zscore(final String key, final String member) {

		return new JedisCommand<Double>(jedisPool) {
			@Override
			public Double execute(Jedis jedis) {
				Double result = jedis.zscore(key, member);
				return result;
			}
		}.run();
	}

	/**
	 * Sort a Set or a List.
	 * <p>
	 * Sort the elements contained in the List, Set, or Sorted Set value at key.
	 * By default sorting is numeric with elements being compared as double
	 * precision floating point numbers. This is the simplest form of SORT.
	 * 
	 * @see #sort(String, String)
	 * @see #sort(String, SortingParams)
	 * @see #sort(String, SortingParams, String)
	 * @param key
	 * @return Assuming the Set/List at key contains a list of numbers, the
	 *         return value will be the list of numbers ordered from the
	 *         smallest to the biggest number.
	 */@Override
	public List<String> sort(final String key) {

		return new JedisCommand<List<String>>(jedisPool) {
			@Override
			public List<String> execute(Jedis jedis) {
				List<String> result = jedis.sort(key);
				return result;
			}
		}.run();

	}

	/**
	 * Sort a Set or a List accordingly to the specified parameters and store
	 * the result at dstkey.
	 * 
	 * @see #sort(String, SortingParams)
	 * @see #sort(String)
	 * @see #sort(String, String)
	 * @param key
	 * @param sortingParameters
	 * @param dstkey
	 * @return The number of elements of the list at dstkey.
	 */
	@Override
	public Long sort(final String key, final SortingParams sortingParameters,
			final String dstkey) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.sort(key, sortingParameters, dstkey);
				return result;
			}
		}.run();

	}

	/**
	 * Sort a Set or a List and Store the Result at dstkey.
	 * <p>
	 * Sort the elements contained in the List, Set, or Sorted Set value at key
	 * and store the result at dstkey. By default sorting is numeric with
	 * elements being compared as double precision floating point numbers. This
	 * is the simplest form of SORT.
	 * 
	 * @see #sort(String)
	 * @see #sort(String, SortingParams)
	 * @see #sort(String, SortingParams, String)
	 * @param key
	 * @param dstkey
	 * @return The number of elements of the list at dstkey.
	 */@Override
	public Long sort(final String key, final String dstkey) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.sort(key, dstkey);
				return result;
			}
		}.run();

	}

	@Override
	public Long zcount(final String key, final double min, final double max) {

		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.zcount(key, min, max);
				return result;
			}
		}.run();

	}

	/**
	 * Return the all the elements in the sorted set at key with a score between
	 * min and max (including elements with score equal to min or max).
	 * <p>
	 * The elements having the same score are returned sorted lexicographically
	 * as ASCII strings (this follows from a property of Redis sorted sets and
	 * does not involve further computation).
	 * <p>
	 * Using the optional
	 * {@link #zrangeByScore(String, double, double, int, int) LIMIT} it's
	 * possible to get only a range of the matching elements in an SQL-alike
	 * way. Note that if offset is large the commands needs to traverse the list
	 * for offset elements and this adds up to the O(M) figure.
	 * <p>
	 * The {@link #zcount(String, double, double) ZCOUNT} command is similar to
	 * {@link #zrangeByScore(String, double, double) ZRANGEBYSCORE} but instead
	 * of returning the actual elements in the specified interval, it just
	 * returns the number of matching elements.
	 * <p>
	 * <b>Exclusive intervals and infinity</b>
	 * <p>
	 * min and max can be -inf and +inf, so that you are not required to know
	 * what's the greatest or smallest element in order to take, for instance,
	 * elements "up to a given value".
	 * <p>
	 * Also while the interval is for default closed (inclusive) it's possible
	 * to specify open intervals prefixing the score with a "(" character, so
	 * for instance:
	 * <p>
	 * {@code ZRANGEBYSCORE zset (1.3 5}
	 * <p>
	 * Will return all the values with score &gt; 1.3 and &lt;= 5, while for
	 * instance:
	 * <p>
	 * {@code ZRANGEBYSCORE zset (5 (10}
	 * <p>
	 * Will return all the values with score &gt; 5 and &lt; 10 (5 and 10
	 * excluded).
	 * <p>
	 * <b>Time complexity:</b>
	 * <p>
	 * O(log(N))+O(M) with N being the number of elements in the sorted set and
	 * M the number of elements returned by the command, so if M is constant
	 * (for instance you always ask for the first ten elements with LIMIT) you
	 * can consider it O(log(N))
	 * 
	 * @see #zrangeByScore(String, double, double)
	 * @see #zrangeByScore(String, double, double, int, int)
	 * @see #zrangeByScoreWithScores(String, double, double)
	 * @see #zrangeByScoreWithScores(String, String, String)
	 * @see #zrangeByScoreWithScores(String, double, double, int, int)
	 * @see #zcount(String, double, double)
	 * @param key
	 * @param min
	 *            a double or Double.MIN_VALUE for "-inf"
	 * @param max
	 *            a double or Double.MAX_VALUE for "+inf"
	 * @return Multi bulk reply specifically a list of elements in the specified
	 *         score range.
	 */@Override
	public Set<String> zrangeByScore(final String key, final double min,
			final double max) {

		return new JedisCommand<Set<String>>(jedisPool) {
			@Override
			public Set<String> execute(Jedis jedis) {
				Set<String> result = jedis.zrangeByScore(key, min, max);
				return result;
			}
		}.run();
	}

	/**
	 * Return the all the elements in the sorted set at key with a score between
	 * min and max (including elements with score equal to min or max).
	 * <p>
	 * The elements having the same score are returned sorted lexicographically
	 * as ASCII strings (this follows from a property of Redis sorted sets and
	 * does not involve further computation).
	 * <p>
	 * Using the optional
	 * {@link #zrangeByScore(String, double, double, int, int) LIMIT} it's
	 * possible to get only a range of the matching elements in an SQL-alike
	 * way. Note that if offset is large the commands needs to traverse the list
	 * for offset elements and this adds up to the O(M) figure.
	 * <p>
	 * The {@link #zcount(String, double, double) ZCOUNT} command is similar to
	 * {@link #zrangeByScore(String, double, double) ZRANGEBYSCORE} but instead
	 * of returning the actual elements in the specified interval, it just
	 * returns the number of matching elements.
	 * <p>
	 * <b>Exclusive intervals and infinity</b>
	 * <p>
	 * min and max can be -inf and +inf, so that you are not required to know
	 * what's the greatest or smallest element in order to take, for instance,
	 * elements "up to a given value".
	 * <p>
	 * Also while the interval is for default closed (inclusive) it's possible
	 * to specify open intervals prefixing the score with a "(" character, so
	 * for instance:
	 * <p>
	 * {@code ZRANGEBYSCORE zset (1.3 5}
	 * <p>
	 * Will return all the values with score &gt; 1.3 and &lt;= 5, while for
	 * instance:
	 * <p>
	 * {@code ZRANGEBYSCORE zset (5 (10}
	 * <p>
	 * Will return all the values with score &gt; 5 and &lt; 10 (5 and 10
	 * excluded).
	 * <p>
	 * <b>Time complexity:</b>
	 * <p>
	 * O(log(N))+O(M) with N being the number of elements in the sorted set and
	 * M the number of elements returned by the command, so if M is constant
	 * (for instance you always ask for the first ten elements with LIMIT) you
	 * can consider it O(log(N))
	 * 
	 * @see #zrangeByScore(String, double, double)
	 * @see #zrangeByScore(String, double, double, int, int)
	 * @see #zrangeByScoreWithScores(String, double, double)
	 * @see #zrangeByScoreWithScores(String, double, double, int, int)
	 * @see #zcount(String, double, double)
	 * @param key
	 * @param min
	 * @param max
	 * @return Multi bulk reply specifically a list of elements in the specified
	 *         score range.
	 */@Override
	public Set<String> zrangeByScore(final String key, final double min,
			final double max, final int offset, final int count) {
		return new JedisCommand<Set<String>>(jedisPool) {
			@Override
			public Set<String> execute(Jedis jedis) {
				Set<String> result = jedis.zrangeByScore(key, min, max, offset,
						count);
				return result;
			}
		}.run();
	}
@Override
	public Set<String> zrangeByScore(final String key, final String min,
			final String max, final int offset, final int count) {
		return new JedisCommand<Set<String>>(jedisPool) {
			@Override
			public Set<String> execute(Jedis jedis) {
				Set<String> result = jedis.zrangeByScore(key, min, max, offset,
						count);
				return result;
			}
		}.run();
	}

	/**
	 * Return the all the elements in the sorted set at key with a score between
	 * min and max (including elements with score equal to min or max).
	 * <p>
	 * The elements having the same score are returned sorted lexicographically
	 * as ASCII strings (this follows from a property of Redis sorted sets and
	 * does not involve further computation).
	 * <p>
	 * Using the optional
	 * {@link #zrangeByScore(String, double, double, int, int) LIMIT} it's
	 * possible to get only a range of the matching elements in an SQL-alike
	 * way. Note that if offset is large the commands needs to traverse the list
	 * for offset elements and this adds up to the O(M) figure.
	 * <p>
	 * The {@link #zcount(String, double, double) ZCOUNT} command is similar to
	 * {@link #zrangeByScore(String, double, double) ZRANGEBYSCORE} but instead
	 * of returning the actual elements in the specified interval, it just
	 * returns the number of matching elements.
	 * <p>
	 * <b>Exclusive intervals and infinity</b>
	 * <p>
	 * min and max can be -inf and +inf, so that you are not required to know
	 * what's the greatest or smallest element in order to take, for instance,
	 * elements "up to a given value".
	 * <p>
	 * Also while the interval is for default closed (inclusive) it's possible
	 * to specify open intervals prefixing the score with a "(" character, so
	 * for instance:
	 * <p>
	 * {@code ZRANGEBYSCORE zset (1.3 5}
	 * <p>
	 * Will return all the values with score &gt; 1.3 and &lt;= 5, while for
	 * instance:
	 * <p>
	 * {@code ZRANGEBYSCORE zset (5 (10}
	 * <p>
	 * Will return all the values with score &gt; 5 and &lt; 10 (5 and 10
	 * excluded).
	 * <p>
	 * <b>Time complexity:</b>
	 * <p>
	 * O(log(N))+O(M) with N being the number of elements in the sorted set and
	 * M the number of elements returned by the command, so if M is constant
	 * (for instance you always ask for the first ten elements with LIMIT) you
	 * can consider it O(log(N))
	 * 
	 * @see #zrangeByScore(String, double, double)
	 * @see #zrangeByScore(String, double, double, int, int)
	 * @see #zrangeByScoreWithScores(String, double, double)
	 * @see #zrangeByScoreWithScores(String, double, double, int, int)
	 * @see #zcount(String, double, double)
	 * @param key
	 * @param min
	 * @param max
	 * @return Multi bulk reply specifically a list of elements in the specified
	 *         score range.
	 */@Override
	public Set<Tuple> zrangeByScoreWithScores(final String key,
			final double min, final double max, final int offset,
			final int count) {
		return new JedisCommand<Set<Tuple>>(jedisPool) {
			@Override
			public Set<Tuple> execute(Jedis jedis) {
				Set<Tuple> result = jedis.zrangeByScoreWithScores(key, min,
						max, offset, count);
				return result;
			}
		}.run();
	}
	 
	@Override
	public Set<String> zrevrangeByScore(final String key, final double max,
			final double min) {

		return new JedisCommand<Set<String>>(jedisPool) {
			@Override
			public Set<String> execute(Jedis jedis) {
				Set<String> result = jedis.zrevrangeByScore(key, max, min);
				return result;
			}
		}.run();
	}
	
	@Override
	public Set<String> zrevrangeByScore(final String key, final String max,
			final String min) {

		return new JedisCommand<Set<String>>(jedisPool) {
			@Override
			public Set<String> execute(Jedis jedis) {
				Set<String> result = jedis.zrevrangeByScore(key, max, min);
				return result;
			}
		}.run();
	}
	
	@Override
	public Set<String> zrevrangeByScore(final String key, final double max,
			final double min, final int offset, final int count) {

		return new JedisCommand<Set<String>>(jedisPool) {
			@Override
			public Set<String> execute(Jedis jedis) {
				Set<String> result = jedis.zrevrangeByScore(key, max, min,
						offset, count);
				return result;
			}
		}.run();
	}
	
	@Override
	public Set<String> zrevrangeByScore(final String key, final String max,
			final String min, final int offset, final int count) {
		return new JedisCommand<Set<String>>(jedisPool) {
			@Override
			public Set<String> execute(Jedis jedis) {
				Set<String> result = jedis.zrevrangeByScore(key, max, min,
						offset, count);
				return result;
			}
		}.run();
	}
	
	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(final String key,
			final double max, double min, int offset, int count) {
		return new JedisCommand<Set<Tuple>>(jedisPool) {
			@Override
			public Set<Tuple> execute(Jedis jedis) {
				Set<Tuple> result = jedis.zrevrangeByScoreWithScores(key, max,
						min, offset, count);
				return result;
			}
		}.run();
	}

	/**
	 * Remove all elements in the sorted set at key with rank between start and
	 * end. Start and end are 0-based with rank 0 being the element with the
	 * lowest score. Both start and end can be negative numbers, where they
	 * indicate offsets starting at the element with the highest rank. For
	 * example: -1 is the element with the highest score, -2 the element with
	 * the second highest score and so forth.
	 * <p>
	 * <b>Time complexity:</b> O(log(N))+O(M) with N being the number of
	 * elements in the sorted set and M the number of elements removed by the
	 * operation
	 */@Override
	public Long zremrangeByRank(final String key, final long start,
			final long end) {
		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.zremrangeByRank(key, start, end);
				return result;
			}
		}.run();

	}

	/**
	 * Remove all the elements in the sorted set at key with a score between min
	 * and max (including elements with score equal to min or max).
	 * <p>
	 * <b>Time complexity:</b>
	 * <p>
	 * O(log(N))+O(M) with N being the number of elements in the sorted set and
	 * M the number of elements removed by the operation
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return Integer reply, specifically the number of elements removed.
	 */
	@Override
	public Long zremrangeByScore(final String key, final double start,
			final double end) {
		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.zremrangeByScore(key, start, end);
				return result;
			}
		}.run();

	}
	 
	@Override
	public Long zremrangeByScore(final String key, final String start,
			final String end) {
		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.zremrangeByScore(key, start, end);
				return result;
			}
		}.run();

	}

	@Override
	public Long zlexcount(final String key, final String min, final String max) {
		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.zlexcount(key, min, max);
				return result;
			}
		}.run();

	}

	@Override
	public Set<String> zrangeByLex(final String key, final String min,
			final String max) {
		return new JedisCommand<Set<String>>(jedisPool) {
			@Override
			public Set<String> execute(Jedis jedis) {
				Set<String> result = jedis.zrangeByLex(key, min, max);
				return result;
			}
		}.run();
	}

	@Override
	public Set<String> zrangeByLex(final String key, final String min,
			final String max, final int offset, final int count) {

		return new JedisCommand<Set<String>>(jedisPool) {
			@Override
			public Set<String> execute(Jedis jedis) {
				Set<String> result = jedis.zrangeByLex(key, min, max, offset,
						count);
				return result;
			}
		}.run();
	}

	@Override
	public Set<String> zrevrangeByLex(String key, String max, String min) {
		return new JedisCommand<Set<String>>(jedisPool) {
			@Override
			public Set<String> execute(Jedis jedis) {
				Set<String> result = jedis.zrevrangeByLex(key, max, min);
				return result;
			}
		}.run();

	}

	@Override
	public Set<String> zrevrangeByLex(String key, String max, String min,
			int offset, int count) {

		return new JedisCommand<Set<String>>(jedisPool) {
			@Override
			public Set<String> execute(Jedis jedis) {
				Set<String> result = jedis.zrevrangeByLex(key, max, min,
						offset, count);
				return result;
			}
		}.run();
	}

	@Override
	public Long zremrangeByLex(final String key, final String min,
			final String max) {
		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.zremrangeByLex(key, min, max);
				return result;
			}
		}.run();

	}

	@Override
	public Long strlen(final String key) {
		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.strlen(key);
				return result;
			}
		}.run();

	}

	@Override
	public Long lpushx(final String key, final String... string) {
		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.lpushx(key, string);
				return result;
			}
		}.run();

	}

	@Override
	public Long rpushx(final String key, final String... string) {
		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.rpushx(key, string);
				return result;
			}
		}.run();

	}

	@Override
	public String echo(final String string) {
		return new JedisCommand<String>(jedisPool) {
			@Override
			public String execute(Jedis jedis) {
				String result = jedis.echo(string);
				return result;
			}
		}.run();

	}

	@Override
	public Long linsert(final String key, final LIST_POSITION where,
			final String pivot, final String value) {
		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.linsert(key, where, pivot, value);
				return result;
			}
		}.run();
	}

	@Override
	public Long setrange(String key, long offset, String value) {
		return new JedisCommand<Long>(jedisPool) {
			@Override
			public Long execute(Jedis jedis) {
				Long result = jedis.setrange(key, offset, value);
				return result;
			}
		}.run();

	}

	@Override
	public String getrange(String key, long startOffset, long endOffset) {
		return new JedisCommand<String>(jedisPool) {
			@Override
			public String execute(Jedis jedis) {
				String result = jedis.getrange(key, startOffset, endOffset);
				return result;
			}
		}.run();

	}

	/**
	 * PSETEX works exactly like {@link #setex(String, int, String)} with the
	 * sole difference that the expire time is specified in milliseconds instead
	 * of seconds. Time complexity: O(1)
	 * 
	 * @param key
	 * @param milliseconds
	 * @param value
	 * @return Status code reply
	 */
	@Override
	public String psetex(final String key, final long milliseconds,
			final String value) {
		return new JedisCommand<String>(jedisPool) {
			@Override
			public String execute(Jedis jedis) {
				String result = jedis.psetex(key, milliseconds, value);
				return result;
			}
		}.run();

	}

}
