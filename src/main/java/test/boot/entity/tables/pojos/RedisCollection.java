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
public class RedisCollection implements Serializable {

    private static final long serialVersionUID = 957523688;

    private Integer id;
    private String  command;
    private Integer connId;

    public RedisCollection() {}

    public RedisCollection(RedisCollection value) {
        this.id = value.id;
        this.command = value.command;
        this.connId = value.connId;
    }

    public RedisCollection(
        Integer id,
        String  command,
        Integer connId
    ) {
        this.id = id;
        this.command = command;
        this.connId = connId;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommand() {
        return this.command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Integer getConnId() {
        return this.connId;
    }

    public void setConnId(Integer connId) {
        this.connId = connId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("RedisCollection (");

        sb.append(id);
        sb.append(", ").append(command);
        sb.append(", ").append(connId);

        sb.append(")");
        return sb.toString();
    }
}