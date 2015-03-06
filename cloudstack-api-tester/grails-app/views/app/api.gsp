
<html>
    <head>
        <meta name="layout" content="api_page" />
        <title>${path}</title> 
        <script type="text/javascript">
        function performChange(chk) {  
            if(chk.checked == true) {
                chk.parentNode.nextSibling.childNodes[0].disabled=false;
                chk.parentNode.nextSibling.childNodes[0].value="";
            } else if(chk.checked == false) {
                chk.parentNode.nextSibling.childNodes[0].disabled=true;
                chk.parentNode.nextSibling.childNodes[0].value="";
            } else {
                chk.parentNode.nextSibling.childNodes[0].disabled=true;
                chk.parentNode.nextSibling.childNodes[0].value="";
            }
        }
        </script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <h3>${params.apiCommand}</h3>
            </div>
                <div class="row col-xs-12 col-md-8">
                    <g:formRemote name="subForm" class="form-horizontal" url="[controller:'App', action:'connect']" 
                        update="root">
                        
                        <div id="form-data">
                            <div class="row">
                                <div class="form-group">
                                    <label for="apiURL" class="col-sm-2 control-label">Server URL:*</label>
                                    <div class="col-sm-10">
                                        <input type="text" required class="form-control" placeholder="apiURL" 
                                        id="apiURL" name="apiURL" >
                                        <p class="help-block">Example: https://cloudstack.com</p>
                                    </div>
                                </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="apikey">Api Key:*</label>
                                <div class="col-sm-10">
                                    <input type="text" required class="form-control" placeholder="Api Key" id="apikey" 
                                    name="apikey">
                                    <p class="help-block">Example: L3YRRXA7hRVJ0o7tnh7wAsb7xthpsZI-h7bV-9Sgl-7QvbfK53-
                                        8sRvwtydj9Tov_km56E4d02jLLM7CKEDneQ</p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="secret">Secret Key:*</label>
                                <div class="col-sm-10">
                                    <input type="text" required class="form-control" placeholder="Secret Key" 
                                    id="secret" name="secret">
                                    <p class="help-block">Example: 9pXKB7yUllQu3i0jN66STHffTlKp5NNq8AABJymNAn9B4UHW0hc
                                        87b18zSBWjuRvRq9-lHkor11gqW7Iy5FRDA</p>
                                </div>
                            </div>

                        </div> 
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Arguments:</label>
                                <div class="col-sm-10">
                                    <g:render template="${path}"/>  
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <g:submitButton class="btn btn-primary" name="Make Request" value="Make Request"   />
                            </div>
                        </div>
                    </div>
                </g:formRemote>
                <div class="row">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="response">Response:</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" rows="18" id="root" disabled="true"> </textarea>
                            </div>
                     </div>
                </div>
            </div>
        </div>
    </body>    
</html>

