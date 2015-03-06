
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
                <td>displaytext</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="displaytext" required="" class="form-control"  ></td>
                <td>displaytext of diskoffering</td>
            </tr>
            <tr>
                <td>2</td>
                <td>diskoffering name</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="name" required="" class="form-control"  ></td>
                <td>name of the disk offering</td>
            </tr>
            <tr>
                <td>3</td>
                <td>bytesreadrate</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="bytesreadrate" disabled="true" required="" class="form-control"></td>
                <td>2</td>
            </tr>
            <tr>
                <td>4</td>
                <td>byteswriterate</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="byteswriterate" disabled="true" required="" class="form-control"></td>
                <td>2</td>
            </tr>
            <tr>
                <td>5</td>
                <td>customized</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="customized" disabled="true" required="" class="form-control"  ></td>
                <td>{true,false}</td>
            </tr>
            <tr>
                <td>6</td>
                <td>customized iops</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="customizediops" disabled="true" required="" class="form-control"  ></td>
                <td>{true,false}</td>
            </tr>
            <tr>
                <td>7</td>
                <td>disksize</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="disksize" disabled="true" required="" class="form-control"  ></td>
                <td>size in GB</td>
            </tr>
            <tr>
                <td>7</td>
                <td>disksize</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="disksize" disabled="true" required="" class="form-control"  ></td>
                <td>size in GB</td>
            </tr>
            <tr>
                <td>8</td>
                <td>displayoffering</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="displayoffering" disabled="true" required="" class="form-control"  ></td>
                <td>display the offering</td>
            </tr>
            <tr>
                <td>9</td>
                <td>domain id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="domainid" disabled="true" required="" class="form-control"></td>
                <td>cb3d4d7d-85ab-4d1c-9540-0660be9fc8b8</td>
            </tr>
            <tr>
                <td>10</td>
                <td>iopsreadrate</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="iopsreadrate" disabled="true" required="" class="form-control"></td>
                <td>4</td>
            </tr>
            <tr>
                <td>11</td>
                <td>iopswriterate</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="iopswriterate" disabled="true" required="" class="form-control"></td>
                <td>4</td>
            </tr>
            <tr>
                <td>12</td>
                <td>maxiops</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="maxiops" disabled="true" required="" class="form-control"></td>
                <td>max iops of the 
                    disk offering</td>
            </tr>
            <tr>
                <td>13</td>
                <td>miniops</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="miniops" disabled="true" required="" class="form-control"></td>
                <td>min iops of the 
                    disk offering</td>
            </tr>
            <tr>
                <td>14</td>
                <td>storage type</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="storagetype" disabled="true" required="" class="form-control"></td>
                <td>{shared,local}</td>
            </tr>
            <tr>
                <td>15</td>
                <td>tags</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="tags" disabled="true" required="" class="form-control"></td>
                <td>tags for the 
                    disk offering</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >