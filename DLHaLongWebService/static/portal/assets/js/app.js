define('jquery', [], function () {
    return jQuery;
});
//"http://103.9.0.215/portal";
var serviceURL = location.protocol + "//" + window.location.hostname + ":" + (window.location.port || 80) + "/portal";
var staticURL = serviceURL;//"http://103.9.0.215/portal";
var arrCollection = [];
var isMobile = false;

require.config({
    baseUrl: staticURL + '/assets/js',
//    paths: {
//        app: '../app',
//        tpl: '../tpl',
//        vendor: '../../vendor'
//    },
    shim: {
        'gonrin': {
            deps: ['underscore', 'jquery', 'backbone'],
            exports: 'Gonrin'
        },
        'backbone': {
            deps: ['underscore', 'jquery'],
            exports: 'Backbone'
        },
        'underscore': {
            exports: '_'
        }
    }
});

require(['jquery', 'backbone', 'router'], function ($, Backbone, Router) {
    $.ajaxSetup({
        headers: {
            'content-type': 'application/json'
        }
    });

    var app = {
        initialize: function () {
            router = new Router();
            this.getHome();

            arrCollection["Attraction"] = "ĐỊA DANH";
            arrCollection["Hotel"] = "KHÁCH SẠN";
            arrCollection["Restaurant"] = "NHÀ HÀNG";
            arrCollection["Event"] = "SỰ KIỆN";
            arrCollection["Tour"] = "LỊCH TRÌNH DU LỊCH";
            arrCollection["Ticket"] = "MUA VÉ";
            arrCollection["MyTicket"] = "VÉ CỦA TÔI";
            $("#search").hide();
            
//            $("#search").click(function () {
//                navigator.camera.getPicture(function (result) {
//                    alert("show content picture Success");
//                    console.log(result);
//                }, function (error) {
//                    console.log(error);
//                    alert("show content picture Error");
//                }, {
//                    sourceType: Camera.PictureSourceType.CAMERA
//                });
//            });
//            this.nav = new Nav();
//            this.nav.render();
//            this.getCurrentUser();
        },
        getParameterUrl: function (parameter, url) {
            if (!url)
                url = window.location.href;
            var reg = new RegExp('[?&]' + parameter + '=([^&#]*)', 'i');
            var string = reg.exec(url);
            return string ? string[1] : undefined;
        },
        getHome: function () {
            var self = this;
            $.ajax({
                url: serviceURL + '/common/Home/1',
                dataType: "json",
                success: function (data) {
                    console.log(data);
                    if (!!data && data.error_code === 0) {
                        renderMenu(data.data);
                    } else {
                        alert("Get Menu Error \n Message:" + data.error_message);
                    }


                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log(XMLHttpRequest.responseText);
                }
            });
        }

    };
    app.isMobile = false;
    app.initialize();
    Backbone.history.start();


});

function renderMenu(data) {
    console.log("render menu");
    var objMenu = $(".navbar .container");
    objMenu.html("");
    //start logo
    var el_logo = $("<a>").attr({"href": "#/", "class": "navbar-brand"});
//        var img_logo = data.logo;
//        el_logo.html('<img src="' + img_logo + '" alt="" class="d-inline-block align-top"><img src="' + img_logo + '" alt="" class="d-inline-block align-top fixed-logo">');
    el_logo.html('<img src="' + staticURL + '/assets/img/logo-white.png" alt="" class="d-inline-block align-top">' +
            '<img src="' + staticURL + '/assets/img/logo.png" alt="" class="d-inline-block align-top fixed-logo">');
    objMenu.append(el_logo);
    objMenu.append('<button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#main-navbar" aria-controls="main-navbar" aria-expanded="false" aria-label="Toggle navigation"> <span class="navbar-toggler-icon"></span> </button>');
    //end logo
    var obj_banner = $(".carousel-banner .lazy-carousel");
    $.each(data.bannerImg, function (index, value) {
        var tpl = '<div>' +
                '<img src="' + staticURL + '/' + value.img + '" alt="" data-lazy="' + staticURL + '/' + value.img + '">' +
                '<p class="carousel-caption">'+value.description+'</p>' +
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
            var el_li = $("<li>").attr({"class": "nav-item", "id": "nav_" + value.objectName});
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

            });

        });

        //end menu 

    }
    layoutRender.init();
}

