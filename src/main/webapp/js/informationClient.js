
$(document).ready
(
    function()
    {

        $.getJSON(outils.url + "/utilisateur/" + document.cookie["bouh"] + "/client/" + getUrlVars()["id"], function(json)
        {
            $.each(json, function(i, v) {
                document.getElementById("nom").value =v.nom;
                document.getElementById("prenom").value = v.prenom;
                document.getElementById("dateNaissance").value = v.dateDeNaisance;
                document.getElementById("lieuNaissance").value = v.lieuDeNaissance;
                document.getElementById("nationalite").value = v.nationalite;
                document.getElementById("metier").value = v.metier;
                document.getElementById("adresse1").value = v.adresse1;
                document.getElementById("adresse2").value = v.adresse2;
                document.getElementById("codePostal").value = v.codePostal;
                document.getElementById("email").value = v.email;
                document.getElementById("telPortable").value = v.telephonePortable;
                document.getElementById("telFixe").value = v.telephoneFixe;
            });
        });

   /* var json =
        [ {
            _id: '27679b1c-4d85-4ae7-99a2-28f21eafe08f',
            nom: 'Levacher',
            prenom: 'Vincent',
            email:"test@d.com",
            dateDeNaisance:"14/10/2012",
            lieuDeNaissance:"panda",
            metier:"metier",
            nationalite:"fr",
            adresse:"38 rue de la vie",
            adresse2:"appartement 22",
            codePostal:"15152",
            telephonePortable:"04512541425",
            telephoneFixe:"04512541425"
        }
        ];*/
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
    outils.ajaxRequestPlus((outils.url+'/utilisateurs/' + $.cookie('bouh') + '/clients',"PUT",$("#SignupForm").serialize(),function(donnees){
        document.location.href= "formulaire.html";
    }));
}
