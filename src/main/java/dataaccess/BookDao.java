package dataaccess;

import java.util.function.Supplier;

import dataaccess.base.BaseDao;
import model.Book;

public class BookDao extends BaseDao<Book, String> {

	@Override
	public String getTableName() {
		return "book";
	}

	@Override
	protected Supplier<String> getPrimaryKeySupplier(Book e) {
		return e::getIsbn;
	}

	// @Override
	// protected String getPrimaryKey() {
	// return "isbn";
	// }

}
