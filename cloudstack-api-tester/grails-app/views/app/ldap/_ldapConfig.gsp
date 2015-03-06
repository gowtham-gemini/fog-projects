
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
                <td>binddn</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="binddn" disabled="true" required="" class="form-control"  ></td>
                <td>distinguished 
                    name of user</td>
            </tr>
            <tr>
                <td>2</td>
                <td>bindpass</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="password" id="textt" 
                    name="bindpass" disabled="true" required="" class="form-control"  ></td>
                <td>desired password</td>
            </tr>
            <tr>
                <td>3</td>
                <td>hostname</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="hostname" disabled="true" required="" class="form-control"  ></td>
                <td>my.ldap.com</td>
            </tr>
            <tr>
                <td>4</td>
                <td>listall</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="listall" disabled="true" required="" class="form-control"  ></td>
                <td>{true,false}</td>
            </tr>
            <tr>
                <td>5</td>
                <td>port</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="port" disabled="true" required="" class="form-control"  ></td>
                <td>389</td>
            </tr>
            <tr>
                <td>6</td>
                <td>queryfilter</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="queryfilter" disabled="true" required="" class="form-control"  ></td>
                <td>desired queryfilter</td>
            </tr>
            <tr>
                <td>7</td>
                <td>searchbase</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="searchbase" disabled="true" required="" class="form-control"  ></td>
                <td>dc=cloud,
                    dc=com</td>
            </tr>
            <tr>
                <td>8</td>
                <td>ssl</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="ssl" disabled="true" required="" class="form-control"  ></td>
                <td>check ssl</td>
            </tr>
            <tr>
                <td>9</td>
                <td>truststore</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="truststore" disabled="true" required="" class="form-control"  ></td>
                <td>Enter the path 
                    to trust 
                    certificates store</td>
            </tr>
            <tr>
                <td>10</td>
                <td>truststore 
                    pass</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="truststorepass" disabled="true" required="" class="form-control"  ></td>
                <td>Enter the 
                    password for 
                    trust store</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >