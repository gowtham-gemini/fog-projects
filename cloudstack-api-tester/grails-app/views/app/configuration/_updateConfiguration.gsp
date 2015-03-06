
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
                <td>configuration name</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="name" required="" class="form-control"  ></td>
                <td>the name of the configuration</td>
            </tr>
            <tr>
                <td>2</td>
                <td>account id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="accountid" disabled="true" required="" class="form-control"></td>
                <td>cb3d4d7d-85ab-4d1c-9540-0660be9fc8b8</td>
            </tr>
            <tr>
                <td>3</td>
                <td>cluster id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="clusterid" disabled="true" required="" class="form-control"></td>
                <td>cb3d4d7d-85ab-4d1c-9540-0660be9fc8b8</td>
            </tr>
            <tr>
                <td>4</td>
                <td>storage id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="storageid" disabled="true" required="" class="form-control"></td>
                <td>cb3d4d7d-85ab-4d1c-9540-0660be9fc8b8</td>
            </tr>
            <tr>
                <td>5</td>
                <td>configuration
                    value</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="value" disabled="true" required="" class="form-control"></td>
                <td>the value of the configuration</td>
            </tr>
            <tr>
                <td>6</td>
                <td>zone id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="zoneid" disabled="true" required="" class="form-control"></td>
                <td>cb3d4d7d-85ab-4d1c-9540-0660be9fc8b8</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >