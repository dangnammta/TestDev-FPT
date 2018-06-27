define(function (require) {
    "use strict";
    var $ = require('jquery'),
            _ = require('underscore');
    Backbone = require('backbone');
    var template = "<section class=\"img-banner\" id=\"banner\" >" +
            "    <div class=\"container\">" +
            "    </div>" +
            "  </section>" +
            "  " +
            "  <section class=\"detail-sect  events-sect\">" +
            "    <div class=\"container\">" +
            "      <nav class=\"breadcrumb\">" +
            "        <a class=\"breadcrumb-item\" href=\"#\">Trang chủ</a>" +
            "        <span class=\"breadcrumb-item active\">Lịch trình du lịch</span>" +
            "      </nav>" +
//            "      <h1>" + data.name + "</h1>" +
//            data.content +
            "    </div>" +
            "  </section>";

    return Backbone.View.extend({
        initialize: function () {
//            this.render();
        },
        collection: "Tour",
        render: function (id) {
            console.log(this.collection);
            var tpl = _.template(template, {});
            this.$el.html(tpl);
            var self = this;
            var path = "/common/" + self.collection + "/" + id;
            $.ajax({
                url: (serviceURL || "") + path,
                data: {"limit": 10},
                dataType: "json",
                contentType: "application/json",
                success: function (data) {
                    console.log(data);
                    if (!!data && data.error_code === 0) {
                        $("#banner").css('background-image', 'url(' + staticURL + "/" + data.data.banner + ')');
                        var objContent = self.$el.find(".detail-sect .container");
                        var objParent = $('<div>').attr({"class": "row"});
                        objContent.append(objParent);
                        var title = $("<h1>").attr({"class": "default col-12"}).html('<span class="highlight">' + data.data.name + ' </span>');
                        objParent.append(title);
                        var objList = $('<div>').attr({"class": "row events paralax-part"});
                        objParent.append(objList);

                        var tour_img = $('<div>').attr({"class": "col-lg-4 col-sm-12 tour-img"}).appendTo(objParent);
                        var tour_thumnail = $('<img>').attr({"class": "img-fullsize", "src": staticURL + "/" + data.data.thumbnail}).appendTo(tour_img);

                        var tour_des = $('<div>').attr({"class": "col-lg-8 col-sm-12"}).appendTo(objParent);
                        var tour_sort_des = $('<div>').attr({"class": "tour-short-desc"}).appendTo(tour_des);
                        $('<p>').attr({"class": "wrap-1-line"}).html("<strong>Điểm khởi hành: </strong>" + data.data.startAddress).appendTo(tour_sort_des);
                        $('<p>').attr({"class": "wrap-1-line"}).html("<strong>Lịch khởi hành: </strong>" + data.data.departure).appendTo(tour_sort_des);
                        $('<p>').attr({"class": "wrap-1-line"}).html("<strong>Phương tiện: </strong>" + data.data.traffic).appendTo(tour_sort_des);
                        $('<p>').attr({"class": "wrap-1-line"}).html("<strong>Điểm tham quan: </strong>" + data.data.location).appendTo(tour_sort_des);
                        $('<p>').html("<strong>Giá tour: </strong><span class='highlight'>" + data.data.price + "</span>").appendTo(tour_sort_des);

                        var tour_detail = $('<div>').attr({"class": "col-12"}).appendTo(objParent);
                        $('<h2>').attr({"class": "mb-5"}).html('Lịch trình <span class="highlight text-uppercase">' + data.data.type + '<span>').appendTo(tour_detail);
                        var tour_plan = $('<dl>').attr({"class": "tour-plan"}).appendTo(tour_detail);
                        $.each(data.data.content, function (index, value) {
                            $('<dt>').html('<a href="javascript:;" class="wrap-1-line mb-3" data-toggle="collapse" data-target="#dd0' + index + '" aria-expanded="true" aria-controls="collapseDl01" data-date="Ngày 0' + (index + 1) + '">' + value.time + '</a>').appendTo(tour_plan);
                            $('<dd>').attr({"id": "dd0" + index, "class": "collapse show mb-5"}).html(value.activity).appendTo(tour_plan);
                        });

                        objParent.append('<div style="margin:0px" class="row events other-tours">' +
                                ' <h2 class="col-12">Tour Khác</h2>' +
                                ' <div class="row col-12 event tour-item">' +
                                '  <div class="col-lg-3 col-sm-12 p-0 tour-img">' +
                                '    <img src="' + staticURL + '/assets/img/img-event-02.png" alt="" class="img-fullsize">' +
                                ' </div>' +
                                '  <div class="col-lg-6 col-sm-12 p-0">' +
                                '  <div class="event-desc">' +
                                '      <p class="event-title">Hạ Long – Tuần Châu – Cát Bà</p>' +
                                '      <p><strong>Điểm khởi hành:</strong> Hà Nội, Hải Phòng, Quảng Ninh</p>' +
                                '      <p><strong>Lịch khởi hành:</strong> Hàng ngày</p>' +
                                '       <p><strong>Phương tiện:</strong> Ô tô + Du thuyền ngủ đêm</p>' +
                                '        <p><strong>Điểm tham quan:</strong> Vịnh Hạ Long, Đảo Tuần Châu, Đảo Cát Bà</p>' +
                                '  </div>' +
                                ' </div>' +
                                '  <div class="col-lg-3 col-sm-12 p-0 tour-booking text-center">' +
                                '  <div class="tour-booking-content">' +
                                '    <p>Giá tour</p>' +
                                '    <strong>4.000.000 VND</strong>' +
                                '    <a href="#" class="text-uppercase btn btn-outline-secondary">Chi tiết</a>' +
                                '    </div>' +
                                '  </div>' +
                                ' </div>' +
                                '<div class="row col-12 event tour-item">' +
                                ' <div class="col-lg-3 col-sm-12 p-0 tour-img">' +
                                '   <img src="' + staticURL + '/assets/img/img-event-01.png" alt="" class="img-fullsize">' +
                                ' </div>' +
                                ' <div class="col-lg-6 col-sm-12 p-0">' +
                                '  <div class="event-desc">' +
                                '     <p class="event-title">Hạ Long – Tuần Châu – Cát Bà</p>' +
                                '      <p><strong>Điểm khởi hành:</strong> Hà Nội, Hải Phòng, Quảng Ninh</p>' +
                                '      <p><strong>Lịch khởi hành:</strong> Hàng ngày</p>' +
                                '      <p><strong>Phương tiện:</strong> Ô tô + Du thuyền ngủ đêm</p>' +
                                '      <p><strong>Điểm tham quan:</strong> Vịnh Hạ Long, Đảo Tuần Châu, Đảo Cát Bà</p>' +
                                '   </div>' +
                                ' </div>' +
                                ' <div class="col-lg-3 col-sm-12 p-0 tour-booking text-center">' +
                                '   <div class="tour-booking-content">' +
                                '     <p>Giá tour</p>' +
                                '     <strong>4.000.000 VND</strong>' +
                                '     <a href="#" class="text-uppercase btn btn-outline-secondary">Chi tiết</a>' +
                                '     </div>' +
                                '   </div>' +
                                ' </div>' +
                                '</div>');
                        $('.nav-item').removeClass('active');
                        $("#nav_Tour").addClass("active");
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