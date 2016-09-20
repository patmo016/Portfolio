/**
 * showRooms display functions for the Sapphire Hotel site
 *
 * Created by: Molly Patterson
 */

/**
 * Module pattern for Rooms functions
 */
var test;

var Rooms = (function () {
    "use strict";

    var pub = {};

    /** parseRooms function
     *iterates through the hotel rooms xml file
     *
     *inserts html onto rooms.html
     *
     */

    function parseRooms(data, target) {
        $(target).empty();
        var html;
        html = "";
        $(data).find("hotelRoom").each(function () {
            var number = $(this).find("number")[0].textContent;
            var roomType = $(this).find("roomType")[0].textContent;
            var description = $(this).find("description")[0].textContent;
            var price = $(this).find("pricePerNight")[0].textContent;

            html += "<p>" + number + ": " + roomType + "</p><p id=describe><i>" + " \t   " + description + "   Price per night:   " + price + "</i></p>";
        });

        $(target).append(html);
    }

    /**
     *On set up ajax is called to take an xml file call the parserooms function on it
     *
     */
    pub.setup = function () {
        var target = $("#rooms");
        $.ajax({
            type: "GET",
            url: "hotelRooms.xml",
            cache: false,
            success: function (data) {
                parseRooms(data, target);
            }
        });

    };


    return pub;

}());

$(document).ready(Rooms.setup);
