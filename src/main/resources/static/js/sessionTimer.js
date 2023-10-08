// Variable para gestionar el temporizador de inactividad
let inactivityTimer;

// Constantes para definir los intervalos de tiempo
const fiveMinutes = 5 * 60 * 1000; //3 minutos en milisegundos
const twoMinutes = 2 * 60 * 1000; //2 minutos en milisegundos

// Variable para almacenar el último momento de actividad detectada
let lastActivityTime = Date.now();

/**
 * Reinicia el temporizador de inactividad y configura
 * un temporizador para mostrar el modal después de un período de inactividad.
 */
function resetTimer() {
    const inactivityDuration = Date.now() - lastActivityTime;

    clearTimeout(inactivityTimer);
    inactivityTimer = setTimeout(showModal, fiveMinutes);
}

/**
 * Detecta la actividad del usuario y reinicia el temporizador de inactividad.
 */
function detectActivity() {
    lastActivityTime = Date.now();
    resetTimer();
}

/**
 * Muestra el modal de extensión de sesión y configura
 * un temporizador para cerrar la sesión del usuario.
 */
function showModal() {
    $('#extendSession').modal('show');
    setTimeout(logoutUser, twoMinutes);
}

// Configurar los listeners para detectar la actividad del usuario
document.addEventListener("DOMContentLoaded", detectActivity);
document.addEventListener("mousemove", detectActivity);
document.addEventListener("keypress", detectActivity);

/**
 * Redirige al usuario a la página de cierre de sesión.
 */
function logoutUser() {
    window.location.href = "/MoodleDrive/LogoutServlet";
}

// Configurar el listener para el botón de extensión de sesión
document.getElementById("btnExtendSession").addEventListener("click", function () {
    // Reiniciar el temporizador y ocultar el modal
    resetTimer();
    $('#extendSession').modal('hide');
    
    // Realizar una petición para mantener la sesión activa
    fetch("/MoodleDrive/KeepAliveServlet").then(response => {
        if (response.ok) {
            console.log("Sesión extendida");
        } else {
            console.error("Error extendiendo la sesión");
        }
    });
});
