package test.boot.service;

import java.util.List;
import java.util.Map;

public interface RedisCommantService {

	List<String> getRedisCommantResult(int connId, String commant) throws Exception;

	Map<String, String> getSupportedRedisCommant();

}
