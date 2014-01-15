
$(document).ready
(
    function()
    {

        $.getJSON(outils.url + "/utilisateurs/" + outils.cookie + "/clients/" + getUrlVars()["id"], function(json)
        {
            document.getElementById("nom").value = json.nom;
            document.getElementById("prenom").value = json.prenom;
            $("#dateDeNaissance").val(json.dateDeNaisance);
            document.getElementById("lieuDeNaissance").value = json.lieuDeNaissance;
            document.getElementById("nationalite").value = json.nationalite;
            document.getElementById("metier").value = json.metier;
            document.getElementById("adresse1").value = json.adresse1;
            document.getElementById("adresse2").value = json.adresse2;
            document.getElementById("codePostal").value = json.codePostal;
            document.getElementById("email").value = json.email;
            document.getElementById("telephonePortable").value = json.telephonePortable;
            document.getElementById("telephoneFixe").value = json.telephoneFixe;
        });
    }
);


function getUrlVars()
{
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}


function SubmitClient()
{
	if($("#SignupForm").valid())
    {
        outils.ajaxRequestPlus(outils.url+'/utilisateurs/' + outils.cookie + '/clients',"PUT",$("#SignupForm").serialize(),function(donnees){
        document.location.href= "formulaire.html";
        });
    }
}
