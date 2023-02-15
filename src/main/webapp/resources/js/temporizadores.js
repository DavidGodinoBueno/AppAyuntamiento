/**
 * 
 */

$(document).ready(function() {
    var mensaje = $("#newNoticia");
	  if(mensaje.text() == "Noticia insertada con exito.") {
		setTimeout(function() {
          $(mensaje).fadeOut(1500);
        },3000);
	}
	
	var useredit = $("#usuariomodificado");
	  if(useredit.text() == "Usuario modificado.") {
		setTimeout(function() {
          $(useredit).fadeOut(1500);
        },3000);
	}
	
	var deletenoticia = $("#delnoticia");
	  if(deletenoticia.text() == "Noticias eliminadas.") {
		setTimeout(function() {
          $(deletenoticia).fadeOut(1500);
        },3000);
	}
	
	var notedit = $("#notedit");
	  if(notedit.text() == "Noticia modificada.") {
		setTimeout(function() {
          $(notedit).fadeOut(1500);
        },3000);
	}
	
	var deleteuser = $("#deleteuser");
	  if(deleteuser.text() == "Usuario eliminado.") {
		setTimeout(function() {
          $(deleteuser).fadeOut(1500);
        },3000);
	}
	
	var altafiesta = $("#altafiesta");
	  if(altafiesta.text() == "Fiesta agregada.") {
		setTimeout(function() {
          $(altafiesta).fadeOut(1500);
        },3000);
	}
});