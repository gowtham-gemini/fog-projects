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
                <td>password</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="password" id="textt" 
                    name="password" required="" class="form-control"  ></td>
                <td>the password for the host</td>
            </tr>
            <tr>
                <td>2</td>
                <td>username</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="username" required="" class="form-control"  ></td>
                <td>the username for the host</td>
            </tr>
            <tr>
                <td>3</td>
                <td>clusterid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="clusterid" disabled="true" required="" class="form-control"  ></td>
                <td>c09f8892-7225-445e-a7cf-1564bed773a3</td>
            </tr>
            <tr>
                <td>4</td>
                <td>Optional</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="hostid" disabled="true" required="" class="form-control"  ></td>
                <td>c09f8892-7225-445e-a7cf-1564bed773a3</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >