<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Gestionnaire de contacts</title>
    <link rel="stylesheet" type="text/css" href="/gestionnaire_de_contacts_web_v1/css/index.css" />
    <link rel="stylesheet" type="text/css" href="/gestionnaire_de_contacts_web_v1/css/indexContact.css" />
    <link rel="stylesheet" type="text/css" href="/gestionnaire_de_contacts_web_v1/css/indexBoutonsRonds.css" />
</head>
<body>
    <div id="container" style="height:<c:out value="${sessionScope.appareilResolutionHauteur}" />px;width:<c:out value="${sessionScope.appareilResolutionLargeur}" />px;margin:calc((100vh - <c:out value="${sessionScope.appareilResolutionHauteur}px)/2) auto;" />px;">
		<div class="topBarre">
			<div id="divSeDeconnecter"><c:out value="${sessionScope.email}" /></div>
		    <button id="boutonSeDeconnecter" onclick="window.location.href='PageUserPrincipale'">×</button>
			<div id="divChangerAppareil"><c:out value="${sessionScope.appareilNom}" /></div>
			<button id="boutonChangerAppareil" onclick="window.location.href='PageAppareilPrincipale'"><img src="img/mobile-screen-button-solid.svg" ></button>
		</div>
		
		<h1>Gestionnaire de contacts</h1>
		
	    <div id="barreRechercherAjouter">
	        <input type="text" id="champRecherche" name="champRecherche" value="<c:out value="${champRecherche}" />" />
		    <button id="boutonAjouterUnContact" onclick="window.location.href='PageContactAjouter'">+</button>
	    </div>
		
		<div id="tableauDeContacts">
			<table border="1">
			    <thead>
			        <tr>
			            <th>Nom</th>
			            <th>Numero</th>
			            <th></th>
			            <th></th>
			        </tr>
			    </thead>
			    <tbody>
        			<c:forEach var="contact" items="${sessionScope.gestionnaireContact.getContacts()}">
			            <tr>
			                <td id="nom_ligne">${contact.getNom()}</td>
			                <td id="numero_ligne">${contact.getNumero()}</td>
			                <td class="btn_ligne"><a href="<c:url value="/PageContactModifier" >
			                	<c:param name="id" value="${contact.id}"/></c:url>">Modifier</a></td>
			                <td class="btn_ligne"><a href="<c:url value="/PageContactSupprimer" >
			                	<c:param name="id" value="${contact.id}"/></c:url>">Supprimer</a></td>
			            </tr>
			        </c:forEach>
			    </tbody>
			</table>
		</div>
		

		
	</div>
</body>
<script>
	var champDeRecherche = document.getElementById("champRecherche");
	champDeRecherche.addEventListener("input", function() {
	    mettreAJourListe(champDeRecherche.value);
	});
	function mettreAJourListe(valeurRecherche) {
	    var lignes = document.querySelectorAll("#tableauDeContacts tbody tr");
	    lignes.forEach(function(ligne) {
	        var nom = ligne.querySelector("#nom_ligne").textContent.toLowerCase();
	        var numero = ligne.querySelector("#numero_ligne").textContent.toLowerCase();
	        if (nom.includes(valeurRecherche.toLowerCase()) || numero.includes(valeurRecherche.toLowerCase())) {
	            ligne.style.display = "table-row";
	        } else {
	            ligne.style.display = "none";
	        }
	    });
	}

</script>
</html>
