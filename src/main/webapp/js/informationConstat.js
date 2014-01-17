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
					'<span class="form control">'+ json.audios[i] +'</span><a href="javascript:supprimerAudio(\'' + json.audios[i] + '\');" class="btn btn-danger btn-xs" style="margin-left: 10px;"><span class="glyphicon glyphicon-remove"></span></a>'
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
				$('#listeFichiers').append(
					'<li style="margin-top: 5px">'+json.annexes[i]+'<a href="javascript:supprimerAnnexe(\'' + json.annexes[i] + '\');" class="btn btn-danger btn-xs" style="margin-left: 10px;"><span class="glyphicon glyphicon-remove"></span></a></li>'
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

function supprimerAudio(fichier){
	supprimerMedia('audio',fichier);
}

function supprimerAnnexe(fichier){
	supprimerMedia('annexe',fichier);
}

function supprimerMedia(type,fichier){
	outils.ajaxRequestPlus(outils.url+'/utilisateurs/' + outils.cookie + '/constats/' + outils.getUrlVars()["id"]+'/'+type,"PUT",fichier,function(donnees){
		location.reload();
	});
}