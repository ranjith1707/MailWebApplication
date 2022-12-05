package classes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import controller.MessageControl;

/**
 * Servlet implementation class MessageRetrival
 */
@WebServlet("/MessageRetrival")
public class MessageRetrival extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MessageRetrival() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Session session=request.Names()

		response.setContentType("application/json");
		String head = request.getParameter("headmessage");
		MessageControl getMessage = new MessageControl();
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("UserId");
		JSONArray arrayObj = new JSONArray();
		Cookie[] cookies = request.getCookies();
		String detail = request.getParameter("delmessage");
		if (detail != null) {
			String[] details = detail.split(",");
			getMessage.messageDelete(Long.parseLong(details[0]), details[1], userId);
			System.out.println(detail);
		} else {
			for (Cookie cookie : cookies)

				if (head.equals("RecivedMail")) {

					JSONObject recivemessage = getMessage.getRecivedMessage(userId);
					arrayObj.add(recivemessage);
					System.out.println(userId);
					System.out.println(recivemessage);

					response.getWriter().append(recivemessage.toString());

				} else if (head.equals("SendMail")) {
					JSONObject sendmessage = getMessage.getComposeMessage(userId);
					arrayObj.add(sendmessage);
					System.out.println(userId);
					System.out.println(sendmessage);

					response.getWriter().append(sendmessage.toString());

				}

		}
	}
}
