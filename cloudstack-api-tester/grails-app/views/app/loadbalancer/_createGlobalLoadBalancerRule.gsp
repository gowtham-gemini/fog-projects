
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
                <td>gslbdomainname</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="gslbdomainname" required="" class="form-control"  ></td>
                <td>domain name for 
                    GSLB service</td>
            </tr>
            <tr>
                <td>2</td>
                <td>gslbservicetype</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="gslbservicetype" required="" class="form-control"  ></td>
                <td>{tcp,udp}</td>
            </tr>
            <tr>
                <td>3</td>
                <td>lbrule name</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="text2" 
                    name="name" required="" class="form-control" ></td>
                <td>name for lbrule</td>
            </tr>
            <tr>
                <td>4</td>
                <td>regionid</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="regionid" required="" class="form-control"></td>
                <td>3bad2815-e3a1-4b79-966a-be96ee078ef7</td>
            </tr>
            <tr>
                <td>5</td>
                <td>account</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="account" disabled="true" required="" class="form-control"></td>
                <td>account associated</td>
            </tr>
            <tr>
                <td>6</td>
                <td>description</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="description" disabled="true" required="" class="form-control"></td>
                <td>description</td>
            </tr>
                <tr>
                <td>8</td>
                <td>domainid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="domainid" disabled="true" required="" class="form-control"></td>
                <td>cb3d4d7d-85ab-4d1c-9540-0660be9fc8b8</td>
            </tr>
            <tr>
                <td>9</td>
                <td>gslbmethod</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="gslbmethod" disabled="true" required="" class="form-control"></td>
                <td>{roundrobin,
                     leastconn,
                     proximity}</td>
            </tr>
            <tr>
                <td>10</td>
                <td>gslbstickysession
                    methodname</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="gslbstickysessionmethodname" disabled="true" required="" class="form-control"></td>
                <td>gslbsticky
                    session
                    methodname</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >