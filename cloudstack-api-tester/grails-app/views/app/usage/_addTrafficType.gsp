
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
                <td>physical
                    networkid</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="physicalnetworkid" required="" class="form-control"  ></td>
                <td>cb3d4d7d-85ab-4d1c-9540-0660be9fc8b8</td>
            </tr>
            <tr>
                <td>2</td>
                <td>traffictype</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="text2" 
                    name="protocol" required="" class="form-control" ></td>
                <td>{Guest,Management,
                     Public,Storage}</td>
            </tr>
            <tr>
                <td>3</td>
                <td>kvmnetworklabel</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="kvmnetworklabel" disabled="true" required="" class="form-control"  ></td>
                <td>networkname label</td>
            </tr>
            <tr>
                <td>4</td>
                <td>vlan id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="vlan" disabled="true" required="" class="form-control"></td>
                <td>vlan id</td>
            </tr>
            <tr>
                <td>5</td>
                <td>vmwarenetworklabel</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="vmwarenetworklabel" disabled="true" required="" class="form-control"  ></td>
                <td>networkname label</td>
            </tr>
            <tr>
                <td>5</td>
                <td>xennetworklabel</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="xennetworklabel" disabled="true" required="" class="form-control"  ></td>
                <td>networkname label</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >