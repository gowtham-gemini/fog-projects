<html>
    <head>
        <meta name="layout" content="api_page" />
        <title>Signup</title>       
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <h4>Create Account</h4>
            </div>
            <div class="row col-xs-12 col-md-8">
                <form class="form-horizontal">
                    <div id="form-data">
                        <div class="row">
                            <g:render contextPath="" template="/default/apiDefaultParam"/>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Arguments:</label>
                                <div class="col-sm-10">
                                    <table class="table">
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
                                                <td>userName</td>
                                                <td>Required</td>
                                                <td><input onchange="ApiBase.getSignature()" class="send" type="checkbox" id="sentUserName" checked="true"></td>
                                                <td><input onkeyup="ApiBase.getSignature()" type="text" id="userName" name="userName" class="form-control"></td>
                                                <td>john@example.com</td>
                                            </tr>
                                            <tr>
                                                <td>2</td>
                                                <td>password</td>
                                                <td>Required</td>
                                                <td><input onchange="ApiBase.getSignature()" class="send" type="checkbox" id="sentPassword" checked="true"></td>
                                                <td><input onkeyup="ApiBase.getSignature()" type="text" id="password" name="password" class="form-control"></td>
                                                <td>xxxxxxx(size:6-15)</td>
                                            </tr>
                                            <tr>
                                                <td>3</td>
                                                <td>firstName</td>
                                                <td>Required</td>
                                                <td><input onchange="ApiBase.getSignature()" class="send" type="checkbox" id="sentFirstName" checked="true"></td>
                                                <td><input onkeyup="ApiBase.getSignature()" type="text" id="firstName" name="firstName" class="form-control"></td>
                                                <td>john</td>
                                            </tr>
                                            <tr>
                                                <td>4</td>
                                                <td>lastName</td>
                                                <td>Required</td>
                                                <td><input onchange="ApiBase.getSignature()" class="send" type="checkbox" id="sentLastName" checked="true"></td>
                                                <td><input onkeyup="ApiBase.getSignature()" type="text" id="lastName" name="lastName" class="form-control"></td>
                                                <td>smith</td>
                                            </tr>
                                            <tr>
                                                <td>5</td>
                                                <td>streetAddress</td>
                                                <td>Required</td>
                                                <td><input onchange="ApiBase.getSignature()" class="send" type="checkbox" id="sentStreetAddress" checked="true"></td>
                                                <td><input onkeyup="ApiBase.getSignature()" type="text" id="streetAddress" name="streetAddress" class="form-control"></td>
                                                <td>45 Park Avenue</td>
                                            </tr>
                                            <tr>
                                                <td>6</td>
                                                <td>extendedAddress</td>
                                                <td>Optional</td>
                                                <td><input onchange="ApiBase.getSignature()" class="send" type="checkbox" id="sentExtendedAddress"></td>
                                                <td><input onkeyup="ApiBase.getSignature()" type="text" id="extendedAddress" name="extendedAddress" class="form-control"></td>
                                                <td>1600 Pennsylvania ave north</td>
                                            </tr>
                                            <tr>
                                                <td>7</td>
                                                <td>city</td>
                                                <td>Required</td>
                                                <td><input onchange="ApiBase.getSignature()" class="send" type="checkbox" id="sentCity" checked="true"></td>
                                                <td><input onkeyup="ApiBase.getSignature()" type="text" id="city" name="city" class="form-control"></td>
                                                <td>New York</td>
                                            </tr>
                                            <tr>
                                                <td>8</td>
                                                <td>country</td>
                                                <td>Required</td>
                                                <td><input onchange="ApiBase.getSignature()" class="send" type="checkbox" id="sentCountry" checked="true"></td>
                                                <td><input onkeyup="ApiBase.getSignature()" type="text" id="country" name="country" class="form-control"></td>
                                                <td>Value Country ID Example: 1(Refer Country API)</td>
                                            </tr>
                                            <tr>
                                                <td>9</td>
                                                <td>phoneNumber</td>
                                                <td>Required</td>
                                                <td><input onchange="ApiBase.getSignature()" class="send" type="checkbox" id="sentPhoneNumber" checked="true"></td>
                                                <td><input onkeyup="ApiBase.getSignature()" type="text" id="phoneNumber" name="phoneNumber" class="form-control"></td>
                                                <td>1234(size:4-20)</td>
                                            </tr>
                                            <tr>
                                                <td>10</td>
                                                <td>state</td>
                                                <td>Required</td>
                                                <td><input onchange="ApiBase.getSignature()" class="send" type="checkbox" id="sentState" checked="true"></td>
                                                <td><input onkeyup="ApiBase.getSignature()" type="text" id="state" name="state" class="form-control"></td>
                                                <td>Value State ID Example: 1(Refer State API)</td>
                                            </tr>
                                            <tr>
                                                <td>11</td>
                                                <td>zip</td>
                                                <td>Required</td>
                                                <td><input onchange="ApiBase.getSignature()" class="send" type="checkbox" id="sentZip" checked="true"></td>
                                                <td><input onkeyup="ApiBase.getSignature()" type="text" id="zip" name="zip" class="form-control"></td>
                                                <td>6813 (Size:1-10)</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <g:render contextPath="" template="/default/apiResponseContent"/>
                </form>
            </div> 
        </div>
    </body>    
</html>
