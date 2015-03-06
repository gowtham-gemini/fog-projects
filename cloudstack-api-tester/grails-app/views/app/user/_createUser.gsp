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
                <td>account name</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="account" required="" class="form-control"  ></td>
                <td>Specific account name</td>
            </tr>
            <tr>
                <td>2</td>
                <td>email</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="email" required="" class="form-control"  ></td>
                <td>username@gmail.com</td>
            </tr>
            <tr>
                <td>3</td>
                <td>firstname</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="firstname" required="" class="form-control"  ></td>
                <td>scott</td>
            </tr>
            <tr>
                <td>4</td>
                <td>lastname</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="lastname" required="" class="form-control"  ></td>
                <td>tiger</td>
            </tr>
            <tr>
                <td>5</td>
                <td>password</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="password" id="textt" 
                    name="password" required="" class="form-control"  ></td>
                <td>desired password</td>
            </tr>
            <tr>
                <td>6</td>
                <td>username</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="username" required="" class="form-control"  ></td>
                <td>desired username</td>
            </tr>
            <tr>
                <td>7</td>
                <td>domainid</td>
                <td>optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="domainid" disabled="true" required="" class="form-control"  ></td>
                <td>desired account name</td>
            </tr>
            <tr>
                <td>8</td>
                <td>timezone</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc" value="cpunumber" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" disabled="true" 
                    name="timezone" required="" class="form-control" ></td>
                <td>based on region</td>
            </tr>
            <tr>
                <td>9</td>
                <td>userid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" ></td><td><input type="text" id="text2" 
                    name="userid" disabled="true" required="" class="form-control" ></td>
                <td>23bb5fe3-721d-414c-976c-871a1d51e57b</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >