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
            $('body').removeClass("hidden");
        }
    }