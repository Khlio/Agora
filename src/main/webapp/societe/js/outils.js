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
			data: datas
        }).done( function(donnees) {
            if (callback != undefined) {
                callback(donnees);
        	}
        }).fail (function(erreur) {
            $( "#dialog-message" ).dialog({
                modal: true,
                buttons: {
                    Ok: function() {
                        $( this ).dialog( "close" );
                    }
                }
            });
		});
	}
};
