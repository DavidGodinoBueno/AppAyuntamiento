/**
 * 
 */

 function fileValidation(){
    var fileInput = document.getElementById('lafoto');
    var filePath = fileInput.value;
    var allowedExtensions = /(.jpg|.jpeg|.png|.gif)$/i;
	   if(!allowedExtensions.exec(filePath)){
        document.getElementById("errorExtension").classList.add("mostrarErroresInputs");
        fileInput.value = '';
        return false;
    } else{
        document.getElementById("errorExtension").classList.remove("mostrarErroresInputs");
       
    }
}