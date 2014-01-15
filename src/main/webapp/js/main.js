window.onload = VerifCookie();

    function VerifCookie()
    {
        var idClientCookie = $.cookie("bouh");

        if(idClientCookie == null)
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