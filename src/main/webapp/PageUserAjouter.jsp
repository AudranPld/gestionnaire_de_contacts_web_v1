<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Gestionnaire de Contacts</title>
    <link rel="stylesheet" type="text/css" href="/gestionnaire_de_contacts_web_v1/css/index.css" />
    <link rel="stylesheet" type="text/css" href="/gestionnaire_de_contacts_web_v1/css/indexUser.css" />
</head>
<body>
    <div id="container">
        <h1>Créer un compte</h1>
		<form action="PageUserPrincipale" method="post">
        	<div class="containerFlex">
        		<div class="ligneFlex">
				    <label for="email" class="labelValider">Mail :</label>
			        <input type="text" class="inputValider" name="email" onblur="validerString(this)" />
			    </div>
        		<div class="ligneFlex">
			        <label for="password" class="labelValider">Mot de passe :</label>
			        <input type="password" class="inputValider" name="password" onblur="validerString(this)" />
			        <input type="hidden" name="id" value="-1" />
			    </div>
			    <div class="divBoutonValiderRetour ligneFlex">
			        <input type="submit" class="boutonValider" value="Ajouter" />
			        <input type="button" class="boutonRetour" value="Retour" onclick="window.location.href='PageUserPrincipale'" />
			    </div>			    
			</div>
		</form>
    </div>
</body>
<script>
	function validerString(inputElement) {
		console.log("a");
	    var texte = inputElement.value;
	    if (texte.trim() === "") {
	        inputElement.style.backgroundColor = "#ff4949";
	        setTimeout(function () {inputElement.style.backgroundColor = "white";}, 200);
	    }
	}
</script>
</html>