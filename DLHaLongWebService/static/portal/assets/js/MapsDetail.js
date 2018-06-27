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
            "        <span id=\"menu_active\" class=\"breadcrumb-item active\">Lịch trình du lịch</span>" +
            "      </nav>" +
//            "      <h1>" + data.name + "</h1>" +
//            data.content +
            "    </div>" +
            "  </section>";
    var html_related_abstraction = '<div style="padding:0px;margin-top:30px;" class="col-sm-12">' +
            '      <h2 class="mt-2 mb-4">Địa danh khác</h2>' +
            '    <div class="hot-locations p-0">' +
            '    <div class="card">' +
            '      <img class="card-img-top" src="' + staticURL + '/assets/img/img-service-02.png" alt="...">' +
            '      <div class="card-body">' +
            '       <h5 class="card-title wrap-1-line"><span class="highlight">Địa Danh</span> - <strong>Đảo Quan Lạn</strong></h5>' +
            '       <p class="card-text wrap-1-line">Thị trấn Vân Đồn, Quảng Ninh</p>' +
            '       <div class="rate view">' +
            '          <a href="#" class="active">♥</a>' +
            '         <a href="#" class="active">♥</a>' +
            '          <a href="#" class="active">♥</a>' +
            '          <a href="#">♥</a>' +
            '          <a href="#">♥</a>' +
            '         </div>' +
            '      </div>' +
            '     </div>' +
            '    <div class="card">' +
            '      <img class="card-img-top" src="' + staticURL + '/assets/img/img-service-03.png" alt="...">' +
            '      <div class="card-body">' +
            '       <h5 class="card-title wrap-1-line"><span class="highlight">Địa Danh</span> - <strong>Bãi Vân Đồn</strong></h5>' +
            '        <p class="card-text wrap-1-line">Thị trấn Vân Đồn, Quảng Ninh</p>' +
            '         <div class="rate view">' +
            '          <a href="#" class="active">♥</a>' +
            '          <a href="#" class="active">♥</a>' +
            '          <a href="#" class="active">♥</a>' +
            '          <a href="#" class="active">♥</a>' +
            '          <a href="#" class="active">♥</a>' +
            '        </div>' +
            '       </div>' +
            '     </div>' +
            '     <div class="card">' +
            '       <img class="card-img-top" src="' + staticURL + '/assets/img/img-service-01.png" alt="...">' +
            '       <div class="card-body">' +
            '         <h5 class="card-title wrap-1-line"><span class="highlight">Địa Danh</span> - <strong>Vịnh Hạ Long</strong></h5>' +
            '         <p class="card-text wrap-1-line">Thành phố Hạ Long, Quảng Ninh</p>' +
            '         <div class="rate view">' +
            '           <a href="#" class="active">♥</a>' +
            '           <a href="#" class="active">♥</a>' +
            '           <a href="#" class="active">♥</a>' +
            '           <a href="#">♥</a>' +
            '          <a href="#">♥</a>' +
            '        </div>' +
            '      </div>' +
            '     </div>' +
            '   </div>' +
            ' </div>';
    var html_related_event = '<div style="padding:0px;margin-top:30px;" class="col-sm-12">' +
            '      <h2 class="mt-2 mb-4">Sự kiện khác</h2>' +
            '    <div class="hot-locations p-0">' +
            '    <div class="card">' +
            '      <img class="card-img-top" src="' + staticURL + '/assets/img/sknembotmau.jpg" alt="...">' +
            '      <div class="card-body">' +
            '       <h5 class="card-title wrap-1-line"><span class="highlight">Sự Kiện</span> - <strong>Lễ hội ném bột màu</strong></h5>' +
            '       <p class="card-text wrap-1-line">Thị trấn Vân Đồn, Quảng Ninh</p>' +
            '       <div class="rate view">' +
            '          <a href="#" class="active">♥</a>' +
            '         <a href="#" class="active">♥</a>' +
            '          <a href="#" class="active">♥</a>' +
            '          <a href="#">♥</a>' +
            '          <a href="#">♥</a>' +
            '         </div>' +
            '      </div>' +
            '     </div>' +
            '    <div class="card">' +
            '      <img class="card-img-top" src="' + staticURL + '/assets/img/skcarnaval.jpg" alt="...">' +
            '      <div class="card-body">' +
            '       <h5 class="card-title wrap-1-line"><span class="highlight">Sự Kiện</span> - <strong>Lễ hội Carnaval Hạ Long 2018</strong></h5>' +
            '        <p class="card-text wrap-1-line">Thị trấn Vân Đồn, Quảng Ninh</p>' +
            '         <div class="rate view">' +
            '          <a href="#" class="active">♥</a>' +
            '          <a href="#" class="active">♥</a>' +
            '          <a href="#" class="active">♥</a>' +
            '          <a href="#" class="active">♥</a>' +
            '          <a href="#" class="active">♥</a>' +
            '        </div>' +
            '       </div>' +
            '     </div>' +
            '     <div class="card">' +
            '       <img class="card-img-top" src="' + staticURL + '/assets/img/skamthucvh.jpg" alt="...">' +
            '       <div class="card-body">' +
            '         <h5 class="card-title wrap-1-line"><span class="highlight">Sự Kiện</span> - <strong>Ngày hội Ẩm thực & Văn hóa châu Á</strong></h5>' +
            '         <p class="card-text wrap-1-line">Thành phố Hạ Long, Quảng Ninh</p>' +
            '         <div class="rate view">' +
            '           <a href="#" class="active">♥</a>' +
            '           <a href="#" class="active">♥</a>' +
            '           <a href="#" class="active">♥</a>' +
            '           <a href="#">♥</a>' +
            '          <a href="#">♥</a>' +
            '        </div>' +
            '      </div>' +
            '     </div>' +
            '   </div>' +
            ' </div>';
    var html_related_hotel = '<div style="padding:0px;margin-top:30px;" class="col-sm-12">' +
            '      <h2 class="mt-2 mb-4">Khách sạn khác</h2>' +
            '    <div class="hot-locations p-0">' +
            '    <div class="card">' +
            '      <img class="card-img-top" src="' + staticURL + '/assets/img/ks8.jpg" alt="...">' +
            '      <div class="card-body">' +
            '       <h5 class="card-title wrap-1-line"><span class="highlight">Khách Sạn</span> - <strong>Hạ Long Plaza</strong></h5>' +
            '       <p class="card-text wrap-1-line">Thị trấn Vân Đồn, Quảng Ninh</p>' +
            '       <div class="rate view">' +
            '          <a href="#" class="active">♥</a>' +
            '         <a href="#" class="active">♥</a>' +
            '          <a href="#" class="active">♥</a>' +
            '          <a href="#">♥</a>' +
            '          <a href="#">♥</a>' +
            '         </div>' +
            '      </div>' +
            '     </div>' +
            '    <div class="card">' +
            '      <img class="card-img-top" src="' + staticURL + '/assets/img/ks5.jpg" alt="...">' +
            '      <div class="card-body">' +
            '       <h5 class="card-title wrap-1-line"><span class="highlight">Khách Sạn</span> - <strong>Sea Star Hạ Long</strong></h5>' +
            '        <p class="card-text wrap-1-line">Thị trấn Vân Đồn, Quảng Ninh</p>' +
            '         <div class="rate view">' +
            '          <a href="#" class="active">♥</a>' +
            '          <a href="#" class="active">♥</a>' +
            '          <a href="#" class="active">♥</a>' +
            '          <a href="#" class="active">♥</a>' +
            '          <a href="#" class="active">♥</a>' +
            '        </div>' +
            '       </div>' +
            '     </div>' +
            '     <div class="card">' +
            '       <img class="card-img-top" src="' + staticURL + '/assets/img/ks9.jpg" alt="...">' +
            '       <div class="card-body">' +
            '         <h5 class="card-title wrap-1-line"><span class="highlight">Khách Sạn</span> - <strong>Star City Ha Long Bay</strong></h5>' +
            '         <p class="card-text wrap-1-line">Thành phố Hạ Long, Quảng Ninh</p>' +
            '         <div class="rate view">' +
            '           <a href="#" class="active">♥</a>' +
            '           <a href="#" class="active">♥</a>' +
            '           <a href="#" class="active">♥</a>' +
            '           <a href="#">♥</a>' +
            '          <a href="#">♥</a>' +
            '        </div>' +
            '      </div>' +
            '     </div>' +
            '   </div>' +
            ' </div>';
    var html_related_restaurant = '<div style="padding:0px;margin-top:30px;" class="col-sm-12">' +
            '      <h2 class="mt-2 mb-4">Nhà hàng khác</h2>' +
            '    <div class="hot-locations p-0">' +
            '    <div class="card">' +
            '      <img class="card-img-top" src="' + staticURL + '/assets/img/nh1.jpg" alt="...">' +
            '      <div class="card-body">' +
            '       <h5 class="card-title wrap-1-line"><span class="highlight">Nhà Hàng</span> - <strong>Nhà hàng Akoya</strong></h5>' +
            '       <p class="card-text wrap-1-line">Thị trấn Vân Đồn, Quảng Ninh</p>' +
            '       <div class="rate view">' +
            '          <a href="#" class="active">♥</a>' +
            '         <a href="#" class="active">♥</a>' +
            '          <a href="#" class="active">♥</a>' +
            '          <a href="#">♥</a>' +
            '          <a href="#">♥</a>' +
            '         </div>' +
            '      </div>' +
            '     </div>' +
            '    <div class="card">' +
            '      <img class="card-img-top" src="' + staticURL + '/assets/img/nh2.jpg" alt="...">' +
            '      <div class="card-body">' +
            '       <h5 class="card-title wrap-1-line"><span class="highlight">Nhà Hàng</span> - <strong>Nhà hàng Pavilion</strong></h5>' +
            '        <p class="card-text wrap-1-line">Thị trấn Vân Đồn, Quảng Ninh</p>' +
            '         <div class="rate view">' +
            '          <a href="#" class="active">♥</a>' +
            '          <a href="#" class="active">♥</a>' +
            '          <a href="#" class="active">♥</a>' +
            '          <a href="#" class="active">♥</a>' +
            '          <a href="#" class="active">♥</a>' +
            '        </div>' +
            '       </div>' +
            '     </div>' +
            '     <div class="card">' +
            '       <img class="card-img-top" src="' + staticURL + '/assets/img/nh3.jpg" alt="...">' +
            '       <div class="card-body">' +
            '         <h5 class="card-title wrap-1-line"><span class="highlight">Nhà Hàng</span> - <strong>Nhà hàng Bayview</strong></h5>' +
            '         <p class="card-text wrap-1-line">Thành phố Hạ Long, Quảng Ninh</p>' +
            '         <div class="rate view">' +
            '           <a href="#" class="active">♥</a>' +
            '           <a href="#" class="active">♥</a>' +
            '           <a href="#" class="active">♥</a>' +
            '           <a href="#">♥</a>' +
            '          <a href="#">♥</a>' +
            '        </div>' +
            '      </div>' +
            '     </div>' +
            '   </div>' +
            ' </div>';
    return Backbone.View.extend({
        initialize: function () {
//            this.render();
        },
        render: function (object, id) {
            var tpl = _.template(template, {});
            this.$el.html(tpl);
            console.log(object);
            var html_related = html_related_abstraction;
            if (object === "Event") {
                html_related = html_related_event;
            } else if (object === "Restaurant") {
                html_related = html_related_restaurant;
            } else if (object === "Hotel") {
                html_related = html_related_hotel;
            }
            var self = this;
            $("#menu_active").html(arrCollection[object]);
            var path = "/common/" + object + "/" + id;
            $.ajax({
                url: (serviceURL || "") + path,
                data: {"limit": 10},
                dataType: "json",
                contentType: "application/json",
                success: function (data) {
                    console.log(data);
                    if (!!data && data.error_code === 0) {
                        var urlBooKing = "javascript:;";
                        if (data.data.marketUrl !== undefined && data.data.marketUrl !== '') {
                            urlBooKing = data.data.marketUrl;
                        }
                        ;
                        $("#banner").css('background-image', 'url(' + staticURL + "/" + data.data.banner + ')');
                        var objContent = self.$el.find(".detail-sect .container");
                        var objParent = $('<div>').attr({"class": "row"});
                        objContent.append(objParent);
                        var title = $("<div>").attr({"class": "col-lg-9 col-xs-12"}).html('<h1 style="line-height: inherit;">' + data.data.name + ' <a style="border:0px;border-radius:16px;color: white;background: #fb8d05" class="text-uppercase btn btn-outline-secondary" href="' + urlBooKing + '">đặt dịch vụ</a></h1>');
                        objParent.append(title);
//                        $("<div>").attr({"class": "col-lg-3 col-xs-12"}).html('<img class="card-img-top" src="' + staticURL + '/assets/img/bandoksplaza.png"/>').appendTo(objParent);
                        var objDetail = $('<div>').attr({"class": "col-lg-9 col-sm-12"});
                        objParent.append(objDetail);
                        var html_maps = '<div class="col-lg-3 col-sm-12">' +
                                '      <h2 class="mt-2 mb-4">Bản đồ</h2>' +
                                '<img class="card-img-top" src="' + staticURL + '/assets/img/bandoksplaza.png"/>';

                        $('<p>').text(data.data.address).appendTo(objDetail);
                        $('<div>').attr({"class": "rate view mb-4"}).html('<a href="#" class="active">♥</a>' +
                                '<a href="#" class="active">♥</a>' +
                                '<a href="#" class="active">♥</a>' +
                                '<a href="#" class="active">♥</a>' +
                                '<a href="#">♥</a>').appendTo(objDetail);
                        $('<p>').attr({"class": "tour-short-desc font-weight-bold"}).text(data.data.description).appendTo(objDetail);
                        $('<p>').attr({"class": "richtext"}).html(data.data.content).appendTo(objDetail);
                        $('<hr>').appendTo(objDetail);
                        $('<div>').attr({"class": "comments-part"}).html('<h2 class="mt-4">Bình luận:</h2>' +
                                '<div class="comments mb-4">' +
                                '<div class="card comment mb-3">' +
                                '   <div class="card-body">' +
                                '    <img src="' + staticURL + '/assets/img/default-avatar.jpg" alt="" width="50" class="float-left mr-3">' +
                                '    <a href="#" class="highlight text-uppercase">LongMD</a>' +
                                '    <p>Good.</p>' +
                                '<div class="rate view" style="text-align: right;position: absolute;right: 0px;bottom: 50px;"> ' +
                                '         <a href="#" class="active">♥</a> ' +
                                '         <a href="#" class="active">♥</a>  ' +
                                '        <a href="#" class="active">♥</a>     ' +
                                '     <a href="#" class="active">♥</a>         ' +
                                ' <a href="#" class="active">♥</a>        ' +
                                '</div>' +
                                '    <small class="text-muted d-block text-right">3 phút trước</small>' +
                                '  </div>' +
                                ' </div>' +
                                '  <div class="card comment mb-3">' +
                                '   <div class="card-body">' +
                                '    <img src="' + staticURL + '/assets/img/default-avatar.jpg" alt="" width="50" class="float-left mr-3">' +
                                '    <a href="#" class="highlight text-uppercase">AnBQ</a>' +
                                '    <p>Very Good</p>' +
                                '<div class="rate view" style="text-align: right;position: absolute;right: 0px;bottom: 50px;"> ' +
                                '         <a href="#" class="active">♥</a> ' +
                                '         <a href="#" class="active">♥</a>  ' +
                                '        <a href="#" class="active">♥</a>     ' +
                                '     <a href="#" class="active">♥</a>         ' +
                                ' <a href="#" class="active">♥</a>        ' +
                                '</div>' +
                                '    <small class="text-muted d-block text-right">5 phút trước</small>' +
                                '   </div>' +
                                ' </div>' +
                                ' <div class="card comment mb-3">' +
                                '  <div class="card-body">' +
                                '    <img src="' + staticURL + '/assets/img/default-avatar.jpg" alt="" width="50" class="float-left mr-3">' +
                                '    <a href="#" class="highlight text-uppercase">NamDV</a>' +
                                '    <p>Checked.</p>' +
                                '<div class="rate view" style="text-align: right;position: absolute;right: 0px;bottom: 50px;"> ' +
                                '         <a href="#" class="active">♥</a> ' +
                                '         <a href="#" class="active">♥</a>  ' +
                                '        <a href="#" class="active">♥</a>     ' +
                                '     <a href="#" class="active">♥</a>         ' +
                                ' <a href="#" class="active">♥</a>        ' +
                                '</div>' +
                                '    <small class="text-muted d-block text-right">6 phút trước</small>' +
                                '  </div>' +
                                ' </div>' +
                                '  <div class="card comment mb-3">' +
                                '   <div class="card-body">' +
                                '    <img src="' + staticURL + '/assets/img/default-avatar.jpg" alt="" width="50" class="float-left mr-3">' +
                                '    <a href="#" class="highlight text-uppercase">ThuyNT</a>' +
                                '    <p>Like</p>' +
                                '<div class="rate view" style="text-align: right;position: absolute;right: 0px;bottom: 50px;"> ' +
                                '         <a href="#" class="active">♥</a> ' +
                                '         <a href="#" class="active">♥</a>  ' +
                                '        <a href="#" class="active">♥</a>     ' +
                                '     <a href="#" class="active">♥</a>         ' +
                                ' <a href="#" class="active">♥</a>        ' +
                                '</div>' +
                                '    <small class="text-muted d-block text-right">7 phút trước</small>' +
                                '   </div>' +
                                ' </div>' +
                                '  <div class="card comment mb-3">' +
                                '    <div class="card-body">' +
                                '    <img src="' + staticURL + '/assets/img/default-avatar.jpg" alt="" width="50" class="float-left mr-3">' +
                                '    <a href="#" class="highlight text-uppercase">KhangBQ</a>' +
                                '    <p>Ahihi</p>' +
                                '<div class="rate view" style="text-align: right;position: absolute;right: 0px;bottom: 50px;"> ' +
                                '         <a href="#" class="active">♥</a> ' +
                                '         <a href="#" class="active">♥</a>  ' +
                                '        <a href="#" class="active">♥</a>     ' +
                                '     <a href="#" class="active">♥</a>         ' +
                                ' <a href="#" class="active">♥</a>        ' +
                                '</div>' +
                                '    <small class="text-muted d-block text-right">10 phút trước</small>' +
                                '   </div>' +
                                ' </div>' +
                                '</div>' +
                                '<hr>' +
                                '<form action="" class="row">' +
                                ' <h5 class="text-uppercase mt-4 mb-3 col-12">Phần bình luận của bạn</h5>' +
                                ' <div class="form-group col-lg-4 col-sm-12">' +
                                '  <label for="ur-comment">Đánh giá:</label>' +
                                '  <div class="mb-2 pl-2"><span class="rate-text highlight">0</span> trên 5</div>' +
                                '  <div class="rate">' +
                                '    <a href="#">♥</a>' +
                                '    <a href="#">♥</a>' +
                                '    <a href="#">♥</a>' +
                                '    <a href="#">♥</a>' +
                                '   <a href="#">♥</a>' +
                                '   </div>' +
                                '   <small class="text-muted pl-2 mt-4 d-block">3 đánh giá</small>' +
                                '   <input type="hidden" class="rate-value">' +
                                '  </div>' +
                                '  <div class="form-group col-lg-8 col-sm-12">' +
                                '    <label for="ur-comment">Nội dung:</label>' +
                                '    <textarea class="form-control" id="ur-comment" rows="3" placeholder="Nhập nội dung đánh giá của bạn"></textarea>' +
                                '    <button id="submit-comment" type="submit" class="btn btn-outline-warning btn-block ml-auto mt-3">Gửi đánh giá</button>' +
                                '  </div>' +
                                '</form>').appendTo(objDetail);

                        objParent.append(html_maps + html_related + "</div>");
                        $('.nav-item').removeClass('active');
                        $("#nav_" + object).addClass("active");
                        layoutRender.rateInit();
                        $("#submit-comment").click(function () {
                            var cmtText = $("#ur-comment").val();
                            if (cmtText.length > 0) {
                                $(".comments").prepend($('<div>').attr({"class": "card comment"}).html('   <div class="card-body">' +
                                        '     <img src="' + staticURL + '/assets/img/default-avatar.jpg" alt="" width="50" class="float-left mr-3">' +
                                        '<a href="#" class="highlight text-uppercase">Nam Dang</a>' +
                                        '<p>' + cmtText + '</p>' +
                                        '<div class="rate view" style="text-align: right;position: absolute;right: 0px;bottom: 50px;"> ' +
                                '         <a href="#" class="active">♥</a> ' +
                                '         <a href="#" class="active">♥</a>  ' +
                                '        <a href="#" class="active">♥</a>     ' +
                                '     <a href="#" class="active">♥</a>         ' +
                                ' <a href="#" class="active">♥</a>        ' +
                                '</div>' +
                                        '<small class="text-muted d-block text-right">Vừa xong</small>' +
                                        '  </div>'));
                                $("#ur-comment").val('');
                            }
                        });
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