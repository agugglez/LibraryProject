package edu.mum.library.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.library.mock.StaffTableMock;
import edu.mum.library.model.Staff;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private SessionManager sessionManager;

	@Override
	public boolean login(String requestId, String password) {
		Optional<Staff> loginUser = StaffTableMock.getStaffList().stream()
				.filter(s -> s.getUserId().equalsIgnoreCase(requestId) && s.getPassword().equals(password)).findAny();
		if (loginUser.isPresent()) {
			sessionManager.setLoginUser(loginUser.get());
		}
		return loginUser.isPresent();
	}

}
