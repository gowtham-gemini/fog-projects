

var g_loginResponse = "";
var j_username = "";
var j_password = "";
var domainName = "";

var AccountLogin = {
    ssoUrl: null,
    csRequest: function() {

        var clientApiUrl = AccountLogin.ssoUrl + "/api";

        console.log(" cs request initialized ****");
        j_username = document.getElementById("username").value;
        j_password = document.getElementById("password").value;
        domainName = document.getElementById("domainName").value;

        var url = null;
        if (clientApiUrl !== undefined && clientApiUrl !== null && clientApiUrl.length > 0) {

            url = unescape(clientApiUrl);
            url = url + "?command=login&response=json&password=" + j_password;
            url = url + "&username=" + j_username;
            url = url + "&domain=" + domainName;
            dojo.xhrGet({
                // The URL of the request
                url: url,
                // The success callback with result from server
                load: function(content) {
                    g_loginResponse = content;
                    document.getElementById("csLoginResponse").value = g_loginResponse;
                    AccountLogin._formPost();

                    //return true;
                },
                // The error handler
                error: function(content) {
                    console.log("err"+content);
                    g_loginResponse = content;
                    document.getElementById("csLoginResponse").value = g_loginResponse;
                   AccountLogin._formPost();
                   
                    // This means the login failed.  You should redirect to your login page.
                    //return false;
                }
            });
//            $.ajax({
//                url: url,
//                crossDomain: true,
//                type: 'POST',
//                //dataType: "json",
//                dataType: "jsonp",
//                async: false,
//                data: {
//                    command: "login",
//                    domain: "/",
//                    password: j_password,
//                    response: "json",
//                    username: j_username
//                    
//                },
//                success: function(json) {
//                    console.log("login success");
//                    g_loginResponse = json.loginresponse;
//                    document.getElementById("csLoginResponse").value = g_loginResponse;
//                    AccountLogin._formPost();
//
//                    console.log(g_loginResponse);
//                    //return true;
//                },
//                error: function(data) {
//                    console.log("login failed")
//                    console.log(data);
//
//                    // to be commented out once checked in server
//                    document.getElementById("csLoginResponse").value = g_loginResponse;
//                    AccountLogin._formPost();
//
//                    // This means the login failed.  You should redirect to your login page.
//                    //return false;
//                },
//                beforeSend: function(XMLHttpRequest) {
//                    return true;
//                }
//            });
        }

        //return true;

    },
    _formPost: function() {
        dojo.byId('loginForm').submit();

    }


};
window.AccountLogin = AccountLogin;

