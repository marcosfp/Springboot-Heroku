

$(document).ready(function() {
	//attach autocomplete
	$("#search").autocomplete({
		minLength: 1,
		delay: 500,
		//define callback to format results
		source: function(request, response) {
			$.getJSON("/modulo/buscar", request, function(result) {
				response($.map(result, function(modulo) {
					return {
						// following property gets displayed in drop down
						label: modulo.nombreModulo,
						// following property gets entered in the textbox
						value: modulo.idModulo,
						// following property is added for our own use
						tag_url: "http://" + window.location.host + "/modulo/" + modulo.idModulo
					}
				}));
			});
		},

		select: function(event, ui) {
			window.location.href = "http://" + window.location.host + "/modulo/" + ui.item.value;
//			}
		}
	});
});