/**
 * 
 */

window.addEventListener("load", function() {
    var elinput = document.querySelector("input");

    var expReg = /^[À-ÿa-zA-Z\s\.\:\"]{5,70}$/;

    var validarCampo = (v) => {
         if(v.target.id == "textoNombre") {
              if(!v.target.value.match(expReg)) {
                   document.getElementById("textoNombre").classList.add("is-invalid");
                   document.getElementById("textoNombre").classList.remove("is-valid");
                   document.getElementById("errorNombre").classList.add("mostrarErroresInputs");
              } else {
                   document.getElementById("textoNombre").classList.remove("is-invalid");
                   document.getElementById("textoNombre").classList.add("is-valid");
                   document.getElementById("errorNombre").classList.remove("mostrarErroresInputs");
              }
         }
    }

    elinput.addEventListener("keyup", validarCampo);
    elinput.addEventListener("blur", validarCampo);

    var btnAltaService = document.getElementById("btnAltaService");
    btnAltaService.addEventListener("click", function(s) {
         var elnombre = document.getElementById("textoNombre");
         if(!elnombre.value.match(expReg)) {
            s.preventDefault();
         }
    });
});