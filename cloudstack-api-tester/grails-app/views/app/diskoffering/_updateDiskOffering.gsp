
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
                <td>diskoffering id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="id" required="" class="form-control"></td>
                <td>cb3d4d7d-85ab-4d1c-9540-0660be9fc8b8</td>
            </tr>
                <tr>
                <td>2</td>
                <td>displaytext</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="displaytext" disabled="true" required="" class="form-control"  ></td>
                <td>displaytext of diskoffering</td>
            </tr>
            <tr>
                <td>3</td>
                <td>diskoffering name</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="name" disabled="true" required="" class="form-control"  ></td>
                <td>name of the disk offering</td>
            </tr>
            <tr>
                <td>4</td>
                <td>sortkey</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="sortkey" disabled="true" required="" class="form-control"  ></td>
                <td>sortkey of the disk offering</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >