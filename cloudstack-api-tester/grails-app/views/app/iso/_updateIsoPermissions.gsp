
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
                <td>templateid</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="id" required="" class="form-control"  ></td>
                <td>29486513-c5a3-476f-8bf0-8c476dfa74c5</td>
            </tr>
            <tr>
                <td>2</td>
                <td>accounts</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="accounts" disabled="true" required="" class="form-control"  ></td>
                <td>Comma delimited list of accounts</td>
            </tr>
            <tr>
                <td>3</td>
                <td>isextractable</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="isextractable" disabled="true" required="" class="form-control"  ></td>
                <td>{true,false}</td>
            </tr>
            <tr>
                <td>4</td>
                <td>isfeatured</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="isfeatured" disabled="true" required="" class="form-control"></td>
                <td>{true, false}</td>
            </tr>
            <tr>
                <td>5</td>
                <td>ispublic</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="ispublic" disabled="true" required="" class="form-control"></td>
                <td>{true, false}</td>
            </tr>
            <tr>
                <td>7</td>
                <td>permission operator</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="op" disabled="true" required="" class="form-control"  ></td>
                <td>{add,remove,reset}</td>
            </tr>
            <tr>
                <td>8</td>
                <td>projectids</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="projectids" disabled="true" required="" class="form-control"  ></td>
                <td>comma delimited list of projects</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >