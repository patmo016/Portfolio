<?php
    session_start();
?>
    <html>
    <?php
    $scriptList = array('jquery-1.11.3.min.js', 'cookies.js');
    //include("restricted/header.php");
    include("restricted/validationFunctions.php");
?>
        <div id="main">
<?php




if (isset($_POST['loginSubmit'])) {
    $user = $_POST['loginUser'];
    $password=$_POST['loginPassword'];

    $conn = new mysqli('sapphire', 'mopatterson', 'patmo016', 'mopatterson_dev');
    if ($conn->connect_errno) {
    echo "Connection issues";
// Something went wrong connecting
}        
$user = $conn->real_escape_string($user);
$password = $conn->real_escape_string($password); 
$query = "SELECT * FROM Users WHERE username = '$user' AND password = SHA('$password')";

$result = $conn->query($query);

      

if ($result->num_rows === 0) {
 echo"Your user name or password were incorrect";
  header('Location: index.php');
    exit;
   
    /*name is not in the table*/
}else{
 
    $row = $result->fetch_assoc();
    $role = $row['role'];
    $_SESSION['role']= $role;
    $_SESSION['authenticatedUser'] = $user;
    
    if (isset($_SESSION['lastPage'])){
    header('Location: '.$_SESSION['lastPage']);
    exit;
    }else{
        echo " session failure";
    }
    /*name is in the system*/
}
}else{
    echo "there was an error";
}
?>
