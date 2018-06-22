package test.boot.dao;

import static test.boot.entity.tables.RedisNode.REDIS_NODE;

import java.util.List;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import test.boot.entity.tables.pojos.RedisNode;
import test.boot.entity.tables.records.RedisNodeRecord;

@Component
public class RedisNodeDao {
	
	@Autowired
	private DSLContext create;
	
	public List<RedisNode> list() {
		return create.selectFrom(REDIS_NODE)
				.orderBy(REDIS_NODE.ID.desc())
				.fetchInto(RedisNode.class);
	}
	
	public List<RedisNode> listByCid(int cid) {
		return create.selectFrom(REDIS_NODE)
				.where(REDIS_NODE.C_ID.eq(cid))
				.fetchInto(RedisNode.class);
	}

	public int save(RedisNodeRecord record) {
		RedisNodeRecord newRecord = create.insertInto(REDIS_NODE)
				.set(record)
				.returning(REDIS_NODE.ID)
				.fetchOne();
		return newRecord.getId();
	}
	
	public int[] save(List<RedisNodeRecord> records) {
		return create.batchInsert(records).execute();
	}

	public int update(RedisNodeRecord record) {
		return create.update(REDIS_NODE)
				.set(record)
				.where(REDIS_NODE.ID.eq(record.getId()))
				.execute();
	}
	
	public int delete(int id) {
		return create.delete(REDIS_NODE)
				.where(REDIS_NODE.ID.eq(id))
				.execute();
	}
	
	public int deleteByCid(int cid) {
		return create.delete(REDIS_NODE)
				.where(REDIS_NODE.C_ID.eq(cid))
				.execute();
	}

	public RedisNode get(int id) {
		return create.selectFrom(REDIS_NODE)
				.where(REDIS_NODE.ID.eq(id))
				.fetchOneInto(RedisNode.class);
	}
	
}