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
	
	<main class="mw1140p center">	
		
		<c:if test="${not empty connectedUser}">

			<section id="filtres">

				<form action="${pageContext.request.contextPath}/ServletPageAccueil" method="POST">

					<div id="recherches">
						<div>Filtres :
							<input type="text" list="lstChoix" id="zoneRecherche" name="zoneRecherche" value=${not empty param.zoneRecherche?param.zoneRecherche:''}>							
							<datalist id="lstChoix">
								<c:forEach items="${listeArticle}" var="article">
									<option value="${article.getNomArticle() }">
								</c:forEach>
							</datalist>
						</div>
						<div><label for="categorie">Catégories : </label>
						<select name="categorie" id="categorie">
								<option value="0">Toutes</option>
							<c:forEach items="${lstCategories}" var="cat">
								<option value="${cat.getId()}" ${param.categorie eq cat.getId()?'selected':''}>${cat.getLibelle()}</option>
							</c:forEach>
						</select>
						</div>
					</div>	
					
					<br>
					<br>

					<nav id="selection">
					<div class="grid-2">
						<input type="radio" id="btn-achats" name="achatsVente" value="achat" ${param.achatsVente != 'vente'?'checked':''} checked hidden onclick="document.getElementById('frmAchats').disabled=false;document.getElementById('frmVentes').disabled=true;document.getElementById('chkMesVentesEnCours').checked=false;document.getElementById('chkVentesNonDebutees').checked=false;document.getElementById('chkVentesTerminees').checked=false;">
						<label for="btn-achats">Achats</label>

						<div ${param.achatsVente == 'vente'?'disabled':''} id="selection-achats">
							<div class="checkbox-filtres">
								<c:choose>
									<c:when test="${param.achatsVente != 'vente' && empty param.chkEncheresOuvertes && empty param.chkMesEncheres && empty param.chkMesEncheresEmportees}">
										<input type="checkbox" id="chkEncheresOuvertes" name="chkEncheresOuvertes" value=1 checked=true}>
									</c:when>
									<c:otherwise>
										<input type="checkbox" id="chkEncheresOuvertes" name="chkEncheresOuvertes" value=1 ${not empty param.chkEncheresOuvertes?'checked':''}>
									</c:otherwise>
								</c:choose>
								<label for="chkEncheresOuvertes">Enchères ouvertes</label>
							</div>
							<div class="checkbox-filtres">							
								<input type="checkbox" id="chkMesEncheres" name="chkMesEncheres" value=2 ${param.chkMesEncheres != null?'checked':''}>
								<label for="chkMesEncheres">Mes enchères</label>
							</div>
							<div class="checkbox-filtres">								
								<input type="checkbox" id="chkMesEncheresEmportees" name="chkMesEncheresEmportees" value=4 ${param.chkMesEncheresEmportees != null?'checked':''}>							
								<label for="chkMesEncheresEmportees">Mes enchères remportées</label>
							</div>
						</div>
						<br>
					</div>
					<div class="grid-2">	
						<input type="radio" id="btn-ventes" name="achatsVente" value="vente" ${param.achatsVente eq 'vente'?'checked':''} hidden onclick="document.getElementById('frmVentes').disabled=false;document.getElementById('frmAchats').disabled=true;document.getElementById('chkEncheresOuvertes').checked=false;document.getElementById('chkMesEncheres').checked=false;document.getElementById('chkMesEncheresEmportees').checked=false;">
						<label for="btn-ventes">Mes ventes</label>

						<div ${param.achatsVente != 'vente'?'disabled':''} id="selection-ventes">
							<div class="checkbox-filtres">
								<c:choose>
									<c:when test="${param.achatsVente == 'vente' && param.chkMesVentesEnCours == null && param.chkVentesNonDebutees == null && param.chkVentesTerminees == null}">
										<input type="checkbox" id="chkMesVentesEnCours" name="chkMesVentesEnCours" value=1 checked=true}>
									</c:when>
									<c:otherwise>
										<input type="checkbox" id="chkMesVentesEnCours" name="chkMesVentesEnCours" value=1 ${param.chkMesVentesEnCours != null?'checked':''}>
									</c:otherwise>
								</c:choose>
								<label for="chkMesVentesEnCours">Mes ventes en cours</label>
							</div>
							<div class="checkbox-filtres">
								<input type="checkbox" id="chkVentesNonDebutees" name="chkVentesNonDebutees" value=2 ${param.chkVentesNonDebutees != null?'checked':''}>
								<label for="chkVentesNonDebutees">Mes ventes non débutées</label>
							</div>
							<div class="checkbox-filtres">
								<input type="checkbox" id="chkVentesTerminees" name="chkVentesTerminees" value=4 ${param.chkVentesTerminees != null?'checked':''}>
								<label for="chkVentesTerminees">Mes ventes terminées</label>
							</div>	
						</div>
					</div>							
					</nav>						
					<input type="submit" value="Rechercher" id="bt">					
				</form>
			</section>
		</c:if>

		<h2>Liste des article en ventes</h2>

		<input type="radio" id="view-list" name="view" checked hidden>
		<input type="radio" id="view-grid" name="view" hidden>

		<section id="encheres">

		<aside>
			affichage :
			<nav>
				<label for="view-list">liste</label>
				<label for="view-grid">grille</label>
			</nav>		
		</aside>

		<c:choose>
			<c:when test="${not empty listeArticle}">
				<c:forEach items="${listeArticle}" var="article">

					<article class="objet">
						<img src="images/websiteplanet-dummy-360X280.jpeg" alt="${article.nomArticle}">
						<h3>${article.nomArticle}</h3>						
						<div>
							<div>Prix</div>
							<div><c:out value="${article.prixVente}" /></div>
							<div>Fin de l'enchère</div>
							<div><c:out value="${article.finDebutEncheres}" /></div>							
							<div>Vendeur</div>
							<div><a href="${pageContext.request.contextPath}/ServletProfil?pseudoProfil=${article.getVendeur().getPseudo()}"><c:out value="${article.getVendeur().getPseudo()}" /></a></div>
						</div>						
						<div>
							<a href="${pageContext.request.contextPath}/ServletDetailVente?id=${article.getNoArticle()}" title="${article.nomArticle}" class="bt" name="nomArticle">fiche produit</a>
						</div>
					</article>

				</c:forEach>
			</c:when>

			<c:otherwise>
				Aucun article à afficher !
			</c:otherwise>
		
		</c:choose>	
		</section>
	</main>

	<footer>
		<c:if test="${!empty requestScope.listeMessagesErreur }">
			<ul>
				<c:forEach var="msg" items="${requestScope.listeMessagesErreur }">
					<li>${msg}</li>
				</c:forEach>
			</ul>
		</c:if>
	</footer>

</body>
</html>