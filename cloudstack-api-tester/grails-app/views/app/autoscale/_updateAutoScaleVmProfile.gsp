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
                <td>autoscaleuserid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="autoscaleuserid" disabled="true" required="" class="form-control"  ></td>
                <td>924990d5-53d7-4c2b-86fd-5eec0cb95b50</td>
            </tr>
              <tr>
                <td>3</td>
                <td>counterparam</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="counterparam" disabled="true" required="" class="form-control"  ></td>
                <td>counterparam list</td>
            </tr>
            <tr>
                <td>4</td>
                <td>destroyvmgraceperiod</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="destroyvmgraceperiod" disabled="true" required="" class="form-control"  ></td>
                <td>the time allowed for existing 
                    connections to get closed 
                    before a vm is destroyed</td>
            </tr>
            <tr>
                <td>5</td>
                <td>templateid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="templateid" disabled="true" required="" class="form-control"  ></td>
                <td>924990d5-53d7-4c2b-86fd-5eec0cb95b50</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >