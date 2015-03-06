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
                <td>iprange id</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="textt" 
                    name="id" required="" class="form-control"></td>
                <td>cb3d4d7d-85ab-4d1c-9540-0660be9fc8b8</td>
            </tr>
            <tr>
                <td>2</td>
                <td>endip</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="endip" disabled="true" required="" class="form-control"></td>
                <td>192.168.1.40</td>
            </tr>
            <tr>
                <td>3</td>
                <td>netmask</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="netmask" disabled="true" required="" class="form-control"></td>
                <td>255.255.255.0</td>
            </tr>
            <tr>
                <td>4</td>
                <td>startip</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="startip" disabled="true" required="" class="form-control"></td>
                <td>192.168.1.1</td>
            </tr>
            <tr>
                <td>5</td>
                <td>vlan</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="vlan" disabled="true" required="" class="form-control"></td>
                <td>vlan name or ip</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >