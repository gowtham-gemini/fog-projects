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
                <td>ID</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true" ></td><td><input type="text" 
                    id="textt" name="id"  required="" class="form-control"  ></td>
                <td>15798d8f-e02b-4e43-a6ad-247fd48fc87b</td>
            </tr>
             <tr>
                <td>2</td>
                <td>displaytext</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="displaytext" required="" disabled="true" class="form-control"  ></td>
                <td>Offer</td>
            </tr>
            <tr>
                <td>3</td>
                <td>name</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" ></td><td><input type="text" id="text2" 
                    name="name" required="" disabled="true" class="form-control" ></td>
                <td>Testapi</td>
            </tr>
            <tr>
                <td>4</td>
                <td>sortKey</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="sortKey" required="" disabled="true" class="form-control"></td>
                <td>3</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >
