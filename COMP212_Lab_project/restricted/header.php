<?php
if (session_id() === "") {
    session_start();
}
?>
<!DOCTYPE html>
    <html>
    <head>
        <title>Classic Cinema</title>
        <link rel="stylesheet" href="style.css">
        <meta charset="utf-8">
      
<?php
$_SESSION['lastPage'] = $_SERVER['PHP_SELF'];
?>
    <?php
    foreach($scriptList as $script) {
    echo "<script src='$script'></script>"; 
    }
?>
    </head>
        
        
        <header>
    
                <h1>Classic Cinema</h1>
    <?php if (isset($_SESSION['authenticatedUser'])) { 
    $sessionName = $_SESSION['authenticatedUser'];
    $sessionRole= $_SESSION['role'];
    ?>
    
       <div id="logout" action='logout.php' method='POST'>
                  <?php echo "<p>Welcome, <span id='logoutUser'>$sessionName $sessionRole </span></p>"; ?>
                    <form id="logoutForm" action = 'logout.php' method='POST'>
                        <input type="submit" id="logoutSubmit" value="Logout">
                    </form>
                </div>
    <?php }else{?>
                <div id="login">
                    <form id="loginForm" action='login.php' method="POST">
                        <label for="loginUser">Username: </label>
                        <input type="text" name="loginUser" id="loginUser">
                        <br>
                        <label for="loginPassword">Password: </label>
                        <input type="password" name="loginPassword" id="loginPassword">
                        <br>
                        <input type="submit" id="loginSubmit" name="loginSubmit" value="Login">
                        <input type="button" onclick="location.href='register.php';" id='register' value="Register">
                    </form>
                </div>
    <?php } ?>

             

  </header>

    

    <body>

            <?php
$currentPage =basename($_SERVER['PHP_SELF']);

echo "<nav><ul>";

if($currentPage === 'index.php') {
echo "<li> Home";
}else{
echo "<li> <a href='index.php'>Home</a>";
}
?>
                <?php 
if ($currentPage === 'classic.php') {
echo "<li> Classics";
}else{
echo "<li> <a href='classic.php'>Classics</a>";
}
?>
                    <?php
if($currentPage ==='scifi.php'){
echo "<li> Science Fiction and Horror";
}else{
echo "<li> <a href='scifi.php'>Science Fiction and Horror</a>";
}
?>                        
        <?php
    if($currentPage ==='hitchcock.php'){
        echo "<li>Alfred Hitchcock";
    }else{
        echo"<li> <a href='hitchcock.php'>Alfred Hitchcock</a>";
    }
        ?>
        
     <?php   
if (isset($_SESSION['authenticatedUser'])) {
    if ($currentPage ==='checkout.php'){
        echo "<li>Checkout";
    }else{
        echo "<li> <a href='checkout.php'>Checkout</a>";
    }    
        
    if ($currentPage ==='orders.php'){
        echo "<li>Orders";
    }else{
        echo "<li> <a href='orders.php'>Orders</a>";
    }
}
else{
    echo "<p><i>Please log in to make an order</i></p>";
}
    
    
?>
    <?php

    
echo "</ul></nav>";
     ?>

