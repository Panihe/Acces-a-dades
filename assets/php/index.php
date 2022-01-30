<?php
// Descripció: Comproba si está establezcut el nom del pais buscat, si está estblezcut aleshores obté el valors del pais buscat, fa la conexió amb la base de dades e inserta les dades del pais buscat
if (isset($_POST["nomPais"])) {
    $nomPais = $_POST["nomPais"];
    $casosConfirmats = $_POST["casosConfirmats"];
    $casosRecuperats = $_POST["casosRecuperats"];
    $morts = $_POST["morts"];
    $capital = $_POST["capital"];
    $continent = $_POST["continent"];
    $localitzacio = $_POST["localitzacio"];
    $poblacio = $_POST["poblacio"];
    $area = $_POST["area"];
    $esperançaDeVida = $_POST["esperançaDeVida"];
    $elevacioEnMetres = $_POST["elevacioEnMetres"];
    $banderaPais = $_POST["banderaPais"];
    $servidor = "localhost";
    $usuario = "root";
    $password = "";
    $dbname = "ae7_servicis_web";
    $conexion = mysqli_connect($servidor, $usuario, $password, $dbname);
    if (!$conexion) {
        echo "Error en la conexion a MySQL: " . mysqli_connect_error();
        exit();
    }
 
    $sql = "INSERT INTO covidpaisos (Pais, Confirmats, Recuperats, Morts, Capital, Continent, Localitzacio, Poblacio, Area,Esperança_de_vida, Elevacio_en_metres, Bandera) VALUES ('" . $nomPais . "','" . $casosConfirmats . "','" . $casosRecuperats . "','" . $morts . "','" . $capital . "','" . $continent . "','" . $localitzacio . "','" . $poblacio . "','" . $area . "','" . $esperançaDeVida . "','" . $elevacioEnMetres . "','" . $banderaPais . "')";
    if (mysqli_query($conexion, $sql)) {
        echo "Registre insertat correctament. Amb el següents valors: \nNom --> ".$nomPais."\nCasos confirmats --> ".$casosConfirmats."\nCasos Recuperats --> ".$casosRecuperats."\nMorts --> ".$morts."\nCapital --> ".$capital."\nContinent --> ".$continent."\nLocalització --> ".$localitzacio."\nPoblació --> ".$poblacio."\nÀrea --> ".$area."\nEsperança de vida --> ".$esperançaDeVida."\nElevació en metres --> ".$elevacioEnMetres."\nBandera País --> ".$banderaPais."";
        
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($conexion);
    }
}
