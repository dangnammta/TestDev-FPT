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
            "  <section class=\"detail-sect\">" +
            "    <div class=\"container\">" +
            "      <nav class=\"breadcrumb\">" +
            "        <a class=\"breadcrumb-item\" href=\"#/\">Trang chủ</a>" +
            "        <span id=\"menu_active\" class=\"breadcrumb-item active\">Category</span>" +
            "      </nav>" +
            "      " +
//            "      <h1>" + data.name + "</h1>" +
//            data.content +
            "    </div>" +
            "  </section>";

    return Backbone.View.extend({
        initialize: function () {
//            this.render();
        },
        render: function (collectionName, id) {

            var tpl = _.template(template, {});
            this.$el.html(tpl);

            var self = this;
            var path = "/common/"+collectionName + "/" + id;
            $("#menu_active").html(arrCollection[collectionName]);
            $.ajax({
                url: (serviceURL || "") + path,
//                data: {"q": JSON.stringify({"filters": filters, "single": true})},
                dataType: "json",
                contentType: "application/json",
                success: function (data) {
                    console.log(data);
                    if (!!data && data.error_code === 0) {
                        $("#banner").css('background-image', 'url(' + staticURL + "/" + data.data.banner + ')');
                        self.$el.find(".detail-sect .container").append('<h1>' + data.data.name + '</h1>' + data.data.content);
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