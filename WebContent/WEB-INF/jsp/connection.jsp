<%@include file="./fragments/header.jsp" %>

	<main>
		<section id="connection">
			<form method="POST" action="${pageContext.request.contextPath}/ServletConnection">		
				<div class="login">
					<label for="identifiant">Identifiant : </label>
					<input type="text" id="identifiant" name="identifiant" required>
				</div>
				<br>
				<div class="login">
					<label for="motdepasse">Mot de passe : </label>
					<input type="password" id="motdepasse" name="motdepasse" required>
				</div>
				<br>
				<input type="submit" value="Se connecter">
			</form>
		</section>
		<br>
		<section id="lien-connection">
			<br>
			<a href="${pageContext.request.contextPath}/ServletInscription">Créer un compte</a>
			<br>	
			<a href="${pageContext.request.contextPath}/ServletPageAccueil">Retour à l'accueil</a>
		</section>
		
		<c:if test="${!empty requestScope.listeMessagesErreur }">
			<br>
			<ul>
				<c:forEach var="msg" items="${requestScope.listeMessagesErreur }">
					<li>${msg}</li>
				</c:forEach>
			</ul>
		</c:if>
	</main>	
</body>
</html>