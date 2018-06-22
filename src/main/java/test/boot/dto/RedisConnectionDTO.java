package test.boot.dto;

import java.util.List;

public class RedisConnectionDTO {

	private Integer id;	private String name;
	
	private List<RedisNodeDTO> nodes;	private String namespace;	private String auth;	private Integer connectionTimeout;	private Integer soTimeout;
	public Integer getId() {		return id;	}	public void setId(Integer id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getNamespace() {		return namespace;	}	public void setNamespace(String namespace) {		this.namespace = namespace;	}	public String getAuth() {		return auth;	}	public void setAuth(String auth) {		this.auth = auth;	}	public Integer getConnectionTimeout() {		return connectionTimeout;	}	public void setConnectionTimeout(Integer connectionTimeout) {		this.connectionTimeout = connectionTimeout;	}	public Integer getSoTimeout() {		return soTimeout;	}	public void setSoTimeout(Integer soTimeout) {		this.soTimeout = soTimeout;	}

	public List<RedisNodeDTO> getNodes() {
		return nodes;
	}

	public void setNodes(List<RedisNodeDTO> nodes) {
		this.nodes = nodes;
	}
}
