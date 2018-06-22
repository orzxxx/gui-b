package test.boot.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.boot.dto.RedisCommandDTO;
import test.boot.service.RedisCommantService;

@RestController
@RequestMapping("/redis-commants")
public class RedisCommantController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RedisCommantService redisCommantService;
	
	@PostMapping("/{connId}")
    public List<String> execute(@PathVariable int connId, @RequestBody RedisCommandDTO command) throws Exception {
		return redisCommantService.getRedisCommantResult(connId, command.getCommand());
    }
	
	@GetMapping("/supported")
	public Map<String, String> supported() {
		return redisCommantService.getSupportedRedisCommant();
	}
	
}