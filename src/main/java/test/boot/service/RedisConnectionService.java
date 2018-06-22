package test.boot.service;

import java.util.List;

import test.boot.dto.RedisConnectionDTO;

public interface RedisConnectionService {

	List<RedisConnectionDTO> listRedisConnection();

	RedisConnectionDTO saveRedisConnection(RedisConnectionDTO dto);
	
	RedisConnectionDTO updateRedisConnection(RedisConnectionDTO dto);

	void removeRedisConnection(int id);

	RedisConnectionDTO getRedisConnection(int id);

	boolean testRedisCollection(int id);

}
