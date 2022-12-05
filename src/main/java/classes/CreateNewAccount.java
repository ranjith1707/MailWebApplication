package classes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import controller.UserControl;

/**
 * Servlet implementation class CreateNewAccount
 */
@WebServlet("/CreateAccount")
public class CreateNewAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateNewAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		PrintWriter out = response.getWriter();
		String name = (String) request.getParameter("Name");
		String dateOfBirth = (String) request.getParameter("DateOfBirth");
		String mobileNumber = (String) request.getParameter("phone");
		String gender = (String) request.getParameter("gender");
		String userId = (String) request.getParameter("userId");
		String password = (String) request.getParameter("password");
		String rePass = (String) request.getParameter("Repassword");
		if (password.equals(rePass)) {
			String result = new UserControl().createNewAccount(userId, password, mobileNumber, dateOfBirth, gender,
					name);
			out.print(result);

		} else {
			System.out.println("Password Missmatch");
		}
	}

}
