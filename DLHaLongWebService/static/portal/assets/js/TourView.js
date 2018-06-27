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
        render: function () {
            console.log(this.collection);
            var tpl = _.template(template, {});
            this.$el.html(tpl);
            var self = this;
            var path = "/common/" + self.collection + "/list/get";
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
                        $('<div>').attr({"class":"bread-tag col-12","style":"margin-top:20px;"}).html('<span style="margin-top: 10px;display: inline-block;">Giá tour</span> <input type="text" style="margin-top: 10px;padding: 2px 5px; width: 100px;margin-right:10px;border: 1px solid #868e96;border-radius: 4px;" value="5.000.000"/> <span style="margin-top: 10px;display: inline-block;">Số ngày</span> <input type="text" style="margin-top: 10px;padding: 2px 5px; width: 100px;margin-right:10px;border: 1px solid #868e96;border-radius: 4px;" value="2"/> <span style="margin-top: 10px;display: inline-block;">Khu vực</span> <input type="text" style="padding: 2px 5px; width: 150px;margin-right:10px;border: 1px solid #868e96;border-radius: 4px;margin-top: 10px;" value="Hạ Long"/> <span class="btn btn-outline-secondary" style="cursor: pointer;margin-top: 10px;">Gợi ý</span>').appendTo(objParent);
                        $('<div>').attr({"class":"bread-tag col-12"}).html('<nav class="breadcrumb"><span class="breadcrumb-item" href="#">Gợi ý tour</span><span class="breadcrumb-item active">Gợi ý nơi ở</span><span class="breadcrumb-item active">Gợi ý nơi ăn</span></nav>').appendTo(objParent);
                        var title = $("<h1>").attr({"class": "default col-12"}).html('<span class="highlight">Gợi ý tour khám phá Hạ Long </span>');
                        objParent.append(title);
                        var objList = $('<div>').attr({"class": "row events paralax-part"});
                        objParent.append(objList);
                        $.each(data.data, function (index, value) {
                            var objChild = $('<div>').attr({"class": "col-lg-12 row event tour-item animated fadeInLeft", "data-animate": "fadeInLeft"});
                            objList.append(objChild);
                            var thumb = '<div class="col-lg-3 col-sm-12 p-0 tour-img"><img src="' + staticURL + "/" + value.thumbnail + '" alt="" class="img-fullsize"></div>';
                            objChild.append(thumb);
                            var content = '<div class="col-lg-6 col-sm-12 p-0">' +
                                    '<div class="event-desc">' +
                                    '<p class="event-title"> ' + value.name + '</p>' +
                                    '<p><strong>Điểm khởi hành:</strong> ' + value.startAddress + '</p>' +
                                    '<p><strong>Lịch khởi hành:</strong> ' + value.departure + '</p>' +
                                    '<p><strong>Phương tiện:</strong> ' + value.traffic + '</p>' +
                                    '<p><strong>Điểm tham quan:</strong> ' + value.location + '</p>' +
                                    '  </div>' +
                                    '</div>' +
                                    '<div class="col-lg-3 col-sm-12 p-0 tour-booking text-center">' +
                                    '<div class="tour-booking-content">' +
                                    '<p>Giá tour</p>' +
                                    '<strong>' + value.price + '</strong>' +
                                    '<a href="#' + self.collection + '/' + value._id + '" class="text-uppercase btn btn-outline-secondary">Chi tiết</a>' +
                                    '</div>' +
                                    '</div>';
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