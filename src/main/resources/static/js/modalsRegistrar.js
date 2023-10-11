document.addEventListener('DOMContentLoaded', function () {
    var errorDocumento = document.getElementById('errorDocumento').value;
    var errorNumeroInvalido = document.getElementById('errorNumeroInvalido').value;
    var registroExitoso = document.getElementById('registroExitoso').value;
    var usuarioExistente = document.getElementById('usuarioExistente').value;

    if (errorDocumento === 'true') {
        $('#campoVacioModal').modal('show');
    }

    if (errorNumeroInvalido === 'true') {
        $('#numeroInvalidoModal').modal('show');
    }

    if (usuarioExistente == 'true') {
        $('#usuarioExistenteModal').modal('show');
    }

    if (usuarioExistente == 'true') {
            $('#contraseniaInvalida').modal('show');
        }

    if (registroExitoso == 'true') {
        $('#usuarioRegistradoModal').modal('show');
        $('#usuarioRegistradoModal').on('hidden.bs.modal', function () {
            window.location.href = "/login/user";
        });
    }
});