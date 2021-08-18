<%@include file="./fragments/header.jsp" %>

<main>

	<section id="inscription">
		<form class="grid-2" action="<%=request.getContextPath()%>/ServletInscription" method="POST">				
					
			<div class="grid-2">				
				<label for="pseuso">Pseudo :<span style="color: darkred;">*</span></label>				
				<input type="text" id="pseudo" name="pseudo" placeholder="votre pseudo ?" required width="100%">		   		
		    </div>		           
				
			<div class="grid-2">    
			    <label for="nom">Nom :<span style="color: darkred;">*</span></label>
			    <input type="text" id="nom" name="nom" placeholder="votre nom ?" required>	    
		    </div>
				 
		    <div class="grid-2">
			    <label for="prenom">Prenom :<span style="color: darkred;">*</span></label>
			    <input type="text" id="prenom" name="prenom" placeholder="votre prï¿½nom ?" required>	    
			</div>    
		
			<div class="grid-2">    
			    <label for="email">Email : <span style="color: darkred;">*</span></label>	    	    
		        <input type="email" name="email" id="email" placeholder="votre email ?" size="15" pattern="^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$"; required>	        
	        </div>
	          
	        <div class="grid-2">
		        <label for="phone">Téléphone :</label>
		        <input type="phone" name="telephone" id="telephone" placeholder="votre telephone ?" size="15">
			</div>	
		
			<div class="grid-2">
				<label for="rue">Rue :<span style="color: darkred;">*</span></label>
				<input type="text" name="rue" id="rue" placeholder="votre rue ?">
			</div>
				
			<div class="grid-2">
				<label for="cp">Code postal :<span style="color: darkred;">*</span></label>
				<input type="text" name="codepostal" id="codepostal" placeholder="votre cp ?">
			</div>	
		
			<div class="grid-2">	
				<label for="ville">Ville :<span style="color: darkred;">*</span></label>
				<input type="text" name="ville" id="ville" placeholder="votre ville ?">		
			</div>
			
			<div class="grid-2">
				<label for="password">Mot de passe :<span style="color: darkred;">*</span></label>				
				<input type="password" name="password" id="password" size="30" minlength="6" required>	
			</div>	
		
			<div class="grid-2">
		        <label for="passwordbis">Répéter le mot de passe :<span style="color: darkred;">*</span></label>
		        <input type="password" name="passwordbis" id="passwordbis" size="30" minlength="6" required>
			</div>   
		   
		    <div class="bt-inscription">
		        <button type="submit" value="ok" id="bt-inscription1" class="bt">Créer</button>  
			</div>
			<div class="bt-inscription">
				<a href="${pageContext.request.contextPath}/ServletConnection" id="bt-inscription2" class="bt">Annuler</a>
			</div>   
		</form>
	</section>
		
		<div id="messageErreur">
			<c:if test="${!empty requestScope.ListeMessageErreur }">					
				<ul>			
					<c:forEach var="msg" items="${requestScope.ListeMessageErreur }">
						<li>${msg }</li>					
					</c:forEach>
				</ul>
			</c:if>
		</div>

</main>

</body>
</html>