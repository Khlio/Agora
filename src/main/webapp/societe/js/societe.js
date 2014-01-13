$(document).ready(function() {
	var url = window.location.search;
	var idSociete = url.substring(url.lastIndexOf("=")+1);
	
	outils.ajaxRequest(outils.url+'/societes/'+idSociete, function(json){
		var societe=json;
		$('#informationsSociete').html(
			'<h1>'+societe.nom+'</h1>'+
			'<p>Siret : '+societe.siret+'</p>'+
			'<p><a href="inscription.html?societe='+societe.id+'">Ajouter un utilisateur</a></p>'
		);
	});

	outils.ajaxRequest(outils.url+'/societes/'+idSociete+'/utilisateurs', function(json){	
		var utilisateurs = json;
		if(utilisateurs.length==0){
			$('#listeUtilisateur').append(
				'<li style="padding-bottom: 5px;"><b>Aucun utilisateur</b></li>'
			);
		}else{
			for(var i=0; i<utilisateurs.length;i++){
				$('#listeUtilisateur').append(
					'<li style="padding-bottom: 5px;"><b>'+utilisateurs[i].prenom+' '+utilisateurs[i].nom+'</b> - <a href="javascript:;" onclick="supprimerUtilisateur(\''+utilisateurs[i].id+'\');">Supprimer</a></li>'
				);
			}
		}
	});
});

function supprimerUtilisateur(id) {
	outils.ajaxRequestPlus(outils.url+'/utilisateurs/'+id,"DELETE",undefined,function(donnees){
		location.reload();
	});
}