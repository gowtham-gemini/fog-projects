
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
                <td>cidrlist</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="cidrlist" required="" class="form-control"  ></td>
                <td>guest cidr list</td>
            </tr>
            <tr>
                <td>2</td>
                <td>esppolicy</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="esppolicy" required="" class="form-control"></td>
                <td>esppolicy</td>
            </tr>
            <tr>
                <td>3</td>
                <td>gateway</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="gateway" required="" class="form-control"></td>
                <td>192.168.1.1</td>
            </tr>
            <tr>
                <td>4</td>
                <td>ikepolicy</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="ikepolicy" required="" class="form-control"></td>
                <td>IKE policy</td>
            </tr>
            <tr>
                <td>5</td>
                <td>ipsecpsk</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="ipsecpsk" required="" class="form-control"></td>
                <td>IPsec preshared-key</td>
            </tr>
            <tr>
                <td>6</td>
                <td>account</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="account" disabled="true" required="" class="form-control"></td>
                <td>account associated</td>
            </tr>
            <tr>
                <td>7</td>
                <td>domainid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="domainid" disabled="true" required="" class="form-control"></td>
                <td>0c914a08-453c-4ea1-ab23-74a57e7168ba</td>
            </tr>
            <tr>
                <td>8</td>
                <td>dpd</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="dpd" disabled="true" required="" class="form-control"></td>
                <td>{enabled,disabled}</td>
            </tr>
            <tr>
                <td>9</td>
                <td>esplifetime</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="esplifetime" disabled="true" required="" class="form-control"></td>
                <td>time in seconds</td>
            </tr>
            <tr>
                <td>10</td>
                <td>ikelifetime</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="ikelifetime" disabled="true" required="" class="form-control"></td>
                <td>time in seconds</td>
            </tr>
            <tr>
                <td>11</td>
                <td>customer gateway name</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="name" disabled="true" required="" class="form-control"></td>
                <td>name of customer gateway</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >