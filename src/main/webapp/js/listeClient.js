/**
 * Created by Panda on 13/01/14.
 */

$('#instantSearch').bind('input',
    function()
    {
        $("#clientChoice").find('option')
            .remove()
            .end();
        /*  $(function() {
         var json = $.getJSON("C:/Users\Panda\WebstormProjects\Mobile\client.json", {
         tags: "mount rainier",
         tagmode: "any",
         format: "json"
         })*/
        var test12 ="(?=^" + document.getElementById('instantSearch').value +")(?=^[a-zA-Z])";

        var json =
            [ {
                _id: '27679b1c-4d85-4ae7-99a2-28f21eafe08f',
                nom: 'Levacher',
                prenom: 'Vincent'
            },
                {
                    _id: '27679b1c-4d85-4ae7-99a2-28f21eafe08f',
                    nom: 'Simon',
                    prenom: 'Jean-Paul'
                }
            ];

        $.each(json, function(i, v) {
            if(v.nom.toLowerCase().search(test12) != -1){
                var select = document.getElementById("clientChoice");
                select.add(new Option(v.nom + " " + v.prenom, v._id));
                return;
            }
            else if(v.prenom.toLowerCase().search(test12) != -1){
                var select = document.getElementById("clientChoice");
                select.add(new Option(v.nom + " " + v.prenom, v._id));
                return;
            }
        });
    });

$('#instantSearchList').bind('input',
    function()
    {
        $("#listClient").find('tr')
            .remove()
            .end();
        /*  $(function() {

         var json = $.getJSON("C:/Users\Panda\WebstormProjects\Mobile\client.json", {
         tags: "mount rainier",
         tagmode: "any",
         format: "json"
         })*/
        var test12 ="(?=^" + document.getElementById('instantSearchList').value +")(?=^[a-zA-Z])";

        var json =
            [ {
                _id: '27679b1c-4d85-4ae7-99a2-28f21eafe08f',
                nom: 'Levacher',
                prenom: 'Vincent'
            },
                {
                    _id: '27679b1c-4d85-4ae7-99a2-28f21eafe08f',
                    nom: 'Simon',
                    prenom: 'Jean-Paul'
                }
            ];

        $.each(json, function(i, v) {
            if(v.nom.toLowerCase().search(test12) != -1){
                $("#listClient").append("<tr><td><a href='InformationClient.html?id=" + v._id + "'>" + v.nom + " " + v.prenom+"</a></td></tr>" );
                return;
            }
            else if(v.prenom.toLowerCase().search(test12) != -1){
                $("#listClient").append("<tr><td><a href='InformationClient.html?id=" + v._id + "'>" + v.nom + " " + v.prenom+"</a></td></tr>" );
                return;
            }
        });
    });

