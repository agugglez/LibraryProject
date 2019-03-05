package dataaccess.base;

import java.util.Optional;

import dataaccess.storage.PersistanceManager;
import model.base.IPrimaryKeyGetter;

public abstract class BaseDaoWithPrimaryKey<T extends IPrimaryKeyGetter<ID>, ID> extends BaseDao<T, ID> {

	@Override
	public T readById(ID id) {
		Optional<T> find = secretGetAll().stream().filter(e -> id.equals(e.getPrimaryKey())).findAny();
		return find.isPresent() ? find.get() : null;
	}

	@Override
	public void save(T t) {
		ID id = t.getPrimaryKey();
		Optional<T> find = secretGetAll().stream().filter(e -> id.equals(e.getPrimaryKey())).findAny();
		if (!find.isPresent()) {
			secretGetAll().add(t);

		}
		PersistanceManager.saveData();
	}

	@Override
	public boolean delete(ID id) {
		Optional<T> find = secretGetAll().stream().filter(e -> id.equals(e.getPrimaryKey())).findAny();
		if (find.isPresent()) {
			secretGetAll().remove(find.get());
			return true;
		}
		return false;

	}

}
