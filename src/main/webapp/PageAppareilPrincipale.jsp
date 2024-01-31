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
			<button id="boutonAjouterUnAppareil" onclick="window.location.href='PageAppareilAjouter'">+</button>
		</div>
        
		<h1>Choix de l'appareil</h1>
		
		<div id="tableauDAppareils">
			<table border="1">
			    <thead>
			        <tr>
			            <th>Nom</th>
			            <th></th>
			            <th></th>
			            <th></th>
			        </tr>
			    </thead>
			    <tbody>
        			<c:forEach var="appareil" items="${sessionScope.gestionnaireAppareil.getAppareils()}">
				            <tr>
				                <td id="nom_ligne"><a href="<c:url value="PageContactPrincipale" >
										<c:param name="appareilNomParam" value="${appareil.getNom()}"/>
										<c:param name="appareilResolutionParam" value="${appareil.getHauteur()}x${appareil.getLargeur()}"/>
									</c:url>">${appareil.getNom()}</a></td>
				                <td id="numero_ligne">(${appareil.getHauteur()}x${appareil.getLargeur()})</td>
				                <td class="btn_ligne"><a href="<c:url value="/PageAppareilModifier" >
				                	<c:param name="id" value="${appareil.id}"/></c:url>">Modifier</a></td>
				                <td class="btn_ligne"><a href="<c:url value="/PageAppareilSupprimer" >
				                	<c:param name="id" value="${appareil.id}"/></c:url>">Supprimer</a></td>
				            </tr>
			        </c:forEach>
			    </tbody>
			</table>
		</div>
</body>
</html>