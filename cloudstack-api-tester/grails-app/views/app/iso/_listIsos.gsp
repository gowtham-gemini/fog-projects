
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
                <td>optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="account" disabled="true" required="" class="form-control"  ></td>
                <td>account name</td>
            </tr>
            <tr>
                <td>2</td>
                <td>bootable</td>
                <td>optional</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)"  ></td><td><input type="text" id="text2" 
                    name="bootable" disabled="true" required="" class="form-control" ></td>
                <td>{true,false}</td>
            </tr>
            <tr>
                <td>3</td>
                <td>domainid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="domainid" required="" disabled="true" class="form-control"></td>
                <td>29486513-c5a3-476f-8bf0-8c476dfa74c5</td>
            </tr>
            <tr>
                <td>4</td>
                <td>hypervisor</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="hypervisor" required="" disabled="true" class="form-control"></td>
                <td>target hypervisor</td>
            </tr>
            <tr>
                <td>5</td>
                <td>isoid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc" value="cpunumber" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" disabled="true" 
                    name="id" required="" class="form-control" ></td>
                <td>e5c28a18-e683-48b9-af69-38f8f81cf60e</td>
            </tr>
            <tr>
                <td>6</td>
                <td>isofilter</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="isofilter" disabled="true" required="" class="form-control"></td>
                <td>{featured,self,selfexecutable}</td>
            </tr>
            <tr>
                <td>7</td>
                <td>ispublic</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="ispublic" disabled="true" required="" class="form-control"></td>
                <td>{true, false}</td>
            </tr>
            <tr>
                <td>8</td>
                <td>isready</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="isready" disabled="true" required="" class="form-control"></td>
                <td>{true, false}</td>
            </tr>
            <tr>
                <td>9</td>
                <td>isrecusive</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="isrecursive" disabled="true" required="" class="form-control"></td>
                <td>{true, false}</td>
            </tr>
             <tr>
                <td>10</td>
                <td>keyword</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc" value="cpunumber" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" disabled="true" 
                    name="keyword" required="" class="form-control" ></td>
                <td>Offer</td>
            </tr>
            <tr>
                <td>11</td>
                <td>listall</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" ></td><td><input type="text" id="text2" 
                    name="listall" disabled="true" required="" class="form-control" ></td>
                <td>{true, false}</td>
            </tr>
            <tr>
                <td>12</td>
                <td>isoname</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="name" disabled="true" required="" class="form-control"></td>
                <td>name of VM</td>
            </tr>
            <tr>
                <td>13</td>
                <td>page</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="page" disabled="true" required="" class="form-control"></td>
                <td></td>
            </tr>
            <tr>
                <td>14</td>
                <td>pagesize</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="pagesize" disabled="true" required="" class="form-control"></td>
                <td></td>
            </tr>
            <tr>
                <td>15</td>
                <td>projectid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="projectid" disabled="true" required="" class="form-control"></td>
                <td>e5c28a18-e683-48b9-af69-38f8f81cf60e</td>
            </tr>
            <tr>
                <td>16</td>
                <td>tags</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="tags" disabled="true" required="" class="form-control"></td>
                <td>Key/value pair</td>
            </tr>
            <tr>
                <td>17</td>
                <td>zoneid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="zoneid" disabled="true" required="" class="form-control"></td>
                <td>e5c28a18-e683-48b9-af69-38f8f81cf60e</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >