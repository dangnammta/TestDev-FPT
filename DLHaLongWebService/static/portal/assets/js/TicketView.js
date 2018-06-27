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
            "        <span class=\"breadcrumb-item active\">Hóa đơn tham quan</span>" +
            "      </nav>" +
            "    </div>" +
            "  </section>";

    return Backbone.View.extend({
        initialize: function () {
//            this.render();
        },
        collection: "Ticket",
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
                        var title = $("<h1>").attr({"class": "default col-12"}).html('<span class="highlight">Hóa đơn tham quan Hạ Long </span>');
                        objParent.append(title);
                        var objList = $('<div>').attr({"class": "row events paralax-part"});
                        objParent.append(objList);
                        var class_row = "col-lg-4 col-sm-12 mt-md-6 mt-lg-6 event animate";
                        var animate = "fadeInLeft";
                        $.each(data.data, function (index, value) {
                            if (index % 2 !== 0) {
                                animate = "fadeInRight";
                            }
                            var objChild = $('<div>').attr({"class": class_row, "data-animate": animate});
                            objList.append(objChild);
                            var thumb = '<img src="' + staticURL + "/" + value.thumbnail + '" alt="" class="img-fullsize">';
                            objChild.append(thumb);
                            var content = '<div class="event-desc">' +
                                    '<p class="event-title"> ' + value.name  + '</p>' +
                                    '<p><b>Thời gian:</b> ' + value.time + '</p>' +
                                    '<p><b>Địa điểm:</b> ' + value.location + '</p>' +
//                                    '<p><b>Mô tả:</b> ' + value.description + '</p>' +
                                    '<p><b>Hóa đơn:</b> <span class="price">' + value.price + '</span></p>' +
                                    '  </div>' +
                                    '</div>';
                            objChild.append(content);
                            var btn_buy_ticket = $('<a>').attr({"class": "text-uppercase btn btn-outline-warning btn-block", "href": "javascript:;"}).html("Mua Hóa đơn Ngay");
                            objChild.append(btn_buy_ticket);
                            btn_buy_ticket.click(function () {
                                BootstrapDialog.show({
                                cssClass: "resizeBootstrapDialog",
                                title: 'Thanh toán thành công',
                                message: '<div class="event-desc">' +
                                    '<h2 class="event-title"> ' + value.name  + '</h2>' +
                                    '<p><b>Thời gian:</b> ' + value.time + '</p>' +
                                    '<p><b>Địa điểm:</b> ' + value.location + '</p>' +
                                    '<p><b>Mô tả:</b> ' + value.description + '</p>' +
                                    '<p><b>Hóa đơn:</b> <span class="price">' + value.price + '</span></p>' +
                                    '  </div>' +
                                    '</div>',
                                buttons: [{
                                        label: "Hóa đơn Đã Mua", cssClass: 'btn-warning',
                                        action: function (dialogItself) {
                                            dialogItself.close();
                                            location.href ="#MyTicket";
                                        }
                                    },{
                                        label: "Đóng", cssClass: 'btn-primary',
                                        action: function (dialogItself) {
                                            dialogItself.close();
                                        }
                                    }]
                            });
                            });
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
                        $("#nav_Ticket").addClass("active");

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