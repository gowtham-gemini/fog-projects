
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
                <td>instancegroup name</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="name" required="" class="form-control"  ></td>
                <td>name of the instance group</td>
            </tr>
            <tr>
                <td>2</td>
                <td>account</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="account" disabled="true" required="" class="form-control"></td>
                <td>account of the 
                    instance group</td>
            </tr>
            <tr>
                <td>3</td>
                <td>domain id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="domainid" disabled="true" required="" class="form-control"></td>
                <td>cb3d4d7d-85ab-4d1c-9540-0660be9fc8b8</td>
            </tr>
            <tr>
                <td>4</td>
                <td>project id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="projectid" disabled="true" required="" class="form-control"></td>
                <td>cb3d4d7d-85ab-4d1c-9540-0660be9fc8b8</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >