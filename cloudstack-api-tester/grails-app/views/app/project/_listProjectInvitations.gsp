
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
                <td>account</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="account" disabled="true" required="" class="form-control"  ></td>
                <td>account name</td>
            </tr>
            <tr>
                <td>2</td>
                <td>activeonly</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="activeonly" disabled="true" required="" class="form-control"  ></td>
                <td>{true,false}</td>
            </tr>
            <tr>
                <td>3</td>
                <td>domainid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="domainid" required="" disabled="true" class="form-control"></td>
                <td>29486513-c5a3-476f-8bf0-8c476dfa74c5</td>
            </tr>
            <tr>
                <td>4</td>
                <td>invitationid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc" value="cpunumber" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" disabled="true" 
                    name="id" required="" class="form-control" ></td>
                <td>e5c28a18-e683-48b9-af69-38f8f81cf60e</td>
            </tr>
            <tr>
                <td>5</td>
                <td>isrecusive</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="isrecursive" disabled="true" required="" class="form-control"></td>
                <td>{true, false}</td>
            </tr>
             <tr>
                <td>6</td>
                <td>keyword</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc" value="cpunumber" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" disabled="true" 
                    name="keyword" required="" class="form-control" ></td>
                <td>Offer</td>
            </tr>
            <tr>
                <td>7</td>
                <td>listall</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" ></td><td><input type="text" id="text2" 
                    name="listall" disabled="true" required="" class="form-control" ></td>
                <td>{true, false}</td>
            </tr>
            <tr>
                <td>8</td>
                <td>page</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="page" disabled="true" required="" class="form-control"></td>
                <td></td>
            </tr>
            <tr>
                <td>9</td>
                <td>pagesize</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="pagesize" disabled="true" required="" class="form-control"></td>
                <td></td>
            </tr>
            <tr>
                <td>11</td>
                <td>project id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="projectid" disabled="true" required="" class="form-control"></td>
                <td>e5c28a18-e683-48b9-af69-38f8f81cf60e</td>
            </tr>
            <tr>
                <td>12</td>
                <td>state</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="state" disabled="true" required="" class="form-control"></td>
                <td>{enabled,disabled}</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >