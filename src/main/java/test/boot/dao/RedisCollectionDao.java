package test.boot.dao;

import static test.boot.entity.tables.RedisCollection.REDIS_COLLECTION;

import java.util.List;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import test.boot.entity.tables.pojos.RedisCollection;
import test.boot.entity.tables.records.RedisCollectionRecord;

@Component
public class RedisCollectionDao {
	
	@Autowired
	private DSLContext create;
	
	public List<RedisCollection> list() {
		return create.selectFrom(REDIS_COLLECTION)
				.orderBy(REDIS_COLLECTION.ID.desc())
				.fetchInto(RedisCollection.class);
	}

	public int save(RedisCollectionRecord record) {
		RedisCollectionRecord newRecord = create.insertInto(REDIS_COLLECTION)
				.set(record)
				.returning(REDIS_COLLECTION.ID)
				.fetchOne();
		return newRecord.getId();
	}

	public int update(RedisCollectionRecord record) {
		return create.update(REDIS_COLLECTION)
				.set(record)
				.where(REDIS_COLLECTION.ID.eq(record.getId()))
				.execute();
	}

	public int delete(int id) {
		return create.delete(REDIS_COLLECTION)
				.where(REDIS_COLLECTION.ID.eq(id))
				.execute();
	}

	public RedisCollection get(int id) {
		return create.selectFrom(REDIS_COLLECTION)
				.where(REDIS_COLLECTION.ID.eq(id))
				.fetchOneInto(RedisCollection.class);
	}
	
	public int countByCommand(Integer id, String command) {
		Condition condition = DSL.trueCondition();
		condition = condition.and(REDIS_COLLECTION.COMMAND.eq(command));
		if (id != null) {
			condition = condition.and(REDIS_COLLECTION.ID.notEqual(id));
		}
		return create.selectCount()
				.from(REDIS_COLLECTION)
				.where(condition)
				.fetchOne(DSL.count());
	}
}