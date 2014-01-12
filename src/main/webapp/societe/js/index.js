$(document).ready(function() {
	outils.ajaxRequest(outils.url+'/societes', function(json){
		var societes=json;
		for(var i=0;i<societes.length;i++){
			$('#listeSociete').append(
				'<li style="padding-bottom: 5px;"><b><a href="societe.html?id='+societes[i].id+'">'+societes[i].nom+'</a></b> - <a href="#">Supprimer</a></li>'
			);
		}
	});
});