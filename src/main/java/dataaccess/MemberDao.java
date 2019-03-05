package dataaccess;

import java.util.function.Supplier;

import dataaccess.base.BaseDao;
import model.Member;

public class MemberDao extends BaseDao<Member, String> {

	@Override
	public String getTableName() {
		return "member";
	}

	// @Override
	// protected String getPrimaryKey() {
	// return "memberId";
	// }

	@Override
	protected Supplier<String> getPrimaryKeySupplier(Member e) {
		return e::getMemberId;
	}

}
