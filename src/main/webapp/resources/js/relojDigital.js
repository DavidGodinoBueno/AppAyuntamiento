/**
 * 
 */

function generarTiempo() {
    let tiempoactual = new Date();

    let horas = tiempoactual.getHours().toString().length < 2 ? "0" + tiempoactual.getHours() : tiempoactual.getHours();
    let minutos = tiempoactual.getMinutes().toString().length < 2 ? "0" + tiempoactual.getMinutes() : tiempoactual.getMinutes();
    let segundos = tiempoactual.getSeconds().toString().length < 2 ? "0" + tiempoactual.getSeconds() : tiempoactual.getSeconds();

    let eltiempo = `${horas}:${minutos}:${segundos}`;

    document.getElementById("reloj").innerHTML = eltiempo;
}

setInterval(() => {
    generarTiempo();
}, 1);