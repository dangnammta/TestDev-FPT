define(function (require) {
    "use strict";
    var $ = require('jquery'),
            _ = require('underscore');
    Backbone = require('backbone');
    var template = '<section class="form-sect">' +
            '<form>' +
            '   <h1 class="text-uppercase mt-3 mb-5 text-center">Đăng Nhập</h1>' +
            '   <div class="form-group">' +
            '     <label for="exampleInputEmail1" class="text-uppercase">Email</label>' +
            '     <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Nhập email">' +
            '    <small id="emailHelp" class="form-text text-muted">Well never share your email with anyone else.</small>' +
            '   </div>' +
            '   <div class="form-group mt-3">' +
            '    <label for="exampleInputPassword1" class="text-uppercase">Mật khẩu</label>' +
            '    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Nhập mật khẩu">' +
            '   </div>' +
            '   <div class="form-group text-center mt-4">' +
            '    <button type="submit" class="btn btn-primary text-uppercase">Đăng Nhập</button>' +
            '    <div class="row mt-4">' +
            '       <div class="col-lg-6 col-sm-12 text-left"><a href="#"><span>Quên mật khẩu</span></a></div>' +
            '       <div class="col-lg-6 col-sm-12 text-right">Chưa có tài khoản? <a href="#"><span class="text-uppercase">Đăng ký</span></a></div>' +
            '    </div>' +
            '    </div>' +
            '  </form>' +
            '</section>';

    return Backbone.View.extend({
        initialize: function () {
//            this.render();

        },
        render: function () {
            var tpl = _.template(template, {});
            this.$el.html(tpl);

            $(".navbar").addClass("fixed-nav forced-mini-nav");
        }

    });

});