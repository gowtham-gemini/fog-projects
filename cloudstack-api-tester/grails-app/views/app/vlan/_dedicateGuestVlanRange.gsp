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
                <td>the name of the account</td>
            </tr>
            <tr>
                <td>2</td>
                <td>domain id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="domainid" required="" class="form-control"  ></td>
                <td>3bad2815-e3a1-4b79-966a-be96ee078ef7</td>
            </tr>
            <tr>
                <td>3</td>
                <td>physicalnetwork id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="physicalnetworkid" required="" class="form-control"  ></td>
                <td>3bad2815-e3a1-4b79-966a-be96ee078ef7</td>
            </tr>
            <tr>
                <td>4</td>
                <td>vlan</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="vlan"  required="" class="form-control"  ></td>
                <td>3bad2815-e3a1-4b79-966a-be96ee078ef7</td>
            </tr>
            <tr>
                <td>5</td>
                <td>project id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="projectid" disabled="true" required="" class="form-control"  ></td>
                <td>3bad2815-e3a1-4b79-966a-be96ee078ef7</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >