$(document).ready
(
         function() {

         $.getJSON(outils.url + "/utilisateurs/" + outils.cookie,function( json ) {
             document.getElementById("nom").value = json.nom;
             document.getElementById("prenom").value = json.prenom;
             document.getElementById("adresse").value = json.adresse;
             document.getElementById("codePostal").value = json.codePostal;
             $("#email").html(json.email);
             document.getElementById("telephone").value = json.telephone;
         });
    }
);

function SubmitProfile()
{
	if($("#SignupForm").valid())
    {
	    outils.ajaxRequestPlus(outils.url+'/utilisateurs/' + outils.cookie,"PUT",$("#SignupForm").serialize(),function(donnees){
	        location.reload();
	    });
    }
}

