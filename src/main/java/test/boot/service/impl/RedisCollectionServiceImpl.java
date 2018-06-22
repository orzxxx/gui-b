package test.boot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import test.boot.common.exception.BusinessException;
import test.boot.dao.RedisCollectionDao;
import test.boot.dto.RedisCollectionDTO;
import test.boot.entity.tables.records.RedisCollectionRecord;
import test.boot.mapper.RedisCollectionMapper;
import test.boot.service.RedisCollectionService;

@Service
@Transactional
public class RedisCollectionServiceImpl implements RedisCollectionService {
	
	@Autowired
	private RedisCollectionMapper redisCollectionMapper;
	
	@Autowired
	private RedisCollectionDao redisCollectionDao;
	
	@Override
	public List<RedisCollectionDTO> listRedisCollection() {
		return redisCollectionMapper.toDTO(redisCollectionDao.list());
	}

	@Override
	public RedisCollectionDTO saveRedisCollection(RedisCollectionDTO dto) {
		RedisCollectionRecord record = redisCollectionMapper.toEntity(dto);
		if (redisCollectionDao.countByCommand(record.getId(), record.getCommand()) > 0) {
			throw new BusinessException("Repetitive command");
		}
		
		int id = redisCollectionDao.save(record);
		dto.setId(id);
		return dto;
	}

	@Override
	public RedisCollectionDTO updateRedisCollection(RedisCollectionDTO dto) {
		RedisCollectionRecord record = redisCollectionMapper.toEntity(dto);
		if (redisCollectionDao.countByCommand(record.getId(), record.getCommand()) > 0) {
			throw new BusinessException("Repetitive command");
		}
		
		redisCollectionDao.update(record);
		return dto;
	}

	@Override
	public void removeRedisCollection(int id) {
		redisCollectionDao.delete(id);
	}

	@Override
	public RedisCollectionDTO getRedisCollection(int id) {
		return redisCollectionMapper.toDTO(redisCollectionDao.get(id));
	}

}
