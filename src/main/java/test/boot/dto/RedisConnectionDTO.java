package test.boot.dto;

import java.util.List;

public class RedisConnectionDTO {

	private Integer id;
	
	private List<RedisNodeDTO> nodes;
	public Integer getId() {

	public List<RedisNodeDTO> getNodes() {
		return nodes;
	}

	public void setNodes(List<RedisNodeDTO> nodes) {
		this.nodes = nodes;
	}
}