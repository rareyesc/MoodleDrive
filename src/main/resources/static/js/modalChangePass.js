document.addEventListener('DOMContentLoaded', function () {
    var passwordChanged = document.getElementById('passwordChanged').value;

    if (passwordChanged == 'false') {
        $('#modalPasswordNoChanged').modal('show');
    }

    if (passwordChanged == 'true') {
            $('#modalPasswordChanged').modal('show');
            $('#modalPasswordChanged').on('hidden.bs.modal', function () {
                window.location.href = "/login/user";
            });
        }
});