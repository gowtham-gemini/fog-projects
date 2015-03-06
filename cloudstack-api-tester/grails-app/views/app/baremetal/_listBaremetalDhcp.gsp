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
                <td>dhcpserver type</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="dhcpservertype" disabled="true" required="" class="form-control"></td>
                <td>type of dhcp device</td>
            </tr>
            <tr>
                <td>2</td>
                <td>dhcpserver
                    device id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="id" disabled="true" required="" class="form-control"  ></td>
                <td>c09f8892-7225-445e-a7cf-1564bed773a3</td>
            </tr>
            <tr>
                <td>3</td>
                <td>keyword</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="keyword" disabled="true" required="" class="form-control"  ></td>
                <td>list by keyword</td>
            </tr>
            <tr>
                <td>4</td>
                <td>page</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="page" disabled="true" required="" class="form-control"  ></td>
                <td></td>
            </tr>
            <tr>
                <td>5</td>
                <td>pagesize</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="pagesize" disabled="true" required="" class="form-control"  ></td>
                <td></td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >