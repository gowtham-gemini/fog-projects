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
                <td>autoscalegroup id</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="id" required="" class="form-control"  ></td>
                <td>924990d5-53d7-4c2b-86fd-5eec0cb95b50</td>
            </tr>
            <tr>
                <td>2</td>
                <td>interval</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="interval" disabled="true" required="" class="form-control"  ></td>
                <td>the frequency at which the 
                    conditions have to be evaluated</td>
            </tr>
            <tr>
                <td>3</td>
                <td>maxmembers</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="maxmembers" disabled="true" required="" class="form-control"  ></td>
                <td>the maximum number of 
                    members in the vmgroup</td>
            </tr>
            <tr>
                <td>4</td>
                <td>minmembers</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="minmembers" disabled="true" required="" class="form-control"  ></td>
                <td>the minimum number of 
                    members in the vmgroup</td>
            </tr>
            <tr>
                <td>4</td>
                <td>scaledownpolicyids</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="scaledownpolicyids" disabled="true" required="" class="form-control"  ></td>
                <td>list of scaledown autoscale policies</td>
            </tr>
            <tr>
                <td>5</td>
                <td>scaleuppolicyids</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="scaleuppolicyids" disabled="true" required="" class="form-control"  ></td>
                <td>list of scaleup autoscale policies</td>
            </tr>

        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >