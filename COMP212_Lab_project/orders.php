<?php
    session_start();
?>


<?php
    $scriptList = array("jquery-1.11.3.min.js",);
   
    include("restricted/header.php");
    ?>


    <div id="main">
  <?php
if (isset($_SESSION['role'])){
    
if ($_SESSION['role'] === 'admin'){

        $xml = simplexml_load_file("orders.xml");

foreach ($xml->order as $order) {
    echo "<p><b> ORDER</b></p>";
         $name = $order->delivery->name;
         echo "<p>Name: $name</p>";
        $email= $order->delivery->email;
        echo "<p>Email: $email</p>";
        $address = $order->delivery->address;
        $city= $order->delivery->city; 
        $postcode = $order->delivery->postcode;
    
    echo"<p> Address: $address, $city, $postcode </p>";

    echo "<p><b> Items: </b></p>";

 foreach($order->items->item as $item){
  
         $title = $item->title;
        echo "<p>Title: $title</p>";
        $price = $item->price;
        echo "<p>Price: $price</p>";
    }
}

}else if ($_SESSION['role'] === 'user'){
     $xml = simplexml_load_file("orders.xml");
    foreach ($xml->order as $order) {
        $username = $order->delivery->username;
        if (strcmp($username, $_SESSION['authenticatedUser'])===0){
            echo "<p><b> ORDER</b></p>";
         $name = $order->delivery->name;
         echo "<p>Name: $name</p>";
        $email= $order->delivery->email;
        echo "<p>Email: $email</p>";
        $address = $order->delivery->address;
        $city= $order->delivery->city; 
        $postcode = $order->delivery->postcode;
    
            echo"<p> Address: $address, $city, $postcode </p>";

            echo "<p><b> Items: </b></p>";
            
        foreach($order->items->item as $item){
  
         $title = $item->title;
        echo "<p>Title: $title</p>";
        $price = $item->price;
        echo "<p>Price: $price</p>";
        }
        }
    }
                    

}else{
        header('Location: index.php');
    exit;
}
}

/*$items = $xml->xpath('items');
foreach ($items as $item) {*/
   
// do something with $item        
?>
    </div>

<?php
    include ("restricted/footer.php"); 
?>