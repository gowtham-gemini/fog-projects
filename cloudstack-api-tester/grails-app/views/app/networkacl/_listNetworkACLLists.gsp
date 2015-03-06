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
                <td>NetworkACL id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="id" disabled="true" required="" class="form-control"  ></td>
                <td>3bad2815-e3a1-4b79-966a-be96ee078ef7</td>
            </tr>
            <tr>
                <td>2</td>
                <td>keyword</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="keyword" disabled="true" required="" class="form-control"  ></td>
                <td>list by keyword</td>
            </tr>
            <tr>
                <td>3</td>
                <td>networkACL name</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="name" disabled="true" required="" class="form-control"  ></td>
                <td>networkACL name</td>
            </tr>
            <tr>
                <td>4</td>
                <td>networkid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="networkid" disabled="true" required="" class="form-control"  ></td>
                <td>3bad2815-e3a1-4b79-966a-be96ee078ef7</td>
            </tr>
            <tr>
                <td>5</td>
                <td>page</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="page" disabled="true" required="" class="form-control"  ></td>
                <td></td>
            </tr>
                <tr>
                <td>6</td>
                <td>pagesize</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="pagesize" disabled="true" required="" class="form-control"  ></td>
                <td></td>
            </tr>
            <tr>
                <td>7</td>
                <td>vpc id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="vpcid" disabled="true" required="" class="form-control"  ></td>
                <td>3bad2815-e3a1-4b79-966a-be96ee078ef7</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >