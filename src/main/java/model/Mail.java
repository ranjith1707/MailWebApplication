package model;

public class Mail {
	private String fromId;
	private String toId;
	private String message;
	private String subject;

	public Mail(String fromId, String toId, String subject, String message) {
		this.fromId = fromId;
		this.toId = toId;
		this.subject = subject;
		this.message = message;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public String getToId() {
		return toId;
	}

	public void setToId(String toId) {
		this.toId = toId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String toString() {
		return "{-from:" + this.fromId + "-to:" + this.toId + "-subject:" + this.subject + "-message:" + this.message
				+ "~}";

	}
}
