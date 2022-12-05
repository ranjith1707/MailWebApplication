package model;

import java.util.HashMap;
import java.util.Map;

public class MailDatabase {
	static MailDatabase mailDB;
	private Map<String, User> userDetails;
	private Map<Long, Mail> composeMails;
	private Map<Long, Mail> recivedMail;

	private MailDatabase() {
		setUserDetails(new HashMap<String, User>());
		setComposeMails(new HashMap<Long, Mail>());
		setRecivedMail(new HashMap<Long, Mail>());
	}

	static public MailDatabase getDb() {
		if (mailDB == null) {
			mailDB = new MailDatabase();
		}
		return mailDB;

	}

	public Map<String, User> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(Map<String, User> userDetails) {
		this.userDetails = userDetails;
	}

	public Map<Long, Mail> getComposeMails() {
		return composeMails;
	}

	public void setComposeMails(Map<Long, Mail> composeMails) {
		this.composeMails = composeMails;
	}

	public Map<Long, Mail> getRecivedMail() {
		return recivedMail;
	}

	public void setRecivedMail(Map<Long, Mail> recivedMail) {
		this.recivedMail = recivedMail;
	}

	public User getUser(String userId) {
		return userDetails.get(userId);

	}

}
