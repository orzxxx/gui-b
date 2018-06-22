/*
 * This file is generated by jOOQ.
*/
package test.boot.entity.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RedisConnection implements Serializable {

    private static final long serialVersionUID = -855988700;

    private Integer id;
    private String  name;
    private String  namespace;
    private String  auth;
    private Integer connectionTimeout;
    private Integer soTimeout;

    public RedisConnection() {}

    public RedisConnection(RedisConnection value) {
        this.id = value.id;
        this.name = value.name;
        this.namespace = value.namespace;
        this.auth = value.auth;
        this.connectionTimeout = value.connectionTimeout;
        this.soTimeout = value.soTimeout;
    }

    public RedisConnection(
        Integer id,
        String  name,
        String  namespace,
        String  auth,
        Integer connectionTimeout,
        Integer soTimeout
    ) {
        this.id = id;
        this.name = name;
        this.namespace = namespace;
        this.auth = auth;
        this.connectionTimeout = connectionTimeout;
        this.soTimeout = soTimeout;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getAuth() {
        return this.auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public Integer getConnectionTimeout() {
        return this.connectionTimeout;
    }

    public void setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public Integer getSoTimeout() {
        return this.soTimeout;
    }

    public void setSoTimeout(Integer soTimeout) {
        this.soTimeout = soTimeout;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("RedisConnection (");

        sb.append(id);
        sb.append(", ").append(name);
        sb.append(", ").append(namespace);
        sb.append(", ").append(auth);
        sb.append(", ").append(connectionTimeout);
        sb.append(", ").append(soTimeout);

        sb.append(")");
        return sb.toString();
    }
}