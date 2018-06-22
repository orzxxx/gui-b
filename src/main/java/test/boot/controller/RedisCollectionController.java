package test.boot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.boot.dto.RedisCollectionDTO;
import test.boot.service.RedisCollectionService;

@RestController
@RequestMapping("/redis-collections")
public class RedisCollectionController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RedisCollectionService redisCollectionService;
	
	@GetMapping
	public List<RedisCollectionDTO> list() {
    	return redisCollectionService.listRedisCollection();
	}
	
	@GetMapping("/{id}")
	public RedisCollectionDTO get(@PathVariable int id) {
		return redisCollectionService.getRedisCollection(id);
	}
	
	@PostMapping
	public RedisCollectionDTO save(@RequestBody RedisCollectionDTO dto) {
		return redisCollectionService.saveRedisCollection(dto);
	}
	
	@PutMapping
	public RedisCollectionDTO update(@RequestBody RedisCollectionDTO dto) {
		return redisCollectionService.updateRedisCollection(dto);
	}
	
	@DeleteMapping("/{id}")
	public void remove(@PathVariable int id) {
		redisCollectionService.removeRedisCollection(id);
	}
	
}