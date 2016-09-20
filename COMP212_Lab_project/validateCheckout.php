
<?php
    $scriptList = array('jquery-1.11.3.min.js', 'cookies.js');
    include("restricted/header.php");
    include("validationFunctions.php");
?>

<div id="main">
   <?php

    $_SESSION['deliveryName'] = $_POST['deliveryName'];
    $_SESSION['deliveryEmail'] = $_POST['deliveryEmail'];
    $_SESSION['deliveryAddress1'] = $_POST['deliveryAddress1'];
    $_SESSION['deliveryAddress2'] = $_POST['deliveryAddress2'];
    $_SESSION['deliveryPostcode'] = $_POST['deliveryPostcode'];
    $_SESSION['cardNumber'] = $_POST['cardNumber'];
    $_SESSION['cardMonth'] = $_POST['cardMonth'];
    $_SESSION['cardType'] = $_POST['cardType'];
    $_SESSION['cardValidation']  = $_POST['cardValidation'];
    $_SESSION['deliveryCity'] = $_POST['deliveryCity'];

$messages = array();


if (isEmpty($_POST['deliveryName'])||isDigits($_POST['deliveryName'])){
        array_push ($messages,"<p>Enter a name</p>");
    }
 if (isEmpty($_POST['deliveryEmail'])){ 
     array_push($messages,"<p>Enter an email</p>"); 
 }else if( !isEmail($_POST['deliveryEmail'])){ 
     array_push($messages,"<p>Enter a valid Email</p>");
 } if (isEmpty($_POST["deliveryAddress1"])){
     array_push($messages,"<p>Enter a delivery address</p>");
 
 } if(isEmpty($_POST["deliveryCity"])){
     array_push($messages,"<p>Enter a delivery city</p>");
     
 } if(isEmpty($_POST["deliveryPostcode"])){
     array_push($messages, "<p>Enter a postcode</p>"); 
     
 }else if (!isDigits($_POST["deliveryPostcode"])){ 
     array_push($messages, "<p>Enter a valid postcode</p>"); 
     
 } if (isEmpty($_POST['cardNumber'])){
     array_push($messages,"<p>Enter credit card Number<p>"); 
     
 } if(isEmpty($_POST["cardValidation"])){ 
     array_push($messages,"<p>Enter CVC</p>");
     
 }else { 
     if (!checkCardNumber($_POST["cardType"], $_POST["cardNumber"])){
     array_push($messages,"<p>Credit card number is not valid for type</p>"); 
     
    } else if (!checkCardVerification($_POST["cardType"], $_POST["cardValidation"])) {
     array_push($messages,"<p>CVC does not Validate</p>"); 
         
     }else if (!checkCardDate($_POST["cardMonth"], $_POST['cardYear'])){
            array_push($messages,"<p>Credit card Dates are not valid</p>");
        }
     
       }

$messagesLength = count($messages); 
if ($messagesLength === 0){ 
    
    $orders = simplexml_load_file('orders.xml');
    $newOrder = $orders->addChild('order');
    $delivery = $newOrder->addChild('delivery');
    
    $delivery->addchild('username', $_SESSION['authenticatedUser']);
    $delivery->addChild('name', $_POST['deliveryName']);
    $delivery->addChild('email', $_POST['deliveryEmail']);
    $delivery->addChild('address', $_POST['deliveryAddress1']);
    $delivery->addChild('address', $_POST['deliveryAddress2']);
    $delivery->addChild('city', $_POST['deliveryCity']);
    $delivery->addChild('postcode', $_POST['deliveryPostcode']);
   
    
    if (isset($_COOKIE['cart'])){ 
        $cart = json_decode($_COOKIE['cart']); 
        $cartlen = count($cart); 
        echo "<table><tr><th> Title</th><th>Price</th></tr>"; 
        
        $items = $newOrder->addChild('items');
        for ($i=0; $i< $cartlen; $i++){
            $item = $items->addChild('item');
            $title= $cart[$i]->title;
            $price = $cart[$i]->price; 
            $item->addChild('title', $title);
            $item->addChild('price', $price);
            echo "<tr><td> $title</td><td> $ $price</td></tr>";
            
            
        } 
        echo "</table>";
    }
    
    
    
     $orders->saveXML('orders.xml');
    
    setcookie('cart', '', time()-3600, '/');
     unset($_COOKIE['cart']);
    
    unset($_SESSION['deliveryName']);
    unset($_SESSION['deliveryEmail']);
    unset($_SESSION['deliveryAddress1']);
    unset($_SESSION['deliveryAddress2']);
    unset($_SESSION['deliveryPostcode']);
    unset($_SESSION['cardNumber']);
    unset($_SESSION['cardMonth']);
    unset($_SESSION['cardType']);
    unset($_SESSION['cardValidation']);
    unset($_SESSION['deliveryCity']);
}else{
    foreach($messages as &$mess){ 
        echo $mess; /*for($i=0; $i
                            <$messagesLength ; $i++){ print_r $messages[$i];*/ } } ?>
            </div>
 <?php include("restricted/footer.php"); ?>

