
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
                <td>storageprovider type</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="type" required="" class="form-control"></td>
                <td>{primary,image}</td>
            </tr>
            <tr>
                <td>2</td>
                <td>keyword</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc" value="cpunumber" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" disabled="true" 
                    name="keyword" required="" class="form-control" ></td>
                <td>Offer</td>
            </tr>
            <tr>
                <td>3</td>
                <td>page</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="page" disabled="true" required="" class="form-control"></td>
                <td></td>
            </tr>
            <tr>
                <td>4</td>
                <td>pagesize</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="pagesize" disabled="true" required="" class="form-control"></td>
                <td></td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >