$(document).ready(function() {
	outils.ajaxRequest(outils.url+'/societes', function(json){
		var societes=json;
		for(var i=0;i<societes.length;i++){
			$('#listeSociete').append(
				'<li style="padding-bottom: 5px;"><b><a href="societe.html?id='+societes[i].id+'">'+societes[i].nom+'</a></b> - <a href="javascript:;" onclick="supprimerSociete(\''+societes[i].id+'\');">Supprimer</a></li>'
			);
		}
	});
});

function supprimerSociete(id){
	outils.ajaxRequestPlus(outils.url+'/societes/'+id,"DELETE",undefined,function(){
		location.reload();
	});
}