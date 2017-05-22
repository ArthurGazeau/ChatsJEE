<%@page import="fr.epsi.myEpsi.beans.Message"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>





<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenue</title>
</head>
<body>
	<h1>
		Bienvenue
		<c:out value="${user.id}" />
	</h1>


	<p>Messages :</p>
	<%
		List<Message> messages = (List<Message>) request.getAttribute("messages");
		if (messages == null || messages.size() == 0) {
			out.print("Pas de message");
		} else {
			for (Message m : messages) {
				out.print(m.getTitle());
			}
		}
	%>
	<ul>
		<c:forEach var="message" items="${messages}">
			<c:if test="${user.getAdministrator()}">




			</c:if>
			<li>${message.titre}${message.content} ${message.author}
				${message.updateDate}</li>
		</c:forEach>
	</ul>

</body>
</html>