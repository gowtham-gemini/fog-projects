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
                <td>DisplayText</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="displaytext" required="" class="form-control"  ></td>
                <td>Netoffer Test</td>
            </tr>
            <tr>
                <td>2</td>
                <td>GuestIpType</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="text2" 
                    name="guestiptype" required="" class="form-control" ></td>
                <td>{Shared, Isolated}</td>
            </tr>
            <tr>
                <td>3</td>
                <td>VLAN Type</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="specifyvlan" required="" class="form-control"></td>
                <td>{true, false}</td>
            </tr>
            <tr>
                <td>4</td>
                <td>SpecifyIpRange</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="specifyipranges" required="" class="form-control"></td>
                <td>{true, false}</td>
            </tr>
            <tr>
                <td>5</td>
                <td>OfferingName</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="name" required="" class="form-control"></td>
                <td>DefaultNetworkOffering</td>
            </tr>
            <tr>
                <td>6</td>
                <td>SupportedServices</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="supportedservices" required="" class="form-control"></td>
                <td>dns, dhcp, vpn</td>
            </tr>
            <tr>
                <td>7</td>
                <td>TrafficType</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="traffictype" required="" class="form-control"></td>
                <td>Guest</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >