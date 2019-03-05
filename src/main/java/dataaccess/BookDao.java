package dataaccess;

import dataaccess.base.BaseDaoWithPrimaryKey;
import model.Book;

public class BookDao extends BaseDaoWithPrimaryKey<Book, String> {

	@Override
	public String getTableName() {
		return "book";
	}
}
