/**
 * Created by Panda on 03/01/14.
 */

$(document).ready
(
    function()
    {
        if(window.matchMedia("(max-height: 600px)").matches)
        {
            $("#SignupForm").formToWizard({ submitButton: 'SaveAccount' });
        }
        
        if(navigator.geolocation) {
        	navigator.geolocation.getCurrentPosition(maPosition);
        }
    }
);

function maPosition(position) {
  var infopos = position.coords.longitude + " " + position.coords.latitude;
  $("#geolocalisation").attr("value", infopos);
}

//appel ajax pour le submit du client
function SubmitClient()
{
	if($("#SignupForm").valid())
    {
	    outils.ajaxRequestPlus(outils.url + '/utilisateurs/' + outils.cookie + '/clients',"PUT",$("#SignupForm").serialize(),function(donnees){
	        document.location.href= "formulaire.html";
	    });
    }
}

function AddNewInput()
{
  	$(".fileUpload").each(function(i,v) 
	{	
		$(this).addClass("hidden");
	});

	$("#listFileUpload").append('<div id="fileUpload" class="fileUpload">' +
							'<span class="help-block">Fichier annexe (audio/photo)</span>' + 
                          '<div class="title">Ajouter un document</div>' +
                            '<input type="file" name="uploadAnomalie" id="uploadAnomalie" class="upload uploadAnomalie" title="Choissisez un fichier" onclick="AddNewInput()"  onchange="ShowFileUpload()">' +
								'</div>');
}

function SubmitConstat(formulaire)
{
	if($("#SignupForm").valid())
    {
		formulaire.action = outils.url + '/utilisateurs/' + outils.cookie + '/constats';
		return true;
    }
	return false;
}

//permet d'afficher dynamiquement les fichier ajoutée par le file input (client description)
function ShowFileUploadClient()
{
    var test =$("#uploadClientDesc");

    var ext = test[0].files[0].name.split('.').pop().toLowerCase();

        if($.inArray(ext, ['mp3','ogg']) == -1) {
        alert('Extension du fichier invalide !');
    }
    else
    {
        var f = test[0].files[0];
        var name = f.name;

        $("#upload-path-client").empty();
        $("#upload-path-client").append("<li>"+ name +"</li>");
    }
}

//permet d'afficher dynamiquement les fichier ajoutée par le file input (document annexe)
function ShowFileUpload()
{
	var taille = parseInt($('.uploadAnomalie').length);
	var test = $('.uploadAnomalie')[taille - 2];

    for(var i=0;i < test.files.length;i++)
    {
        var ext = test.files[i].name.split('.').pop().toLowerCase();

        if($.inArray(ext, ['mp3','ogg','png']) == -1)
        {
            alert('Extension du fichier invalide !');
        }
        else
        {
            var f = test.files[i];
            var name = f.name;
            $('#upload-path').append("<li>"+ name +"</li>");
        }
    }
}
