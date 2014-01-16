$(document).ready
(
    function()
    {
		var listeClients=undefined;
        $("#listClient").find('tr')
        .remove()
        .end();
		
		$.getJSON(outils.url + "/utilisateurs/" + outils.cookie + "/clients", function(json)
		{
			listeClients=json;
			$.each(json, function(i, v) {
				$("#listClient").append("<tr><td><a href='informationClient.html?id=" + v.id + "'>" + v.nom + " " + v.prenom+"</a></td></tr>" );
			});
		});
		
		$('#instantSearchList').bind('input',
			function()
			{
				$("#listClient").find('tr')
					.remove()
					.end();

				var recherche = document.getElementById('instantSearchList').value;
				
				$.each(listeClients, function(i, v) {
					var fullName = v.nom.toLowerCase() + " " + v.prenom.toLowerCase();
					if(fullName.indexOf(recherche.toLowerCase())!=-1){
						$("#listClient").append("<tr><td><a href='informationClient.html?id=" + v.id + "'>" + v.nom + " " + v.prenom+"</a></td></tr>" );
					}
				});
			}
		);    
    }
);