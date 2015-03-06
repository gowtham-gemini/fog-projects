
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
                <td>storagepool name</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="name" required="" class="form-control"></td>
                <td>name for the storage pool</td>
            </tr>
            <tr>
                <td>2</td>
                <td>url</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="url" required="" class="form-control"></td>
                <td>http://cloudstack/storage</td>
            </tr>
            <tr>
                <td>3</td>
                <td>zone id</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="zoneid" required="" class="form-control"></td>
                <td>29486513-c5a3-476f-8bf0-8c476dfa74c5</td>
            </tr>
            <tr>
                <td>4</td>
                <td>capacitybytes</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="capacitybytes" disabled="true" required=""  class="form-control"></td>
                <td>2</td>
            </tr>
            <tr>
                <td>5</td>
                <td>capacityiops</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="capacityiops" disabled="true" required="" class="form-control"></td>
                <td>2</td>
            </tr>
            <tr>
                <td>6</td>
                <td>cluster id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="clusterid" disabled="true" required="" class="form-control"></td>
                <td>29486513-c5a3-476f-8bf0-8c476dfa74c5</td>
            </tr>
            <tr>
                <td>7</td>
                <td>details</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc" value="cpunumber" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" disabled="true" 
                    name="details" disabled="true" required="" class="form-control" ></td>
                <td>the details for the storage pool</td>
            </tr>
            <tr>
                <td>8</td>
                <td>hypervisor type</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc" value="cpunumber" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" disabled="true" 
                    name="hypervisor" disabled="true" required="" class="form-control" ></td>
                <td>{KVM,VMware}</td>
            </tr>
            <tr>
                <td>9</td>
                <td>managed</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc" value="cpunumber" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" disabled="true" 
                    name="managed" disabled="true" required="" class="form-control" ></td>
                <td>{true,false}</td>
            </tr>
            <tr>
                <td>10</td>
                <td>pod id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="podid" disabled="true" required="" class="form-control"></td>
                <td>29486513-c5a3-476f-8bf0-8c476dfa74c5</td>
            </tr>
            <tr>
                <td>11</td>
                <td>provider</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="provider" disabled="true" required="" class="form-control"></td>
                <td>the storage provider name</td>
            </tr>
            <tr>
                <td>12</td>
                <td>scope</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="scope" disabled="true" required="" class="form-control"></td>
                <td>{cluster,zone}</td>
            </tr>
            <tr>
                <td>13</td>
                <td>tags</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="tags" disabled="true" required="" class="form-control"></td>
                <td>the tags for the storage pool</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >