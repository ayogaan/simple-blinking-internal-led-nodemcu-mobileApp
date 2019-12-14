<?php
$koneksi = mysqli_connect("localhost","arfan","nugraha","myhome");
if(mysqli_connect_errno()){
	echo "connection failed";
}else{
$query = "select * from hardware";
$result = mysqli_query($koneksi, $query);
$arrayHasil = array();
while($row=mysqli_fetch_assoc($result)){
	$arrayHasil =$row;
	echo json_encode($arrayHasil);
	}


}



?>
