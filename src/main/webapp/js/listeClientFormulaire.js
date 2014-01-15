$(document).ready
(
    function()
    {
    var recherche ="(?=^" + document.getElementById('instantSearch').value +")(?=^[a-zA-Z])";

    $.getJSON(outils.url + "/utilisateurs/" + outils.cookie + "/clients", function(json)
     {
         $.each(json, function(i, v) {
             if(v.nom.toLowerCase().search(recherche) != -1){
                 var select = document.getElementById("clientChoice");
                 select.add(new Option(v.nom + " " + v.prenom, v.id));
                 return;
             }
             else if(v.prenom.toLowerCase().search(recherche) != -1){
                 var select = document.getElementById("clientChoice");
                 select.add(new Option(v.nom + " " + v.prenom, v.id));
                 return;
             }
         });
     });
    }
);


$('#instantSearch').bind('input',
	    function()
	    {
	        $("#clientChoice").find('option')
	            .remove()
	            .end();
	        
	        $("#clientChoice").closest('.control-group').removeClass("success");

	        var recherche ="(?=^" + document.getElementById('instantSearch').value +")(?=^[a-zA-Z])";

	        $.getJSON(outils.url + "/utilisateurs/" + outils.cookie + "/clients", function(json)
	         {
	             $.each(json, function(i, v) {
	                 if(v.nom.toLowerCase().search(recherche) != -1){
	                     var select = document.getElementById("clientChoice");
	                     select.add(new Option(v.nom + " " + v.prenom, v.id));
	                     return;
	                 }
	                 else if(v.prenom.toLowerCase().search(recherche) != -1){
	                     var select = document.getElementById("clientChoice");
	                     select.add(new Option(v.nom + " " + v.prenom, v.id));
	                     return;
	                 }
	             });
	         });
	    });
