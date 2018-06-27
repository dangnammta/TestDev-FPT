define(function (require) {
    "use strict";
    var $ = require('jquery'),
            _ = require('underscore');
    Backbone = require('backbone');
    var template = '<section class=\"img-banner\" id=\"banner\" >' +
            '    <div class=\"container\"> ' +
            '    </div> ' +
            '  </section> ' +
            '  <section class=\"detail-sect  events-sect\"> ' +
            '    <div class="container">' +
            '     <div class="row">' +
//            '         <div class="welcome-line mt-4 col-12">' +
//            '            Xin chào <a href="#" class="highlight">Dang Nam</a> <button type="button" class="btn btn-warning btn-sm ml-2">Thoát</button>' +
//            '        </div>' +
            '      <h1 class="default col-12">Hóa đơn của tôi <span class="badge badge-pill badge-dark">10</span></h1>' +
            ' <div class="row events paralax-part">' +
            '    <div class="col-lg-12 row event animate tour-item" data-animate="fadeInLeft">' +
            '         <div class="col-lg-3 col-sm-12 p-0 tour-img">' +
            '               <img src="' + staticURL + '/assets/img/hangbahang.jpg'+'" alt="" class="img-fullsize">' +
            '          </div>' +
            '            <div class="col-lg-6 col-sm-12 p-0">' +
            '                 <div class="event-desc">' +
            '             <p class="event-title">Vịnh Hạ long</p>' +
            '           <p><strong>Điểm khởi hành: </strong> Hà Nội, Hải Phòng, Quảng Ninh</p>' +
            '             <p><strong>Lịch khởi hành:</strong> Hàng ngày</p>' +
            '              <p><strong>Số lượng hóa đơn: </strong>10</p>' +
            '               <p><strong>Người mua: </strong>Trần Kim Anh</p>' +
            '                <p><strong>Điện thoại: </strong>0908171789</p>' +
            '             </div>' +
            '          </div>' +
            '          <div class="col-lg-3 col-sm-12 p-0 tour-booking text-center">' +
            '              <div class="tour-booking-content">' +
            '                   <p>Giá tiền</p>' +
            '                   <strong>1.000.000 VND</strong>' +
            '                   <a href="javascript:;" class="text-uppercase btn btn-outline-secondary">Lấy mã QR</a>' +
            '                </div>' +
            '             </div>' +
            '          </div>' +
            '           <div class="col-lg-12 row event animate tour-item" data-animate="fadeInLeft">' +
            '                <div class="col-lg-3 col-sm-12 p-0 tour-img">' +
            '      <img src="' + staticURL + '/assets/img/dongthiencung.jpg'+'" alt="" class="img-fullsize">' +
            '   </div>' +
            '    <div class="col-lg-6 col-sm-12 p-0">' +
            '         <div class="event-desc">' +
            '              <p class="event-title">Hang Sửng Sốt</p>' +
            '               <p><strong>Điểm khởi hành: </strong> Hà Nội, Hải Phòng, Quảng Ninh</p>' +
            '                <p><strong>Lịch khởi hành:</strong> Hàng ngày</p>' +
            '                 <p><strong>Số lượng hóa đơn: </strong>10</p>' +
            '                  <p><strong>Người mua: </strong>Trần Kim Anh</p>' +
            '                   <p><strong>Điện thoại: </strong>0908171789</p>' +
            '                </div>' +
            '             </div>' +
            ' <div class="col-lg-3 col-sm-12 p-0 tour-booking text-center">' +
            '      <div class="tour-booking-content">' +
            '           <p>Giá tiền</p>' +
            '            <strong>1.500.000 VND</strong>' +
            '             <a href="javascript:;" class="text-uppercase btn btn-outline-secondary">Lấy mã QR</a>' +
            '          </div>' +
            '       </div>' +
            '    </div>' +
            '     <div class="col-lg-12 row event animate tour-item" data-animate="fadeInLeft">' +
            '         <div class="col-lg-3 col-sm-12 p-0 tour-img">' +
            '              <img src="' + staticURL + '/assets/img/captreoyentukhuhoi.jpg'+'" alt="" class="img-fullsize">' +
            '           </div>' +
            '            <div class="col-lg-6 col-sm-12 p-0">' +
            '              <div class="event-desc">' +
            '                   <p class="event-title">Vịnh Hạ long</p>' +
            '                   <p><strong>Điểm khởi hành: </strong> Hà Nội, Hải Phòng, Quảng Ninh</p>' +
            '                   <p><strong>Lịch khởi hành:</strong> Hàng ngày</p>' +
            '                   <p><strong>Số lượng hóa đơn: </strong>10</p>' +
            '                   <p><strong>Người mua: </strong>Trần Kim Anh</p>' +
            '                   <p><strong>Điện thoại: </strong>0908171789</p>' +
            '               </div>' +
            '           </div>' +
            '           <div class="col-lg-3 col-sm-12 p-0 tour-booking text-center">' +
            '               <div class="tour-booking-content">' +
            '                    <p>Giá tiền</p>' +
            '                     <strong>1.000.000 VND</strong>' +
            '                      <a href="javascript:;" class="text-uppercase btn btn-outline-secondary">Lấy mã QR</a>' +
            '                   </div>' +
            '                </div>' +
            '  </div>' +
            '   <div class="col-lg-12 row event animate tour-item" data-animate="fadeInLeft">' +
            '        <div class="col-lg-3 col-sm-12 p-0 tour-img">' +
            '             <img src="' + staticURL + '/assets/img/dongthiencung.jpg'+'" alt="" class="img-fullsize">' +
            '          </div>' +
            '        <div class="col-lg-6 col-sm-12 p-0">' +
            '               <div class="event-desc">' +
            '                 <p class="event-title">Hang Sửng Sốt</p>' +
            '                   <p><strong>Điểm khởi hành: </strong> Hà Nội, Hải Phòng, Quảng Ninh</p>' +
            '                    <p><strong>Lịch khởi hành:</strong> Hàng ngày</p>' +
            '                     <p><strong>Số lượng hóa đơn: </strong>10</p>' +
            '                      <p><strong>Người mua: </strong>Trần Kim Anh</p>' +
            '                       <p><strong>Điện thoại: </strong>0908171789</p>' +
            '                    </div>' +
            '                </div>' +
            '                 <div class="col-lg-3 col-sm-12 p-0 tour-booking text-center">' +
            '                      <div class="tour-booking-content">' +
            '                           <p>Giá tiền</p>' +
            '                            <strong>1.500.000 VND</strong>' +
            '                             <a href="javascript:;" class="text-uppercase btn btn-outline-secondary">Lấy mã QR</a>' +
            '                          </div>' +
            '                       </div>' +
            '                    </div>' +
            '        <div class="col-lg-12 row event animate tour-item" data-animate="fadeInLeft">' +
            '             <div class="col-lg-3 col-sm-12 p-0 tour-img">' +
            '                  <img src="' + staticURL + '/assets/img/hangbahang.jpg'+'" alt="" class="img-fullsize">' +
            '               </div>' +
            '<div class="col-lg-6 col-sm-12 p-0">' +
            '    <div class="event-desc">' +
            '         <p class="event-title">Vịnh Hạ long</p>' +
            '          <p><strong>Điểm khởi hành: </strong> Hà Nội, Hải Phòng, Quảng Ninh</p>' +
            '           <p><strong>Lịch khởi hành:</strong> Hàng ngày</p>' +
            '            <p><strong>Số lượng hóa đơn: </strong>10</p>' +
            '             <p><strong>Người mua: </strong>Trần Kim Anh</p>' +
            '              <p><strong>Điện thoại: </strong>0908171789</p>' +
            '           </div>' +
            '        </div>' +
            '         <div class="col-lg-3 col-sm-12 p-0 tour-booking text-center">' +
            '              <div class="tour-booking-content">' +
            '                   <p>Giá tiền</p>' +
            '                    <strong>1.000.000 VND</strong>' +
            '                     <a href="javascript:;" class="text-uppercase btn btn-outline-secondary">Lấy mã QR</a>' +
            '                  </div>' +
            '               </div>' +
            '        </div>' +
            '         <div class="col-lg-12 row event animate tour-item" data-animate="fadeInLeft">' +
            '    <div class="col-lg-3 col-sm-12 p-0 tour-img">' +
            '         <img src="' + staticURL + '/assets/img/hangbahang.jpg'+'" alt="" class="img-fullsize">' +
            '      </div>' +
            '       <div class="col-lg-6 col-sm-12 p-0">' +
            '            <div class="event-desc">' +
            '                 <p class="event-title">Hang Sửng Sốt</p>' +
            '                  <p><strong>Điểm khởi hành: </strong> Hà Nội, Hải Phòng, Quảng Ninh</p>' +
            '                   <p><strong>Lịch khởi hành:</strong> Hàng ngày</p>' +
            '                    <p><strong>Số lượng hóa đơn: </strong>10</p>' +
            '                     <p><strong>Người mua: </strong>Trần Kim Anh</p>' +
            '                      <p><strong>Điện thoại: </strong>0908171789</p>' +
            '                   </div>' +
            '                </div>' +
            '                 <div class="col-lg-3 col-sm-12 p-0 tour-booking text-center">' +
            '                      <div class="tour-booking-content">' +
            '                           <p>Giá tiền</p>' +
            '                            <strong>1.500.000 VND</strong>' +
            '                             <a href="javascript:;" class="text-uppercase btn btn-outline-secondary">Lấy mã QR</a>' +
            '                          </div>' +
            '                       </div>' +
            '                    </div>' +
            '           <div class="col-lg-12 row event animate tour-item" data-animate="fadeInLeft">' +
            '                <div class="col-lg-3 col-sm-12 p-0 tour-img">' +
            '         <img src="' + staticURL + '/assets/img/hangbahang.jpg'+'" alt="" class="img-fullsize">' +
            '      </div>' +
            '       <div class="col-lg-6 col-sm-12 p-0">' +
            '            <div class="event-desc">' +
            '                 <p class="event-title">Vịnh Hạ long</p>' +
            '                  <p><strong>Điểm khởi hành: </strong> Hà Nội, Hải Phòng, Quảng Ninh</p>' +
            '                   <p><strong>Lịch khởi hành:</strong> Hàng ngày</p>' +
            '                    <p><strong>Số lượng hóa đơn: </strong>10</p>' +
            '                     <p><strong>Người mua: </strong>Trần Kim Anh</p>' +
            '                      <p><strong>Điện thoại: </strong>0908171789</p>' +
            '                   </div>' +
            '               </div>' +
            '                <div class="col-lg-3 col-sm-12 p-0 tour-booking text-center">' +
            '                    <div class="tour-booking-content">' +
            '                        <p>Giá tiền</p>' +
            '                        <strong>1.000.000 VND</strong>' +
            '                        <a href="javascript:;" class="text-uppercase btn btn-outline-secondary">Lấy mã QR</a>' +
            '                    </div>' +
            '                </div>' +
            '            </div>' +
            '             <div class="col-lg-12 row event animate tour-item" data-animate="fadeInLeft">' +
            '  <div class="col-lg-3 col-sm-12 p-0 tour-img">' +
            '       <img src="' + staticURL + '/assets/img/dongthiencung.jpg'+'" alt="" class="img-fullsize">' +
            '    </div>' +
            '     <div class="col-lg-6 col-sm-12 p-0">' +
            '          <div class="event-desc">' +
            '               <p class="event-title">Hang Sửng Sốt</p>' +
            '                <p><strong>Điểm khởi hành: </strong> Hà Nội, Hải Phòng, Quảng Ninh</p>' +
            '                 <p><strong>Lịch khởi hành:</strong> Hàng ngày</p>' +
            '                  <p><strong>Số lượng hóa đơn: </strong>10</p>' +
            '                   <p><strong>Người mua: </strong>Trần Kim Anh</p>' +
            '                    <p><strong>Điện thoại: </strong>0908171789</p>' +
            '    </div>' +
            ' </div>' +
            '  <div class="col-lg-3 col-sm-12 p-0 tour-booking text-center">' +
            '       <div class="tour-booking-content">' +
            '            <p>Giá tiền</p>' +
            '             <strong>1.500.000 VND</strong>' +
            '              <a href="javascript:;" class="text-uppercase btn btn-outline-secondary">Lấy mã QR</a>' +
            '           </div>' +
            '        </div>' +
            '     </div>' +
            '  </div>' +
            '   <nav aria-label="Page navigation" class="col-12">' +
            '        <ul class="pagination justify-content-center">' +
            '             <li class="page-item disabled"><span class="page-link" href="javascript:;">Trước</span></li>' +
            '              <li class="page-item active"><a class="page-link" href="javascript:;">1</a></li>' +
            '                <li class="page-item"><a class="page-link" href="javascript:;">2</a></li>' +
            '               <li class="page-item"><a class="page-link" href="javascript:;">3</a></li>' +
            '                <li class="page-item"><a class="page-link" href="javascript:;">Tiếp</a></li>' +
            '            </ul>' +
            '        </nav>' +
            '    </div>' +
            ' </div>';
    "  </section>";

    return Backbone.View.extend({
        initialize: function () {
//            this.render();
        },
        collection: "MyTicket",
        render: function () {
            console.log(this.collection);
            var tpl = _.template(template, {});
            this.$el.html(tpl);
            $('.tour-booking-content .btn').click(function (){
                BootstrapDialog.show({
                                cssClass: "resizeBootstrapDialog",
                                title: 'Mã QR code',
                                message: '<div class="event-desc text-center">' +
                                    '<img src="' + staticURL + '/assets/img/QR_code.jpg'+'" alt="" class="">' +
                                    '</div>',
                                buttons: [{
                                        label: "Đóng", cssClass: 'btn-primary',
                                        action: function (dialogItself) {
                                            dialogItself.close();
                                        }
                                    }]
                            });
            });
            $('.nav-item').removeClass('active');
            $("#nav_Ticket").addClass("active");
        }

    });

});