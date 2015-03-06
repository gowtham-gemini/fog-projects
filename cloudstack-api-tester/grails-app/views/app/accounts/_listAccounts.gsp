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
                <td>accounttype</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="accounttype" disabled="true" required="" class="form-control"></td>
                <td>{0-User, 1-Admin, 2-DomainAdmin}</td>
            </tr>
            <tr>
                <td>2</td>
                <td>domainid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="domainid" disabled="true" required="" class="form-control"></td>
                <td>23bb5fe3-721d-414c-976c-871a1d51e57b</td>
            </tr>
            <tr>
                <td>3</td>
                <td>accountid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="id" disabled="true" required="" class="form-control"></td>
                <td>23bb5fe3-721d-414c-976c-871a1d51e57b</td>
            </tr>
            <tr>
                <td>4</td>
                <td>iscleanup required</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="iscleanuprequired" disabled="true" required="" class="form-control"></td>
                <td>{true, false}</td>
            </tr>
            <tr>
                <td>5</td>
                <td>isrecursive</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="isrecursive" disabled="true" required="" class="form-control"></td>
                <td>{true, false}</td>
            </tr>
            <tr>
                <td>6</td>
                <td>keyword</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="keyword" disabled="true" required="" class="form-control"></td>
                <td>keyword related to account</td>
            </tr>
            <tr>
                <td>7</td>
                <td>listall</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="listall" disabled="true" required="" class="form-control"></td>
                <td>{true, false}</td>
            </tr>
            <tr>
                <td>8</td>
                <td>account name</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="name" disabled="true" required="" class="form-control"></td>
                <td>Admin,Scott</td>
            </tr>
            <tr>
                <td>9</td>
                <td>page</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="page" disabled="true" required="" class="form-control"></td>
                <td></td>
            </tr>
            <tr>
                <td>10</td>
                <td>page size</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="name" disabled="true" required="" class="form-control"></td>
                <td></td>
            </tr>
            <tr>
                <td>11</td>
                <td>state</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="state" disabled="true" required="" class="form-control"></td>
                <td>{enabled,disabled,locked}</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >