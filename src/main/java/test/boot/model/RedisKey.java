package test.boot.model;

import java.util.List;

public class RedisKey {
	
	private String key;
	
	private String type;
	
	private List<RedisKey> children;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<RedisKey> getChildren() {
		return children;
	}

	public void setChildren(List<RedisKey> children) {
		this.children = children;
	}
	
}
