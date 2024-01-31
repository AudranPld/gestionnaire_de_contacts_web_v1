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
	    
        <h1>Ajouter un contact</h1>
		<form action="PageContactPrincipale" method="get">
        	<div class="containerFlex">
        		<div class="ligneFlex">
			        <tr>
			            <td><label for="nom" class="labelValider">Nom :</label></td>
			            <td style="text-align: right;"><input type="text" class="inputValider" name="nom" onblur="validerString(this)" /></td>
			        </tr>
			    </div>
        		<div class="ligneFlex">
			        <tr>
			            <td><label for="numero" class="labelValider">Numéro :</label></td>
			            <td style="text-align: right;"><input type="text" class="inputValider" name="numero" /></td>
			            <td><input type="hidden" name="id" value="-1" /></td>
			        </tr>
			    </div>
			    <div class="divBoutonValiderRetour ligneFlex">
			        <input type="submit" class="boutonValider" value="Ajouter" />
			        <input type="button" class="boutonRetour" value="Retour" onclick="window.location.href='PageContactPrincipale'" />
			    </div>
		</form>
    </div>
</body>
</html>