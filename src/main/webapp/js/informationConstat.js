$(document).ready(function(){
	$.getJSON(outils.url + "/utilisateurs/" + outils.cookie + "/constats/" + outils.getUrlVars()["id"], function(json)
	{
		document.getElementById("nom").value = json.nom;
		document.getElementById("adresse1").value = json.adresse1;
		document.getElementById("adresse2").value = json.adresse2;
		document.getElementById("codePostal").value = json.codePostal;
		
		$.getJSON(outils.url + "/utilisateurs/" + outils.cookie + "/clients", function(listeClients){
			for(var i=0;i<listeClients.length;i++){
				if(json.client==listeClients[i].id){
					$('#client').html(
						listeClients[i].prenom+' '+listeClients[i].nom
					);
					break;
				}
			}
		});
		
		$('#date').html(
			json.date
		);
		
		$('#geolocalisation').html(
			json.geolocalisation
		);
		
		if(json.audios.length==0){
			$('#audio').append(
				'<p class="form control">Aucun fichier audio.</p>'
			);
		}
		else{
			for(var i=0;i<json.audios.length;i++){
				$('#audio').append(
					'<p class="form control">'+json.audios[i]+'</p>'
				);
			}
		}
		
		if(json.annexes.length==0){
			$('#annexes').append(
				'<p class="form control">Aucun document.</p>'
			);
		}
		else{
			for(var i=0;i<json.annexes.length;i++){
				$('#annexes').append(
					'<p class="form control">'+json.annexes[i]+'</p>'
				);
			}
		}
	});
});

function validerConstat(formulaire){
	
	if($("#formModificationConstat").valid()){
		outils.ajaxRequestPlus(outils.url+'/utilisateurs/' + outils.cookie + '/constats/' + outils.getUrlVars()["id"],"PUT",$("#formModificationConstat").serialize(),function(donnees){
			location.reload();
		});
	}
	
	return false;
}