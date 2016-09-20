/*var Submit = (function () {

    var pub;

    pub = {};

    function onSubmit() {
        console.log("onsubmit called");
        var bookingList, newBooking;
        bookingList = Cookie.get("booking");
        if (bookingList) {
            bookingList = JSON.parse(bookingList);
        } else {
            bookingList = [];
        }
        newBooking = {};
        newBooking.name = $(this).parent().parent().find("#bookingName").val();
        newBooking.checkIn = $(this).parent().parent().siblings().find("#checkIn").val();
        newBooking.checkOut = $(this).parent().parent().siblings().find("#checkOut").val();
        newBooking.room = $(this).parent().siblings().find("#selectRoom").val();

        /*newItem.price = this.parentNode.getElementsByClassName("price")[0].innerHTML;*/
/*bookingList.push(newBooking);
        Cookie.set("booking", JSON.stringify(bookingList));
        console.log(Cookie.get("booking"));

        $("#pendingbookings").html("<p>Pending Booking for:  " + newBooking.name + "</p><p> Room number:  " + newBooking.room + "</p><p>Check in: " + newBooking.checkIn + "</p><p>Checkout: " + newBooking.checkOut + "</p>");
    }






    pub.setup = function () {
        $("#submitForm").click(onSubmit);


    };

    return pub;
}());

$(document).ready(Submit.setup);*/
