(function($){
    $('#login_button').click(function(){
        var password = $('#admin_password').val()
        if(password.length == 0){
            $('#admin_password').val('');
        }else{
            hashedpassword = (new jsSHA(password,'ASCII')).getHash('SHA-384','HEX');
            var postdata = {
                'name': 'admin',
                'password': hashedpassword
            };
            $.ajax({
                url: '/admin/loginreq',
                type: 'POST',
                data: JSON.stringify(postdata),
                complete: function(result){
                    if(result.code == false){
                        alert("FAILED TO LOGIN");
                        $('#admin_password').val('');
                    }
                },
                dataType: 'json',
                contentType: 'text/json'
            });
        }
     });
})(jQuery);
