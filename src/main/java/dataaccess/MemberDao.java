package dataaccess;

import dataaccess.base.BaseDaoWithPrimaryKey;
import model.Member;

public class MemberDao extends BaseDaoWithPrimaryKey<Member, String> {

	@Override
	public String getTableName() {
		return "member";
	}

}
