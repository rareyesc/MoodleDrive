/* global txtPass, txtRePass, formulario */

/**
 * Este script se encarga de la validación del formulario de cambio de contraseña.
 * Se encarga de verificar la validez de la contraseña y su confirmación en tiempo real.
 */
document.addEventListener("DOMContentLoaded", function () {

    // Referencia al formulario en el DOM.
    const formulario = document.getElementById("changePassForm");
    /**
     * Agregar listener para el evento input en el campo de contraseña, para validarla en tiempo real.
     */
    txtPass.addEventListener("input", function () {
        validarContrasenia();
        validarRepetirContrasenia();
    });

    /**
     * Agregar listener para el evento input en el campo de repetición de contraseña, para validarla en tiempo real.
     */
    txtRePass.addEventListener("input", validarRepetirContrasenia);

    /**
     * Función para validar la contraseña introducida.
     * @returns {boolean} - Retorna true si la contraseña es válida, de lo contrario, false.
     */
    function validarContrasenia() {
        const contrasenia = txtPass.value;
        const esValida =
                /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_])(?=.{8,})/.test(
                        contrasenia
                        );

        if (!contrasenia) {
            $(txtPass).css({
                border: "",
                "background-color": ""
            });
        } else {
            const cssValido = {
                border: "2px solid green",
                "background-color": "#eaffea"
            };
            const cssInvalido = {
                border: "2px solid red",
                "background-color": "#ffe0e0"
            };
            $(txtPass).css(esValida ? cssValido : cssInvalido);
        }

        return esValida;
    }

    /**
     * Función para validar si las contraseñas introducidas coinciden.
     * @returns {boolean} - Retorna true si las contraseñas coinciden, de lo contrario, false.
     */
    function validarRepetirContrasenia() {
        const coinciden = txtPass.value === txtRePass.value;

        if (!txtRePass.value) {
            $(txtRePass).css({
                border: "",
                "background-color": ""
            });
        } else {
            const cssValido = {
                border: "2px solid green",
                "background-color": "#eaffea"
            };
            const cssInvalido = {
                border: "2px solid red",
                "background-color": "#ffe0e0"
            };
            $(txtRePass).css(coinciden ? cssValido : cssInvalido);
        }

        return coinciden;
    }

    /**
     * Listener para el evento de envío del formulario.
     * Se encarga de aplicar todas las validaciones antes de permitir el envío.
     * 
     * @param {Event} event - El objeto de evento asociado con el envío del formulario.
     */
    formulario.addEventListener("submit", function (event) {
        let esValido = true; // Suponemos que todo es válido hasta que se demuestre lo contrario

        // Lista de todas las funciones de validación
        const validaciones = [
            validarContrasenia,
            validarRepetirContrasenia
        ];

        // Revisar cada función de validación
        for (const validar of validaciones) {
            // Si alguna función de validación devuelve false, prevenir el envío del formulario
            if (!validar()) {
                event.preventDefault();
                return; // Salir del loop y del evento
            }
        }
    });
});