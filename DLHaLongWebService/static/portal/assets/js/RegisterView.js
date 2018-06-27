define(function (require) {
    "use strict";
    var $ = require('jquery'),
            _ = require('underscore');
    Backbone = require('backbone');
    var template = '<section class="form-sect">' +
            '<form>' +
            ' <h1 class="text-uppercase mt-3 mb-5 text-center">Đăng ký</h1>' +
            '  <div class="form-group"> ' +
            '   <label for="exampleInputEmail1" class="text-uppercase">Email</label>' +
            '   <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Nhập email">' +
            '   <small id="emailHelp" class="form-text text-muted">Well never share your email with anyone else. </small>'+
            ' </div>' +
            '  <div class="form-group mt-3">' +
            '    <label for="exampleInputPassword1" class="text-uppercase">Mật khẩu</label>' +
            '    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Nhập mật khẩu">' +
            '  </div>' +
            '  <div class="form-group mt-3">' +
            '   <label for="exampleInputPassword2" class="text-uppercase">Nhập lại mật khẩu</label>' +
            '    <input type="password" class="form-control" id="exampleInputPassword2" placeholder="Nhập lại mật khẩu">' +
            '  </div>' +
            ' <div class="form-group text-center mt-4">' +
            '   <button type="submit" class="btn btn-primary text-uppercase">Đăng Ký</button>' +
            '   <a href="#" class="btn-block mt-4"><i class="fa fa-reply"></i> Trở về trang <span class="text-uppercase">Đăng Nhập</span></a>' +
            '  </div> ' +
            ' </form>' +
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