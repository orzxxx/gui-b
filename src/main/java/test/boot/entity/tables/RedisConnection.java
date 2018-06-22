/*
 * This file is generated by jOOQ.
*/
package test.boot.entity.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;

import test.boot.entity.Keys;
import test.boot.entity.Public;
import test.boot.entity.tables.records.RedisConnectionRecord;


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
public class RedisConnection extends TableImpl<RedisConnectionRecord> {

    private static final long serialVersionUID = 84940221;

    /**
     * The reference instance of <code>PUBLIC.REDIS_CONNECTION</code>
     */
    public static final RedisConnection REDIS_CONNECTION = new RedisConnection();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RedisConnectionRecord> getRecordType() {
        return RedisConnectionRecord.class;
    }

    /**
     * The column <code>PUBLIC.REDIS_CONNECTION.ID</code>.
     */
    public final TableField<RedisConnectionRecord, Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("(NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_1D194736_C69A_4659_A0C2_D18FE5E91A9B)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>PUBLIC.REDIS_CONNECTION.NAME</code>.
     */
    public final TableField<RedisConnectionRecord, String> NAME = createField("NAME", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>PUBLIC.REDIS_CONNECTION.NAMESPACE</code>.
     */
    public final TableField<RedisConnectionRecord, String> NAMESPACE = createField("NAMESPACE", org.jooq.impl.SQLDataType.VARCHAR.length(8), this, "");

    /**
     * The column <code>PUBLIC.REDIS_CONNECTION.AUTH</code>.
     */
    public final TableField<RedisConnectionRecord, String> AUTH = createField("AUTH", org.jooq.impl.SQLDataType.VARCHAR.length(64), this, "");

    /**
     * The column <code>PUBLIC.REDIS_CONNECTION.CONNECTION_TIMEOUT</code>.
     */
    public final TableField<RedisConnectionRecord, Integer> CONNECTION_TIMEOUT = createField("CONNECTION_TIMEOUT", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>PUBLIC.REDIS_CONNECTION.SO_TIMEOUT</code>.
     */
    public final TableField<RedisConnectionRecord, Integer> SO_TIMEOUT = createField("SO_TIMEOUT", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * Create a <code>PUBLIC.REDIS_CONNECTION</code> table reference
     */
    public RedisConnection() {
        this("REDIS_CONNECTION", null);
    }

    /**
     * Create an aliased <code>PUBLIC.REDIS_CONNECTION</code> table reference
     */
    public RedisConnection(String alias) {
        this(alias, REDIS_CONNECTION);
    }

    private RedisConnection(String alias, Table<RedisConnectionRecord> aliased) {
        this(alias, aliased, null);
    }

    private RedisConnection(String alias, Table<RedisConnectionRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<RedisConnectionRecord, Integer> getIdentity() {
        return Keys.IDENTITY_REDIS_CONNECTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<RedisConnectionRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_9;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<RedisConnectionRecord>> getKeys() {
        return Arrays.<UniqueKey<RedisConnectionRecord>>asList(Keys.CONSTRAINT_9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RedisConnection as(String alias) {
        return new RedisConnection(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public RedisConnection rename(String name) {
        return new RedisConnection(name, null);
    }
}
