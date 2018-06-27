define(function (require) {
    "use strict";
    var $ = require('jquery'),
            _ = require('underscore');
    Backbone = require('backbone');

    return Backbone.View.extend({
        initialize: function () {
//            this.render();
        },
        render: function () {
            var self = this;
            // Compile the template using underscore
            var tpl = "<section class=\"carousel-banner\">" +
                    "      <div class=\"lazy-carousel\">" +
                    "      </div>" +
                    "    </section>" +
                    "  " +
                    "  <section class=\"cultures-sect\">" +
                    "    <div class=\"container\">" +
                    "      <div class=\"row\">" +
                    "        <div class=\"col-lg-8 col-sm-12 ml-auto mr-auto\">" +
                    "          <h1 class=\"text-center\">Địa điểm du lịch</h1>" +
                    "          <p class=\"text-center\">Quảng Ninh không chỉ có các bãi biển đẹp còn có những cánh rừng nguyên sinh rất đẹp, hệ sinh thái phong phú có thể phát triển các loại hình du lịch khám phá, mạo hiểm, ẩm thực...</p>" +
                    "        </div>" +
                    "        <div class=\"col-12 ml-auto mr-auto row attraction\">" +
                    "          " +
                    "        </div>" +
                    "      </div>" +
                    "    </div>" +
                    "  </section>" +
                    "  <section class=\"events-sect\">" +
                    "    <div class=\"container\">" +
                    "      <div class=\"row\">" +
                    "        <div class=\"col-lg-8 col-sm-12 ml-auto mr-auto\">" +
                    "          <h1 class=\"text-center\">Sự kiện du lịch</h1>" +
                    "        </div>" +
                    "        <div class=\"col-12 ml-auto mr-auto row events\">" +
                    "          " +
                    "        </div>" +
                    "      </div>" +
                    "    </div>" +
                    "  </section>" +
                    "  " +
                    "    <section class=\"img-banner\" id=\"banner\">" +
                    "    " +
                    "  </section>" +
                    "  " +
                    "<section class=\"map-target\">"
                    + "        <div class=\"container\">"
                    + "            <ul class=\"targets-tab row paralax-part\">"
                    + "                <li class=\"col-lg-2 col-md-4 animate\" data-animate=\"bounceIn\">"
                    + "                    <a data-pin-url=\""+staticURL+"/assets/img/icon_attraction.ico\" href=\"#history\" id=\"map-history\" data-api=\""+staticURL+"/api/map_attraction.json\" data-method=\"GET\">Địa danh</a>"
                    + "                </li>"
                    + "                <li class=\"col-lg-2 col-md-4 animate\" data-animate=\"bounceIn\">"
                    + "                    <a data-pin-url=\""+staticURL+"/assets/img/icon_hotel.ico\" href=\"#hotel\" id=\"map-hotel\" data-api=\""+staticURL+"/api/map_hotel.json\" data-method=\"GET\">Khách sạn</a>"
                    + "                </li>"
                    + "                <li class=\"col-lg-2 col-md-4 animate\" data-animate=\"bounceIn\">"
                    + "                    <a data-pin-url=\""+staticURL+"/assets/img/icon_food.ico\" href=\"#food\" id=\"map-food\" data-api=\""+staticURL+"/api/map_restaurant.json\" data-method=\"GET\">Ăn uống</a>"
                    + "                </li>"
                    + "                <li class=\"col-lg-2 col-md-4 animate\" data-animate=\"bounceIn\">"
                    + "                    <a data-pin-url=\""+staticURL+"/assets/img/icon_gift.ico\" href=\"#gift\" id=\"map-gift\" data-api=\""+staticURL+"/api/map-gift.json\" data-method=\"GET\">Lưu niệm</a>"
                    + "                </li>"
                    + "                <li class=\"col-lg-2 col-md-4 animate\" data-animate=\"bounceIn\">"
                    + "                    <a data-pin-url=\""+staticURL+"/assets/img/icon_wifi.ico\" href=\"#wifi\" id=\"map-wifi\" data-api=\""+staticURL+"/api/map-wifi.json\" data-method=\"GET\">Wifi free</a>"
                    + "                </li>"
                    + "                <li class=\"col-lg-2 col-md-4 animate\" data-animate=\"bounceIn\">"
                    + "                    <a data-pin-url=\""+staticURL+"/assets/img/icon_atm.ico\" href=\"#atm\" id=\"map-atm\" data-api=\""+staticURL+"/api/map_atm.json\" data-method=\"GET\">Máy ATM</a>"
                    + "                </li>"
                    + "            </ul>"
                    + "        </div>"
                    + "        <div id=\"map\"  data-pin-url=\""+staticURL+"/assets/img/pin.png\"></div>"
                    + "    </section>"
                    + "  " +
                    "  <section class=\"services-sect\">" +
                    "    <div class=\"container\">" +
                    "      <div class=\"row\">" +
                    "        <div class=\"col-lg-8 col-sm-12 ml-auto mr-auto\">" +
                    "          <h1 class=\"text-center\">Gợi ý lịch trình</h1>" +
                    "        </div>" +
                    "        <div class=\"col-12 ml-auto mr-auto row services paralax-part\">" +
                    "          " +
                    "        </div>" +
                    "      </div>" +
                    "    </div>" +
                    "  </section>";
            var template = _.template(tpl, {});
            // Load the compiled HTML into the Backbone "el"
            this.$el.html(template);
            
            mapHandler.init();
            $('#map-history').click();
            self.getData();

        },
        getData: function () {
            var self = this;
            var path = serviceURL + "/api/home/data";
//            console.log(path);
            $.getJSON(path, function (data) {
                if (data !== undefined && data.error_code === 0) {
                    self.renderMenu(data.data.home);
                    self.renderTour(data.data.tour);
                    self.renderEvent(data.data.event);
                    self.renderAttraction(data.data.attraction);
                }
            });
        },
        renderMenu: function (data) {
//            console.log("render menu");
            var objMenu = $(".navbar .container");
            objMenu.html("");
            //start logo
            var el_logo = $("<a>").attr({"href": "#/", "class": "navbar-brand"});
//        var img_logo = data.logo;
//        el_logo.html('<img src="' + img_logo + '" alt="" class="d-inline-block align-top"><img src="' + img_logo + '" alt="" class="d-inline-block align-top fixed-logo">');
            el_logo.html('<img src="'+staticURL+'/assets/img/logo-white.png" alt="" class="d-inline-block align-top">' +
                    '<img src="'+staticURL+'/assets/img/logo.png" alt="" class="d-inline-block align-top fixed-logo">');
            objMenu.append(el_logo);
            objMenu.append('<button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#main-navbar" aria-controls="main-navbar" aria-expanded="false" aria-label="Toggle navigation"> <span class="navbar-toggler-icon"></span> </button>');
            //end logo
            var obj_banner = $(".carousel-banner .lazy-carousel");
            $.each(data.bannerImg, function (index, value) {
                var tpl = '<div>' +
                        '<img src="' + staticURL+'/'+value + '" alt="" data-lazy="' + staticURL+'/'+ value + '">' +
                        '<p class="carousel-caption"></p>' +
                        '</div>';
                obj_banner.append(tpl);
            });

///start menu==========================
            var el_menu = $("<div>").attr({"id": "main-navbar", "class": "collapse navbar-collapse"});
            objMenu.append(el_menu);
            var ul_menu = $("<ul>").attr({"class": "navbar-nav ml-auto"});
            el_menu.append(ul_menu);
            if (data.menu !== undefined && data.menu.length > 0) {
                $.each(data.menu, function (index, value) {
                    var el_li = $("<li>").attr({"class": "nav-item"});
                    ul_menu.append(el_li);
                    if (value.subMenu !== undefined && value.subMenu.length > 0) {
                        el_li.addClass("dropdown");
                        var el_a = $("<a>").attr({"id": "menu_" + value._id, "class": "nav-link dropdown-toggle", "href": "javascript:;", "data-toggle": "dropdown", "aria-haspopup": "true", "aria-expanded": "false"});
                        el_a.html(value.name);
                        el_li.append(el_a);
                        var div_submenu = $("<div>").attr({"class": "dropdown-menu", "aria-labelledby": "menu_" + value.id});
                        el_li.append(div_submenu);
                        $.each(value.subMenu, function (subIndex, subValue) {
                            var el_a_sub = $("<a>").attr({"class": "dropdown-item", "href": subValue.link}).html(subValue.name);
                            div_submenu.append(el_a_sub);
//                            el_a_sub.click(function () {
////                       location.href = 
//                            });
                        });

                    } else {
                        var el_a = $("<a>").attr({"class": "nav-link", "href": value.link});
                        el_li.append(el_a);
                        if (index === 0) {
                            el_a.html(value.name + '<span class="sr-only">(current)</span>');
                            el_li.addClass("active");
                        } else {
                            el_a.html(value.name);
                        }
                    }
                    el_li.click(function () {
                        ul_menu.children().removeClass('active');

                        $(this).addClass("active");
                        $("#main-navbar").removeClass("show");

                    });

                });

                //end menu 

            }
        },
        renderEvent: function (data) {
            var urlEventDetails = "Event";
            $('.events').empty();
            for (var i = 0; i < data.length; i++) {
                if(i===3){
                    return false;
                }
                var objEvent = data[i];
                var animate = "fadeInLeft";
                if(i % 2){
                    animate = "fadeInRight";
                }else if(i % 3){
                    animate = "fadeInUp";
                }
                var divEvent = $("<div>").attr({ "class": "col-lg-4 col-sm-12 event animated","data-animate":animate}).appendTo($('.events'));
                divEvent.click(function(){
                   location.href = "#" + urlEventDetails + "/" + objEvent._id; 
                });
                $("<img>").attr({"src":staticURL + "/"+ objEvent.thumbnail, "class": "img-fullsize"}).appendTo(divEvent);
                var divEventDes = $("<div>").attr({"class": "event-desc"}).appendTo(divEvent);
                $("<p>").attr({"class": "event-title"}).appendTo(divEventDes).text(objEvent.name);
                $("<p>").text(objEvent.startDate + " - " + objEvent.endDate).appendTo(divEventDes);
//                var address = $("<p>").text(objEvent.description).appendTo(divEventDes);

            }
        },
        renderAttraction: function (data) {
            var urlAttractionDetails = "Attraction";
            $('.attraction').empty();
            for (var i = 0; i < data.length; i++) {
                var objAttraction = data[i];
                var tagAAttraction = $("<a>").attr({"href": "#" + urlAttractionDetails + "/" + objAttraction._id}).addClass("col-lg-3 col-md-6 col-sm-12").appendTo($('.attraction'));
                var imageAttraction = $("<img>").attr({"src": serviceURL+ "/" +objAttraction.thumbnail, "class": "img-fullsize"}).appendTo(tagAAttraction);
                var spanAttraction = $("<span>").attr({"class": "culture-item-name"}).text(objAttraction.name).appendTo(tagAAttraction);
            }
        },
        renderTour: function (data) {
            var self = this;
            var urlTourDetails = "Tour";
            var element = $('.services-sect .services');
            element.empty();
            var animate = "fadeInUp";
            for (var i = 0; i < data.length; i++) {
                if (i % 2 !== 0) {
                    animate = "fadeInDown";
                }
                var objTour = data[i];
                var divTour = $("<div>").attr({"href": "#" + urlTourDetails + "/" + objTour._id, "class": "service animate", "data-animate": animate}).appendTo(element);
                divTour.click(function () {
                   location.href= $(this).attr("href");
                });
                var imageTour = $("<img>").attr({"src": staticURL + "/" + objTour.thumbnail, "class": "img-fullsize"}).appendTo(divTour);
                var divServiceDes = $("<div>").attr({"class": "service-desc"}).appendTo(divTour);
                var titleService = $("<p>").attr({"class": "service-title"}).appendTo(divServiceDes);
                titleService.html('<small>' + objTour.type + '</small> - ' + '<span>' + objTour.price + '</span>');
                divServiceDes.append('<p>' + objTour.description + '</p>');
//                var small = $("<small>").text(objTour.type).appendTo(titleService);
//                var des = $("<p>").text(objTour.description).appendTo(divServiceDes);

            }
            self.slideRender();
        },
        slideRender: function () {
            if ($('.lazy-carousel').length) {
                $('.lazy-carousel').slick({
                    lazyLoad: 'progressive',
                    slidesToShow: 1,
                    slidesToScroll: 1,
                    autoplay: true,
                    autoplaySpeed: 6000,
                    dots: true,
                    infinite: true,
                    cssEase: 'linear'
                });
            }

            $(".services").slick({
                infinite: !0,
                slidesToShow: 3,
                slidesToScroll: 2,
                autoplay: !0,
                autoplaySpeed: 3e3,
                responsive: [{
                        breakpoint: 992,
                        settings: {
                            slidesToShow: 2,
                            slidesToScroll: 2
                        }
                    }, {
                        breakpoint: 480,
                        settings: {
                            slidesToShow: 1,
                            slidesToScroll: 1
                        }
                    }]
            });

        }

    });
});
//  var search_view = new SearchView({ el: $("#content") });





