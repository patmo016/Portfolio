/* Global Variables*/
var test;
var num = [];
var roomNumbers = [];
var numbers = [];
var bookedRooms = [];

/*Modual used for making booking*/
var makeBooking = (function() {

    var pub = {};

    function showHideDetails() {
        var checkin = $("#checkIn").val();
        var checkout = $("#checkOut").val();

        if (checkin === '' || checkout === '') {
            $("#roomsbutton").parent().parent().siblings().hide();
            $("#errors").html("You must enter a valid date");
        }
        if (checkin >= checkout) {
            $("#errors").html("You must enter a valid date");
        } else {
            $("#errors").html("");
            $("#roomsbutton").parent().parent().siblings().show();
        }
        console.log("showhide is called");

    }


    /*function that checks whether a room is already booked or not n the dates the user submits*/
    function isBooked() {
        showHideDetails;
        
        var checkInDate = ($("#roomsbutton").parent().siblings().find("#checkIn").val()).split("/");
        var checkOutDate = ($("#roomsbutton").parent().siblings().find("#checkOut").val()).split("/");

        var inDay = parseInt(checkInDate[1]);
        var inMonth = parseInt(checkInDate[0]);
        var inYear = parseInt(checkInDate[2]);
        //new Date object
        checkInDate = new Date(inYear, inMonth - 1, inDay);

        var outDay = parseInt(checkOutDate[1]);
        var outMonth = parseInt(checkOutDate[0]);
        var outYear = parseInt(checkOutDate[2]);
        //new date object
        checkOutDate = new Date(outYear, outMonth - 1, outDay);

        $.ajax({
            type: "GET",
            url: "roomBookings.xml",
            cache: false,
            success: function(xml) {
                /*Shows rooms if they are not booked.*/
                $(xml).find("booking").each(function() {
                    var number = $(this).find("number")[0].textContent;
                    roomNumbers.push(number);

                    var bookedinD = $(this).find("day")[0].textContent;
                    var bookedinM = $(this).find("month")[0].textContent;
                    var bookedinY = $(this).find("year")[0].textContent;
                    var bookedinDate = new Date(bookedinY, bookedinM - 1, bookedinD);

                    var bookedoutD = $(this).find("day")[0].textContent;
                    var bookedoutM = $(this).find("month")[0].textContent;
                    var bookedoutY = $(this).find("year")[0].textContent;
                    var bookedoutDate = new Date(bookedoutY, bookedoutM - 1, bookedoutD);

                    var roombooked = {
                        bookedIn: bookedinDate,
                        bookedOut: bookedoutDate,
                        number: number
                    };
                    //pushes the roombooked objects onto an array
                    bookedRooms.push(roombooked);
                });

                var i;
                var j;
                //iterates through booked rooms
                for (i = 0; i < bookedRooms.length; i += 1) {
                    if (bookedRooms[i].bookedIn <= checkOutDate && bookedRooms[i].bookedOut > checkInDate) {
                        /* removes rooms which are already booked */
                        for (j = numbers.length - 1; j >= 0; j -= 1) {
                            if (numbers[j] === bookedRooms[i].number) {
                                numbers.splice(i, 1);

                            }
                        }
                    }
                }

                var i;
                /*html to insert into the form*/
                var html = "<fieldset><p><lable for=name> Enter your name: </lable><input type=text name=name id=bookingName placeholder=Name></p><p><lable for=selectroom id=room> Select a Room </lable><select name=selectroom id=selectRoom>";
                for (i = 0; i < roomNumbers.length; i++) {
                    if ($.inArray(roomNumbers[i].number, numbers) != -1) {

                        html += "<option value= " + roomNumbers[i].number + "_" + roomNumbers[i].roomType + "_" + roomNumbers[i].price + " >" + roomNumbers[i].number + ":  " + roomNumbers[i].roomType + ":  " + roomNumbers[i].price + "</option>";
                    }
                }


                html += "</select></p></fieldset>";
                //show the submit form button
                //if(checkInDate !== '')
                $("#submitForm").show();
                //inserts html
                $("#selectRoom").html(html);
            }
        });
    }


    function parseBookings(data, target) {
        var html;
        $(target).empty();
        var i;

        $(data).find("hotelRoom").each(function() {
            var number = $(this).find("number")[0].textContent;
            var roomType = $(this).find("roomType")[0].textContent;
            var description = $(this).find("description")[0].textContent;
            var price = $(this).find("pricePerNight")[0].textContent;
            numbers.push(number);
            var bookedNum = {
                number: number,
                roomType: roomType,
                price: price
            }
            roomNumbers.push(bookedNum);

        });
        $("#roomsbutton").click(isBooked);


    }




    function booking() {

        var target = $("#selectRoom");

        $.ajax({
            type: "GET",
            url: "hotelRooms.xml",
            cache: false,
            success: function(data) {
                parseBookings(data, target);
            }
        });

    }


    pub.setup = function() {
        $("#checkIn").datepicker();
        $("#checkOut").datepicker();
        $("#submitForm").hide();
        $("#roomsbutton").click(isBooked);
        booking();


    };
    return pub;
}());

$(document).ready(makeBooking.setup);