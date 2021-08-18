<%@include file="./fragments/header.jsp" %>

	<main>		
			<form action="" method="POST">
				<section id="champs-saisi">
					<br>	
						<div id="div1">	
							<div class="pseudo">
								<label for="pseuso">Pseudo :</label>
						        <input type="text" id="pseudo" name="pseudo" value="${profilConnecte.getPseudo() }"><br>
						    </div>		           
							<br>		
							<div class="nom">    
							    <label for="nom">Nom :</label>
							    <input type="text" id="nom" name="nom" value="${profilConnecte.getNom() }"><br>	    
						    </div>
							<br>
						</div>    
					    <div class="prenom">
						    <label for="prenom">Prenom :</label>
						    <input type="text" id="prenom" name="prenom" value="${profilConnecte.getPrenom() }"><br>	    
						</div>    
						<br>
						<div class="email">    
						    <label for="email">Email :</label>   	    
					        <input type="email" name="email" id="email" value="${profilConnecte.getEmail() }" size="25" pattern="^[\w+.-]+@\w+.\w{2,5}$"><br>	        
				        </div>
				    	<br>    
				        <div class="telephone">
					        <label for="phone">Téléphone :</label>
					        <input type="phone" name="telephone" id="telephone" value="${profilConnecte.getTelephone() }" size="15"><br>
						</div>	
						<br>
						<div class="rue">
							<label for="rue">Rue :</label>
							<input type="text" name="rue" id="rue" value="${profilConnecte.getRue() }"><br>
						</div>
						<br>		
						<div class="codePostal">
							<label for="cp">Code postal :</label>
							<input type="text" name="codepostal" id="codepostal" value="${profilConnecte.getCodePostal() }"><br>
						</div>	
						<br>
						<div class="ville">	
							<label for="ville">Ville :</label>
							<input type="text" name="ville" id="ville" value="${profilConnecte.getVille() }"><br>		
						</div>
						<br>	
						<div class="passwordActuel">
							<label for="passwordActuel">Rentrez votre mot de passe actuel :<span style="color: darkred;">*</span></label>
					        <input type="password" name="passwordActuel" id="passwordActuel" size="30"  ><br>
						</div>
						<br>
						<div class="password">
					        <label for="password">Tapez votre nouveau mot de passe :<span style="color: darkred;">*</span></label>
					        <input type="password" name="password" id="password" size="30" minlength="6" ><br>
						</div> 	
						<br>
						<div class="passwordbis">
					        <label for="passwordbis">Confirmation du mot de passe:<span style="color: darkred;">*</span></label>
					        <input type="password" name="passwordbis" id="passwordbis" size="30" minlength="6" ><br>
						</div>   
						<br>
						<div class="points">
							<label for="point">Votre Crédit : ${profilConnecte.getCredit() }</label>
							
						</div>	        	
		        </section>
		        	<br>
		        <div id="nav-utilisateur">
					<div class="bouton-enregistrer">
						<button type="submit" value="ok" class="bt">Enregistrer</button><br>
					</div>				
					
					<a href="${pageContext.request.contextPath }/supprimer?idProfil=${connectedUser.getNoUtilisateur()}" class="bt" title="Supprimer">Supprimer</a>
					
				</div>			
		    </form>
		<br>
		
		
		<p id="modif-reussie">${requestScope.messageOk }</p>	
		
			
		<c:if test="${!empty requestScope.listeMessagesErreur }">
			<ul>
				<c:forEach var="msg" items="${requestScope.listeMessagesErreur }">
					<li>${msg}</li>
				</c:forEach>
			</ul>
		</c:if>
		
		<a href="${pageContext.request.contextPath}/ServletPageAccueil" class="bt">Retour à l'accueil</a>
		            
	
	</main>

</body>
</html>