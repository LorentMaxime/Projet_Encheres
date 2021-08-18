<%@include file="./fragments/header.jsp" %>

	<main>
				
					<table>
						<tr>
							<td><strong>Pseudo :</strong></td>
							<td><strong><c:out value="${profilConnecte.getPseudo()}" /></strong></td>
						</tr>
						<tr>
							<td>Nom :</td>
							<td><c:out value="${profilConnecte.getNom()}" /></td>
						</tr>
						<tr>
							<td>Prénom :</td>
							<td><c:out value="${profilConnecte.getPrenom()}" /></td>
						</tr>
						<tr>
							<td>Email :</td>
							<td><c:out value="${profilConnecte.getEmail()}" /></td>
						</tr>
						<tr>
							<td>Téléphone :</td>
							<td><c:out value="${profilConnecte.getTelephone()}" /></td>
						</tr>
						<tr>
							<td>Rue :</td>
							<td><c:out value="${profilConnecte.getRue()}" /></td>
						</tr>
						<tr>
							<td>Code Postal :</td>
							<td><c:out value="${profilConnecte.getCodePostal()}" /></td>
						</tr>
						<tr>
							<td>Code Ville :</td>
							<td><c:out value="${profilConnecte.getVille()}" /></td>
						</tr>
					</table>
					<br>
	</main>
				
	<footer>		
		<c:if test="${not empty connectedUser && profilConnecte.getPseudo() == connectedUser.getPseudo()}">
			<a href="<%=request.getContextPath()%>/ServletModificationProfil" class="bt">Modifier le profil</a>
		</c:if>
			<a href="${pageContext.request.contextPath}/ServletPageAccueil" class="bt">Retour à l'accueil</a>
	</footer>


</body>
</html>