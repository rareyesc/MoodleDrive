document.addEventListener('DOMContentLoaded', function () {
    var emailNoExiste = document.getElementById('emailNoExiste').value;
    var envioCorreo = document.getElementById('envioCorreo').value;

    if (emailNoExiste == 'true') {
        $('#modalEmailNoExiste').modal('show');
    }

    if (envioCorreo == 'true') {
            $('#modalEnvioCorreo').modal('show');
            $('#modalEnvioCorreo').on('hidden.bs.modal', function () {
                window.location.href = "/login/user";
            });
        }
});