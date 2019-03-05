package dataaccess.base;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import dataaccess.storage.PersistanceManager;

public abstract class BaseDao<T, ID> implements IDao<T, ID> {

	abstract protected String getTableName();

	abstract protected String getPrimaryKeyName();

	private List<T> getEntityAllData() {
		List<T> allData = (List<T>) readField(PersistanceManager.getLibrary(), getTableName());
		return allData;
	}

	private <T> T readField(Object obj, String name) {
		Field f;
		try {
			f = obj.getClass().getDeclaredField(name);
			f.setAccessible(true);
			return (T) f.get(obj); // IllegalAccessException
		} catch (Exception e) {
			throw new RuntimeException("fail to read Field", e);
		}
	}

	@Override
	public List<T> getAll() {
		return getEntityAllData();
	}

	@Override
	public T readById(ID id) {
		Optional<T> find = getEntityAllData().stream().filter(e -> id.equals(readField(e, getPrimaryKeyName())))
				.findAny();
		return find.isPresent() ? find.get() : null;
	}

	@Override
	public void save(T t) {
		ID id = readField(t, this.getPrimaryKeyName());
		Optional<T> find = getEntityAllData().stream().filter(e -> id.equals(readField(e, getPrimaryKeyName())))
				.findAny();
		if (!find.isPresent()) {
			getEntityAllData().add(t);

		}
		PersistanceManager.saveData();
	}

	@Override
	public boolean delete(ID id) {
		Optional<T> find = getEntityAllData().stream().filter(e -> id.equals(readField(e, getPrimaryKeyName())))
				.findAny();
		if (find.isPresent()) {
			getEntityAllData().remove(find.get());
			return true;
		}
		return false;

	}

}
