<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Accueil</title>
</head>
<body>

	<table style="width: 100%; text-align: center">
		<tr>
			<td>
				<form action="/fr.epsi.myEpsi/ConnexionServlet" method="post">
					Email :<br> <input type="text" name="email" required> <br>
					Password :<br> <input type="password" name="password" required><br>
					<input type="submit" value="Connexion">
				</form>
			</td>
			<td>
				<form action="/fr.epsi.myEpsi/InscriptionServlet" method="post">
					Email :<br> <input type="text" name="email" required> <br>
					Password :<br> <input type="password" name="password" required><br>
					Confirmation Password :<br> <input type="password" name="confirmationPassword" required><br>
					<input type="submit" value="Enregistrer">
				</form>
			</td>
		</tr>
	</table>


</body>
</html>