<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>messages</title>
<style type="text/css">
#main {
	display: flex;
	background-colur: gray;
	margin-left: 280px;
	margin-top: 80px;
	border-radius: 2px;
}

#heading {
	height: 500px;
	width: 250px;
	border: solid;
	border-width: 1px;
	border-colur: black;
	background-color: #F5CBA7;
}

#messageHead {
	height: 500px;
	width: 400px;
	border: solid;
	border-width: 1px;
	border-colur: black;
	background-color: #ECF0F1;
	color: #ECF0F1;
	overflow: scroll;
}

#mess {
	colur: blue;
	text-align: center;
}

h3 {
	color: #A04000;
	font-family: sans-serif;
	font-size: 20px;
}

h3:hover {
	color: #CD6155;
}

#innerheading {
	padding-top: 40px;
	padding-left: 10px;
	background-color: #F5CBA7;
}

#userId {
	color: black;
}

#message {
	overflow: scroll;
	text-overflow: "----";
	font-family: sans-serif;
	font-size: smaller;
}

#next {
	float: right;
}

#del {
	margin-left: 30px;
	margin-right: 0px;
}
</style>
</head>
<body>
	<%
	String userId = (String) session.getAttribute("UserId");
	%>
	<div id="main">

		<div id="heading">
			<div>
				User ID:
				<P id="userId">
					<%
					out.println(userId);
					%>
				</P>
			</div>
			<div id="innerheading">
				<div class="headmessage" onclick="compose()" value="CoposeMail">
					<h3>Compose Mail</h3>
				</div>
				<div class="headmessage" onclick="callServlet('RecivedMail')"
					value="RecivedMail">
					<h3>Inbox Mail</h3>
				</div>
				<div class="headmessage" onclick="callServlet('SendMail')"
					value="SendMail">
					<h3>Send Mail</h3>
				</div>

				<div class="headmessage">
					<h3>
						<a href="./index.html" class="logout">Logout</a>
					</h3>
				</div>
				<input type="button" id="previos" value="preview" onclick="next(-1)">
				<input type="button" id="del" value="Delete" onclick="del()">
				<input type="button" id="next" onclick="next(1)" value="next">

				<%
				session.setAttribute("UserId", userId);
				%>
			</div>
		</div>
		<div id="messageHead">
			<h1 id="mess">Messages</h1>
			from :
			<p id="from"></p>
			to:
			<p id="to"></p>
			Subject :
			<p id="subject"></p>
			message :
			<div id="message"></div>

		</div>
	</div>
	<SCRIPT TYPE="text/javascript">
            // Submits form
      
          var  d= callServlet();
            var index=0;
            var allMessages;
            var currentHead="";
           
            function callServlet(data) {
            	index=0;
            	currentHead=data;
            	 let col=document.getElementById('messageHead');
            	allMessages="";
            	document.getElementById('to').innerText ="";
            	document.getElementById('message').innerText ="";
            	document.getElementById('subject').innerText ="";
            	document.getElementById('from').innerText ="";
            	var head = data;
            	var xhr = new XMLHttpRequest();
            	var input = "headmessage="+head;
            	xhr.open("POST", "./MessageRetrival", true);
            	xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
            	xhr.send(input);
            	xhr.onreadystatechange = function(){
            		if(this.readyState == 4 && this.status == 200) {
            			var response = JSON.parse(this.responseText);
            			const myJSON = JSON.stringify(response);
            			 allMessages=myJSON.split(",");
            				console.log(allMessages);
            				next(1);
            				if(allMessages[0]==="{}"){
            					document.getElementById('subject').style.color ='#060100';
            					document.getElementById('subject').innerText ="Empty";
            					console.log("Empty");
            				}
            				else{
            					col.style.color ='#060100';
            				}
            				
            		}
            	}
            	
            }
             
            var messageId=0;
            
            function del(){
            	var head = messageId+","+currentHead;
            	console.log(head);
            	var xhr = new XMLHttpRequest();
            	var input ="delmessage="+head;
            	xhr.open("POST", "./MessageRetrival", true);
            	xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
            	xhr.send(input);
            	xhr.onreadystatechange = function(){
            		
            	}
            }
            
            
            function next(position){
            	index+=position;
            	if(index>allMessages.length){
            		index=allMessages.length;
            	}
            	if(index<0){
            		index=1;
            	}
            	var ms=allMessages[index-1].split("-");
            	console.log("Message"+ms);
                 var getId=ms[0].split(":");
                 var id=getId[0].split("\"");
                 messageId=id[1];
         			for(let i=0; i<ms.length; i++){
         				
         				var display=ms[i].split(":");
         				if(display[0]=="to"){
         					document.getElementById('to').
					innerText = display[1];
							}
							if (display[0] == "message") {
								console.log(display[1]);
								var splitmessage = display[1].split("~");
								document.getElementById('message').innerText = splitmessage[0];

							}
							if (display[0] == "subject") {
								document.getElementById('subject').innerText = display[1];
							}
							if (display[0] == "from") {
								document.getElementById('from').innerText = display[1];
							}

						}
					}
            
            
           function compose(){
            	window.open("JSPFiles/ComposeMail.jsp", "_blank", "scrollbars=1,resizable=1,height=300,width=450",);
            }
           
           function logout(){
        	   
           }
           
				</SCRIPT>
</body>
</html>