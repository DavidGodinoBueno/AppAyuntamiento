/**
 * 
 */

window.addEventListener("load", function() {
   var input = document.querySelector("input");
   var textarea = document.querySelector("textarea");

   var expReg = {
        Titulo: /^[À-ÿa-zA-Z0-9\s]{10,80}$/,
        Descripcion: /^.{20,500}$/
   }

   var validarCampos = (v) => {
       switch(v.target.id) {
           case "textoTitulo":
             checkCampos(v.target, "Titulo", expReg.Titulo, "Titulo");
           break;
           case "textoDescripcion":
             checkCampos(v.target, "Descripcion", expReg.Descripcion, "Descripcion");
           break;
       }
   }

   var checkCampos = (elid, campo, exp, elerror) => {
         if(!elid.value.match(exp)) {
              document.getElementById(`texto${campo}`).classList.add("is-invalid");
              document.getElementById(`texto${campo}`).classList.remove("is-valid");
              document.getElementById(`error${elerror}`).classList.add("mostrarErroresInputs");
         } else {
             document.getElementById(`texto${campo}`).classList.remove("is-invalid");
             document.getElementById(`texto${campo}`).classList.add("is-valid");
             document.getElementById(`error${elerror}`).classList.remove("mostrarErroresInputs");
         }
   }

   input.addEventListener("keyup", validarCampos);
   input.addEventListener("blur", validarCampos);

   textarea.addEventListener("keyup", validarCampos);
   textarea.addEventListener("blur", validarCampos);

   var btnNuevaNoticia = document.getElementById("btnNuevaNoticia");
   btnNuevaNoticia.addEventListener("click", function(n) {
       var loscampos = {
           Eltitulo: document.getElementById("textoTitulo"),
           Ladescripcion: document.getElementById("textoDescripcion")
       }
       if(!(loscampos.Eltitulo.value.match(expReg.Titulo) 
          && loscampos.Ladescripcion.value.match(expReg.Descripcion))) {
            n.preventDefault();
       }
   });
});