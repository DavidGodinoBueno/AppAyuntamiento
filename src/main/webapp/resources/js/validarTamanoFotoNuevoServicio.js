/**
 * 
 */

window.addEventListener("load", function() {
    var btnAltaService = document.getElementById("btnAltaService");
    btnAltaService.addEventListener("click", function(s) {
	    var sizefoto = document.getElementById("lafoto").files[0].size;
        if(sizefoto > 1048576) {
            document.getElementById("errorFoto").classList.add("mostrarErroresInputs"); 
            s.preventDefault();
        } else {
            document.getElementById("errorFoto").classList.remove("mostrarErroresInputs"); 
        }

    });
});