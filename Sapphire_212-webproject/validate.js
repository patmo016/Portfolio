var Validate = (function () {
    "use strict";

    var pub;

    pub = {};

    function checkNotEmpty(textValue) {
        return textValue.trim().length > 0;
    }

    /* adds booking to an array of cookies.*/
    function addCookie(submit) {
        console.log("adding booking");
        var bookingList, newBooking;
        bookingList = Cookie.get("booking");
        if (bookingList) {
            bookingList = JSON.parse(bookingList);
        } else {
            bookingList = [];
        }
        newBooking = {};
        newBooking.name = $(submit).parent().siblings().find("#bookingName").val();
        newBooking.checkIn = $(submit).parent().siblings().find("#checkIn").val();
        newBooking.checkOut = $(submit).parent().siblings().find("#checkOut").val();
        newBooking.room = $(submit).parent().siblings().find("#selectRoom").val();
        bookingList.push(newBooking);
        Cookie.set("booking", JSON.stringify(bookingList), 48);

    }

    /*Possible functions for checking date validation*/
    /* function checkDates(checkIn, checkOut) {
         var errorHTML = "";
         var messages = [];

         if (checkIn >= checkOut) {
             $("#errors").html("<p>Please enter correct dates </p>");
             //$("#select).hide();
         }
     }*/

    function checkName(deliveryName, messages) {
        if (!checkNotEmpty(deliveryName)) {
            messages.push("You must enter a name ");
        }
    }
    /*function that overall validates. makes cookie iff no errors*/

    function validateForm() {
        var messages, name, html, errorHTML;

        messages = [];


        // Name validation

        checkName($("#bookingName").val(), messages);


        if (messages.length === 0) {
            console.log("no errors");
            html = "<p>Booking submited. Go to the <a href=pending.html>Pending Bookings page </a> to veiw Booking</p>";
            $("#errors").html(html);

            addCookie($("#submitForm"));

        } else {
            console.log("errors");
            errorHTML = "<p><strong>There were errors processing your form</strong></p>";
            errorHTML += "<ul>";
            messages.forEach(function (msg) {
                errorHTML += "<li>" + msg;
            });
            errorHTML += "</ul>";
            $("#errors").html(errorHTML);
        }

        return false;
    }

    /**
     * Setup function for validation.
     *
     * Adds validation to the form on submission.
     * If the validation fails (returns false) then the form is not submitted.
     * Adds a Datepicker to checkout.
     * If the sumit button is clicked it validates the form.
     */
    pub.setup = function () {

        $("#submitForm").click(validateForm);
        /*$("#submitForm").click("submit");*/
    };

    return pub;

}());

$(document).ready(Validate.setup);
