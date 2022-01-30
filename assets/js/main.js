function getWorldInfo() {
    let strBusquedaPais = document.getElementById('strBusquedaPais').value;
    axios
        .get("https://restcountries.com/v3.1/name/" + strBusquedaPais)
        .then(response => {
            console.log(response.data)
            let nomPais = response.data[0].name.common;
            let capitalPais = response.data[0].capital[0];
            let continentPais = response.data[0].continents[0];
            let areaPais = response.data[0].area;
            let poblacioPais = response.data[0].population;
            let banderaPais = response.data[0].flags.svg;
           

            document.getElementById('nomPais').textContent = nomPais;
            document.getElementById('capitalPais').textContent = capitalPais;
            document.getElementById('continentPais').textContent = continentPais;
            document.getElementById('areaPais').textContent = areaPais;
            document.getElementById('poblacioPais').textContent = poblacioPais;
            document.getElementById('banderaPais').src = banderaPais;
            
        })
        .catch(error => {
            console.error(error);
        });
}

function guardarInfo() {
    let nomPais = document.getElementById('nomPais').textContent;
    let capitalPais = document.getElementById('capitalPais').textContent;
    let continentPais = document.getElementById('continentPais').textContent;
    let areaPais = document.getElementById('areaPais').textContent;
    let poblacioPais = document.getElementById('poblacioPais').textContent;
    let banderaPais = document.getElementById('banderaPais').src;

    $.ajax({
        type: "POST", //metode POST per a enviar dades al servidor
        url: "./assets/php/index.php", // ruta del fitxer PHP del servidor
        data: {nomPais: nomPais, capitalPais: capitalPais, continentPais: continentPais, areaPais: areaPais, poblacioPais: poblacioPais, banderaPais: banderaPais}, // dades a enviar (p.ex. {valor:valor, nom:nom})
        success: function (response) { //resultat del PHP del servidor
            alert(response);
        },
        error: function () {
            alert('Error');
        }
    });
}