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
                <td>diskvolume name</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="textt" 
                    name="name" required="" class="form-control"></td>
                <td>name of the disk</td>
            </tr>
            <tr>
                <td>2</td>
                <td>account name</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="account" disabled="true" required="" class="form-control"></td>
                <td>scott</td>
            </tr>
            <tr>
                <td>3</td>
                <td>diskofferingid</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="textt" 
                    name="diskofferingid" required="" class="form-control"></td>
                <td>34d1b31c-0e80-4f34-9a6e-02ee639d7dba</td>
            </tr>
            <tr>
                <td>4</td>
                <td>domainid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="domainid" disabled="true" required="" class="form-control"></td>
                <td>dc363418-5c05-48a6-8ca2-ed6685300e37</td>
            </tr>
            <tr>
                <td>5</td>
                <td>maxiops</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="maxiops" disabled="true" required="" class="form-control"></td>
                <td>7</td>
            </tr>
            <tr>
                <td>6</td>
                <td>miniops</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="miniops" disabled="true" required="" class="form-control"></td>
                <td>1</td>
            </tr>
            <tr>
                <td>7</td>
                <td>projectid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="projectid" disabled="true" required="" class="form-control"></td>
                <td>dc363418-5c05-48a6-8ca2-ed6685300e37</td>
            </tr>
            <tr>
                <td>8</td>
                <td>size</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="size" disabled="true" required="" class="form-control"></td>
                <td>volume size</td>
            </tr>
            <tr>
                <td>9</td>
                <td>snapshotid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="snapshotid" disabled="true" required="" class="form-control"></td>
                <td>dc363418-5c05-48a6-8ca2-ed6685300e37</td>
            </tr>
            <tr>
                <td>10</td>
                <td>zoneid</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="textt" 
                    name="zoneid" required="" class="form-control"></td>
                <td>dc363418-5c05-48a6-8ca2-ed6685300e37</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >