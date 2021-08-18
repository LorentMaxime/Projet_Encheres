<%@include file="./fragments/header.jsp" %>

	<main>
			
		<form action="<%=request.getContextPath()%>/ServletDetailVente" method="post">
		<input type="text" value ="${article.getNoArticle()}" name="id" hidden>	
		<!-- <input type="text" value ="${profilConnecte.getNoUtilisateur()}" name="idUtilisateur" hidden> -->	
				<table>
					<tr>
						<td><strong>${article.getNomArticle()}</strong></td>
					</tr>
					<tr>
						<td>Description: </td>
						<td><c:out value="${article.getDescription()}" /></td>
					</tr>
					<tr>
						<td>Catégorie : </td>
						<td><c:out value="${article.getCategorie().getLibelle()}" /></td>
					</tr>
					<tr>
						<td>Meilleur offre: </td>
						<td><c:out value="${article.getEnchere().montantEnchere} par : ${article.getEnchere().getUtilisateurId().getPseudo() }" /></td>
					</tr>
					<tr>
						<td>Mise à prix: </td>
						<!-- <td><c:out value="${article.getPrixInitial()}" /></td> -->
						<td><c:out value="${not empty tata? tata : article.getPrixInitial()}" /></td>
					</tr>
					<tr>
						<td>Fin de l'enchère : </td>
						<td><c:out value="${article.getFinDebutEncheres()}" /></td>
					</tr>
					<tr>
						<td>Retrait :</td>
						<td><c:out value="${article.getVendeur().getRue()}" /></td>
						<td><c:out value="${article.getVendeur().getCodePostal()}" /></td><td><c:out value="${article.getVendeur().getVille()}" /></td>
					</tr>
					<tr>
						<td>Vendeur :</td>
						<td><c:out value="${article.getVendeur().getPseudo()}" /></td>
					</tr>
				</table>
				
				<c:if test="${not empty connectedUser && article.getVendeur().getPseudo() != connectedUser.getPseudo()}">
				<label for="enchere">Ma proposition:</label>
				<input type="number" name="enchere" id="enchere">
				<button type="submit">Enchérir</button><label for="enchere"> Mes crédits : ${connectedUser.getCredit()}</label>
				</c:if>
		</form>
		
		
		<div id="messageErreur">
			<c:if test="${!empty requestScope.listeErreur }">
			<br>		
				<ul>			
					<c:forEach var="msg" items="${requestScope.listeErreur }">
						<li>${msg }</li>					
					</c:forEach>
				</ul>
			</c:if>
		</div>
		<a href="${pageContext.request.contextPath}/ServletPageAccueil">Retour à l'accueil</a>
		
	</main>	
</body>

</html>