
////(function ($, e, window, document, undefined) {
//    "use strict";
var url = location.protocol + "//" + window.location.hostname + ":" + window.location.port;
var home = {
    init: function () {
        home.renderHome();
        home.getData();
    },
    getData: function () {
        var path = "/api/home/data";
        console.log(url + path);
        $.getJSON(url + path, function (data) {
            if (data !== undefined && data.error_code === 0) {
                home.renderMenu(data.data.home);
                home.renderTour(data.data.tour);
                home.renderEvent(data.data.event);
                home.renderAttraction(data.data.attraction);
            }
        });
    },
    renderHome: function () {
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
                "  <section class=\"map-target\">" +
                "    <div class=\"container\">" +
                "      <ul class=\"targets-tab row\">" +
                "        <li class=\"col-lg-2 col-md-4\">" +
                "          <a href=\"#history\" id=\"map-history\" data-api=\"api/map-history.json\" data-method=\"GET\">Di tích lịch sử</a>" +
                "        </li>" +
                "        <li class=\"col-lg-2 col-md-4\">" +
                "          <a href=\"#hotel\" id=\"map-hotel\" data-api=\"api/map-hotel.json\" data-method=\"GET\">Khách sạn</a>" +
                "        </li>" +
                "        <li class=\"col-lg-2 col-md-4\">" +
                "          <a href=\"#food\" id=\"map-food\" data-api=\"api/map-food.json\" data-method=\"GET\">ăn uống</a>" +
                "        </li>" +
                "        <li class=\"col-lg-2 col-md-4\">" +
                "          <a href=\"#gift\" id=\"map-gift\" data-api=\"api/map-gift.json\" data-method=\"GET\">lưu niệm</a>" +
                "        </li>" +
                "        <li class=\"col-lg-2 col-md-4\">" +
                "          <a href=\"#wifi\" id=\"map-wifi\" data-api=\"api/map-wifi.json\" data-method=\"GET\">wifi free</a>" +
                "        </li>" +
                "        <li class=\"col-lg-2 col-md-4\">" +
                "          <a href=\"#atm\" id=\"map-atm\" data-api=\"api/map-atm.json\" data-method=\"GET\">máy atm</a>" +
                "        </li>" +
                "      </ul>" +
                "    </div>" +
                "    <div id=\"map\" data-lat=\"21.0287797\" data-lng=\"105.850176\" data-zoom=\"16\" data-pin-url=\"assets/img/pin.png\"></div>" +
                "  </section>" +
                "  " +
                "  <section class=\"services-sect\">" +
                "    <div class=\"container\">" +
                "      <div class=\"row\">" +
                "        <div class=\"col-lg-8 col-sm-12 ml-auto mr-auto\">" +
                "          <h1 class=\"text-center\">Gợi ý lịch trình</h1>" +
                "        </div>" +
                "        <div class=\"col-12 ml-auto mr-auto row services\">" +
                "          " +
                "        </div>" +
                "      </div>" +
                "    </div>" +
                "  </section>";
        $("#content").html(tpl);
        $("#search").click(function(){
            navigator.camera.getPicture(function(result){
                alert("show content picture Success");
                console.log(result);
            },function(error){
                console.log(error);
                alert("show content picture Error");
            },{
            sourceType : Camera.PictureSourceType.CAMERA
            });
        });
    },
    renderEvent: function (data) {
        var urlEventDetails = "Event";
        $('.events').empty();
        for (var i = 0; i < data.length; i++) {
            var objEvent = data[i];
            var divEvent = $("<div>").attr({"href": "#"+urlEventDetails + "/" + objEvent._id, "class": "col-lg-6 col-sm-12 event"}).appendTo($('.events'));
            var imageEvent = $("<img>").attr({"src": objEvent.thumbnail, "class": "img-fullsize"}).appendTo(divEvent);
            var divEventDes = $("<div>").attr({"class": "event-desc"}).appendTo(divEvent);
            var titleEvent = $("<p>").attr({"class": "event-title"}).appendTo(divEventDes).text(objEvent.name);
            var time = $("<p>").text(objEvent.startDate + " - " + objEvent.endDate).appendTo(divEventDes);
            var address = $("<p>").text(objEvent.description).appendTo(divEventDes);

        }
    },
    renderAttraction: function (data) {
        var urlAttractionDetails = "Attraction";
        $('.attraction').empty();
        for (var i = 0; i < data.length; i++) {
            var objAttraction = data[i];
            var tagAAttraction = $("<a>").attr({"href": "#" + urlAttractionDetails + "/" + objAttraction._id}).addClass("col-lg-3 col-md-6 col-sm-12").appendTo($('.attraction'));
            var imageAttraction = $("<img>").attr({"src": objAttraction.thumbnail, "class": "img-fullsize"}).appendTo(tagAAttraction);
            var spanAttraction = $("<span>").attr({"class": "culture-item-name"}).text(objAttraction.Name).appendTo(tagAAttraction);
        }
    },
    renderTour: function (data) {
        var urlTourDetails = "Tour";
        $('.services').empty();
        for (var i = 0; i < data.length; i++) {
            var objTour = data[i];
            var divTour = $("<div>").attr({"href": urlTourDetails + objTour._id, "class": "service"}).appendTo($('.services'));
            divTour.click(function () {
                getDetail(urlTourDetails, objTour._id);
            });
            var imageTour = $("<img>").attr({"src": objTour.thumbnail, "class": "img-fullsize"}).appendTo(divTour);
            var divServiceDes = $("<div>").attr({"class": "service-desc"}).appendTo(divTour);
            var titleService = $("<p>").attr({"class": "service-title"}).appendTo(divServiceDes);
            var small = $("<small>").text(objTour.Type).appendTo(titleService);
            var des = $("<p>").text(objTour.description).appendTo(divServiceDes);

        }
        slideRender();
    },
    renderMenu: function (data) {
        console.log("render menu");
        var objMenu = $(".navbar .container");
        objMenu.html("");
        //start logo
        var el_logo = $("<a>").attr({"href": "javascript:void(0);", "class": "navbar-brand"});
//        var img_logo = data.logo;
//        el_logo.html('<img src="' + img_logo + '" alt="" class="d-inline-block align-top"><img src="' + img_logo + '" alt="" class="d-inline-block align-top fixed-logo">');
        el_logo.html('<a class="navbar-brand" href="/">' +
                '<img src="assets/img/logo-white.png" alt="" class="d-inline-block align-top">' +
                '<img src="assets/img/logo.png" alt="" class="d-inline-block align-top fixed-logo">' +
                '</a>');
        objMenu.append(el_logo);
        objMenu.append('<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main-navbar" aria-controls="main-navbar" aria-expanded="false" aria-label="Toggle navigation"> <span class="navbar-toggler-icon"></span> </button>');
        //end logo
        var obj_banner = $(".carousel-banner .lazy-carousel");
        $.each(data.bannerImg, function (index, value) {
            var tpl = '<div>' +
                    '<img src="' + value + '" alt="" data-lazy="' + value + '">' +
                    '<p class="carousel-caption"></p>' +
                    '</div>';
            obj_banner.append(tpl);
        });
        
        // start  banner medium
        if (!!data.image && data.image.length > 1)
            $(".img-banner").each(function (index) {
                $(this).html('<div class="container">' +
                        '<h1><img src="' + data.image[index] + '" alt="" class="img-responsive"></h1>' +
                        '</div>');
                $(this).css('background-image', 'url(' + data.image[index] + ')');
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
                    $.each(data.subMenu, function (subIndex, subValue) {
                        var el_a_sub = $("<a>").attr({"class": "dropdown-item", "href": subValue.link}).html(subValue.name);
                        div_submenu.append(el_a_sub);
                        el_a_sub.click(function () {
//                       location.href = 
                        });
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
                    ul_menu.closest("li").removeClass('active');
                    $(this).addClass("active");

                });

            });

            //end menu 


        }

    }
};

function getDetail(object, id) {
    var url = location.protocol + "//" + window.location.hostname + ":" + window.location.port;
    var path = "/" + object + "/" + id;
    console.log(url + path);
    $.getJSON(url + path, function (data) {
        if (data !== undefined && data.error_code === 0) {
            console.log(data.data);
            renderDetails(data.data);
        }
    });
}
function renderDetails(data) {
    var tpl = "<section class=\"img-banner\" id=\"banner\" style=\"background-image: url('" + data.banner + "');\">" +
            "    <div class=\"container\">" +
            "    </div>" +
            "  </section>" +
            "  " +
            "  <section class=\"detail-sect\">" +
            "    <div class=\"container\">" +
            "      <nav class=\"breadcrumb\">" +
            "        <a class=\"breadcrumb-item\" href=\"#\">Trang chủ</a>" +
            "        <a class=\"breadcrumb-item\" href=\"#\">Hoạt động</a>" +
            "        <span class=\"breadcrumb-item active\">Category</span>" +
            "      </nav>" +
            "      " +
            "      <h1>" + data.name + "</h1>" +
            data.content +
            "    </div>" +
            "  </section>";
    $("#content").html(tpl);
}


function slideRender() {
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



//    function RenderHotel(data) {
//        var urlTourDetails = "event";
//        $('.events').empty();
//        for (var i = 0; i < data.length; i++) {
//            var objEvent = data[i];
//            var divEvent = $("<div>").attr({"href": urlTourDetails + objEvent._id, "class": "col-lg-6 col-sm-12 event"}).appendTo($('.events'));
//
//            var imageEvent = $("<img>").attr({"src": objEvent.thumbnail, "class": "img-fullsize"}).appendTo(divEvent);
//            var divEventDes = $("<div>").attr({"class": "event-desc"}).appendTo(divEvent);
//            var titleEvent = $("<p>").attr({"class": "event-title"}).appendTo(divEventDes).text(objEvent.name);
//            var time = $("<p>").text(objEvent.startDate + " - " + objEvent.endDate).appendTo(divEventDes);
//            var address = $("<p>").text(objEvent.content).appendTo(divEventDes);
//
//        }
//    }

//$(document).ready(function () {
//    home.init();
//});
//})(jQuery, window, document);
