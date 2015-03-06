
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
                <td>certificate</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="certificate" required="" class="form-control"  ></td>
                <td>the certificate 
                    to be uploaded</td>
            </tr>
            <tr>
                <td>2</td>
                <td>domain suffix</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="password" id="textt" 
                    name="domainsuffix" required="" class="form-control"  ></td>
                <td>DNS suffix that
                    the certificate
                    is granted for</td>
            </tr>
            <tr>
                <td>3</td>
                <td>certficatetype id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="id" disabled="true" required="" class="form-control"  ></td>
                <td>{1,2,3}</td>
            </tr>
            <tr>
                <td>4</td>
                <td>certicatename</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="name" disabled="true" required="" class="form-control"  ></td>
                <td>name for the 
                    certificate</td>
            </tr>
            <tr>
                <td>5</td>
                <td>privatekey</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="privatekey" disabled="true" required="" class="form-control"  ></td>
                <td>privatekey for 
                    the certificate</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >