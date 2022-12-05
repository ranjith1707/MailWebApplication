<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>

<style>
#divmenu {
	margin-top: 100px;
	background-color: #AED6F1;
	height: 500px;
	width: 400px;
	padding-left: 20px;
	padding-top: 20px;
	border-radius: 10px;
}

#sub {
	margin-left: 150px;
	margin-top: 40px;
	height: 35px;
	width: 80px;
	border-radius: 10px;
}

form {
	margin-top: 40px;
}

h2 {
	text-align: center;
}

a {
	margin-left: 99px;
}

display {
	height: 35px;
	margin-left: 70px;
	text-align: center;
	padding-top: 10px;
}
</style>
</head>
<body>
	<div id="divmenu">
		<h2>SignIn</h2>
		<form action="../ComposeMail" method="post">
			<table>

				<tr>
					<td>Enter UserId:</td>
					<td><input type="email" id="from" name="userId" required>
					</td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td>To:</td>
					<td><input type="email" name="to" required></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td>Subject:</td>
					<td><input type="text" name="subject" required></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td>Message:</td>
					<td><input type="text" name="message" required></td>
				</tr>
				<tr>
					<td></td>
				</tr>

			</TABLE>
			<input type="submit" value="Send" id="sub">

		</form>
		<br> <br>

		<h2 id="display"></h2>
	</div>

	<script>
	
     
     var id = '<%=session.getAttribute("UserId")%>
		';
		document.getElementById("from").value = id;
	<%session.setAttribute("UserId", session.getAttribute("UserId"));%>
		
	</script>


</body>
</html>