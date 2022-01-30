<?php
if (isset($_POST["nomPais"])) {
    $nomPais = $_POST["nomPais"];
    $capitalPais = $_POST["capitalPais"];
    $continentePais = $_POST["continentPais"];
    $areaPais = $_POST["areaPais"];
    $poblacioPais = $_POST["poblacioPais"];
    $banderaPais = $_POST["banderaPais"];
    $servidor = "localhost";
    $usuario = "root";
    $password = "";
    $dbname = "exercicis_t4";
    $conexion = mysqli_connect($servidor, $usuario, $password, $dbname);
    if (!$conexion) {
        echo "Error en la conexion a MySQL: " . mysqli_connect_error();
        exit();
    }
    $sql = "INSERT INTO paisos (NomPais, Capital, Continent, Area, Poblacio, Bandera) VALUES ('" . $nomPais . "','" . $capitalPais . "','" . $continentePais . "','" . $areaPais . "','" . $poblacioPais . "','" . $banderaPais . "')";
    if (mysqli_query($conexion, $sql)) {
        echo "Registro insertado correctamente.\n".$nomPais."\n".$capitalPais."\n".$continentePais."\n".$areaPais."\n".$poblacioPais."\n".$banderaPais."";
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($conexion);
    }
}
