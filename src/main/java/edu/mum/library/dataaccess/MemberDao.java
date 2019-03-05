package edu.mum.library.dataaccess;

import edu.mum.library.dataaccess.base.BaseDaoWithPrimaryKey;
import edu.mum.library.model.Member;

public class MemberDao extends BaseDaoWithPrimaryKey<Member, String> {

	@Override
	public String getTableName() {
		return "member";
	}

}
