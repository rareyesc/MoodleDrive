document.addEventListener('DOMContentLoaded', function () {
    console.log("El DOM ha sido completamente cargado y analizado");
    var closeButtons = document.querySelectorAll('.closeModalButton');
    closeButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            var modalId = button.getAttribute('data-modal-id');
            if (modalId) {
                $('#' + modalId).modal('hide');
                document.body.classList.remove('modal-open');
                setTimeout(function () {
                    window.location.href = "/MoodleDrive/LoginServlet";
                }, 500);
            }
        });
    });
});

if (window.history.replaceState) {
    window.history.replaceState(null, null, window.location.href);
}

function navigateToLogin() {
    $('#usuarioRegistradoModal').modal('hide');
    console.log("navigateToLogin ha sido ejecutado");
}