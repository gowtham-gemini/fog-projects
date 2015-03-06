
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
                <td>networkid</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="networkid" required="" class="form-control"  ></td>
                <td>cb3d4d7d-85ab-4d1c-9540-0660be9fc8b8</td>
            </tr>
            <tr>
                <td>2</td>
                <td>protocol</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="text2" 
                    name="protocol" required="" class="form-control" ></td>
                <td>{TCP,UDP}</td>
            </tr>
            <tr>
                <td>3</td>
                <td>cidrlist</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="Optional" disabled="true" required="" class="form-control"></td>
                <td>cidr list</td>
            </tr>
            <tr>
                <td>4</td>
                <td>endport</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="endport" disabled="true" required="" class="form-control"  ></td>
                <td>8080</td>
            </tr>
            <tr>
                <td>5</td>
                <td>icmpcode</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="icmpcode" disabled="true" required="" class="form-control"  ></td>
                <td>{0,1,2,3.....}</td>
            </tr>
            <tr>
                <td>6</td>
                <td>icmptype</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="icmptype" disabled="true" required="" class="form-control"  ></td>
                <td>type {0,1,2.....}</td>
            </tr>
            <tr>
                <td>7</td>
                <td>startport</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="startport" disabled="true" required="" class="form-control"  ></td>
                <td>7070</td>
            </tr>
            <tr>
                <td>8</td>
                <td>firewallruletype</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="type" disabled="true" required="" class="form-control"  ></td>
                <td>{system,user}</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >