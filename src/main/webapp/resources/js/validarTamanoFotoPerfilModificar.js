/**
 * 
 */

window.addEventListener("load", function() {
     var btnModificar = document.getElementById("btnModificar");

     btnModificar.addEventListener("click", function(r) {
	 var fotoperfil = document.getElementById("elperfil").files[0].size;
       if(fotoperfil > 1048576) {
         document.getElementById("errorFotoperfil").classList.add("mostrarErroresInputs");
         r.preventDefault();
       } else {
        document.getElementById("errorFotoperfil").classList.remove("mostrarErroresInputs");
       }
     });
});