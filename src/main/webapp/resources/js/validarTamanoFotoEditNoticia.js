/**
 * 
 */

window.addEventListener("load", function() {
	var btnEditNoticia = document.getElementById("btnEditNoticia");
	btnEditNoticia.addEventListener("click", function(n) {
		 var lafoto = document.getElementById("lafoto").files[0].size;
		if(lafoto > 1048576) {
			document.getElementById("errorFoto").classList.add("mostrarErroresInputs");
			n.preventDefault();
		} else {
			document.getElementById("errorFoto").classList.remove("mostrarErroresInputs");
		}
	});
});