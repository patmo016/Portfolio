<?php
if (session_id() === "") {
    session_start();
}
?>

<?php

function addReviewForm($xmlFileName) {
    if (isset($_SESSION['authenticatedUser'])) {
        echo "<form action='addReview.php' method='POST'>";
        echo " <input type='hidden' name='xmlFileName' value='$xmlFileName'>";
        echo " Rate film: ";
        echo "<select name='rating'><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select>  ";
        echo "<input type='submit' name='reviewSubmit' value='submit'>";
        echo"<p></p>";
        
        // Rest of the form goes in here
       echo "</form>";
    }
}

?>

<?php
    
if(isset($_POST['reviewSubmit'])){
    $xmlfile = $_POST['xmlFileName'];
    $user= $_SESSION['authenticatedUser'];
    $rating= $_POST['rating'];
    
    $reviews = simplexml_load_file($xmlfile);
    $review = $reviews->addChild('review');
    $review->addChild('user', $user);
    $review->addChild('rating', $rating);
    
    $reviews->saveXML($xmlfile);
        
     if (isset($_SESSION['lastPage'])){
    header('Location: '.$_SESSION['lastPage']);
    exit;
     }

    
    }
?>