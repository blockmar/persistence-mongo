package com.blockmar.persistence.mongo;

import java.lang.reflect.Constructor;
import java.util.Date;

import org.bson.types.ObjectId;

import com.blockmar.persistence.Repository;
import com.blockmar.persistence.RepositoryQuery;
import com.blockmar.persistence.RepositoryQueryResult;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.Key;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.query.Query;
import com.mongodb.Mongo;

public class MongoRepository<T, W extends MongoRepositoryObject<T>> implements
		Repository<T> {

	private final Class<W> wrapperClazz;

	protected final Datastore datastore;
	protected final Morphia morphia;

	public MongoRepository(Class<W> wrapperClazz, Mongo mongo, String database) {
		this.wrapperClazz = wrapperClazz;
		morphia = new Morphia();
		morphia.map(wrapperClazz);
		datastore = morphia.createDatastore(mongo, database);
	}

	public T findById(String id) {
		try {
			return getSource(findByIdWrapped(id));
		} catch (IllegalArgumentException e) {
			//An invalid ObjectId-string does not result in an exception. 
			return null;
		}
	}

	public <V> RepositoryQuery<T> find() {
		return new MongoRepositoryQuery<T>(datastore.find(wrapperClazz));
	}
	
	public <V> RepositoryQuery<T> find(String key, V value) {
		return new MongoRepositoryQuery<T>(findWrapped(key, value));
	}
	
	public RepositoryQueryResult<T> findAll() {
		return new MongoRepositoryQueryResult<T>(datastore.find(wrapperClazz));
	}

	protected W findByIdWrapped(String id) {
		return findByIdWrapped(new ObjectId(id));
	}
	
	protected W findByIdWrapped(ObjectId id) {
		Key<W> key = new Key<W>(wrapperClazz, id);
		return findByKey(key);
	}

	protected <V> W findOneWrapped(String key, V value) {
		Query<W> query = datastore.find(wrapperClazz, key, value);
		W foundObject = query.get();
		return foundObject;
	}

	private <V> Query<W> findWrapped(String key, V value) {
		return datastore.find(wrapperClazz, key, value);
	}

	public T store(T object) {
		W wrappedObject = wrapObject(object);
		Key<W> key = store(wrappedObject);
		return getSource(findByKey(key));
	}

	protected Key<W> store(W wrappedObject) {
		wrappedObject.setModified(new Date());
		return datastore.save(wrappedObject);
	}

	protected W wrapObject(T object) {
		try {
			Constructor<W> constructor = wrapperClazz
					.getDeclaredConstructor(new Class[] { object.getClass() });
			constructor.setAccessible(true);
			W wrappedObject = constructor.newInstance(new Object[] { object });
			
			if(wrappedObject.id != null) {
				W oldObject = findByIdWrapped(wrappedObject.id);
				if(oldObject != null) {
					wrappedObject.updateExtraFields(oldObject);
				}
			}
			else {
				wrappedObject.setCreated(new Date());
			}
			
			return wrappedObject;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	protected W findByKey(Key<W> key) {
		W foundObject = datastore.getByKey(wrapperClazz, key);
		return foundObject;
	}

	private T getSource(W foundObject) {
		return foundObject == null ? null : foundObject.get();
	}

	@Override
	public void delete(T object) {
		W wrappedObject = wrapObject(object);
		datastore.delete(wrappedObject);
	}
}
