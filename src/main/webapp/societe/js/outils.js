var outils = {
	url:'../rest',
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
	}
};
