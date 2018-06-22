package test.boot.dto;

public class RedisCollectionDTO {

	private Integer id;	private String command;
	
	private Integer connId;
	public Integer getId() {		return id;	}	public void setId(Integer id) {		this.id = id;	}	public String getCommand() {		return command;	}	public void setCommand(String command) {		this.command = command;	}

	public Integer getConnId() {
		return connId;
	}

	public void setConnId(Integer connId) {
		this.connId = connId;
	}	
}
