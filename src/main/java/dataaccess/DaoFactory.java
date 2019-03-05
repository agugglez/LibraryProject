package dataaccess;

public class DaoFactory {
	private DaoFactory() {

	}

	private static BookDao bookDao = new BookDao();
	private static MemberDao memberDao = new MemberDao();

	public static BookDao getBookDao() {
		return bookDao;
	}

	public static MemberDao getMemberDao() {
		return memberDao;
	}

}
