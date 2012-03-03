package com.blockmar.persistence.mongo;

import java.util.Date;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity
public abstract class MongoRepositoryObject<T> {

	@Id
	public ObjectId id;

	public Date created;
	public Date modified;

	public MongoRepositoryObject() {
	}

	public MongoRepositoryObject(T object) {
	}

	public abstract T get();
	
	public void updateExtraFields(MongoRepositoryObject<T> old) {
		this.created = old.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
}
