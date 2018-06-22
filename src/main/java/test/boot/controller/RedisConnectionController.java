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

import test.boot.dto.RedisConnectionDTO;
import test.boot.service.RedisConnectionService;

@RestController
@RequestMapping("/redis-connections")
public class RedisConnectionController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RedisConnectionService redisConnectionService;
	
	@GetMapping
	public List<RedisConnectionDTO> list() {
		return redisConnectionService.listRedisConnection();
    }
	
	@PostMapping
    public RedisConnectionDTO save(@RequestBody RedisConnectionDTO dto) throws Exception {
		return redisConnectionService.saveRedisConnection(dto);
    }
	
	@GetMapping("/{id}")
	public RedisConnectionDTO get(@PathVariable int id) {
		return redisConnectionService.getRedisConnection(id);
	}
	
	@PutMapping("/{id}")
	public RedisConnectionDTO update(@RequestBody RedisConnectionDTO dto) {
		return redisConnectionService.updateRedisConnection(dto);
	}
	
	@DeleteMapping("/{id}")
	public void remove(@PathVariable int id) {
		redisConnectionService.removeRedisConnection(id);
	}

	@PostMapping("/test/{id}")
	public boolean test(@PathVariable int id) {
		return redisConnectionService.testRedisCollection(id);
	}
	
}