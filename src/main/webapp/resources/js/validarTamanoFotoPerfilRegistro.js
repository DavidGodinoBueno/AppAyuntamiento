/**
 * 
 */

window.addEventListener("load", function() {
     var btnRegistro = document.getElementById("btnRegistro");

     btnRegistro.addEventListener("click", function(r) {
	 var fotoperfil = document.getElementById("lafoto").files[0].size;
       if(fotoperfil > 1048576) {
         document.getElementById("errorFotoperfil").classList.add("mostrarErroresInputs");
         r.preventDefault();
       } else {
        document.getElementById("errorFotoperfil").classList.remove("mostrarErroresInputs");
       }
     });
});