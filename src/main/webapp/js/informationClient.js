

$(document).ready
(
    function()
    {

    /*  $(function() {
     var json = $.getJSON("C:/Users\Panda\WebstormProjects\Mobile\client.json", {
     tags: "mount rainier",
     tagmode: "any",
     format: "json"
     })*/

    var json =
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
        ];

    $.each(json, function(i, v) {
        document.getElementById("Nom").value =v.nom;
        document.getElementById("Prenom").value = v.prenom;
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
    }
);

function SubmitClient()
{
    outils.ajaxRequestPlus((outils.url+'/utilisateurs/' + $.cookie('bouh') + '/clients',"PUT",$("#SignupForm").serialize(),function(donnees){
        document.location.href= "formulaire.html";
    }));
}
