window.onload = VerifCookie();

    function VerifCookie()
    {
        var idClientCookie = $.cookie("bouh");

        if(idClientCookie == undefined || idClientCookie == null || idClientCookie == "null")
        {
           window.location.replace("login.html");
        }
        else
        {
        	outils.ajaxRequestErreur(outils.url + '/utilisateurs/' + outils.cookie, function(donnees) {
        		$('body').removeClass("hidden");
    	    }, function(erreur) {
    	    	window.location.replace("login.html");
    	    });
        }
    }
	 
function deconnexion(){
	outils.ajaxRequestPlus(outils.url+'/deconnexion/'+outils.cookie,"POST",undefined,function(donnees){
		$.cookie("bouh", null);
		window.location.replace("login.html");
	});
}