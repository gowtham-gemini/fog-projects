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
                <td>physicalnetwork id</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="textt" 
                    name="id" required="" class="form-control"></td>
                <td>cb3d4d7d-85ab-4d1c-9540-0660be9fc8b8</td>
            </tr>
            <tr>
                <td>2</td>
                <td>networkspeed</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="networkspeed" disabled="true" required="" class="form-control"></td>
                <td>{1G,10G}</td>
            </tr>
            <tr>
                <td>3</td>
                <td>physicalnetwork state</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="state" disabled="true" required="" class="form-control"></td>
                <td>{enabled,disabled}</td>
            </tr>
            <tr>
                <td>4</td>
                <td>tags</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="tags" disabled="true" required="" class="form-control"></td>
                <td>tag values</td>
            </tr>
            <tr>
                <td>5</td>
                <td>vlan</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="vlan" disabled="true" required="" class="form-control"></td>
                <td>vlan name</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >