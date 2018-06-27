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
            "        <span class=\"breadcrumb-item active\">Quản lý dịch vụ</span>" +
            "      </nav>" +
            "   <h1 style=\"text-align:right;\" class=\" default col-12\"><button id=\"addnew\" class=\"btn btn-warning btn-lg\">Thêm mới dịch vụ</button></h1>" +
            "<div class=\"row events paralax-part\">" +
            "   <div class=\"col-lg-4 col-sm-12 mt-md-6 mt-lg-6 event animated fadeInLeft\" data-animate=\"fadeInLeft\">" +
            "      <img src=\"" + staticURL + "/assets/img/dongthiencung.jpg\" alt=\"\" class=\"img-fullsize\">" +
            "      <div class=\"event-desc\">" +
            "         <p class=\"event-title\"><b>Tên:</b> bán vé du lịch hạ long</p>" +
            "         <p><b>Thời gian:</b> 06:00 - 18:30</p>" +
            "         <p><b>Mô tả:</b> Động Thiên Cung</p>" +
            "         <p><b>Giá vé:</b> <span class=\"price\"> 50.000 VNĐ</span></p>" +
            "      </div>" +
            "      <a class=\"text-uppercase btn btn-outline-warning btn-block\" href=\"javascript:;\">Xóa</a>" +
            "   </div>" +
            "   <div class=\"col-lg-4 col-sm-12 mt-md-6 mt-lg-6 event animated fadeInRight\" data-animate=\"fadeInRight\">" +
            "      <img src=\"" + staticURL + "/assets/img/hangbahang.jpg\" alt=\"\" class=\"img-fullsize\">" +
            "      <div class=\"event-desc\">" +
            "         <p class=\"event-title\"><b>Tên:</b> Vé du lịch đảo hòn trống mái</p>" +
            "         <p><b>Thời gian:</b> 06:00 - 18:30</p>" +
            "         <p><b>Mô tả:</b> Động Thiên Cung</p>" +
            "         <p><b>Giá vé:</b> <span class=\"price\"> 50.000 VNĐ</span></p>" +
            "      </div>" +
            "      <a class=\"text-uppercase btn btn-outline-warning btn-block\" href=\"javascript:;\">Xóa</a>" +
            "   </div>" +
            "   <div class=\"col-lg-4 col-sm-12 mt-md-6 mt-lg-6 event animated fadeInRight\" data-animate=\"fadeInRight\">" +
            "      <img src=\"http://103.9.0.215:80/portal/assets/img/captreoyentukhuhoi.jpg\" alt=\"\" class=\"img-fullsize\">" +
            "      <div class=\"event-desc\">" +
            "         <p class=\"event-title\"> Vé cáp treo Yên Tử khứ hồi</p>" +
            "         <p><b>Thời gian:</b>  05:00 - 20:00</p>" +
            "         <p><b>Địa điểm:</b> Chùa Yên Tử</p>" +
            "         <p><b>Giá vé:</b> <span class=\"price\">280.000 VNĐ</span></p>" +
            "      </div>" +
            "      <a class=\"text-uppercase btn btn-outline-warning btn-block\" href=\"javascript:;\">Xóa</a>" +
            "   </div>" +
            "   <div class=\"col-lg-4 col-sm-12 mt-md-6 mt-lg-6 event animated fadeInRight\" data-animate=\"fadeInRight\">" +
            "      <img src=\"http://103.9.0.215:80/portal/assets/img/duthuyen1.jpg\" alt=\"\" class=\"img-fullsize\">" +
            "      <div class=\"event-desc\">" +
            "         <p class=\"event-title\"> Vé du thuyền standard</p>" +
            "         <p><b>Thời gian:</b>  2/1/2018 - 31/12/2018</p>" +
            "         <p><b>Địa điểm:</b> Vịnh Hạ Long, Hòn Trống Mái</p>" +
            "         <p><b>Giá vé:</b> <span class=\"price\">400.000 VNĐ/H</span></p>" +
            "      </div>" +
            "      <a class=\"text-uppercase btn btn-outline-warning btn-block\" href=\"javascript:;\">Xóa</a>" +
            "   </div>" +
            "   <div class=\"col-lg-4 col-sm-12 mt-md-6 mt-lg-6 event animated fadeInRight\" data-animate=\"fadeInRight\">" +
            "      <img src=\"http://103.9.0.215:80/portal/assets/img/halong.jpg\" alt=\"\" class=\"img-fullsize\">" +
            "      <div class=\"event-desc\">" +
            "         <p class=\"event-title\"> Vịnh Hạ Long</p>" +
            "         <p><b>Thời gian:</b> 06:00 - 18:30</p>" +
            "         <p><b>Địa điểm:</b> Vịnh Hạ Long</p>" +
            "         <p><b>Giá vé:</b> <span class=\"price\"> 120.000 VNĐ</span></p>" +
            "      </div>" +
            "      <a class=\"text-uppercase btn btn-outline-warning btn-block\" href=\"javascript:;\">Xóa</a>" +
            "   </div>" +
            "</div>" +
            "    </div>" +
            "  </section>";

    return Backbone.View.extend({
        initialize: function () {
        },
        collection: "Service",
        render: function () {
            var tpl = _.template(template, {});
            this.$el.html(tpl);

            $("#addnew").click(function () {
                BootstrapDialog.show({
                    cssClass: "resizeBootstrapDialog",
                    title: 'Thêm dịch vụ',
                    message: '<div class="event-desc">' +
                            '<div class="form-group"><label>Tên dịch vụ:</label>  <input class="form-control" ></input></div>' +
                            '<div class="form-group"><label>Loại dịch vụ:</label>  <select class="form-control" >'+
                                    '<option>Tour du lịch</option><option>Khách sạn</option><option>Vé thăm quan</option><option>Di chuyển</option></select></div>' +
                            '<div class="form-group"><label>Chọn ảnh đại diện:</label>  <input class="form-control"  type="file"></input></div>' +
                            '<div class="form-group"><label>Giá dịch vụ</label>  <input class="form-control" ></input></div>' +
                            '<div class="form-group"><label>Mô tả dịch vụ:</label>  <textarea class="form-control" ></textarea></div>' +
                            '</div>',
                    buttons: [{
                            label: "Thêm", cssClass: 'btn-primary',
                            action: function (dialogItself) {
                                dialogItself.close();
//                                            location.href ="#MyTicket";
                            }
                        }, {
                            label: "Đóng", cssClass: 'btn-default',
                            action: function (dialogItself) {
                                dialogItself.close();
                            }
                        }]
                });
            });
        }

    });

});