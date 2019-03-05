package edu.mum.library.dataaccess;

import edu.mum.library.dataaccess.base.BaseDaoWithPrimaryKey;
import edu.mum.library.model.Book;

public class BookDao extends BaseDaoWithPrimaryKey<Book, String> {

	@Override
	public String getTableName() {
		return "book";
	}
}
