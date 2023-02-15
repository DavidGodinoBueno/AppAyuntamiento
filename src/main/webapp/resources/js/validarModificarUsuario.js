/**
 * 
 */

window.addEventListener("load", function() {
    var btnModificar = document.getElementById("btnModificar");
    var inputs = document.querySelectorAll("input");

    const regExp = {
        Dni: /^\d{8}[A-Z]{1}$/,
		Nombre: /^[À-ÿa-zA-Z\s]{4,20}$/,
		Email: /^([a-zA-Z0-9_\.\-]){3,12}\@(([a-zA-Z0-9\-]){4,13}\.)+(com|org|es|edu)$/,
		Clave: /^(?=.*\d)(?=.*[\u0021-\u002b\u003c-\u0040])(?=.*[A-Z])(?=.*[a-z])\S{8,16}$/,
		Direccion: /^.{8,30}$/
    }

    var validarCampos = (v) => {
        switch(v.target.id) {
            case "textoDni":
                comprobarCampos(v.target, "Dni", regExp.Dni, "Dni");
            break;
            case "textoNombre":
                comprobarCampos(v.target, "Nombre", regExp.Nombre, "Nombre");
            break;
            case "textoClave":
                comprobarCampos(v.target, "Clave", regExp.Clave, "Clave");
            break;
            case "textoEmail":
                comprobarCampos(v.target, "Email", regExp.Email, "Email");
            break;
            case "textoDireccion":
                comprobarCampos(v.target, "Direccion", regExp.Direccion, "Direccion");
            break;
        }
    }

   var comprobarCampos = (elid, elcampo, exp, error) => {
         if(!elid.value.match(exp)) {
              document.getElementById(`texto${elcampo}`).classList.add("is-invalid");
              document.getElementById(`texto${elcampo}`).classList.remove("is-valid");
              document.getElementById(`error${error}`).classList.add("mostrarErroresInputs");
         } else {
              document.getElementById(`texto${elcampo}`).classList.remove("is-invalid");
              document.getElementById(`texto${elcampo}`).classList.add("is-valid");
              document.getElementById(`error${error}`).classList.remove("mostrarErroresInputs");
         }
   }

    inputs.forEach((i) => {
        i.addEventListener("keyup", validarCampos);
        i.addEventListener("blur", validarCampos);
    });

    var campos = {
         Eldni: document.getElementById("textoDni"),
         Elnombre: document.getElementById("textoNombre"),
         Laclave: document.getElementById("textoClave"),
         Elcorreo: document.getElementById("textoEmail"),
         Ladireccion: document.getElementById("textoDireccion")
    }

    btnModificar.addEventListener("click", function(r) {
       if(!(campos.Eldni.value.match(regExp.Dni) 
       && campos.Elnombre.value.match(regExp.Nombre)
       && campos.Laclave.value.match(regExp.Clave)
       && campos.Elcorreo.value.match(regExp.Correo)
       && campos.Ladireccion.value.match(regExp.Direccion))) {
         r.preventDefault();
       }
    });

});