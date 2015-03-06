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
                <td>domainid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc" onclick="performChange(this);"
                    value="cpunumber"></td><td><input type="text" id="textt" name="domainid" class="form-control"
                    disabled="true" ></td>
                <td>15798d8f-e02b-4e43-a6ad-247fd48fc87b</td>
            </tr>
              <tr>
                <td>2</td>
                <td>id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="id" required="" disabled="true" class="form-control"  ></td>
                <td>15798d8f-e02b-4e43-a6ad-247fd48fc87b</td>
            </tr>
            <tr>
                <td>3</td>
                <td>issystem</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" ></td><td><input type="text" id="text2" 
                    name="issystem" required="" disabled="true" class="form-control" ></td>
                <td>{true,false}</td>
            </tr>
            <tr>
                <td>4</td>
                <td>keyword</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="keyword" required="" disabled="true" class="form-control"></td>
                <td>Offer</td>
            </tr>
            <tr>
                <td>5</td>
                <td>name</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="name" required="" disabled="true" class="form-control"></td>
                <td>Testapi</td>
            </tr>
            <tr>
                <td>6</td>
                <td>page</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="page" required="" disabled="true" class="form-control"></td>
                <td></td>
            </tr>
            <tr>
                <td>7</td>
                <td>pagesize</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="pagesize" required="" disabled="true" class="form-control"></td>
                <td></td>
            </tr>
            <tr>
                <td>8</td>
                <td>systemvmtype</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="systemvmtype" required="" disabled="true" class="form-control"></td>
                <td>consoleproxy</td>
            </tr>
            <tr>
                <td>9</td>
                <td>virtualmachineid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="virtualmachineid" required="" disabled="true" class="form-control"></td>
                <td>dbb6d3f8-e625-11e3-ba20-001676787aec</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >
