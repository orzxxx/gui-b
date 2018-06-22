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
import test.boot.entity.tables.records.RedisNodeRecord;


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
public class RedisNode extends TableImpl<RedisNodeRecord> {

    private static final long serialVersionUID = -1765980206;

    /**
     * The reference instance of <code>PUBLIC.REDIS_NODE</code>
     */
    public static final RedisNode REDIS_NODE = new RedisNode();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RedisNodeRecord> getRecordType() {
        return RedisNodeRecord.class;
    }

    /**
     * The column <code>PUBLIC.REDIS_NODE.ID</code>.
     */
    public final TableField<RedisNodeRecord, Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("(NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_25A3DF42_66BE_4FAD_82FA_DC938C2B0094)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>PUBLIC.REDIS_NODE.HOST</code>.
     */
    public final TableField<RedisNodeRecord, String> HOST = createField("HOST", org.jooq.impl.SQLDataType.VARCHAR.length(64), this, "");

    /**
     * The column <code>PUBLIC.REDIS_NODE.PORT</code>.
     */
    public final TableField<RedisNodeRecord, Integer> PORT = createField("PORT", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>PUBLIC.REDIS_NODE.C_ID</code>.
     */
    public final TableField<RedisNodeRecord, Integer> C_ID = createField("C_ID", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * Create a <code>PUBLIC.REDIS_NODE</code> table reference
     */
    public RedisNode() {
        this("REDIS_NODE", null);
    }

    /**
     * Create an aliased <code>PUBLIC.REDIS_NODE</code> table reference
     */
    public RedisNode(String alias) {
        this(alias, REDIS_NODE);
    }

    private RedisNode(String alias, Table<RedisNodeRecord> aliased) {
        this(alias, aliased, null);
    }

    private RedisNode(String alias, Table<RedisNodeRecord> aliased, Field<?>[] parameters) {
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
    public Identity<RedisNodeRecord, Integer> getIdentity() {
        return Keys.IDENTITY_REDIS_NODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<RedisNodeRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_7;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<RedisNodeRecord>> getKeys() {
        return Arrays.<UniqueKey<RedisNodeRecord>>asList(Keys.CONSTRAINT_7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RedisNode as(String alias) {
        return new RedisNode(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public RedisNode rename(String name) {
        return new RedisNode(name, null);
    }
}
