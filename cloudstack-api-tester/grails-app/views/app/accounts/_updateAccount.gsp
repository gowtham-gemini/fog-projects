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
                <td>newname</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="newname" required="" class="form-control"  ></td>
                <td>desired name</td>
            </tr>
            <tr>
                <td>2</td>
                <td>account name</td>
                <td>optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true" ></td><td><input type="text" id="textt" 
                    name="account" required="" class="form-control"  ></td>
                <td>desired account name</td>
            </tr>
            <tr>
                <td>3</td>
                <td>accountdetails</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc" value="cpunumber" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" disabled="true" 
                    name="accountdetails" required="" class="form-control" ></td>
                <td>specific parameters of account</td>
            </tr>
            <tr>
                <td>4</td>
                <td>domainid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="domainid"  required="" class="form-control"></td>
                <td>23bb5fe3-721d-414c-976c-871a1d51e57b</td>
            </tr>
            <tr>
                <td>5</td>
                <td>id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="id" disabled="true" required="" class="form-control"></td>
                <td>23bb5fe3-721d-414c-976c-871a1d51e57b</td>
            </tr>
            <tr>
                <td>6</td>
                <td>networkdomain</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="networkdomain" disabled="true" required="" class="form-control"></td>
                <td>network domain name</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >