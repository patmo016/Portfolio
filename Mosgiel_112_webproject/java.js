



var images = ["images/image1.jpg", "images/image2.jpg", "images/image3.gif", "images/images4.gif"];
var num = 4;
var current = 1;
var timer;
	
function slide() {
		timer = setTimeout("scrollImage()",5000);
	}
function scrollImage() {
		clearTimeout(timer);
		current+1 == num ? current = 0 : current = current+1;
		refreshImage();
	}
function refreshImage() {
		document.getElementById("myImage").src = images[current];
		var img = document.getElementById("myImage");
		slide();
	}
function scrollLeft() {
		clearTimeout(timer);
		current == 0 ? current = num-1 : current = current-1;
		refreshImage();
	}
function scrollRight() {
		clearTimeout(timer);
		current+1 == num ? current = 0 : current = current;
		refreshImage();
	}
