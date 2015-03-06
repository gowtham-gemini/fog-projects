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
                <td>format</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="textt" 
                    name="format" required="" class="form-control"></td>
                <td>QCOW2,OVA,andVHD</td>
            </tr>
            <tr>
                <td>2</td>
                <td>volume name</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true" ></td><td><input type="text" id="textt" 
                    name="name" required="" class="form-control"  ></td>
                <td>volume name</td>
            </tr>
            <tr>
                <td>3</td>
                <td>url</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true" ></td><td><input type="text" id="textt" 
                    name="url" required="" class="form-control"  ></td>
                <td>possible url http://andhttps://</td>
            </tr>
            <tr>
                <td>4</td>
                <td>zoneid</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true" ></td><td><input type="text" id="textt" 
                    name="zoneid" required="" class="form-control"  ></td>
                <td>dc363418-5c05-48a6-8ca2-ed6685300e37</td>
            </tr>
            <tr>
                <td>5</td>
                <td>account name</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="account" disabled="true" required="" class="form-control"></td>
                <td>scott</td>
            </tr>
            <tr>
                <td>6</td>
                <td>checksum</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="deviceid" disabled="true" required="" class="form-control"></td>
                <td>md5 checksum of volume</td>
            </tr>
            <tr>
                <td>7</td>
                <td>domainid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="domainid" disabled="true" required="" class="form-control"></td>
                <td>dc363418-5c05-48a6-8ca2-ed6685300e37</td>
            </tr>
            <tr>
                <td>8</td>
                <td>imagestoreuuid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="imagestoreuuid" disabled="true" required="" class="form-control"></td>
                <td>dc363418-5c05-48a6-8ca2-ed6685300e37</td>
            </tr>
            <tr>
                <td>9</td>
                <td>projectid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="projectid" disabled="true" required="" class="form-control"></td>
                <td>dc363418-5c05-48a6-8ca2-ed6685300e37</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >