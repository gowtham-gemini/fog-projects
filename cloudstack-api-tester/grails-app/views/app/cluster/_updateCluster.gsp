
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
                <td>cluster id</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="id" required="" class="form-control"  ></td>
                <td>cb3d4d7d-85ab-4d1c-9540-0660be9fc8b8</td>
            </tr>
            <tr>
                <td>2</td>
                <td>allocationstate</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="allocationstate" disabled="true" required="" class="form-control"></td>
                <td>{enabled,disabled}</td>
            </tr>
             <tr>
                <td>3</td>
                <td>cluster name</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="clustername" disabled="true" required="" class="form-control"  ></td>
                <td>the cluster name</td>
            </tr>
            <tr>
                <td>4</td>
                <td>cluster type</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" ></td><td><input type="text" id="text2" 
                    name="clustertype" disabled="true" required="" class="form-control" ></td>
                <td>{CloudManaged,
                    ExternalManaged}</td>
            </tr>
            <tr>
                <td>5</td>
                <td>hypervisor</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" ></td><td><input type="text" id="text2" 
                    name="hypervisor" disabled="true" required="" class="form-control" ></td>
                <td>{XenServer,KVM,VMware}</td>
            </tr>
            <tr>
                <td>6</td>
                <td>managedstate</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="password" id="textt" 
                    name="managedstate" disabled="true" required="" class="form-control"></td>
                <td>vsm username</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >