package dataaccess;
import java.util.List;

import model.Member;

public interface LibraryDAO {

	List<Member> getAllMembers();
	void addMember(Member m);
	void deleteMember(Member m);
	void updateMember(Member m);
}
