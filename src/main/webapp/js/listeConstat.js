$(document).ready(function(){
	var listeClients = undefined;
	var listeConstats = undefined;
	
	$.getJSON(outils.url + "/utilisateurs/" + outils.cookie + "/clients", function(json){
		listeClients=json;
		for(var i=0;i<listeClients.length;i++){
			$('#selectClients').append(
				'<option value="'+listeClients[i].id+'">'+listeClients[i].prenom+' '+listeClients[i].nom+'</option>'
			);
		}
	});
	
	$.getJSON(outils.url + "/utilisateurs/" + outils.cookie + "/constats", function(json){
		listeConstats=json;
		$.each(json, function(i, v) {
			for(var i=0;i<listeClients.length;i++){
				if(v.client==listeClients[i].id){
					$("#listConstat").append("<tr><td><a href=informationConstat.html?id="+v.id+">" + v.nom +"</a></td><td><a href=informationClient.html?id="+listeClients[i].id+">" + listeClients[i].nom +"</a></td><td>" + v.date + "</td></tr>" );
					break;
				}
			}
		});
	});
	
	function rechercheClient(formulaire){
		var idClient=formulaire.elements['selectClients'].value;
		var nomClient ='';
		var prenomClient ='';
		
		$('#listConstat').html('');
		
		for(var i=0;i<listeClients.length;i++){
			if(idClient==listeClients[i].id){
				nomClient=listeClients[i].nom;
				prenomClient=listeClients[i].prenom;
				break;
			}
		}
		for(var i=0;i<listeConstats.length;i++){
			if(idClient==listeConstats[i].client){
				$("#listConstat").append("<tr><td>" + listeConstats[i].nom +"</td><td>" + nomClient +" "+ prenomClient +"</td><td>" + listeConstats[i].date + "</td></tr>" );
			}
		}
		
		return false;
	}
});