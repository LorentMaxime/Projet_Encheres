<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- ajoute la librairie JSTL à la page -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.css">

<title>Troc n' Roll</title>
</head>

<body>

	<header>
		<div id="logo"><a href="${pageContext.request.contextPath}/ServletPageAccueil"><img src="images/logoTrocNRoll.jpg" alt="logo site Troc and Roll"></a></div>

		<input type="checkbox" id="burger">
		<label id="burger-button" for="burger">Menu</label>

		<nav id="burger-content">
			
				<c:choose>
					<c:when test="${empty connectedUser}">
						<div id="lien-cnx"><a href="${pageContext.request.contextPath}/ServletConnection">S'inscrire - se connecter</a></div>
					</c:when>
					<c:otherwise>
						<ul id="liens-nav">
							<li><a href="${pageContext.request.contextPath}/ServletAjoutArticle">Vendre un article</a></li>
							<li><a href="${pageContext.request.contextPath}/ServletProfil?pseudoProfil=${connectedUser.getPseudo()}">Profil</a></li>
							<li><a href="${pageContext.request.contextPath}/ServletDeconnexion">Déconnexion</a></li>
						</ul>				
					</c:otherwise>
				</c:choose> 	
			
		</nav>

		<div id="overlay"></div>

	</header>
	
	<!--<div id="statut-cnx">
			<c:if test="${not empty connectedUser}">
				<p id="tag-connection">Bienvenue : ${connectedUser.getPseudo()}</p>
			</c:if>
	</div>-->