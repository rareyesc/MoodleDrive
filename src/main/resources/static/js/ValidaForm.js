/**
 * Este script maneja la validación del formulario y otros comportamientos de la UI relacionados.
 * Se asegura de que el formulario solo se pueda enviar cuando todos los campos estén debidamente validados,
 * y también proporciona retroalimentación visual al usuario sobre la validez de los datos ingresados.
 */
document.addEventListener("DOMContentLoaded", function () {
    // Referencias a elementos del DOM.
    const txtEmail = document.getElementById("txtEmail");
    const txtDia = document.getElementById("txtDia");
    const txtMes = document.getElementById("txtMes");
    const txtAnio = document.getElementById("txtAnio");
    const txtPass = document.getElementById("txtPass");
    const txtRePass = document.getElementById("txtRePass");
    const formulario = document.getElementById("registroForm");

    /**
     * Muestra un modal especificado por su selector.
     * @param {string} selector - El selector del modal a mostrar.  
     */
    function mostrarModal(selector) {
        $(selector).modal("show");
    }

    /**
     * Valida el campo de número de documento.
     * @returns {boolean} - Retorna true si la validación es exitosa, false en caso contrario.
     */
    function validarNumeroDocumento() {
        const nDoc = document.getElementById("txtnDoc").value;

        // Verificar si el campo está vacío
        if (nDoc === "") {
            mostrarModal("#campoVacioModal");
            return false;
        }

        // Verificar si el valor no es un número o si es menor a 10000
        if (isNaN(nDoc) || parseInt(nDoc) < 10000) {
            mostrarModal("#numeroInvalidoModal");
            return false;
        }

        // Todas las validaciones pasaron
        return true;
    }

    /**
     * Valida el tipo de documento seleccionado.
     * @returns {boolean} - Retorna true si el tipo de documento es válido, de lo contrario, false.
     */
    function validarTipoDocumento() {
        const tipoDocumento = document.getElementById("txtTDoc").value;

        // Verificar si el campo está vacío o es "Seleccionar..."
        if (tipoDocumento === "" || tipoDocumento === -1) {
            mostrarModal("#seleccionarDocumentoModal");
            return false;
        }

        // Verificar si el valor es diferente de las opciones válidas
        const opcionesValidas = ["1", "2", "3"];
        if (!opcionesValidas.includes(tipoDocumento)) {
            mostrarModal("#tipoDocumentoInvalidoModal");
            return false;
        }

        // Validación exitosa
        return true;
    }

    /**
     * Realiza varias validaciones en un campo de texto, como verificar si está vacío, contiene números o caracteres especiales.
     * @param {string} id - El ID del campo de texto.
     * @param {boolean} esObligatorio - Indica si el campo de texto es obligatorio.
     * @param {string} modalObligatorio - El ID del modal a mostrar si el campo es obligatorio y está vacío.
     * @param {string} modalInvalido - El ID del modal a mostrar si el campo contiene valores inválidos.
     * @returns {boolean} - Retorna true si el campo de texto es válido, de lo contrario, false.
     */
    function validarCampo(id, esObligatorio, modalObligatorio, modalInvalido) {
        let campo = document.getElementById(id).value;

        // Verificar si el campo está vacío y es obligatorio
        if (campo === "" && esObligatorio) {
            $(modalObligatorio).modal("show");
            return false;
        }

        // Verificar si el campo contiene números
        if (/\d/.test(campo)) {
            $(modalInvalido).modal("show");
            return false;
        }

        // Verificar si el campo contiene caracteres especiales
        const caracteresEspeciales = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/;
        if (caracteresEspeciales.test(campo)) {
            $(modalInvalido).modal("show");
            return false;
        }

        // Convertir caracteres con tilde a vocales sin tilde y ñ a n
        campo = campo
                .replace(/á/g, "a")
                .replace(/é/g, "e")
                .replace(/í/g, "i")
                .replace(/ó/g, "o")
                .replace(/ú/g, "u")
                .replace(/ñ/g, "n")
                .replace(/Ñ/g, "N");

        // Actualizar el valor del campo con los cambios
        document.getElementById(id).value = campo;

        return true;
    }

    /**
     * Valida el primer nombre ingresado.
     * @returns {boolean} - Retorna true si el primer nombre es válido, de lo contrario, false.
     */
    function validarPrimerNombre() {
        return validarCampo(
                "txtpNombre",
                true,
                "#primerNombreObligatorioModal",
                "#primerNombreInvalidoModal"
                );
    }

    /**
     * Valida el segundo nombre ingresado.
     * @returns {boolean} - Retorna true si el segundo nombre es válido, de lo contrario, false.
     */
    function validarSegundoNombre() {
        return validarCampo("txtsNombre", false, "", "#segundoNombreInvalidoModal");
    }

    /**
     * Valida el primer apellido ingresado.
     * @returns {boolean} - Retorna true si el primer apellido es válido, de lo contrario, false.
     */
    function validarPrimerApellido() {
        return validarCampo(
                "txtpApellido",
                true,
                "#primerApellidoObligatorioModal",
                "#primerApellidoInvalidoModal"
                );
    }

    /**
     * Valida el segundo apellido ingresado.
     * @returns {boolean} - Retorna true si el segundo apellido es válido, de lo contrario, false.
     */
    function validarSegundoApellido() {
        return validarCampo(
                "txtsApellido",
                false,
                "",
                "#segundoApellidoInvalidoModal"
                );
    }

    /**
     * Evento que maneja la validación de contraseñas en tiempo real mientras el usuario escribe.
     */
    txtPass.addEventListener("input", function () {
        validarContrasenia();
        validarRepetirContrasenia();
    });

    /**
     * Evento que maneja la validación de repetición de contraseñas en tiempo real mientras el usuario escribe.
     */
    txtRePass.addEventListener("input", validarRepetirContrasenia);

    /**
     * Valida la contraseña ingresada por el usuario.
     * @returns {boolean} - Retorna true si la contraseña cumple con los requisitos, false en caso contrario.
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
     * Compara las contraseñas ingresadas para asegurarse de que coincidan.
     * @returns {boolean} - Retorna true si las contraseñas coinciden, false en caso contrario.
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
     * Valida el campo de correo electrónico del formulario.
     * El correo electrónico debe cumplir con un formato específico y no estar vacío.
     * @returns {boolean} - Retorna true si el correo electrónico es válido, de lo contrario, false.
     */
    function validarEmail() {
        const email = txtEmail.value;

        // Verificar si el campo de correo electrónico está vacío
        if (!email) {
            mostrarModal("#emailVacioModal");
            return false;
        }

        // Verificar si el correo electrónico cumple con el formato específico
        const formatoValido = /^[A-Za-z0-9._%+-]+@sanmateo\.edu\.co$/;
        if (!formatoValido.test(email)) {
            mostrarModal("#emailInvalidoModal");
            return false;
        }
        // Si todas las validaciones son exitosas, retorna true
        return true;
    }

    /**
     * Valida el campo de día de nacimiento.
     * El día de nacimiento debe ser un número entre 1 y 31, inclusive.
     * @returns {boolean} - Retorna true si el día de nacimiento es válido, de lo contrario, false.
     */
    function validarDiaNacimiento() {
        const diaNacimiento = txtDia.value;
        if (!diaNacimiento || diaNacimiento === "dia") {
            mostrarModal("#seleccionarDiaModal");
            return false;
        }

        if (isNaN(diaNacimiento) || diaNacimiento < 1 || diaNacimiento > 31) {
            mostrarModal("#diaNacimientoInvalidoModal");
            return false;
        }
        return true;
    }

    /**
     * Valida el campo de mes de nacimiento.
     * El mes de nacimiento debe ser un número entre 1 y 12, inclusive.
     * @returns {boolean} - Retorna true si el mes de nacimiento es válido, de lo contrario, false.
     */
    function validarMesNacimiento() {
        const mesNacimiento = txtMes.value;
        if (!mesNacimiento || mesNacimiento === "mes") {
            mostrarModal("#seleccionarMesModal");
            return false;
        }

        if (isNaN(mesNacimiento) || mesNacimiento < 1 || mesNacimiento > 12) {
            mostrarModal("#mesNacimientoInvalidoModal");
            return false;
        }
        return true;
    }

    /**
     * Valida el campo de año de nacimiento.
     * El año de nacimiento debe ser un número entre 1950 y 2013, inclusive.
     * @returns {boolean} - Retorna true si el año de nacimiento es válido, de lo contrario, false.
     */
    function validarAnioNacimiento() {
        const anioNacimiento = txtAnio.value;
        if (!anioNacimiento || anioNacimiento === "anio") {
            mostrarModal("#seleccionarAnioModal");
            return false;
        }

        if (
                isNaN(anioNacimiento) ||
                anioNacimiento < 1950 ||
                anioNacimiento > 2013
                ) {
            mostrarModal("#anioNacimientoInvalidoModal");
            return false;
        }
        return true;
    }

    /**
     * Evento que maneja la validación del formulario al momento de su envío.
     */
    formulario.addEventListener("submit", function (event) {
        // Suponemos que todo es válido hasta que se demuestre lo contrario
        let esValido = true;

        // Lista de todas las funciones de validación
        const validaciones = [
            validarNumeroDocumento,
            validarTipoDocumento,
            validarPrimerNombre,
            validarSegundoNombre,
            validarPrimerApellido,
            validarSegundoApellido,
            validarEmail,
            validarDiaNacimiento,
            validarMesNacimiento,
            validarAnioNacimiento,
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
