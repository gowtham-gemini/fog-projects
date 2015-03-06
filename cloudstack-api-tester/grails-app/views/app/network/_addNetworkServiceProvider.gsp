
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
                <td>physicalnetwork id</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="text2" 
                    name="physicalnetworkid" required="" class="form-control" ></td>
                <td>96a9c3c3-40bd-4cfb-a1ba-6afae41778eb</td>
            </tr>
            <tr>
                <td>3</td>
                <td>destinationphysicalnetworkid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" ></td><td><input type="text" id="text2" 
                    name="destinationphysicalnetworkid" disabled="true" required="" class="form-control" ></td>
                <td>96a9c3c3-40bd-4cfb-a1ba-6afae41778eb</td>
            </tr>
            <tr>
                <td>4</td>
                <td>servicelist</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" ></td><td><input type="text" id="text2" 
                    name="servicelist" disabled="true" required="" class="form-control" ></td>
                <td>list of services</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >