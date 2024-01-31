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
    <link rel="stylesheet" type="text/css" href="/gestionnaire_de_contacts_web_v1/css/indexAppareil.css" />
    <link rel="stylesheet" type="text/css" href="/gestionnaire_de_contacts_web_v1/css/indexBoutonsRonds.css" />
</head>
<body>
    <div id="container">
		<div class="topBarre">
			<div id="divSeDeconnecter"><c:out value="${sessionScope.email}" /></div>
		    <button id="boutonSeDeconnecter" onclick="window.location.href='PageUserPrincipale'">×</button>
		</div>
        
        <h1>Ajouter un appareil</h1>
		<form action="PageAppareilPrincipale" method="post">
        	<div class="containerFlex">
        		<div class="ligneFlex">
	        		<label for="nom" class="labelValider">Nom :</label>
				    <input type="text" class="inputValider" name="nom" onblur="validerString(this)" />
		        </div>
        		<div class="ligneFlex">
		            <label for="hauteur" class="labelValider">Hauteur :</label>
		            <input type="text" class="inputValider" name="hauteur" onblur="validerInt(this, 380, 630)" />
		        </div>
        		<div class="ligneFlex">
			        <label for="largeur" class="labelValider">Largeur :</label>
			        <input type="text" class="inputValider" name="largeur" onblur="validerInt(this, 380, 1200)" />
		            <input type="hidden" name="id" value="-1" />
		        </div>
			    <div class="divBoutonValiderRetour ligneFlex">
			        <input type="submit" class="boutonValider" value="Ajouter" />
			        <input type="button" class="boutonRetour" value="Retour" onclick="window.location.href='PageAppareilPrincipale'" />
			    </div>
        	</div>
		</form>
    </div>
</body>
<script>
	function validerString(inputElement) {
	    var texte = inputElement.value;
	    if (texte.trim() === "") {
	        inputElement.style.backgroundColor = "#ff4949";
	        setTimeout(function () {inputElement.style.backgroundColor = "white";}, 200);
	    }
	}
	function validerInt(inputElement, min, max) {
	    var valeur = parseInt(inputElement.value);
	    if (isNaN(valeur) || valeur < min || valeur > max) {
	        inputElement.style.backgroundColor = "#ff4949";
	        setTimeout(function () {inputElement.style.backgroundColor = "white";}, 200);
	    }
	    if (isNaN(valeur) || valeur < min) {
	        valeur = min;
	    } else if (valeur > max) {
	        valeur = max;
	    }
	    inputElement.value = valeur;
	}
</script>
</html>