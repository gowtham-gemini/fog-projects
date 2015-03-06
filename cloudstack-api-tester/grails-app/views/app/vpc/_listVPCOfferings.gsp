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
                <td>displaytext</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="displaytext" disabled="true" required="" class="form-control"></td>
                <td>displaytext associated with vpc</td>
            </tr>
            <tr>
                <td>2</td>
                <td>vpcid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="id" disabled="true" required="" class="form-control"></td>
                <td>cb3d4d7d-85ab-4d1c-9540-0660be9fc8b8</td>
            </tr>
            <tr>
                <td>3</td>
                <td>isdefault</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="isdefault" disabled="true" required="" class="form-control"></td>
                <td>{true,false}</td>
            </tr>
            <tr>
                <td>4</td>
                <td>keyword</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="keyword" disabled="true" required="" class="form-control"></td>
                <td>search by keyword</td>
            </tr>
            <tr>
                <td>5</td>
                <td>name</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="name" disabled="true" required="" class="form-control"></td>
                <td>name of the vpc</td>
            </tr>
            <tr>
                <td>6</td>
                <td>page</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="page" disabled="true" required="" class="form-control"></td>
                <td></td>
            </tr>
            <tr>
                <td>7</td>
                <td>pagesize</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="pagesize" disabled="true" required="" class="form-control"></td>
                <td></td>
            </tr>
            <tr>
                <td>8</td>
                <td>state</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="state" disabled="true" required="" class="form-control"></td>
                <td>{enabled,disabled}</td>
            </tr>
            <tr>
                <td>9</td>
                <td>supportedservices</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="supportedservices" disabled="true" required="" class="form-control"></td>
                <td>certain services</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >