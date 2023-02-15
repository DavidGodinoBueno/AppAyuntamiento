/**
 * 
 */

window.addEventListener("load", function() {
	var btnAltaFiesta = document.getElementById("btnAltaFiesta");
	btnAltaFiesta.addEventListener("click", function(a) {
		var sizefoto = document.getElementById("lafoto").files[0].size;
		if(sizefoto > 1048576) {
			a.preventDefault();
			document.getElementById("errorFoto").classList.add("mostrarErroresInputs");
		} else {
			document.getElementById("errorFoto").classList.remove("mostrarErroresInputs");
		}
	});
});