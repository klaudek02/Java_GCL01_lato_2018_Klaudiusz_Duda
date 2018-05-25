package com.example.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
	@Autowired
	private AdminProperties properties;
	boolean logged = false;

	boolean login(String login, String password) {
		if (!isLogged()) {
			if (login.equals(properties.getLogin()) && password.equals(properties.getPassword())) {
				logged = true;
				return true;
			}
			logged = false;
			return false;
		}
		return true;
	}
	
	void logout() {
		logged = false;
	}
	
	boolean isLogged() {
		return logged;
	}
}
