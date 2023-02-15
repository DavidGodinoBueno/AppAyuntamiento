/**
 * 
 */

window.addEventListener("load", function() {
	var input = document.getElementById("nombre");
	
	var expReg = /^[À-ÿa-zA-Z0-9\s]{10,30}$/;
	
	var validarCampo = (v) => {
		if(v.target.id == "nombre") {
			if(!v.target.value.match(expReg)) {
				document.getElementById("nombre").classList.add("is-invalid");
				document.getElementById("nombre").classList.remove("is-valid");
				document.getElementById("errorNombre").classList.add("mostrarErroresInputs");
			} else {
				document.getElementById("nombre").classList.remove("is-invalid");
				document.getElementById("nombre").classList.add("is-valid");
				document.getElementById("errorNombre").classList.remove("mostrarErroresInputs");
			}
		}
	}
	
	input.addEventListener("keyup", validarCampo);
	input.addEventListener("blur", validarCampo);
	
	var btnAltaFiesta = document.getElementById("btnAltaFiesta");
	btnAltaFiesta.addEventListener("click", function(a) {
		var estenombre = document.getElementById("nombre");
		if(!estenombre.value.match(expReg)) {
			a.preventDefault();
		}
	});
});