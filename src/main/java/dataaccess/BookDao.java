package dataaccess;

import dataaccess.base.BaseDao;
import model.Book;

public class BookDao extends BaseDao<Book, String> {

	@Override
	protected String getTableName() {
		return "book";
	}

	@Override
	protected String getPrimaryKeyName() {
		return "isbn";
	}


}
