
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
                <td>virtualmachineid</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="id" required="" class="form-control"  ></td>
                <td>29486513-c5a3-476f-8bf0-8c476dfa74c5</td>
            </tr>
            <tr>
                <td>2</td>
                <td>displayname</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="account" required="" disabled="true" class="form-control"></td>
                <td>user generated name</td>
            </tr>
            <tr>
                <td>3</td>
                <td>displayvm</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="displayvm" disabled="true" required="" class="form-control"></td>
                <td>{true,false}</td>
            </tr>
            <tr>
                <td>3</td>
                <td>group</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc" value="cpunumber" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" disabled="true" 
                    name="group" required="" class="form-control" ></td>
                <td>group VM belongs to</td>
            </tr>
            <tr>
                <td>4</td>
                <td>haenable</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" ></td><td><input type="text" id="text2" 
                    name="haenable" disabled="true" required="" class="form-control" ></td>
                <td>{true,false}</td>
            </tr>
             <tr>
                <td>5</td>
                <td>isdynamicallyscalable</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="isdynamicallyscalable" required="" disabled="true" class="form-control"></td>
                <td>{true,false}</td>
            </tr>
            <tr>
                <td>6</td>
                <td>ostypeid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="ostypeid" disabled="true" required="" class="form-control"></td>
                <td>id of OS</td>
            </tr>
            <tr>
                <td>7</td>
                <td>userdata</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="userdata" disabled="true" required="" class="form-control"></td>
                <td>data to VM</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >