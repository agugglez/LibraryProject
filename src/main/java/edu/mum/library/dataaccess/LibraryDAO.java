package edu.mum.library.dataaccess;
import java.util.List;

import edu.mum.library.model.Member;

public interface LibraryDAO {

	List<Member> getAllMembers();
	void addMember(Member m);
	void deleteMember(Member m);
	void updateMember(Member m);
}
