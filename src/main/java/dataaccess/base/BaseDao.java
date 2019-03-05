package dataaccess.base;

import java.util.List;

import dataaccess.storage.PersistanceManager;

public abstract class BaseDao<T, ID> implements IDao<T, ID> {

	abstract public String getTableName();

	@Override
	public List<T> getAll() {
		return secretGetAll();
	}

	protected List<T> secretGetAll() {
		return PersistanceManager.getEntityAllData(this);
	}
}
