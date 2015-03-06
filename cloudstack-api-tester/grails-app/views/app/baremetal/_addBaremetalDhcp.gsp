
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
                <td>dhcpserver type</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="dhcpservertype" required="" class="form-control"></td>
                <td>type of dhcp device</td>
            </tr>
            <tr>
                <td>2</td>
                <td>dhcp password</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="password" id="textt" 
                    name="password" required="" class="form-control"  ></td>
                <td>password to reach dhcp device</td>
            </tr>
            <tr>
                <td>3</td>
                <td>physicalnetwork id</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="physicalnetworkid" required="" class="form-control"></td>
                <td>cb3d4d7d-85ab-4d1c-9540-0660be9fc8b8</td>
            </tr>
            <tr>
                <td>5</td>
                <td>dhcpappliance url</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="url" required="" class="form-control"></td>
                <td>url of external
                    dhcp device</td>
            </tr>
            <tr>
                <td>6</td>
                <td>username</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="username" required="" class="form-control"></td>
                <td>username to reach dhcp device</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >