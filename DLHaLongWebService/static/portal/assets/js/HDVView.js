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
            "        <a class=\"breadcrumb-item\" href=\"#\">Trang chủ</a>" +
            "        <span class=\"breadcrumb-item active\">Trợ Lý Ảo</span>" +
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
            var template_content = "<div id=\"single_post\">" +
                                    "<strong>Chào mừng Qúy khách đến với địa điểm tham quan Hòn Trống Mái</strong>"+
                                    "<p><strong>Hòn Trống Mái hay còn gọi là hòn Gà Chọi là biểu tượng cho du lịch Hạ Long, bất cứ ai đến Hạ Long đều không thể bỏ qua điểm du lịch thú vị này.</strong></p></div>  <p style=\"text-align: justify;\"></p><p style=\"text-align: justify;\">Hòn Trống Mái nằm ở phía Tây Nam của <strong>Vịnh Hạ Long</strong>, cách biển <strong>Bãi Cháy</strong> khoảng 5km. Từ Bến Tàu <strong>Bãi Cháy</strong> đi về phía Tây Nam sau khi đi qua Hòn Chó Đá, Đỉnh Lư Hương du khách sẽ có cơ hội ngắm nhìn hình ảnh hòn Trống Mái với hình dáng như hai con gà khổng lồ (một trống – một mái) với chiều cao khoảng 10 m giương cánh đá nhau trên mặt biển.</p><p style=\"text-align: justify;\"><strong>Hòn Trống Mái</strong> là cặp đá hình dánh như đôi gà kỳ vỹ, sừng sững giữa biển khơi, một trong những sự ưu ái của thiên nhiên dành riêng&nbsp; cho Vịnh Hạ Long. Nếu đến <strong>Vịnh Hạ Long</strong> du lịch mà chưa đến nơi này là chưa biết Hạ Long. Cái tên \"Trống – Mái\" gợi cho chúng ta hình ảnh một đôi nam – nữ với tình yêu thương và sự gắn kết. Khối đá có hình dáng đẹp thơ mộng ấy gắn với truyền thuyết về một mối tình chung thủy. Hình ảnh của hòn Trống Mái đã trở thành biểu tượng cho sự&nbsp; khát khao hạnh phúc mà người xưa đã khéo léo gửi gắm vào&nbsp; vùng biển đảo nơi đây.</p><p style=\"text-align: center;\"></p><p style=\"text-align: justify;\">Nhìn từ xa với dáng đứng chênh vênh và chiếc chân nhỏ đỡ tấm thân khổng lồ tưởng chừng có thể đổ ập bất cứ khi nào nếu có một cơn sóng vỗ mạnh. Thế nhưng hai hòn đá đó đã đứng hiên ngang giữa đất trời hàng triệu năm và dường như nhờ đôi chân nhỏ bé này mà hai con gà khổng lồ trở nên kiêu hãnh và hấp dẫn hơn.</p><p style=\"text-align: justify;\">Giữa một vùng biển nước bao la, đảo Gà Chọi hiện nên xinh đẹp và ấn tượng, lúc bình minh lên từ phía xa chiếu ánh sáng rực rỡ nhuộm đổ đôi gà khổng lồ bên nhau.</p><p><img style=\"display: block; margin-left: auto; margin-right: auto;\" title=\"Hòn Trống Mái 3\" src=\"https://www.wyndhamhalong.com/images/blog/2017/Thang_1/h%C3%B2n-Tr%E1%BB%91ng-M%C3%A1i.jpg\" alt=\"Hòn Trống Mái 3\" width=\"480\" height=\"317\" class=\"img-responsive\"></p><p style=\"text-align: center;\"><em>Vẻ đẹp hòn Trống Mái</em></p><p style=\"text-align: justify;\">Với những giá trị về mặt mỹ thuật và ý nghĩa của nó Hòn Gà Chọi đã trở thành một biểu tượng của Vịnh Hạ Long và du lịch Hạ Long Quảng Ninh. Hòn Gà Chọi cũng là nguồn cảm hứng cho biết bao nhiêu nghệ sỹ sáng tác nghệ thuật. Không những thế những nghệ nhân sản xuất hàng thủ công mỹ nghệ của Việt Nam cũng dùng hình ảnh của hòn Trống Mái để tạo nên những sản phẩm có tính nghệ thuật để quảng bá hình ảnh của Vịnh Hạ Long nói riêng và du lịch Việt Nam nói chung.</p><p style=\"text-align: justify;\">Nếu Qúy khách đang du lịch ở <strong> Hạ Long</a></strong> – một trong bảy kỳ quan thiên nhiên của thế giới Qúy khách nhất định đừng quên chiêm ngưỡng tác phẩm nghệ thuật của tạo hóa này nhé! Hẹn gặp lại Qúy khách !</p>" +
                                   "</div>"
                                    +"<audio  autoplay=\"autoplay\">"
                                          +"<source src=\"static/portal/assets/video/nguoihuongdan.mp3\" type=\"audio/mpeg\" />"
                                      +"</audio>";
                
            var self = this;
            $("#banner").css('background-image', 'url(static/portal/assets/img/bn_hontrongmai.jpg)');
            self.$el.find(".detail-sect .container").append('<h1>Hòn Trống Mái - Vịnh Hạ Long</h1>' + template_content);
            
            
            
        }

    });

});