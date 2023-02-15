/**
 * 
 */

window.addEventListener("load", function() {
     var btnEditServicio = document.getElementById("btnEditServicio");
     btnEditServicio.addEventListener("click", function(s) {
        var lafoto = document.getElementById("lafoto").files[0].size;
        if(lafoto > 1048576) {
            document.getElementById("errorFoto").classList.add("mostrarErroresInputs");
            s.preventDefault();
        } else {
            document.getElementById("errorFoto").classList.remove("mostrarErroresInputs");
        }
     });
});