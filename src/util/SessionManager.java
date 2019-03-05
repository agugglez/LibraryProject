package util;

import java.util.Arrays;
import java.util.List;

import model.Staff;

public class SessionManager {

	private static Staff staff;

	public static boolean login(String username, String password) {

		for(Staff s: getStaffList()){
			if(s.getUserId() == username && s.getPassword() == password)
			{
				staff = s;
				return true;
			}
		}

		return false;
	}

	private static List<Staff> getStaffList(){
		Staff staff1 = new Staff("augusto", "otsugua");
		Staff staff2 = new Staff("dong", "gnod");
		Staff staff3 = new Staff("andrew", "werdna");

		return Arrays.asList(staff1, staff2, staff3);
	}

	public static void logout() {

		staff=null;

	}

	public static Staff getStaff() {
		return staff;
	}
}
