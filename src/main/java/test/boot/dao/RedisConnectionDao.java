package test.boot.dao;

import static test.boot.entity.tables.RedisConnection.REDIS_CONNECTION;

import java.util.List;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import test.boot.entity.tables.pojos.RedisConnection;
import test.boot.entity.tables.records.RedisConnectionRecord;

@Component
public class RedisConnectionDao {
	
	@Autowired
	private DSLContext create;
	
	public List<RedisConnection> list() {
		return create.selectFrom(REDIS_CONNECTION)
				.orderBy(REDIS_CONNECTION.ID.asc())
				.fetchInto(RedisConnection.class);
	}

	public int save(RedisConnectionRecord record) {
		RedisConnectionRecord newRecord = create.insertInto(REDIS_CONNECTION)
				.set(record)
				.returning(REDIS_CONNECTION.ID)
				.fetchOne();
		return newRecord.getId();
	}

	public int update(RedisConnectionRecord record) {
		return create.update(REDIS_CONNECTION)
				.set(record)
				.where(REDIS_CONNECTION.ID.eq(record.getId()))
				.execute();
	}

	public int delete(int id) {
		return create.delete(REDIS_CONNECTION)
				.where(REDIS_CONNECTION.ID.eq(id))
				.execute();
	}

	public RedisConnection get(int id) {
		return create.selectFrom(REDIS_CONNECTION)
				.where(REDIS_CONNECTION.ID.eq(id))
				.fetchOneInto(RedisConnection.class);
	}
	
	public int countByName(Integer id, String name) {
		Condition condition = DSL.trueCondition();
		condition = condition.and(REDIS_CONNECTION.NAME.eq(name));
		if (id != null) {
			condition = condition.and(REDIS_CONNECTION.ID.notEqual(id));
		}
		return create.selectCount()
				.from(REDIS_CONNECTION)
				.where(condition)
				.fetchOne(DSL.count());
	}

	public List<RedisConnection> listByName(String name) {
		return create.selectFrom(REDIS_CONNECTION)
				.where(REDIS_CONNECTION.NAME.eq(name))
				.orderBy(REDIS_CONNECTION.ID.desc())
				.fetchInto(RedisConnection.class);
	}

}