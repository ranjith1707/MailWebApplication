package controller;

import database.DatabaseManagement;
import model.MailDatabase;

public class LoginController {

	public String userLogInCheck(String userId, String password) {
		if (userNameCheck(userId)) {
			if (passwordCheck(userId, password)) {
				return "true";
			} else {
				return "Password_Incorrect";
			}
		}
		return "UserId_Incorrect";

	}

	private boolean userNameCheck(String userId) {

		if (MailDatabase.getDb().getUserDetails().isEmpty()) {
			new DatabaseManagement().userRetrival();

		} else {
			if (!MailDatabase.getDb().getUserDetails().containsKey(userId)) {
				new DatabaseManagement().userRetrival();
			}
		}
		if (MailDatabase.getDb().getUserDetails().containsKey(userId)) {
			return true;
		}

		return false;

	}

	private boolean passwordCheck(String userId, String password) {
		if (MailDatabase.getDb().getUser(userId).getPassword().equals(password)) {
			return true;
		}
		return false;

	}

}
