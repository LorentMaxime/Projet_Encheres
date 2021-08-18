<%@include file="./fragments/header.jsp" %>

<main>

	<form action="<%=request.getContextPath()%>/ServletAjoutArticle" method="POST">
		
	
		<br>
		<div>
			<label for="nomArticle">Nom de l'article :</label><br>
	        <input type="text" id="nomArticle" name="nomArticle" required><br>
		    <br>
		    <label for="description">Description :<span style="color: darkred;">*</span></label><br>
		    <input type="text" id="description" name="description"required><br><br>
		    <label for="categorie">Catégories :</label><br>
                <select name="categorie" id="categorie">
                    <option value="1">Informatique</option>
                    <option value="2">Ameublement</option>
                    <option value="3">Vêtement</option>
                    <option value="4">Sport&Loisirs</option>
                </select><br>
	    <br>
	    </div>
	    <div>
		    <label for="dateDebutEncheres">Date du début des enchères :<span style="color: darkred;">*</span></label><br>
		    <input type="date" id="dateDebutEncheres" name="dateDebutEncheres" required><br>	    
		    <br>
		    <label for="dateFinEncheres">Date de la fin des enchères :<span style="color: darkred;">*</span></label><br>	    	    
	        <input type="date" name="dateFinEncheres" id="dateFinEncheres" required><br>
	        <br>
        </div>
        <div>
	        <label for="prixInitial">Prix initial de l'enchère :<span style="color: darkred;">*</span></label><br>
	        <input type="number" name="prixInitial" id="prixInitial"required><br>
			<br>
		</div>
		
	    <div>
	        <button type="submit" value="ok" class="bt">Ajouter</button><br>
	        <br>

	        <a href="${pageContext.request.contextPath}/ServletPageAccueil" class="bt">Annuler</a>

        </div> 	     
    </form>
    <form>
    	<div>
    	<fieldset>
    	<legend>Retrait</legend>
    	<label for="rue">Rue : </label>
    	<input type="text" name="rue" id="rue" value="${connectedUser.getRue()}"><br>
    	<label for="codepostal">Code Postal : </label>
    	<input type="text" name="codepostal" id="codepostal" value="${connectedUser.getCodePostal()}"><br>
    	<label for="ville">Ville : </label>
    	<input type="text" name="ville" id="ville" value="${connectedUser.getVille()}">
    	</fieldset>
    	</div>
    </form>
</main>

</body>
</html>