package dataaccess;

import dataaccess.base.BaseDao;
import model.Member;

public class MemberDao extends BaseDao<Member, String> {

	@Override
	protected String getTableName() {
		return "member";
	}

	@Override
	protected String getPrimaryKeyName() {
		return "memberId";
	}

}
