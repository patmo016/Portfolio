<?php
    $scriptList = array("jquery-1.11.3.min.js","showHide.js","cookies.js","cart.js","reviews.js");
    
    include("restricted/header.php");
 include("addReview.php");


   ?>

    <div id="main">
        <h2>Classic Films</h2>

        <section class="film">
            <img src="images/Gone_With_the_Wind.jpg" alt="Gone With the Wind">
            <h3>Gone With the Wind (1939)</h3>
            <p>Directed by: Victor Fleming, George Cukor, Sam Wood</p>
            <p>Starring: Clark Gable, Vivien Leigh, Leslie Howard, Olivia de Havilland, Hattie McDaniel</p>
            <p>An epic historical romance and winner of 8 Academy Awards (from 13 nominations).</p>
            <p>
                $<span class="price">13.99</span>
                <input type="button" value="Add to Cart" class="buy">
            </p>
            <?php
            addReviewForm("reviews/Gone_With_the_Wind.xml");
            ?>
                
        </section>

        <section class="film">
            <img src="images/The_Jazz_Singer.jpg" alt="The Jazz Singer">
            <h3>The Jazz Singer (1927)</h3>
            <p>Directed by: Alan Crosland</p>
            <p>Starring: Al Jolson, May McAvoy, Warner Oland, Cantor Rosenblatt</p>
            <p>The first feature length 'talkie', The Jazz Singer is a piece of cinematic history</p>
            <p>
                $<span class="price">13.99</span>
                <input type="button" value="Add to Cart" class="buy">
            </p>
            <?php
            addReviewForm("reviews/The_Jazz_Singer.xml");
            ?>
                
        </section>

        <section class="film">
            <img src="images/Metropolis.jpg" alt="Metropolis">
            <h3>Metropolis (1927)</h3>
            <p>Directed by: Fritz Lang</p>
            <p>Starring: Alfred Abel, Brigitte Helm, Gustav Fr&ouml;hlich, Rudolf Klein-Rogge</p>
            <p>A lovingly restored version of Fritz Lang's dystopian masterpiece, one of the great achievements of the silent era.</p>
            <p>
                $<span class="price">19.99</span>
                <input type="button" value="Add to Cart" class="buy">
            </p>
            <?php
            addReviewForm("reviews/Metropolis.xml");
            ?>
                
        </section>

    </div>

    <?php include ("restricted/footer.php"); ?>
