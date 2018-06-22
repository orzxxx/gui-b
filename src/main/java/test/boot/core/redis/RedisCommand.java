package test.boot.core.redis;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RedisCommand {
	
	private String methodName;
	
	private String command;
	
	private String desc;
	
	private Method jedisMethod;
	
	private Class<?>[] parameterTypes;
	
	public RedisCommand(String command, String desc) {
		super();
		this.methodName = command;
		this.command = command;
		this.desc = desc;
	}
	
	public RedisCommand(String command, String methodName, String desc) {
		super();
		this.command = command;
		this.methodName = methodName;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Class<?>[] getParameterTypes() {
		return parameterTypes;
	}

	public void setParameterTypes(Class<?>[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}

	public Method getJedisMethod() {
		return jedisMethod;
	}

	public void setJedisMethod(Method jedisMethod) {
		this.jedisMethod = jedisMethod;
	}
	
	public boolean numberMatchingValidate(String[] parameters) {
		if (parameterTypes.length == 1 && parameterTypes[0].equals(Map.class)) {
			return parameters.length >= 2 && parameters.length % 2 == 0;
		}
		
		if (parameterTypes.length == 1 && parameterTypes[0].equals(Array.class)) {
			return parameters.length > 0;
		}
		
		return parameterTypes.length == parameters.length;
	}
	
	public boolean typeMatchingValidate(String[] parameters) {
		if (parameterTypes.length == 0) {
			return true;
		}
		
		// 特殊处理
		if (command.equals("ZADD")) {
			try {
				for (int i = 0; i < parameters.length / 2; i++) {
					Double.valueOf(parameters[i*2]);
				}
			} catch (ClassCastException | NumberFormatException e) {
				return false;
			}
		}
		
		if (parameterTypes[0].equals(Map.class) || parameterTypes[0].equals(Array.class)) {
			return true;
		}
		
		for (int i = 0; i < parameterTypes.length; i++) {
			try {
				if (parameterTypes[i].equals(Double.class) || parameterTypes[i].equals(double.class)) {
					Double.valueOf(parameters[i]);
				} else if (parameterTypes[i].equals(Integer.class) || parameterTypes[i].equals(int.class)) {
					Integer.valueOf(parameters[i]);
				} else if (parameterTypes[i].equals(Long.class) || parameterTypes[i].equals(long.class)) {
					Long.valueOf(parameters[i]);
				} else {
					parameterTypes[i].cast(parameters[i]);
				}
			} catch (ClassCastException | NumberFormatException e) {
				return false;
			}
		}
		
		return true;
	}

	public Object execute(JedisExecutor jedisExecutor, String key, String[] parameters) 
			throws Exception {
		Object[] args = new Object[1 + parameterTypes.length];
		args[0] = key;
		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].equals(Double.class) || parameterTypes[i].equals(double.class)) {
				args[i+1] = Double.valueOf(parameters[i]);
			} else if (parameterTypes[i].equals(Integer.class) || parameterTypes[i].equals(int.class)) {
				args[i+1] = Integer.valueOf(parameters[i]);
			} else if (parameterTypes[i].equals(Long.class) || parameterTypes[i].equals(long.class)) {
				args[i+1] = Long.valueOf(parameters[i]);
			} else if (parameterTypes[i].equals(Map.class)) {
				// 特殊处理
				if (command.equals("ZADD")) {
					Map<String, Double> memberToScore = new HashMap<>();
					for (int j = 0; j < parameters.length / 2; j++) {
						memberToScore.put(parameters[j*2 + 1], Double.valueOf(parameters[j*2]));
					}
					args[i+1] = memberToScore;
				} else {
					Map<String, String> fieldToValue = new HashMap<>();
					for (int j = 0; j < parameters.length / 2; j++) {
						fieldToValue.put(parameters[j*2], parameters[j*2 + 1]);
					}
					args[i+1] = fieldToValue;
				}
			} else {
				args[i+1] = parameterTypes[i].cast(parameters[i]);
			}
		}
		
		return jedisMethod.invoke(jedisExecutor, args);
	}
	
}
