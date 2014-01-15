function valider(formulaire){
	var siret = formulaire.elements['inputSiret'].value;
	
	var siretValide = new RegExp("^[0-9]{3}( |\-){1}[0-9]{3}( |\-){1}[0-9]{3} [0-9]{5}$","g");
	
	if(!siretValide.test(siret)){
		alert('Le numero de siret est invalide');
		return false;
	}
	
	outils.ajaxRequestPlus((outils.url+'/societes'),"POST",$('#inscriptionSociete').serialize(),function(idSociete){
		document.location.href="societe.html?id="+idSociete;
	});
	
	return false;
}