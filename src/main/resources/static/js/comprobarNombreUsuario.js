$("body").on('keyup', '#username', comprobarNombreUsuario);


function comprobarNombreUsuario() {

	var nombreUsuario = $('#username').val();

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});

	$.ajax({
		url: "/profesor/disponibleUsername/" + nombreUsuario,
		contentType: "application/json; charset=utf-8",
		data: { "username": nombreUsuario },
		type: "POST",
		success: function(response) {

			var alerta;
			if (response == "false") {
				alerta =
					"<div class='form-group col-md-8 alert alert-success' role='alert'>" +
					"Este nombre de usuario está libre para su uso" +
					"</div>";
			} else {
				alerta =
					"<div class='form-group col-md-8 alert alert-danger' role='alert'>" +
					"Este nombre de usuario no está disponible" +
					"</div>";

			}
			$('#nombreUsuarioError').html(alerta);

		},
		error: function(xhr, status, error) {

			var alerta =
				"<div class='alert alert-danger' role='alert'>" +
				"Este nombre de usuario ya está ocupado" +
				"</div>";

			$('#nombreUsuarioError').html(alerta);
		}
	});

}