
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
                <td>algorithm</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="algorithm" required="" class="form-control"  ></td>
                <td>{source,
                    roundrobin,
                    leastconn}</td>
            </tr>
            <tr>
                <td>2</td>
                <td>instanceport</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="instanceport" required="" class="form-control"  ></td>
                <td>8080</td>
            </tr>
            <tr>
                <td>3</td>
                <td>loadbalancer name</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="text2" 
                    name="name" required="" class="form-control" ></td>
                <td>desired name</td>
            </tr>
            <tr>
                <td>4</td>
                <td>networkid</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="textt" 
                    name="networkid" required="" class="form-control"></td>
                <td>cb3d4d7d-85ab-4d1c-9540-0660be9fc8b8</td>
            </tr>
            <tr>
                <td>5</td>
                <td>scheme</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="textt" 
                    name="scheme" required="" class="form-control"></td>
                <td>internal</td>
            </tr>
            <tr>
                <td>6</td>
                <td>sourceipaddress
                    networkid</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="textt" 
                    name="sourceipaddressnetworkid" required="" class="form-control"></td>
                <td>cb3d4d7d-85ab-4d1c-9540-0660be9fc8b8</td>
            </tr>
            <tr>
                <td>7</td>
                <td>sourceport</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="textt" 
                    name="sourceport" required="" class="form-control"></td>
                <td>80</td>
            </tr>
            <tr>
                <td>8</td>
                <td>description</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="description" disabled="true" required="" class="form-control"></td>
                <td>description of LB</td>
            </tr>
            <tr>
                <td>9</td>
                <td>sourceipaddress</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="sourceipaddress" disabled="true" required="" class="form-control"></td>
                <td>192.168.1.1</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >