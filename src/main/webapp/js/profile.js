

$(document).ready
(
          function() {

         $.getJSON(outils.url + "/utilisateur/" + document.cookie['bouh'],function( json ) {
             $.each(json, function(i, v) {
                 document.getElementById("nom").value =v.nom;
                 document.getElementById("prenom").value = v.prenom;
                 document.getElementById("adresse").value = v.adresse;
                 document.getElementById("codePostal").value = v.codePostal;
                 document.getElementById("email").value = v.email;
                 document.getElementById("telephone").value = v.telephone;
             });
             });

        /*var json =
            [ {
                _id: '27679b1c-4d85-4ae7-99a2-28f21eafe08f',
                nom: 'Levacher',
                prenom: 'Vincent',
                email:"test@d.com",
                nationalite:"fr",
                adresse:"38 rue de la vie",
                codePostal:"15152",
                telephone:"04512541425"
            }
            ];

        $.each(json, function(i, v) {
            document.getElementById("Nom").value =v.nom;
            document.getElementById("Prenom").value = v.prenom;
            document.getElementById("adresse").value = v.adresse;
            document.getElementById("codePostal").value = v.codePostal;
            document.getElementById("email").value = v.email;
            document.getElementById("telephone").value = v.telephone;
        });*/
    }
);

function SubmitClient()
{
    outils.ajaxRequestPlus((outils.url+'/utilisateurs/' + $.cookie('bouh'),"PUT",$("#SignupForm").serialize(),function(donnees){
        document.location.href= "index.html";
    }));
}

