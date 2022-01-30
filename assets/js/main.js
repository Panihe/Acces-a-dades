// Métode: getCountryCovidInfo()
// Descripció métode: Obté tots el valors del pais buscat i mostra cada valor en el elements que té el id asignat
// Parámetros de entrada: No
// Parámetros de salida: No
function getCountryCovidInfo() {
    /* Recollix el pais buscat en el campp de busqueda */
    let strBusquedaPais = document.getElementById('strBusquedaPais').value;

    axios
        /* Li passe el pais buscat al enllaç de la API per a mostrar la informació */
        .get("https://covid-api.mmediagroup.fr/v1/cases?country=" + strBusquedaPais)
        .then(response => {
            console.log(response.data)

            /* Recollix el nom del pais buscat */
            let pais = response.data.All.country;
            /* Recollix els casos confirmats del pais buscat */
            let casosConfirmats = response.data.All.confirmed;
            /* Recollix els casos recuperats del pais buscat */
            let casosRecuperats = response.data.All.recovered;
            /* Recollix les morts del pais buscat */
            let morts = response.data.All.deaths;
            /* Recollix la capital del pais buscat */
            let capital = response.data.All.capital_city;
            /* Recollix el contienent del pais buscat */
            let continent = response.data.All.continent;
            /* Recollix la localització del pais buscat */
            let localitzacio = response.data.All.location;
            /* Recollix la població del pais buscat */
            let poblacio = response.data.All.population;
            /* Recollix el àrea del pais buscat */
            let area = response.data.All.sq_km_area;
            /* Recollix la esperança de vida del pais buscat */
            let esperançaDeVida = response.data.All.life_expectancy;
            /* Recollix la elevació del pais buscat */
            let elevacioEnMetres = response.data.All.elevation_in_meters;

            /* Li pase el nom, recollit abans, al element amb id pais */
            document.getElementById('pais').textContent = pais;
            /* Li pase els casos confirmats, recollit abans, al element amb id casosConfirmats */
            document.getElementById('casosConfirmats').textContent = casosConfirmats;
            /* Li pase els casos recuperats recollit abans al element amb id casosRecuperats */
            document.getElementById('casosRecuperats').textContent = casosRecuperats;
            /* Li pase les morts, recollit abans, al element amb id morts */
            document.getElementById('morts').textContent = morts;
            /* Li pase la capital, recollit abans, al element amb id capital */
            document.getElementById('capital').textContent = capital;
            /* Li pase el continent, recollit abans, al element amb id cotinent */
            document.getElementById('continent').textContent = continent;
            /* Li pase la localització, recollit abans, al element amb id localitzaci0*/
            document.getElementById('localitzacio').textContent = localitzacio;
            /* Li pase la població, recollit abans, al element amb id poblacio */
            document.getElementById('poblacio').textContent = poblacio;
            /* Li pase el àrea, recollit abans, més km2 al element amb id area */
            document.getElementById('area').textContent = area + " km\xB2";
            /* Li pase la esperança de vida, recollit abans, al element amb id esperança-de-vida */
            document.getElementById('esperança-de-vida').textContent = esperançaDeVida;
            /* Li pase la elevació en metres sobre el nivell del mar, recollit abans, al element amb id elevacio-en-metres */
            document.getElementById('elevacio-en-metres').textContent = elevacioEnMetres;

        })
        .catch(error => {
            console.error(error);
        });

    axios
        /* Li passe el pais buscat al enllaç de la API per a mostrar la bandera */
        .get("https://restcountries.com/v3.1/name/" + strBusquedaPais)
        .then(response => {
            console.log(response.data[0])

            /* Recollix la bandera  del pais buscat */
            let banderaPais = response.data[0].flags.png;

            /* Li pase la bandera, recollit abans, al element amb id pais */
            document.getElementById('banderaPais').src = banderaPais;

        })
        .catch(error => {
            console.error(error);
        });
}

// Métode: guardarInfo()
// Descripció métode: Guarda el valors del pais buscat en la base de dades
// Parámetros de entrada: No
// Parámetros de salida: No
function guardarInfo() {
    let nomPais = document.getElementById('pais').textContent;
    let casosConfirmats = document.getElementById('casosConfirmats').textContent;
    let casosRecuperats = document.getElementById('casosRecuperats').textContent;
    let morts = document.getElementById('morts').textContent;
    let capital = document.getElementById('capital').textContent;
    let continent = document.getElementById('continent').textContent;
    let localitzacio = document.getElementById('localitzacio').textContent;
    let poblacio = document.getElementById('poblacio').textContent;
    let area = document.getElementById('area').textContent;
    let esperançaDeVida = document.getElementById('esperança-de-vida').textContent;
    let elevacioEnMetres = document.getElementById('elevacio-en-metres').textContent;
    let banderaPais = document.getElementById('banderaPais').src;

    $.ajax({
        type: "POST", //metode POST per a enviar dades al servidor
        url: "./assets/php/index.php", // ruta del fitxer PHP del servidor
        data: { nomPais: nomPais, casosConfirmats: casosConfirmats, casosRecuperats: casosRecuperats, morts: morts, capital: capital, continent: continent, localitzacio: localitzacio, poblacio: poblacio, area: area, esperançaDeVida: esperançaDeVida, elevacioEnMetres: elevacioEnMetres, banderaPais: banderaPais }, // dades a enviar (p.ex. {valor:valor, nom:nom})
        success: function (response) { //resultat del PHP del servidor
            alert(response);
        },
        error: function () {
            alert('Error');
        }
    });
}