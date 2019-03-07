package edu.mum.library.view.dto;

import edu.mum.library.model.Member;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MemberDto extends PersonDto<Member> {

	private StringProperty memberId;

	public MemberDto(Member member) {
		super(member);
		this.memberId = new SimpleStringProperty(member.getMemberId());
	}

	public String getMemberId() {
		return memberId.getValue();
	}

	public StringProperty memberIdProperty() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId.set(memberId);
	}

	// public Member toMember() {
	// Member member = new Member(this.getMemberId(), this.getFirstName(),
	// this.getLastName(), this.getPhoneNumber());
	// try {
	// BeanUtils.copyProperties(member, this);
	// } catch (IllegalAccessException e) {
	// e.printStackTrace();
	// } catch (InvocationTargetException e) {
	// e.printStackTrace();
	// }
	// member.setPersonAddress(new Address(this.getStreet(), this.getCity(),
	// this.getState(), this.getZipcode()));
	// return member;
	// }
}
