define(function (require) {
    "use strict";
    var $ = require('jquery'),
            _ = require('underscore');
    Backbone = require('backbone');
    var template = "    <section class=\"img-banner\" id=\"banner\">" +
            "    " +
            "  </section>" +
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
            + "        <div id=\"map\"  data-pin-url=\"" + staticURL + "/assets/img/pin.png\"></div>"
            + "    </section>" +
            "  <section class=\"detail-sect  events-sect\">" +
            "    <div class=\"container\">" +
            "      <nav class=\"breadcrumb\">" +
            "        <a class=\"breadcrumb-item\" href=\"#\">Trang chủ</a>" +
            "        <span id=\"menu_active\" class=\"breadcrumb-item active\"></span>" +
            "      </nav>" +
            "    </div>" +
            "  </section>";

    return Backbone.View.extend({
        initialize: function () {
//            this.render();

        },
        render: function (object) {
            var tpl = _.template(template, {});
            this.$el.html(tpl);
            var aliasName = "Địa Danh";
            if (object === "Hotel") {
                aliasName = "Khách Sạn";
            } else if (object === "Atm") {
                aliasName = "Máy ATM";
            } else if (object === "Restaurant") {
                aliasName = "Nhà Hàng";
            } else if (object === "Event") {
                aliasName = "Sự Kiện";
            }
            $("#menu_active").html(arrCollection[object]);
            var self = this;
            var path = "/common/" + object + "/list/get";
            $.ajax({
                url: (serviceURL || "") + path,
                data: {"limit": 10},
                dataType: "json",
                contentType: "application/json",
                success: function (data) {
                    console.log(data);
                    if (!!data && data.error_code === 0) {
                        $("#banner").css('background-image', 'url(' + staticURL + "/" + data.data[0].banner + ')');
                        var objContent = self.$el.find(".detail-sect .container");
                        var objParent = $('<div>').attr({"class": "row"});
                        objContent.append(objParent);
                        var title = $("<h1>").attr({"class": "default col-12"}).text(aliasName);
                        objParent.append(title);
                        var objList = $('<div>').attr({"class": "hot-locations row"});
                        objParent.append(objList);
                        if(object === "Event"){
                            objList.append("<img src=\"" + staticURL + "/assets/img/bieudosk.png"+"\" alt=\"\" class=\"img-fullsize\">");
                        }
                        $.each(data.data, function (index, value) {
                            var objChild = $('<div>').attr({"class": "card col-lg-3 col-md-6 col-sm-12"});
                            objChild.click(function () {
                                location.href = "#" + object + "/" + value._id;
                            });
                            objList.append(objChild);
                            var thumb = '<img src="' + staticURL + "/" + value.thumbnail + '" alt="" class="card-img-top">';
                            objChild.append(thumb);
                            var content = '<div class="card-body">' +
                                    '<h5 class="card-title wrap-1-line"><span class="highlight">' + aliasName + '</span> - <strong>' + value.name + '</strong></h5>' +
                                    '<p class="card-text wrap-1-line">' + value.address + '.</p>' +
                                    '<div class="rate view">' +
                                    ' <a href="#" class="active">♥</a>' +
                                    ' <a href="#" class="active">♥</a>' +
                                    ' <a href="#">♥</a>' +
                                    ' <a href="#">♥</a>' +
                                    ' <a href="#">♥</a>' +
                                    ' </div>' +
                                    '  </div>';
                            objChild.append(content);
                        });
                        var paging = '<nav aria-label="Page navigation" class="col-12">' +
                                '<ul class="pagination justify-content-center">' +
                                '<li class="page-item disabled"><span class="page-link" href="javascript:;">Trước</span></li>' +
                                '<li class="page-item active"><a class="page-link" href="javascript:;">1</a></li>' +
                                '<li class="page-item"><a class="page-link" href="javascript:;">2</a></li>' +
                                '<li class="page-item"><a class="page-link" href="javascript:;">3</a></li>' +
                                '<li class="page-item"><a class="page-link" href="javascript:;">Tiếp</a></li>' +
                                '</ul>' +
                                '</nav>';
                        objParent.append(paging);
                        $('.nav-item').removeClass('active');
                        $("#nav_"+object).addClass("active");
                        mapHandler.init();
                        var element_active;
                        if (object === "Hotel") {
                            $('.targets-tab a').removeClass('active');
                            element_active = $("#map-hotel");
                            element_active.click();
                        } else if (object === "Restaurant") {
                            $('.targets-tab a').removeClass('active');
                            element_active = $("#map-food");
                            element_active.click();
                        } else if (object === "Attraction") {
                            $('.targets-tab a').removeClass('active');
                            element_active = $("#map-history");
                            element_active.click();
                        } else if (object === "Event") {
                            $(".map-target").hide();
                        }
                        $('.targets-tab li').css("pointer-events", "none");
                        $('.targets-tab li').css("cursor", "not-allowed");
//                        $('.targets-tab a').attr("disabled","disabled");
//                        $('.targets-tab a').off('mouseenter mouseleave');
                        if (!!element_active) {
                            $(element_active).parent().css("pointer-events", "all");
                        }
                    } else {
                        console.log(data);
                    }
                },
                error: function (xhr, status, error) {
                    try {
                        var data = $.parseJSON(xhr.responseText);
                        console.log(data);
                    } catch (e) {
                        // error
                    }
                }
            });
        }

    });

});