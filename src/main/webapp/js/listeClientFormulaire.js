$(document).ready(function() {

	var listeClients=undefined;
	$.getJSON(outils.url + "/utilisateurs/" + outils.cookie + "/clients", function(json)
	{
		listeClients=json;
		$.each(json, function(i, v) {
			var select = document.getElementById("clientChoice");
			select.add(new Option(v.nom + " " + v.prenom, v.id));
		});
	});
	
	$('#instantSearch').bind('input',function() {
		$("#clientChoice").find('option')
			.remove()
			.end();
	        
		$("#clientChoice").closest('.control-group').removeClass("success");

		var recherche =document.getElementById('instantSearch').value;
		
		$.each(listeClients, function(i, v) {
			var fullName = v.nom.toLowerCase() + " " + v.prenom.toLowerCase();
			if(fullName.indexOf(recherche.toLowerCase())!=-1){
				var select = document.getElementById("clientChoice");
				select.add(new Option(v.nom + " " + v.prenom, v.id));
			}
		});	
	});
});