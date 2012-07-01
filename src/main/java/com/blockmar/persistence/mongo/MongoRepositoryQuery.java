package com.blockmar.persistence.mongo;

import java.util.Iterator;

import com.blockmar.persistence.RepositoryQuery;
import com.blockmar.persistence.RepositoryQueryResult;
import com.google.code.morphia.query.Query;

public class MongoRepositoryQuery<T> implements RepositoryQuery<T> {
	
	private Query<? extends MongoRepositoryObject<T>> query;
	
	public MongoRepositoryQuery(Query<? extends MongoRepositoryObject<T>> query) {
		this.query = query;
	}

	@Override
	public T first() {
		Iterator<? extends MongoRepositoryObject<T>> iterator = query.iterator();
		if(iterator.hasNext()) {
			MongoRepositoryObject<T> object = iterator.next();
			return object.get();
		}
		return null;
	}
	
	@Override
	public RepositoryQueryResult<T> result() {
		return new MongoRepositoryQueryResult<T>(query);
	}
	
	@Override
	public <V> MongoRepositoryQuery<T> offset(int offset) {
		query = query.offset(offset);
		return this;
	}

	@Override
	public <V> MongoRepositoryQuery<T> and(String key, V value) {
		query = query.filter(key, value);
		return this;
	}
	
	@Override
	public <V> MongoRepositoryQuery<T> orderBy(String key, RepositoryQuery.Order direction) {
		if(direction == Order.DESC) {
			key = "-" + key;
		}
		query = query.order(key);
		return this;
	}
	
	@Override
	public <V> MongoRepositoryQuery<T> orderBy(String key) {
		return orderBy(key, Order.ASC);
	}
}
