
/* Exercici 2 - Introdueix el codi necessari per a que quan es faça clic en el botó es cride a una funció Javascript que mostre un missatge d’alerta indicant que el botó s’ha polsat. */
const boto = document.getElementById('enviar');

boto.addEventListener('click', () => mostrarAlerta());

const mostrarAlerta = () => {
    alert("El botó s'ha polsat");
}