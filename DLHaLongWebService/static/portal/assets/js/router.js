define(function (require) {

    "use strict";

    var $ = require('jquery'),
            Backbone = require('backbone');
    var HomeView = require('HomeView');
    var DetailView = require('DetailView');
    var TourView = require('TourView');
    var TicketView = require('TicketView');
    var TourDetail = require('TourDetail');
    var MapsView = require('MapsView');
    var MapsDetail = require('MapsDetail');
    var Login = require('LoginView');
    var Register = require('RegisterView');
    var HDVView = require('HDVView');
    var MyTicket = require('MyTicket');
    var ServiceView = require('ManagementServiceView');

    return Backbone.Router.extend({
        routes: {
            ":object/:id": "detail",
            ":object": "object",
            "*path": "defaultRoute"
        },
        defaultRoute: function () {
            var home_view = new HomeView({el: $("#content")});
            home_view.render();
            $("html, body").animate({ scrollTop: 0 }, "slow");
        },
        object: function (object) {
            if (object === "Tour") {
                var tourView = new TourView({el: $("#content")});
                tourView.render();
                return false;
            } else if (object === "Ticket") {
                var ticket = new TicketView({el: $("#content")});
                ticket.render();
                return false;
            } else if (object === "Event" || object === "Hotel" || object === "Attraction" || object === "Atm" || object === "Restaurant") {
                var mapsview = new MapsView({el: $("#content")});
                mapsview.render(object);
                return false;
            } else if (object === "Login") {
                var login = new Login({el: $("#content")});
                login.render();
                return false;
            } else if (object === "Register") {
                var register = new Register({el: $("#content")});
                register.render();
            } else if (object === "huongdanvien") {
                var hdv = new HDVView({el: $("#content")});
                hdv.render();
            } else if (object === "MyTicket") {
                var myticket = new MyTicket({el: $("#content")});
                myticket.render();
            } else if (object === "Service") {
                var service = new ServiceView({el: $("#content")});
                service.render();
            } else {
                var home_view = new HomeView({el: $("#content")});
                home_view.render();
            }
            $("html, body").animate({ scrollTop: 0 }, "slow");
        },
        detail: function (object, id) {
            if (object === "Tour") {
                var detail_tour = new TourDetail({el: $("#content")});
                detail_tour.render(id);
                return false;
            } else if (object === "Hotel" || object === "Attraction" || object === "Atm" || object === "Restaurant" || object === "Event") {
                var mapsdetail = new MapsDetail({el: $("#content")});
                mapsdetail.render(object, id);
                return false;
            }
            var detail_view = new DetailView({el: $("#content")});
            detail_view.render(object, id);
            $("html, body").animate({ scrollTop: 0 }, "slow");
        }

    });

});