package test.boot.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import test.boot.common.exception.BusinessException;
import test.boot.core.redis.JedisExecutor;
import test.boot.core.redis.RedisCommantHelper;
import test.boot.core.redis.RedisExecutorFactory;
import test.boot.dao.RedisConnectionDao;
import test.boot.dao.RedisNodeDao;
import test.boot.dto.RedisConnectionDTO;
import test.boot.entity.tables.records.RedisConnectionRecord;
import test.boot.entity.tables.records.RedisNodeRecord;
import test.boot.mapper.RedisConnectionMapper;
import test.boot.mapper.RedisNodeMapper;
import test.boot.service.RedisConnectionService;

@Service
@Transactional
public class RedisConnectionServiceImpl implements RedisConnectionService {
	
	@Autowired
	private RedisCommantHelper redisCommentHelper;
	
	@Autowired
	private RedisConnectionDao redisConnectionDao;
	
	@Autowired
	private RedisNodeDao redisNodeDao;
	
	@Autowired
	private RedisConnectionMapper redisConnectionMapper;
	
	@Autowired
	private RedisNodeMapper redisNodeMapper;
	
	@Override
	public List<RedisConnectionDTO> listRedisConnection() {
		return redisConnectionDao.list()
				.stream()
				.map(r -> {
					RedisConnectionDTO dto = redisConnectionMapper.toDTO(r);
					dto.setNodes(redisNodeDao.listByCid(r.getId())
							.stream()
							.map(n -> redisNodeMapper.toDTO(n))
							.collect(Collectors.toList()));
					return dto;
				}).collect(Collectors.toList());
	}

	@Override
	public RedisConnectionDTO saveRedisConnection(RedisConnectionDTO dto) {
		RedisConnectionRecord record = redisConnectionMapper.toEntity(dto);
		if (redisConnectionDao.countByName(record.getId(), record.getName()) > 0) {
			throw new BusinessException("This name is already in use");
		}
		
		int id = redisConnectionDao.save(record);
		dto.setId(id);
		redisNodeDao.save(dto.getNodes()
				.stream()
				.map(n -> {
					RedisNodeRecord r = redisNodeMapper.toEntity(n);
					r.setCId(id);
					return r;
				})
				.collect(Collectors.toList()));
		return dto;
	}
	
	@Override
	public RedisConnectionDTO updateRedisConnection(RedisConnectionDTO dto) {
		RedisConnectionRecord record = redisConnectionMapper.toEntity(dto);
		if (redisConnectionDao.countByName(record.getId(), record.getName()) > 0) {
			throw new BusinessException("This name is already in use");
		}
		redisConnectionDao.update(record);
		redisNodeDao.deleteByCid(dto.getId());
		redisNodeDao.save(dto.getNodes()
				.stream()
				.map(n -> {
					RedisNodeRecord r = redisNodeMapper.toEntity(n);
					r.setCId(dto.getId());
					return r;
				})
				.collect(Collectors.toList()));
		
		return dto;
	}

	@Override
	public void removeRedisConnection(int id) {
		redisConnectionDao.delete(id);
		redisNodeDao.deleteByCid(id);
	}

	@Override
	public RedisConnectionDTO getRedisConnection(int id) {
		RedisConnectionDTO result = redisConnectionMapper.toDTO(redisConnectionDao.get(id));
		result.setNodes(redisNodeDao.listByCid(id)
				.stream()
				.map(n -> redisNodeMapper.toDTO(n))
				.collect(Collectors.toList()));
		return result;
	}


	@Override
	public boolean testRedisCollection(int id) {
		JedisExecutor jedisExecutor = RedisExecutorFactory.createRedisExecutor(id);
		try {
			redisCommentHelper.executeCommand(jedisExecutor, "EXISTS TEST");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
