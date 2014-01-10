(function($){
    $('#login_button').click(function(){
        var password = $('#admin_password').val()
        if(password.length == 0){
            $('#admin_password').val('');
        }else{
            hashedpassword = (new jsSHA(password,'ASCII')).getHash('SHA-384','HEX');
            var postdata = {
                "name": "admin",
                "password": hashedpassword
            };
            $.ajax({
                url: '/admin/loginreq',
                type: 'POST',
                data: JSON.stringify(postdata),
                dataType: 'json',
                contentType: 'text/json',
                success: function(result){
                    location.href=result.msg;
                },
                error: function(result){
                    alert("FAILED TO LOGIN");
                    $('#admin_password').val('');
                }
            });
        }
     });
})(jQuery);
