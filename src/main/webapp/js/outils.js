var outils = {
	url: 'rest',
    cookie: $.cookie("bouh"),
    ajaxRequest: function(url, callback) {
        $.ajax({
            type: "GET",
            dataType: 'json',
            url: url,
            success: function(donnees) {
                if (callback != undefined) {
                    callback(donnees);
            	}
            }
        });
    },
    ajaxRequestErreur: function(url, callback, callbackErreur) {
        $.ajax({
            type: "GET",
            dataType: 'json',
            url: url,
            success: function(donnees) {
                if (callback != undefined) {
                    callback(donnees);
            	}
            },
        	error: function(erreur) {
        		if (callbackErreur != undefined) {
        			callbackErreur(erreur);
            	}
        	}
        });
    },
	ajaxRequestPlus: function(url, typeRequest, datas, callback) {
		if(datas==undefined) datas = {};
		$.ajax({
            type: typeRequest,
            url: url,
			data: datas,
            success: function(donnees) {
                if (callback != undefined) {
                    callback(donnees);
            	}
            },
			error: function(erreur) {
				var dialogue = $( "#dialog-message" );
				dialogue.removeClass("fade");
				dialogue.empty();
				dialogue.append(erreur.responseText);
				dialogue.dialog({
                    modal: true,
                    title: erreur.statusText,
                    buttons: {

                        Ok: function() {
                            $( this ).dialog( "close" );
                        }
                    }
                });
			}
        });
	},
	
	getUrlVars: function(){
		var vars = [], hash;
		var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
		for(var i = 0; i < hashes.length; i++)
		{
			hash = hashes[i].split('=');
			vars.push(hash[0]);
			vars[hash[0]] = hash[1];
		}
		return vars;
	}
	
};
