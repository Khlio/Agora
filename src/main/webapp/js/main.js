window.onload = VerifCookie();

    function VerifCookie()
    {
        var idClientCookie = document.cookie['bouh'];

        if(idClientCookie ==null)
        {
        	window.location.replace("login.html");
            $('body').removeClass("hidden");
        }
        else
        {
            $('body').removeClass("hidden");
        }
    }