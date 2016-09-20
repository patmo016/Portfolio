var Pending = (function () {
    "use strict";
    var pub;

    pub = {};

    /**
     *Fuction that is called when the clear booking button is clicked.
     *removes the cookie that stores the booking, therefore the pending cookie itself.
     *
     */

    function clear() {
        Cookie.clear("booking");
        $("#pendingbookings").html("Booking removed");
    }

    /**
     *function the inserts html in the pending.html page to display the pendingbookings that the user has just made
     *
     */
    function makeBookingHTML(itemList) {
        var html;
        html = "";
        itemList.forEach(function (booking) {
            html += "<p> Pending Booking for: " + booking.name + "</p><p> Room Number: " + booking.room + "</p><p>Check In:  " + booking.checkIn + "</p><p>Check Out: " + booking.checkOut + "</p>";
        });
        html += "<p>Click here to remove the pending booking <button type=button name=clear id=clear>Remove Bookings</button></p>";

        return html;
    }

    /** 
     *Setup function that gets the cookie and calls makeBookingHTML to display it.
     */
    pub.setup = function () {

        var bookingList, bookingElement;
        bookingList = Cookie.get("booking");
        if (bookingList) {
            bookingList = JSON.parse(bookingList);
            $("#pendingbookings").html(makeBookingHTML(bookingList));

        } else {
            $("#pendingbookings").html("<p> There are no bookings</p>");
        }
        $("#clear").click(clear);
    };

    return pub;
}());

$(document).ready(Pending.setup);
