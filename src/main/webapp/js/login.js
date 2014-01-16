/**
 * Created by Panda on 14/01/14.
 */

function SignUp()
{
	if($("#SignupForm").valid())
    {
	    outils.ajaxRequestPlus(outils.url +'/connexion',"POST",$("#SignupForm").serialize(),function(donnees){
	        document.location.href= "index.html";
	        var date = new Date();
	        var minutes = 240;
	        date.setTime(date.getTime() - date.getTimezoneOffset() * 60000 + (minutes * 60 * 1000));
	        $.cookie("bouh", donnees, { expires: date });
	    });
    }
}
