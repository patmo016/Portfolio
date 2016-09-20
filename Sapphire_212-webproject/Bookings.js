/**
 * Bookings display functions for the Sapphire Hotel site
 * Used by the admins to veiw confirmed bookings from the an xml file. 
 *
 * Created by: Molly Patterson
 */

/**
 * Module pattern for Bookings functions
 */


var Bookings = (function () {
    "use strict";
    var pub;

    pub = {};

    
    /**
    *Function AdminBookings that displays and xml file 
    *on the admin.html page
    *
    */
    
    function adminBookings(data, target) {
    
        $(target).empty();
        var html;
        html = "<table><tr><th>Name</th><th>Room Number</th><th>Check In Date</th><th>Check Out Date</th></tr>";
        $(data).find("booking").each(function () {
            var number = $(this).find("number")[0].textContent;
            var name = $(this).find("name")[0].textContent;
            var checkin = $(this).find("checkin")[0].textContent;
            var checkout = $(this).find("checkout")[0].textContent;

            html += "<tr><td>" + number + "</td><td> " + name + "</td><td>" + checkin + "</td><td>" + checkout + "</td></tr>";
        });

        html += "</table>";

        $(target).append(html);
        
    }

/** 
*called on setup.
*
*the Ajax gets an XML file and calles the AdmonBookings function on success
*
*/
    
    pub.setup = function () {
        var target = $("#veiwBookings");
        $.ajax({
            type: "GET",
            url: "roomBookings.xml",
            cache: false,
            success: function (data) {
                adminBookings(data, target);
            }
        });
        
    };
    return pub;
    
}());

$(document).ready(Bookings.setup);
