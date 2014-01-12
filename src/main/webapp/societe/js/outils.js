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
	
	ajaxRequestForm: function(url, form, callback) {
		$.ajax({
            type: "PUT",
            url: url,
			data: form,
			cache: false,
            success: function(donnees) {
                if (callback != undefined) {
                    callback(donnees);
            	}
            }
        });
	}
};