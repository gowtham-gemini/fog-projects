
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
                <td>PhysicalNetwork Name</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="name" required="" class="form-control"  ></td>
                <td>Test Network</td>
            </tr>
            <tr>
                <td>2</td>
                <td>Zone id</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="text2" 
                    name="zoneid" required="" class="form-control" ></td>
                <td>96a9c3c3-40bd-4cfb-a1ba-6afae41778eb</td>
            </tr>
            <tr>
                <td>3</td>
                <td>broadcastdomainrange</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" ></td><td><input type="text" id="text2" 
                    name="broadcastdomainrange" disabled="true" required="" class="form-control" ></td>
                <td>broadcast domain range</td>
            </tr>
            <tr>
                <td>4</td>
                <td>domainid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" ></td><td><input type="text" id="text2" 
                    name="domainid" disabled="true" required="" class="form-control" ></td>
                <td>96a9c3c3-40bd-4cfb-a1ba-6afae41778eb</td>
            </tr>
            <tr>
                <td>5</td>
                <td>isolationmethods</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" ></td><td><input type="text" id="text2" 
                    name="isolationmethods" disabled="true" required="" class="form-control" ></td>
                <td>{VLAN,L3,GRE}</td>
            </tr>
            <tr>
                <td>6</td>
                <td>networkspeed</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" ></td><td><input type="text" id="text2" 
                    name="networkspeed" disabled="true" required="" class="form-control" ></td>
                <td>{1G,10G}</td>
            </tr>
            <tr>
                <td>7</td>
                <td>tags</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" ></td><td><input type="text" id="text2" 
                    name="tags" disabled="true" required="" class="form-control" ></td>
                <td>tag values</td>
            </tr>
            <tr>
                <td>8</td>
                <td>vlan</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" ></td><td><input type="text" id="text2" 
                    name="vlan" disabled="true" required="" class="form-control" ></td>
                <td>vlan physical network</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >