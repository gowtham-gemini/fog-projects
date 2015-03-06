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
                <td>action</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="action" required="" class="form-control"  ></td>
                <td>action to be executed</td>
            </tr>
            <tr>
                <td>2</td>
                <td>conditionids</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="conditionids" required="" class="form-control"  ></td>
                <td>list of IDs of the conditions</td>
            </tr>
            <tr>
                <td>3</td>
                <td>duration</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="duration" required="" class="form-control"  ></td>
                <td>duration for the conditions</td>
            </tr>
             <tr>
                <td>4</td>
                <td>quiettime</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="quiettime" disabled="true" required="" class="form-control"  ></td>
                <td>the cool down period for which 
                    the policy should not be evaluated </td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >