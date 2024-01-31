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
    <link rel="stylesheet" type="text/css" href="/gestionnaire_de_contacts_web_v1/css/indexUser.css" />
    <link rel="stylesheet" type="text/css" href="/gestionnaire_de_contacts_web_v1/css/indexBoutonsRonds.css" />
</head>
<body>
    <div id="container">
        <h1>Se connecter</h1>
		<form action="ControleurUserConnexion" method="post">
		    <c:if test="${not empty sessionScope.login}">
			    <div id="authentification_false" class="ligneFlex"><c:out value="${sessionScope.login}" /></div>
			    <c:remove var="sessionScope.login" scope="session" />
			</c:if>
       		<div class="containerFlex">
       			<div class="ligneFlex">
				    <label for="email" class="labelValider">Mail :</label>
			        <input type="text" class="inputValider" name="email" value="test@mail.fr" onblur="validerString(this)" />
			  	</div>
       			<div class="ligneFlex">
	       			<label for="password" class="labelValider">Mot de passe :</label>
				    <input type="password" class="inputValider" name="password" value="a" onblur="validerString(this)" />
			  	</div>
			    <div class="divBoutonValiderRetour ligneFlex">
			        <input type="submit" class="boutonValider" value="Se connecter" />
			        <input type="button" class="boutonValider" value="Créer un compte" onclick="window.location.href='PageUserAjouter'" />
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
