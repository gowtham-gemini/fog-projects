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
                <td>diskvolume id</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="textt" 
                    name="id" required="" class="form-control"></td>
                <td>23bb5fe3-721d-414c-976c-871a1d51e57b</td>
            </tr>
            <tr>
                <td>2</td>
                <td>virtualmachineid</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true" ></td><td><input type="text" id="textt" 
                    name="virtualmachineid" required="" class="form-control"  ></td>
                <td>23bb5fe3-721d-414c-976c-871a1d51e57b</td>
            </tr>
            <tr>
                <td>3</td>
                <td>deviceid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="deviceid" disabled="true" required="" class="form-control"></td>
                <td>* 1 - /dev/xvdb*</td>
            </tr>
            
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >