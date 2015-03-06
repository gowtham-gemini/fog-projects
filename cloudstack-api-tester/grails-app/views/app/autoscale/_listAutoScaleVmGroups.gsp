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
                <td>list resources by account</td>
            </tr>
            <tr>
                <td>2</td>
                <td>domain id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="domainid" disabled="true" required="" class="form-control"  ></td>
                <td>49b00016-7748-463f-88f0-a3e7ca8fc21f</td>
            </tr>
            <tr>
                <td>3</td>
                <td>autoscalevmgroup id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="id" disabled="true" required="" class="form-control"  ></td>
                <td>49b00016-7748-463f-88f0-a3e7ca8fc21f</td>
            </tr>
            <tr>
                <td>4</td>
                <td>isrecursive</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="isrecursive" disabled="true" required="" class="form-control"  ></td>
                <td>{true,false}</td>
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
                <td>lbrule id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="lbruleid" disabled="true" required="" class="form-control"  ></td>
                <td>49b00016-7748-463f-88f0-a3e7ca8fc21f</td>
            </tr>
            <tr>
                <td>7</td>
                <td>listall</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="listall" disabled="true" required="" class="form-control"  ></td>
                <td>{true,false}</td>
            </tr>
            <tr>
                <td>8</td>
                <td>page</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="page" disabled="true" required="" class="form-control"  ></td>
                <td></td>
            </tr>
            <tr>
                <td>9</td>
                <td>pagesize</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="pagesize" disabled="true" required="" class="form-control"  ></td>
                <td></td>
            </tr>
            <tr>
                <td>10</td>
                <td>policy id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="policyid" disabled="true" required="" class="form-control"  ></td>
                <td>49b00016-7748-463f-88f0-a3e7ca8fc21f</td>
            </tr>
            <tr>
                <td>11</td>
                <td>project id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="projectid" disabled="true" required="" class="form-control"  ></td>
                <td>49b00016-7748-463f-88f0-a3e7ca8fc21f</td>
            </tr>
            <tr>
                <td>12</td>
                <td>vmprofile id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="vmprofileid" disabled="true" required="" class="form-control"  ></td>
                <td>49b00016-7748-463f-88f0-a3e7ca8fc21f</td>
            </tr>
            <tr>
                <td>13</td>
                <td>zone id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="zoneid" disabled="true" required="" class="form-control"  ></td>
                <td>49b00016-7748-463f-88f0-a3e7ca8fc21f</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >