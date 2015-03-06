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
                <td>counterid</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="counterid" required="" class="form-control"  ></td>
                <td>924990d5-53d7-4c2b-86fd-5eec0cb95b50</td>
            </tr>
            <tr>
                <td>2</td>
                <td>relationaloperator</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="email" required="" class="form-control"  ></td>
                <td>{==,!=,>,>=,<,<=}</td>
            </tr>
            <tr>
                <td>3</td>
                <td>threshold</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="threshold" required="" class="form-control"  ></td>
                <td>threshold value</td>
            </tr>
            <tr>
                <td>4</td>
                <td>account</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="account" disabled="true" required="" class="form-control"  ></td>
                <td>account name</td>
            </tr>
            <tr>
                <td>5</td>
                <td>domainid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="domainid" disabled="true" required="" class="form-control"  ></td>
                <td>924990d5-53d7-4c2b-86fd-5eec0cb95b50</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >