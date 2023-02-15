/**
 * 
 */

window.addEventListener("load", function() {
	
	var verfotoGrande = (f) => {
		for(var i=0;i<document.getElementsByClassName("losidimagen").length+1;i++) {
	      switch(f.target.id) {
			 case `laimagen${i}`: mostrarImagen(`${i}`); break;
		   }
		}
	}
	 
	var mostrarImagen = (idimagen) => {
		var imagengrande = document.getElementById("imagengrande");
		var cuadroimagen = document.getElementById("cuadroimagen");
		cuadroimagen.classList.add("verimagen");
		var fuente = document.getElementById(`laimagen${idimagen}`).getAttribute("src");
		imagengrande.setAttribute("src", fuente);
	}
	
	for(var i=0;i<document.getElementsByTagName("img").length-1;i++) {
		   document.getElementsByTagName("img")[i].addEventListener("click", verfotoGrande);
	}
});