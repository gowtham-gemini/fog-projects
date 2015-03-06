var ApiBase = {
    require : function() {
        require([
        "dojo",
        "dojo/dom",
        "dojo/ready",
        "dojo/store/JsonRest",
        "dojox/encoding/digests/MD5",
        "dojox/encoding/base64",
        "dijit/registry",    
        "dojo/dom-construct",
        "dojo/query",
        "dojo/dom-class"
        ],function(dojo, dom, ready, JsonRest, MD5, Base64, registry) {
            window.JsonRest = JsonRest;
            window.Base64 = Base64;
            window.MD5 = MD5;
            window.registry = registry;
        });
    },
    
    send: function() {
        
        var serverUrl = document.getElementById("serverUrl").value;
        var apiUrl = document.getElementById("apiUrl").value;
        var apiKey = document.getElementById("apiKey").value;
        var signature = document.getElementById("signature").value;
//        var captchaValue = document.getElementById("captchaValue").value;
//                
//        var method = document.getElementById("methodValue").value;
        
        
        var queryData = "";
        
        var argNodes = document.querySelectorAll(".send");
        
        var jsonData = {};
                
        for (var i = 0; i < argNodes.length; i++) {
             
            if(argNodes[i].checked === true || argNodes[i].checked === "true") {
                var key = argNodes[i].parentNode.nextElementSibling.childNodes[0].getAttribute("name");
                var value = argNodes[i].parentNode.nextElementSibling.childNodes[0].value;
                queryData += queryData.length === 0 ? "" : "&";
                queryData += encodeURI(key)+"="+encodeURI(value);
                jsonData[key] = value;
            }
        }
        
        queryData += queryData.length === 0 ? "" : "&";
        queryData += "apiKey="+apiKey;
        queryData += "&signature="+signature;
        
        var requestUrl = serverUrl+apiUrl+"?"+queryData;
                
        var capthaJobStore = new JsonRest({
            target: "/APITestInterface/app/checkCaptha/"         
        });
        
        
        capthaJobStore.query({
            captchaValue: captchaValue
        }).then(function(data) {
                       
            if(data.response.status === "Success") {
                
                
                if(method === "POST") {
                    document.getElementById("requestUrl").value = serverUrl+apiUrl;
                    document.getElementById("requestBody").value = JSON.stringify(jsonData); ;
                    
                } else {
                    document.getElementById("requestUrl").value = requestUrl;
                    document.getElementById("requestBody").value = "";
                }
                
                
                var jobStore = new JsonRest({
                    target: "/APITestInterface/app/processApi/"         
                });

                jobStore.query({
                    serverUrl : serverUrl,
                    apiUrl: apiUrl,
                    queryData : queryData,
                    requestUrl : requestUrl,
                    method: method
                }).then(function(data) {
                    document.getElementById("response").value = JSON.stringify(data); 
                });
                document.getElementById("capthaImage").src = "/APITestInterface/simpleCaptcha/captcha?time=" + new Date();
                            
            } else {
                
                alert("Invalid Captcha");
                document.getElementById("capthaImage").src = "/APITestInterface/simpleCaptcha/captcha?time=" + new Date();
                
            }
            
        });
        
    },
    
    
    getSignature : function() {
        
        var secretKey = document.getElementById("secretKey").value;
                       
        var queryData = "";
        
        var argNodes = document.querySelectorAll(".send");
        
        for (var i = 0; i < argNodes.length; i++) {
             
            if(argNodes[i].checked === true || argNodes[i].checked === "true") {
                var key = argNodes[i].parentNode.nextElementSibling.childNodes[0].getAttribute("name");
                var value = argNodes[i].parentNode.nextElementSibling.childNodes[0].value;
                queryData += queryData.length === 0 ? queryData : "&";
                queryData += encodeURI(key)+"="+encodeURI(value);
            }
        }
        
        var bytes = [];
        for (var i = 0; i < queryData.length; ++i) {
          bytes.push(queryData.charCodeAt(i));
        }
        
        var base64 = Base64.encode(bytes);
        
        var data = base64+secretKey;
        
        var md5 =  hex_md5(data);
               
        document.getElementById("signature").value = md5;
    }
    
    
};

