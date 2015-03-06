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
                <td>NetworkACLItem id</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="id" required="" class="form-control"  ></td>
                <td>3bad2815-e3a1-4b79-966a-be96ee078ef7</td>
            </tr>
            <tr>
                <td>2</td>
                <td>action</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="action" disabled="true" required="" class="form-control"  ></td>
                <td>{allow,deny}</td>
            </tr>
            <tr>
                <td>3</td>
                <td>cidrlist</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="cidrlist" disabled="true" required="" class="form-control"  ></td>
                <td>cidr list</td>
            </tr>
            <tr>
                <td>4</td>
                <td>endport</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="endport" disabled="true" required="" class="form-control"  ></td>
                <td>8080</td>
            </tr>
            <tr>
                <td>5</td>
                <td>icmpcode</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="icmpcode" disabled="true" required="" class="form-control"  ></td>
                <td>{0,1,2,3.....}</td>
            </tr>
            <tr>
                <td>6</td>
                <td>icmptype</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="icmptype" disabled="true" required="" class="form-control"  ></td>
                <td>type {0,1,2.....}</td>
            </tr>
            <tr>
                <td>7</td>
                <td>networkid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="networkid" disabled="true" required="" class="form-control"  ></td>
                <td>3bad2815-e3a1-4b79-966a-be96ee078ef7</td>
            </tr>
            <tr>
                <td>8</td>
                <td>number</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="number" disabled="true" required="" class="form-control"  ></td>
                <td>network number</td>
            </tr>
                <tr>
                <td>9</td>
                <td>startport</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="startport" disabled="true" required="" class="form-control"  ></td>
                <td>7070</td>
            </tr>
            <tr>
                <td>10</td>
                <td>traffictype</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="traffictype" disabled="true" required="" class="form-control"  ></td>
                <td>{ingress,egress}</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >