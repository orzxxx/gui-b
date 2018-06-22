/*
 * This file is generated by jOOQ.
*/
package test.boot.entity;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import test.boot.entity.tables.RedisCollection;
import test.boot.entity.tables.RedisConnection;
import test.boot.entity.tables.RedisNode;


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
public class Public extends SchemaImpl {

    private static final long serialVersionUID = -468548793;

    /**
     * The reference instance of <code>PUBLIC</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>PUBLIC.REDIS_CONNECTION</code>.
     */
    public final RedisConnection REDIS_CONNECTION = test.boot.entity.tables.RedisConnection.REDIS_CONNECTION;

    /**
     * The table <code>PUBLIC.REDIS_COLLECTION</code>.
     */
    public final RedisCollection REDIS_COLLECTION = test.boot.entity.tables.RedisCollection.REDIS_COLLECTION;

    /**
     * The table <code>PUBLIC.REDIS_NODE</code>.
     */
    public final RedisNode REDIS_NODE = test.boot.entity.tables.RedisNode.REDIS_NODE;

    /**
     * No further instances allowed
     */
    private Public() {
        super("PUBLIC", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        List result = new ArrayList();
        result.addAll(getSequences0());
        return result;
    }

    private final List<Sequence<?>> getSequences0() {
        return Arrays.<Sequence<?>>asList(
            Sequences.SYSTEM_SEQUENCE_1D194736_C69A_4659_A0C2_D18FE5E91A9B,
            Sequences.SYSTEM_SEQUENCE_25A3DF42_66BE_4FAD_82FA_DC938C2B0094,
            Sequences.SYSTEM_SEQUENCE_C59BD5C2_4793_4348_96DD_48FA120B8E95);
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            RedisConnection.REDIS_CONNECTION,
            RedisCollection.REDIS_COLLECTION,
            RedisNode.REDIS_NODE);
    }
}
