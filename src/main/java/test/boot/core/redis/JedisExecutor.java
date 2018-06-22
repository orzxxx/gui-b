package test.boot.core.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;

public interface JedisExecutor {

	String set(String key, String value);

	String get(String key);

	Boolean exists(String key);

	//Long persist(String key);

	String type(String key);
	
	/*Long expire(String key, int seconds);

	Long pexpire(String key, long milliseconds);
	
	Long expireAt(String key, long unixTime);
	
	Long pexpireAt(String key, long millisecondsTimestamp);
	
	Long ttl(String key);

	Long pttl(String key);*/

	/*Boolean setbit(String key, long offset, boolean value);
	
	Boolean setbit(String key, long offset, String value);
	
	Boolean getbit(String key, long offset);*/

	Long setrange(String key, long offset, String value);
		
	String getrange(String key, long startOffset, long endOffset);
	
	String getSet(String key, String value);
	
	Long setnx(String key, String value);
	
	String setex(String key, int seconds, String value);

	String psetex(String key, long milliseconds, String value);

	Long decrBy(String key, long integer);

	Long decr(String key);

	Long incrBy(String key, long integer);

	Double incrByFloat(String key, double value);
	
	Long incr(String key);
	
	Long append(String key, String value);

	String substr(String key, int start, int end);

	Long hset(String key, String field, String value);
		
	String hget(String key, String field);
	
	Long hsetnx(String key, String field, String value);

	String hmset(String key, Map<String, String> hash);
	
	List<String> hmget(String key, String... fields);

	Long hincrBy(String key, String field, long value);

	Double hincrByFloat(String key, String field, double value);
	
	Boolean hexists(String key, String field);

	Long hdel(String key, String... field);

	Long hlen(String key);

	Set<String> hkeys(String key);
	
	List<String> hvals(String key);
	
	Map<String, String> hgetall(String key);
	
	Long rpush(String key, String... string);

	Long lpush(String key, String... string);

	Long llen(String key);

	List<String> lrange(String key, long start, long end);
		
	String ltrim(String key, long start, long end);
		
	String lindex(String key, long index);

	String lset(String key, long index, String value);
	
	Long lrem(String key, long count, String value);

	String lpop(String key);

	String rpop(String key);

	Long sadd(String key, String... member);

	Set<String> smembers(String key);

	Long srem(String key, String... member);
			
	String spop(String key);
	
	Set<String> spop(String key, long count);

	Long scard(String key);
	
	Boolean sismember(String key, String member);
	
	String srandmember(String key);
	
	List<String> srandmember(String key, int count);

	Long strlen(String key);

	Long zadd(String key, Map<String, Double> scoreMembers);

	Set<String> zrange(String key, long start, long end);

	Long zrem(String key, String... member);
		
	Double zincrby(String key, double score, String member);

	Long zrank(String key, String member);

	Long zrevrank(String key, String member);

	Set<String> zrevrange(String key, long start, long end);

	Set<Tuple> zrangeWithScores(String key, long start, long end);

	Set<Tuple> zrevrangeWithScores(String key, long start, long end);

	Long zcard(String key);

	Double zscore(String key, String member);

	List<String> sort(String key);

	Long zcount(String key, double min, double max);

	Set<String> zrangeByScore(String key, double min, double max);

	Set<String> zrevrangeByScore(String key, double max, double min);

	Set<String> zrangeByScore(String key, double min, double max, int offset, int count);
	
	Set<String> zrevrangeByScore(String key, String max, String min);

	Set<String> zrangeByScore(String key, String min, String max, int offset, int count);

	Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count);

	Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count);

	Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count);

	Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count);

	Long zremrangeByRank(String key, long start, long end);
	
	Long zremrangeByScore(String key, double start, double end);

	Long zremrangeByScore(String key, String start, String end);
		
	Long zlexcount(String key, String min, String max);

	Set<String> zrangeByLex(String key, String min, String max);

	Set<String> zrangeByLex(String key, String min, String max, int offset, int count);

	Set<String> zrevrangeByLex(String key, String max, String min);

	Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count);

	Long zremrangeByLex(String key, String min, String max);

	Long linsert(String key, LIST_POSITION where, String pivot, String value);
	
	Long lpushx(String key, String... string);
	
	Long rpushx(String key, String... string);
	
	String echo(String string);
	
	/*Long bitcount(String key);
	
	Long bitcount(String key, long start, long end);
	
	ScanResult<String> scan(String cursor, ScanParams params);
	
	Long bitpos(String key, boolean value);
	
	Long bitpos(String key, boolean value, BitPosParams params);*/
	
	/*ScanResult<Entry<String, String>> hscan(String key, String cursor);
	
	ScanResult<Entry<String, String>> hscan(String key, String cursor, ScanParams params);
	
	ScanResult<String> sscan(String key, String cursor);
	
	ScanResult<String> sscan(String key, String cursor, ScanParams params);
	
	ScanResult<Tuple> zscan(String key, String cursor);

	ScanResult<Tuple> zscan(String key, String cursor, ScanParams params);*/
	
	/*Long pfadd(String key, String... elements);

	long pfcount(String key);
	
	List<String> blpop(int timeout, String key);
	
	List<String> brpop(int timeout, String key);*/
	
	Long del(String... keys);

	/*List<String> blpop(int timeout, String... keys);
	
	List<String> brpop(int timeout, String... keys);*/

	List<String> mget(String... keys);
	
	String mset(String... keysvalues);

	Long msetnx(String... keysvalues);
		
	String rename(String oldkey, String newkey);

	Long renamenx(String oldkey, String newkey);

	String rpoplpush(String srckey, String dstkey);

	/*	Set<String> sdiff(String... keys);*/
	
	Long sdiffstore(String dstkey, String... keys);
		
	Set<String> sinter(String... keys);

	Long sinterstore(String dstkey, String... keys);
		
	Long smove(String srckey, String dstkey, String member);

	Long sort(String key, SortingParams sortingParameters, String dstkey);

	Long sort(String key, String dstkey);

	/*Set<String> sunion(String... keys);

	Long sunionstore(String dstkey, String... keys);
		
	Long zinterstore(String dstkey, String... sets);
		
	Long zunionstore(String dstkey, String... sets);
		
	String brpoplpush(String source, String destination, int timeout);

	Long publish(String channel, String message);*/

	/*void subscribe(JedisPubSub jedisPubSub, String... channels);
	
	void psubscribe(JedisPubSub jedisPubSub, String... patterns);

	Long bitop(BitOP op, String destKey, String... srcKeys);
	
	String pfmerge(String destkey, String... sourcekeys);
		
	long pfcount(String... keys);

	Object eval(String script, int keyCount, String... params);

	Object eval(String script, String key);

	Object eval(String script, List<String> keys, List<String> args);

	Object evalsha(String sha1, int keyCount, String... params);

	Object evalsha(String sha1, List<String> keys, List<String> args);

	Object evalsha(String script, String key);*/

	/*Boolean scriptExists(String sha1, String key);

	List<Boolean> scriptExists(String key, String... sha1);

	String scriptLoad(String script, String key);

	Long geoadd(String key, double longitude, double latitude, String member);

	Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap);

	Double geodist(String key, String member1, String member2);
	
	Double geodist(String key, String member1, String member2, GeoUnit unit);

	List<String> geohash(String key, String... members);

	List<GeoCoordinate> geopos(String key, String... members);

	List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius,
			GeoUnit unit);

	List<GeoRadiusResponse> georadiusByMember(String key,
			String member, double radius, GeoUnit unit);

	List<Long> bitfield(String key, String... arguments);*/

}

