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
                <td>autoscalepolicy id</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="id" required="" class="form-control"  ></td>
                <td>49b00016-7748-463f-88f0-a3e7ca8fc21f</td>
            </tr>
            <tr>
                <td>2</td>
                <td>conditionids</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="conditionids" disabled="true" required="" class="form-control"  ></td>
                <td>list of IDs of the conditions</td>
            </tr>
            <tr>
                <td>3</td>
                <td>duration</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="duration" disabled="true" required="" class="form-control"  ></td>
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