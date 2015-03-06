
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
                <td>cluster name</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="clustername" required="" class="form-control"  ></td>
                <td>the cluster name</td>
            </tr>
            <tr>
                <td>2</td>
                <td>cluster type</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="text2" 
                    name="clustertype" required="" class="form-control" ></td>
                <td>{CloudManaged,
                    ExternalManaged}</td>
            </tr>
            <tr>
                <td>3</td>
                <td>hypervisor</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="text2" 
                    name="hypervisor" required="" class="form-control" ></td>
                <td>{XenServer,KVM,VMware}</td>
            </tr>
            <tr>
                <td>4</td>
                <td>pod id</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="text2" 
                    name="podid" required="" class="form-control" ></td>
                <td>3bad2815-e3a1-4b79-966a-be96ee078ef7</td>
            </tr>
            <tr>
                <td>5</td>
                <td>zone id</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="text2" 
                    name="zoneid" required="" class="form-control" ></td>
                <td>3bad2815-e3a1-4b79-966a-be96ee078ef7</td>
            </tr>
            <tr>
                <td>6</td>
                <td>allocationstate</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="allocationstate" disabled="true" required="" class="form-control"></td>
                <td>{enabled,disabled}</td>
            </tr>
            <tr>
                <td>7</td>
                <td>guestswitch name</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="guestswitchname" disabled="true" required="" class="form-control"></td>
                <td>name of virtual switch</td>
            </tr>
            <tr>
                <td>8</td>
                <td>guestswitch type</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="guestswitchtype" disabled="true" required="" class="form-control"></td>
                <td>{vmwaresvs,vmwaredvs}</td>
            </tr>
            <tr>
                <td>9</td>
                <td>password</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="password" id="textt" 
                    name="password" disabled="true" required="" class="form-control"></td>
                <td>the password for the host</td>
            </tr>
            <tr>
                <td>10</td>
                <td>publicvswitch name</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="publicvswitchname" disabled="true" required="" class="form-control"></td>
                <td>name of virtual switch</td>
            </tr>
            <tr>
                <td>11</td>
                <td>publicvswitch type</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="publicvswitchtype" disabled="true" required="" class="form-control"></td>
                <td>type of virtual switch</td>
            </tr>
            <tr>
                <td>12</td>
                <td>url</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="url" disabled="true" required="" class="form-control"></td>
                <td>the url</td>
            </tr>
            <tr>
                <td>13</td>
                <td>username</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="username" disabled="true" required="" class="form-control"></td>
                <td>the username of the cluster</td>
            </tr>
            <tr>
                <td>14</td>
                <td>vsmipaddress</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="vsmipaddress" disabled="true" required="" class="form-control"></td>
                <td>192.168.1.2</td>
            </tr>
            <tr>
                <td>15</td>
                <td>vsmpassword</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="password" id="textt" 
                    name="vsmpassword" disabled="true" required="" class="form-control"></td>
                <td>vsm password</td>
            </tr>
            <tr>
                <td>16</td>
                <td>vsmusername</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="password" id="textt" 
                    name="vsmusername" disabled="true" required="" class="form-control"></td>
                <td>vsm username</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >