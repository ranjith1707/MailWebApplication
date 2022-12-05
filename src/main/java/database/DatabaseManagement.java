package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Mail;
import model.MailDatabase;
import model.User;

public class DatabaseManagement {
	public static void main(String[] args) {
		DatabaseManagement m = new DatabaseManagement();
		m.getMessageId();
	}

	private ResultSet dataRetrival(String query) {
		Statement statement = DatabaseConnection.getStatement();
		ResultSet result = null;
		try {
			result = statement.executeQuery(query);
			return result;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

	public void dataInsertion(String query) {
		Statement statement = DatabaseConnection.getStatement();
		try {

			int exicute = statement.executeUpdate(query);

		} catch (Exception e) {
			System.out.println("Server Error");

			e.printStackTrace();
		}
	}

	public void SendMessageRetrival(String userId) {
		String qury = "select reciver.toId,messages.* from reciver,messages where messages.messageId=reciver.messageId AND reciver.messageId in(select messageId from sender where fromId='"
				+ userId + "'); ";

		try {
			ResultSet data = dataRetrival(qury);
			MailDatabase.getDb().getComposeMails().clear();
			while (data.next()) {

				MailDatabase.getDb().getComposeMails().put(data.getLong("messageId"),
						new Mail(userId, data.getString("toId"), data.getString("Subject"), data.getString("message")));
			}

		} catch (SQLException e) {

		}
	}

	public void recivedMailRetrival(String userId) {
		try {
			String query = "select messages.*,sender.fromId from messages,sender where messages.messageId=sender.messageId AND sender.messageId in(select messageId from reciver where toId='"
					+ userId + "');";

			ResultSet data = dataRetrival(query);
			MailDatabase.getDb().getRecivedMail().clear();
			while (data.next()) {

				MailDatabase.getDb().getRecivedMail().put(data.getLong("messageId"), new Mail(data.getString("fromId"),
						userId, data.getString("Subject"), data.getString("message")));
			}

		} catch (Exception e) {

		}
	}

	public void userRetrival() {
		String query = "select users.*,userdetails.name from users join userdetails where users.userId=userdetails.userId";
		ResultSet data = dataRetrival(query);
		try {
			while (data.next()) {
				MailDatabase.getDb().getUserDetails().put(data.getString("UserId"),
						new User(data.getString("UserId"), data.getString("password"), data.getString("name")));
			}
			System.out.println();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public long getMessageId() {

		String query = "select max(messageId)as messageId from messages;";
		long id = 0;
		try {
			ResultSet data = dataRetrival(query);
			while (data.next()) {
				id = data.getLong("messageId");
				System.out.println(id);
				return id;
			}

		} catch (Exception e) {
			System.out.println("Message Id no Geted");
			e.printStackTrace();
		}
		return id;

	}

	public boolean addNewUser(String userId, String password, String mobileNumber, String dateOfBirth, String gender,
			String name) {

		String userQuery = "insert into users(UserId,password) values('" + userId + "','" + password + "');";
		String detailsQuery = "insert into userdetails(UserId,dateOfBirth,mobileNumber,name,gender) values('" + userId
				+ "','" + dateOfBirth + "','" + mobileNumber + "','" + name + "','" + gender + "');";
		try {
			dataInsertion(userQuery);
			dataInsertion(detailsQuery);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

}
