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
</head>
<body>
    <div id="container">
	    
        <h1>Modifier un contact</h1>
		<form action="PageUserPrincipale" method="post">
        	<div class="containerFlex">
        		<div class="ligneFlex">
			        <tr>
			            <td><label for="email" class="labelModifier">Email :</label></td>
			            <td style="text-align: right;"><input type="text" class="inputModifier" name="nom" value="${email}"/></td>
			        </tr>
		        </div>
        		<div class="ligneFlex">
			        <tr>
			            <td><label for="password" class="labelModifier">Mot de passe :</label></td>
			            <td style="text-align: right;"><input type="password" class="inputModifier" name="password" value="${password}"/></td>
			            <td><input type="hidden" name="id" value="${id}" /></td>
			        </tr>
		        </div>
			    <div class="divBoutonModifierRetour ligneFlex">
			        <input type="submit" class="boutonModifier" value="Modifier" />
			        <input type="button" class="boutonRetour" value="Retour" onclick="window.location.href='PageUserPrincipale'" />
			    </div>
			</div>
		</form>
    </div>
</body>
</html>