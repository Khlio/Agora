/**
 * Created by Panda on 13/01/14.
 */

$(document).ready
(
    function()
    {

        $("#listClient").find('tr')
        .remove()
        .end();

    var recherche ="(?=^" + document.getElementById('instantSearchList').value +")(?=^[a-zA-Z])";

    $.getJSON(outils.url + "/utilisateurs/" + outils.cookie + "/clients", function(json)
    {
        $.each(json, function(i, v) {
            if(v.nom.toLowerCase().search(recherche) != -1){
                $("#listClient").append("<tr><td><a href='informationClient.html?id=" + v.id + "'>" + v.nom + " " + v.prenom+"</a></td></tr>" );
                return;
            }
            else if(v.prenom.toLowerCase().search(recherche) != -1){
                $("#listClient").append("<tr><td><a href='informationClient.html?id=" + v.id + "'>" + v.nom + " " + v.prenom+"</a></td></tr>" );
                return;
            }
        });
    });
    
    }
);


$('#instantSearchList').bind('input',
    function()
    {

        $("#listClient").find('tr')
            .remove()
            .end();

        var recherche ="(?=^" + document.getElementById('instantSearchList').value +")(?=^[a-zA-Z])";

        $.getJSON(outils.url + "/utilisateurs/" + outils.cookie + "/clients", function(json)
        {
            $.each(json, function(i, v) {
                if(v.nom.toLowerCase().search(recherche) != -1){
                    $("#listClient").append("<tr><td><a href='informationClient.html?id=" + v.id + "'>" + v.nom + " " + v.prenom+"</a></td></tr>" );
                    return;
                }
                else if(v.prenom.toLowerCase().search(recherche) != -1){
                    $("#listClient").append("<tr><td><a href='informationClient.html?id=" + v.id + "'>" + v.nom + " " + v.prenom+"</a></td></tr>" );
                    return;
                }
            });
        });
});
