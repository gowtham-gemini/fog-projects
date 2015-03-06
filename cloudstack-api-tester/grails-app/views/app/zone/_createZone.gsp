
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
                <td>first dns</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="dns1" required="" class="form-control"></td>
                <td>8.8.2.2</td>
            </tr>
            <tr>
                <td>2</td>
                <td>internal dns1</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="internaldns1" required="" class="form-control"></td>
                <td>http://cloudstack/storage</td>
            </tr>
            <tr>
                <td>3</td>
                <td>zone name</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="name" required="" class="form-control"></td>
                <td>the name of the zone</td>
            </tr>
            <tr>
                <td>4</td>
                <td>network type</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="networktype" required=""  class="form-control"></td>
                <td>{basic,advanced}</td>
            </tr>
            <tr>
                <td>5</td>
                <td>allocationstate</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="allocationstate" disabled="true" required="" class="form-control"></td>
                <td>{enabled,disabled}</td>
            </tr>
            <tr>
                <td>6</td>
                <td>second dns</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="dns2" disabled="true" required="" class="form-control"></td>
                <td>4.4.2.2</td>
            </tr>
            <tr>
                <td>7</td>
                <td>domain name</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc" value="cpunumber" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" disabled="true" 
                    name="domain" disabled="true" required="" class="form-control" ></td>
                <td>Network domain name for 
                    the networks in the zone</td>
            </tr>
            <tr>
                <td>8</td>
                <td>domain id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc" value="cpunumber" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" disabled="true" 
                    name="domainid" disabled="true" required="" class="form-control" ></td>
                <td>29486513-c5a3-476f-8bf0-8c476dfa74c5</td>
            </tr>
            <tr>
                <td>9</td>
                <td>guestcidr address</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc" value="cpunumber" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" disabled="true" 
                    name="guestcidraddress" disabled="true" required="" class="form-control" ></td>
                <td>192.168.100.0/22</td>
            </tr>
            <tr>
                <td>10</td>
                <td>internal dns2</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="internaldns2" disabled="true" required="" class="form-control"></td>
                <td>29486513-c5a3-476f-8bf0-8c476dfa74c5</td>
            </tr>
            <tr>
                <td>11</td>
                <td>ip6dns1</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="ip6dns1" disabled="true" required="" class="form-control"></td>
                <td>2001:4860:4860::8888</td>
            </tr>
            <tr>
                <td>12</td>
                <td>ip6dns2</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="ip6dns2" disabled="true" required="" class="form-control"></td>
                <td>2001:4860:4860::8844</td>
            </tr>

            <tr>
                <td>13</td>
                <td>localstorageenabled</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="localstorageenabled" disabled="true" required="" class="form-control"></td>
                <td>{true,false}</td>
            </tr>
            <tr>
                <td>14</td>
                <td>securitystorageenabled</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="securitystorageenabled" disabled="true" required="" class="form-control"></td>
                <td>{true,false}</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >