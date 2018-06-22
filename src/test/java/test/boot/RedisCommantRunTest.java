package test.boot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional
public class RedisCommantRunTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	public void executeTest() {
		Map<String, String> params = new HashMap<>();
		params.put("command", "set test3 kkk");
		//zrangebyscore ztest2 0 99 lIMit 0 10 withscores
		//ResponseEntity<List> resp = testRestTemplate.postForEntity("/redis-commants/3", "hgetall job.tag", List.class, params);
		ResponseEntity<List> resp = testRestTemplate.postForEntity("/redis-commants/3", params, List.class, params);
		//ResponseEntity<List> resp = testRestTemplate.getForEntity("/redis-comments/3", null, List.class, params);
		System.out.println(resp);
	}
}