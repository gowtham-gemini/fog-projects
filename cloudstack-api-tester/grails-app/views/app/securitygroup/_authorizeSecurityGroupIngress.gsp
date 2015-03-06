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
                <td>securitygroup account</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="account" disabled="true" required="" class="form-control"  ></td>
                <td>the account of the security group</td>
            </tr>
            <tr>
                <td>2</td>
                <td>cidrlist</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="cidrlist" disabled="true" required="" class="form-control"  ></td>
                <td>the cidrlist associated</td>
            </tr>
            <tr>
                <td>3</td>
                <td>domainid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="domainid" disabled="true" required="" class="form-control"  ></td>
                <td>3bad2815-e3a1-4b79-966a-be96ee078ef7</td>
            </tr>
            <tr>
                <td>4</td>
                <td>endport</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="endport" disabled="true" required="" class="form-control"  ></td>
                <td>7080</td>
            </tr>
            <tr>
                <td>5</td>
                <td>icmp code</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="icmpcode" disabled="true" required="" class="form-control"  ></td>
                <td>error code for this icmp message</td>
            </tr>
            <tr>
                <td>6</td>
                <td>icmp type</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="icmptype" disabled="true" required="" class="form-control"  ></td>
                <td>type of icmp message</td>
            </tr>
            <tr>
                <td>7</td>
                <td>projectid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="projectid" disabled="true" required="" class="form-control"  ></td>
                <td>3bad2815-e3a1-4b79-966a-be96ee078ef7</td>
            </tr>
            <tr>
                <td>8</td>
                <td>protocol</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="protocol" disabled="true" required="" class="form-control"  ></td>
                <td>{tcp,udp}</td>
            </tr>
            <tr>
                <td>9</td>
                <td>securitygroup id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="securitygroupid" disabled="true" required="" class="form-control"  ></td>
                <td>3bad2815-e3a1-4b79-966a-be96ee078ef7</td>
            </tr>
            <tr>
                <td>10</td>
                <td>securitygroup name</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="securitygroupname" disabled="true" required="" class="form-control"  ></td>
                <td>the name of the security group</td>
            </tr>
            <tr>
                <td>11</td>
                <td>startport</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="startport" disabled="true" required="" class="form-control"  ></td>
                <td>7080</td>
            </tr>
            <tr>
                <td>12</td>
                <td>usersecuritygroup list</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="usersecuritygrouplist" disabled="true" required="" class="form-control"  ></td>
                <td>user to security group mapping</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >