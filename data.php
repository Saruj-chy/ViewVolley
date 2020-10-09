<?php

//database constants
define('DB_HOST', 'localhost');
define('DB_USER', 'root');
define('DB_PASS', '');
define('DB_NAME', 'viewdata');

 
$conn = mysqli_connect(DB_HOST,DB_USER,DB_PASS,DB_NAME) or die('Unable to Connect');

//connecting to database and getting the connection object
$conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);

//Checking if any error occured while connecting
if (mysqli_connect_errno()) {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  die();
}

$r = getData($conn) ;

$data = array() ;


while($row=$r->fetch_array(MYSQLI_ASSOC) ){
  $data[] = $row ;
}



$response = array() ;
$response['data'] = array() ;
$response['data'] = $data ;

echo json_encode($response) ;


//   get group


function getData($con){
  $sql = "SELECT * FROM data WHERE 1" ;

  $r = mysqli_query($con,$sql) ;

  return $r ;
}


mysqli_close($conn) ;

?>