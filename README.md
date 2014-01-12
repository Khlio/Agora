Agora
=====

Web application Bootstrap/RESTful Java pour la gestion de création de constats d'huissiers de justice.

Les différents services disponibles :
GET :
-	/societes : Liste des sociétés
-	/societes/<id_société> : Affiche la société
-	/societes/<id_société>/utilisateurs : Affiche les utilisateurs de la société
-	/utilisateurs/<id_utilisateur> : Affiche l’utilisateur
-	/utilisateurs/<id_utilisateur>/clients : Affiche les clients de la société de l’utilisateur
-	/utilisateurs/<id_utilisateur>/clients/<id_client> : Affiche le client
-	/utilisateurs/<id_utilisateur>/constats : Affiche les constats de l’utilisateur
-	/utilisateurs/<id_utilsiateur>/constats/clients/<id_client> : Affiche les constats du client
-	/utilisateurs/<id_utilisateur>/constats/<id_constat> : Affiche le constat

POST :
-	/societes : Création d’une société
-	/connexion : Connexion d’un utilisateur
-	/deconnexion : Déconnexion d’un utilisateur
-	/utilisateurs/<id_utilisateur>/constats : Création d’un constat

PUT :
-	/societes/<id_société> : Ajoute un utilisateur à la société
-	/utilisateurs/<id_utilisateur> : Modifie l’utilisateur
-	/utilisateurs/<id_utilisateur>/clients : Ajoute un client à la société de l’utilisateur
-	/utilisateurs/<id_utilisateur>/clients/<id_client> : Modifie le client
-	/utilisateurs/<id_utilisateur>/constats/<id_constat> : Modifie le constat

DELETE :
-	/societes/<id_société> : Supprime la société
-	/utilisateurs/<id_utilisateur> : Supprime l’utilisateur
-	/utilisateurs/<id_utilisateur>/clients/<id_client> : Supprime le client
-	/utilisateurs/<id_utilisateur>/constats/<id_constat> : Supprime le constat
