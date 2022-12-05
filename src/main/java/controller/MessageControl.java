package controller;

import java.util.Map;

import org.json.simple.JSONObject;

import java.sql.ResultSet;

import database.DatabaseManagement;
import model.Mail;
import model.MailDatabase;

public class MessageControl {
	public static void main(String[] args) {
		MessageControl c = new MessageControl();
		System.out.println(c.getRecivedMessage("ranjithkumar@gmail.com"));

	}

	public void messageSending(String fromId, String toId, String subject, String message) {
		UserControl userControl = new UserControl();

		String[] users = toId.split(",");
		String to;
		DatabaseManagement dbManage = new DatabaseManagement();
		String query = "insert into messages(subject,message)values('" + subject + "','" + message + "')";
		dbManage.dataInsertion(query);
		long messageId = dbManage.getMessageId();
		dbManage.dataInsertion("insert into sender(messageId,fromId)values(" + messageId + ",'" + fromId + "')");
		for (int i = 0; i < users.length; i++) {
			to = users[i];
			if (userControl.userIsFound(to)) {
				dbManage.dataInsertion("insert into reciver(messageId,toId)values(" + messageId + ",'" + toId + "')");
			} else {
				System.out.println("Message Not Send");

			}
		}
	}

	public JSONObject getComposeMessage(String userId) {

		DatabaseManagement dbManage = new DatabaseManagement();
		dbManage.SendMessageRetrival(userId);

		JSONObject json = new JSONObject();
		for (Map.Entry<Long, Mail> map : MailDatabase.getDb().getComposeMails().entrySet()) {
			json.put(map.getKey(), map.toString());

		}
		return json;

	}

	public JSONObject getRecivedMessage(String userId) {

		DatabaseManagement dbManage = new DatabaseManagement();
		dbManage.recivedMailRetrival(userId);

		JSONObject json = new JSONObject();
		for (Map.Entry<Long, Mail> map : MailDatabase.getDb().getRecivedMail().entrySet()) {
			json.put(map.getKey(), map.toString());

		}
		return json;
	}

	public void messageDelete(long messageId, String messageType, String userId) {

		if (messageType.equals("RecivedMail")) {
			String query = "delete from reciver where toId='" + userId + "' && messageId=" + messageId + ";";
			new DatabaseManagement().dataInsertion(query);
		} else {

			String query = "delete from sender where fromId='" + userId + "' && messageId=" + messageId + ";";
			new DatabaseManagement().dataInsertion(query);
		}

	}

}
