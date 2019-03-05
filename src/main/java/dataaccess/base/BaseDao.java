package dataaccess.base;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import dataaccess.storage.PersistanceManager;

public abstract class BaseDao<T, ID> implements IDao<T, ID> {

	abstract public String getTableName();

	abstract protected Supplier<ID> getPrimaryKeySupplier(T e);

	protected ID getPrimaryKeyValue(T e) {
		return getPrimaryKeySupplier(e).get();
	}

	@Override
	public List<T> getAll() {
		return secretGetAll();
	}

	private List<T> secretGetAll() {
		return PersistanceManager.getEntityAllData(this);
	}

	@Override
	public T readById(ID id) {
		Optional<T> find = secretGetAll().stream().filter(e -> id.equals(getPrimaryKeyValue(e))).findAny();
		return find.isPresent() ? find.get() : null;
	}

	@Override
	public void save(T t) {
		ID id = getPrimaryKeyValue(t);
		Optional<T> find = secretGetAll().stream().filter(e -> id.equals(getPrimaryKeyValue(e))).findAny();
		if (!find.isPresent()) {
			secretGetAll().add(t);

		}
		PersistanceManager.saveData();
	}

	@Override
	public boolean delete(ID id) {
		Optional<T> find = secretGetAll().stream().filter(e -> id.equals(getPrimaryKeyValue(e))).findAny();
		if (find.isPresent()) {
			secretGetAll().remove(find.get());
			return true;
		}
		return false;

	}

}
