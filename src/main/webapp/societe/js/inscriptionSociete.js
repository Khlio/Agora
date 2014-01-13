function valider(formulaire){
	var siret = formulaire.elements['inputSiret'].value;
	
	var siretValide = new RegExp("^[0-9]{3}( |\-){1}[0-9]{3}( |\-){1}[0-9]{3} [0-9]{5}$","g");
	
	if(!siretValide.test(siret)){
		alert('Le numero de siret est invalide');
		return false;
	}
	return true;
}