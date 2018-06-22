/*
 * This file is generated by jOOQ.
*/
package test.boot.entity.tables.records;


import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;

import test.boot.entity.tables.RedisConnection;


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
public class RedisConnectionRecord extends UpdatableRecordImpl<RedisConnectionRecord> implements Record6<Integer, String, String, String, Integer, Integer> {

    private static final long serialVersionUID = 938896647;

    /**
     * Setter for <code>PUBLIC.REDIS_CONNECTION.ID</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>PUBLIC.REDIS_CONNECTION.ID</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>PUBLIC.REDIS_CONNECTION.NAME</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>PUBLIC.REDIS_CONNECTION.NAME</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>PUBLIC.REDIS_CONNECTION.NAMESPACE</code>.
     */
    public void setNamespace(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>PUBLIC.REDIS_CONNECTION.NAMESPACE</code>.
     */
    public String getNamespace() {
        return (String) get(2);
    }

    /**
     * Setter for <code>PUBLIC.REDIS_CONNECTION.AUTH</code>.
     */
    public void setAuth(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>PUBLIC.REDIS_CONNECTION.AUTH</code>.
     */
    public String getAuth() {
        return (String) get(3);
    }

    /**
     * Setter for <code>PUBLIC.REDIS_CONNECTION.CONNECTION_TIMEOUT</code>.
     */
    public void setConnectionTimeout(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>PUBLIC.REDIS_CONNECTION.CONNECTION_TIMEOUT</code>.
     */
    public Integer getConnectionTimeout() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>PUBLIC.REDIS_CONNECTION.SO_TIMEOUT</code>.
     */
    public void setSoTimeout(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>PUBLIC.REDIS_CONNECTION.SO_TIMEOUT</code>.
     */
    public Integer getSoTimeout() {
        return (Integer) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Integer, String, String, String, Integer, Integer> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Integer, String, String, String, Integer, Integer> valuesRow() {
        return (Row6) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return RedisConnection.REDIS_CONNECTION.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return RedisConnection.REDIS_CONNECTION.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return RedisConnection.REDIS_CONNECTION.NAMESPACE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return RedisConnection.REDIS_CONNECTION.AUTH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return RedisConnection.REDIS_CONNECTION.CONNECTION_TIMEOUT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field6() {
        return RedisConnection.REDIS_CONNECTION.SO_TIMEOUT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getNamespace();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getAuth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getConnectionTimeout();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value6() {
        return getSoTimeout();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RedisConnectionRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RedisConnectionRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RedisConnectionRecord value3(String value) {
        setNamespace(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RedisConnectionRecord value4(String value) {
        setAuth(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RedisConnectionRecord value5(Integer value) {
        setConnectionTimeout(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RedisConnectionRecord value6(Integer value) {
        setSoTimeout(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RedisConnectionRecord values(Integer value1, String value2, String value3, String value4, Integer value5, Integer value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RedisConnectionRecord
     */
    public RedisConnectionRecord() {
        super(RedisConnection.REDIS_CONNECTION);
    }

    /**
     * Create a detached, initialised RedisConnectionRecord
     */
    public RedisConnectionRecord(Integer id, String name, String namespace, String auth, Integer connectionTimeout, Integer soTimeout) {
        super(RedisConnection.REDIS_CONNECTION);

        set(0, id);
        set(1, name);
        set(2, namespace);
        set(3, auth);
        set(4, connectionTimeout);
        set(5, soTimeout);
    }
}