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
                <td>projectid</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true" ></td><td><input type="text" id="textt" 
                    name="projectid" required="" class="form-control"  ></td>
                <td>19ac7940-5f85-4731-82e4-1ba2b59948b9</td>
            </tr>
            <tr>
                <td>2</td>
                <td>account name</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="account" disabled="true" required="" class="form-control"></td>
                <td>name of the account</td>
            </tr>
            <tr>
                <td>3</td>
                <td>email</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="email" disabled="true" required="" class="form-control"></td>
                <td>scott@gmail.com</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >