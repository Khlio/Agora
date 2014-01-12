$(document).ready(function() {
	var url = window.location.search;
	var idSociete = url.substring(url.lastIndexOf("=")+1);
	
	outils.ajaxRequest(outils.url+'/societes', function(json){
		var societes=json;
		for(var i=0;i<societes.length;i++){
			$('#inputSociete').append(
				'<option value="'+societes[i].id+'" '+(societes[i].id==idSociete?'selected':'')+'>'+societes[i].nom+'</option>'
			);
		}
	});
});

function valider(formulaire){
	
	var mail = formulaire.elements['inputEmail'].value;
	var mailVerification = formulaire.elements['inputEmailVerification'].value;
	var MDP = formulaire.elements['inputMDP'].value;
	var MDPVerification = formulaire.elements['inputMDPVerification'].value;
	var numeroTelephone = formulaire.elements['inputTel'].value;
	if(mail!=mailVerification){
		alert('Veuillez entrer la même adresse mail');
		return false;
	}
	var uneMajuscule = new RegExp("(?=(.*[A-Z]){1,})","g");
	var unChiffre = new RegExp("(?=(.*[0-9]){1,})","g");
	var unCaractereSpe = new RegExp("[^a-zA-Z1-9]{1,}","g");
	var numeroTel = new RegExp("^0[1-8][0-9]{8}$","g");
	
	if(!uneMajuscule.test(MDP)){
		alert('Votre mot de passe doit contenir au moins une majuscule');
		return false;
	}
	if(!unChiffre.test(MDP)){
		alert('Votre mot de passe doit contenir au moins un chiffre');
		return false;
	}
	if(!unCaractereSpe.test(MDP)){
		alert('Votre mot de passe doit contenir au moins un caractère spéciale');
		return false;
	}
	if(MDP.length<8){
		alert('Votre mot de passe doit contenir au moins huit caractère');
		return false;
	}
	
	if(MDP!=MDPVerification){
		alert('Veuillez entrer le même mot de passe');
		return false;
	}
	
	if(!numeroTel.test(numeroTelephone)){
		alert('Numero de téléphone non valide');
		return false;
	}
	
	outils.ajaxRequestForm(outils.url+'/societes/'+formulaire.elements['inputSociete'].value,$("#inscription").serialize(),function(donnees){});
	
	return false;
}