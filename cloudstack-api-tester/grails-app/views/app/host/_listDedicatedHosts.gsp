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
                <td>name of the account</td>
            </tr>
            <tr>
                <td>2</td>
                <td>affinitygroup id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="affinitygroupid" disabled="true" required="" class="form-control"  ></td>
                <td>c09f8892-7225-445e-a7cf-1564bed773a3</td>
            </tr>
            <tr>
                <td>3</td>
                <td>domain id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="domainid" disabled="true" required="" class="form-control"  ></td>
                <td>c09f8892-7225-445e-a7cf-1564bed773a3</td>
            </tr>
            <tr>
                <td>4</td>
                <td>host id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="hostid" disabled="true" required="" class="form-control"  ></td>
                <td>c09f8892-7225-445e-a7cf-1564bed773a3</td>
            </tr>
            <tr>
                <td>5</td>
                <td>keyword</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="keyword" disabled="true" required="" class="form-control"  ></td>
                <td>list by keyword</td>
            </tr>
            <tr>
                <td>6</td>
                <td>page</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="page" disabled="true" required="" class="form-control"  ></td>
                <td></td>
            </tr>
            <tr>
                <td>7</td>
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