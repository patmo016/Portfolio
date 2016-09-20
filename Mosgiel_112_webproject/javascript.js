/*java script for Underwater Hockey*/
/*Molly Patterson*/
/*username= mopatterson*/
/*COMP112 Assignment 2015*/

var images = new Array("images/image1.jpg", "images/image2.jpg", "images/image3.gif", "images/images4.gif");
var index= 0;

function goRight(){
	var img = document.getElementById("myImage");
	index = index+1;
	if(index==images.length){
		index=0;
	}
	img.src = images[index];
}

function goLeft(){
	var img = document.getElementById("myImage");
	index = index-1;
	if(index < 0){
		index=images.length-1;
	}
	img.src = images[index];
}
	


function setup (){
	var leftArrow= document.getElementById("leftArrow");
	var rightArrow= document.getElementById("rightArrow");
	leftArrow.onclick= goLeft;
	rightArrow.onclick= goRight;
}

if (document.getElementById){
	window.onload= setup;
}

