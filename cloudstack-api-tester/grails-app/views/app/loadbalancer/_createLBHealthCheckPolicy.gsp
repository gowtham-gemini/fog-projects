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
                <td>lbruleid</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="lbruleid" required="" class="form-control"  ></td>
                <td>3bad2815-e3a1-4b79-966a-be96ee078ef7</td>
            </tr>
            <tr>
                <td>2</td>
                <td>description</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="description" disabled="true" required="" class="form-control"></td>
                <td>description</td>
            </tr>
            <tr>
                <td>3</td>
                <td>healthythreshold</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="healthythreshold" disabled="true" required="" class="form-control"></td>
                <td>2</td>
            </tr>
            <tr>
                <td>4</td>
                <td>intervaltime</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="intervaltime" disabled="true" required="" class="form-control"></td>
                <td>1sec - 20940sec</td>
            </tr>
            <tr>
                <td>5</td>
                <td>pingpath</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="pingpath" disabled="true" required="" class="form-control"></td>
                <td>http ping path</td>
            </tr>
            <tr>
                <td>6</td>
                <td>responsetimeout</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="reponsetimeout" disabled="true" required="" class="form-control"></td>
                <td>2sec - 60sec</td>
            </tr>
            <tr>
                <td>7</td>
                <td>unhealthythreshold</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="unhealthythreshold" disabled="true" required="" class="form-control"></td>
                <td>2</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >