package test.boot.service;

import java.util.List;

import test.boot.dto.RedisCollectionDTO;

public interface RedisCollectionService {

	List<RedisCollectionDTO> listRedisCollection();

	RedisCollectionDTO saveRedisCollection(RedisCollectionDTO record);

	RedisCollectionDTO updateRedisCollection(RedisCollectionDTO record);

	void removeRedisCollection(int id);

	RedisCollectionDTO getRedisCollection(int id);

}
