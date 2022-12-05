package controller;

import database.DatabaseManagement;
import model.MailDatabase;
import model.User;

public class UserControl {
	public boolean userIsFound(String userId) {
		if (MailDatabase.getDb().getUserDetails().isEmpty()) {
			DatabaseManagement manage = new DatabaseManagement();
			manage.userRetrival();
		}

		if (MailDatabase.getDb().getUserDetails().containsKey(userId)) {
			return true;
		}
		return false;

	}

	public String createNewAccount(String userId, String password, String mobileNumber, String dateOfBirth,
			String gender, String name) {
		if (!userIsFound(userId)) {
			new DatabaseManagement().addNewUser(userId, password, mobileNumber, dateOfBirth, gender, name);

			return "Your Account Successfully Created";
		} else {
			System.out.println("User Id Alredy Exsist ! ");
			return "User ID Alredy Exsist ! ";
		}

	}
}
