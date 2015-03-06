
<html>
    <head>
        <meta name="layout" content="api_page" />
        <title>${path}</title> 
        <script type="text/javascript">
        function performChange(chk) {  
            if(chk.checked == true) {
                chk.parentNode.nextSibling.childNodes[0].disabled=false;

            } else {
                chk.parentNode.nextSibling.childNodes[0].disabled=true;              
            }
        }
        </script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <h4>${path}</h4>
            </div>
                <div class="row col-xs-12 col-md-8">
                    <g:formRemote name="subForm" class="form-horizontal" url="[controller:'App', action:'connect']" update="root">

                        <div id="form-data">
                            <div class="row">
                                <div class="form-group">
                                    <label for="serverUrl" class="col-sm-2 control-label">Server URL:*</label>
                                    <div class="col-sm-10">
                                        <input type="text" name="serverUrl" required class="form-control" id="serverUrl" >
                                        <p class="help-block">Example: https://cloudstack.com</p>
                                    </div>
                                </div>

<!--                            <div class="form-group">
                                <label for="apiUrl" class="col-sm-2 control-label">Api URL:*</label>
                                <div class="col-sm-10">
                                    <input type="text" id="apiUrl" value="${apiUrl}" required class="form-control" placeholder="Api URL" name="apiUrl">
                                    <p class="help-block">Example: /api/admin/Network/dedicatePublicIpRange</p>
                                </div>
                            </div>-->

                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="apiKey">Api Key:*</label>
                                <div class="col-sm-10">
                                    <input type="text" required class="form-control" placeholder="Api Key" id="apiKey" name="apiKey">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="secretKey">Secret Key:*</label>
                                <div class="col-sm-10">
                                    <input type="text" required class="form-control" placeholder="Secret Key" id="secretKey" name="secretKey">
                                </div>
                            </div>

                        </div> 
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Arguments:</label>
                                <div class="col-sm-10">
<!--                                   <table class="table">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Name</th>
                                                <th>Required</th>
                                                <th>Send</th>
                                                <th>Value</th>
                                                <th>Example</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>1</td>
                                                <td>CPUNumber</td>
                                                <td>Required</td>
                                                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" name="cpunumber" required="" class="form-control"  ></td>
                                                <td>1-7 Cores</td>
                                            </tr>
                                            <tr>
                                                <td>2</td>
                                                <td>CPUSpeed</td>
                                                <td>Required</td>
                                                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" onclick="performChange(this)" checked="true" ></td><td><input type="text" id="text2" name="cpuspeed" required="" class="form-control" ></td>
                                                <td>100-1000 Mhz</td>
                                            </tr>
                                            <tr>
                                                <td>3</td>
                                                <td>DisplayText</td>
                                                <td>Required</td>
                                                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" name="displaytext" required="" class="form-control"></td>
                                                <td>Display Text</td>
                                            </tr>
                                            <tr>
                                                <td>4</td>
                                                <td>Memory</td>
                                                <td>Required</td>
                                                <td><input class="send" type="checkbox" id="memoryc"  value="memory" onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" name="memory" required="" class="form-control"></td>
                                                <td>100-1000 MiB</td>
                                            </tr>
                                            <tr>
                                                <td>5</td>
                                                <td>Name</td>
                                                <td>Required</td>
                                                <td><input class="send" type="checkbox" id="namec" value="name" onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" name="name" required="" class="form-control"></td>
                                                <td>{Compute,Disk,System,Network} Offering</td>
                                            </tr>
                                        </tbody>
                                    </table>-->
                                    <p>Hello this paragraph is for testing</p>
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

