
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
                <td>ServiceOfferingId</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="serviceofferingid" required="" class="form-control"  ></td>
                <td>29486513-c5a3-476f-8bf0-8c476dfa74c5</td>
            </tr>
            <tr>
                <td>2</td>
                <td>TemplateId</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="text2" 
                    name="templateid" required="" class="form-control" ></td>
                <td>29486513-c5a3-476f-8bf0-8c476dfa74c5</td>
            </tr>
            <tr>
                <td>3</td>
                <td>ZoneId</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="zoneid" required="" class="form-control"></td>
                <td>e5c28a18-e683-48b9-af69-38f8f81cf60e</td>
            </tr>
            <tr>
                <td>4</td>
                <td>account</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="account" required="" disabled="true" class="form-control"></td>
                <td>account in VM </td>
            </tr>
            <tr>
                <td>5</td>
                <td>affinitygroupnames</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="affinitygroupnames" disabled="true" required="" class="form-control"></td>
                <td>affinitygroupnames</td>
            </tr>
            <tr>
                <td>6</td>
                <td>diskofferingid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc" value="cpunumber" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" disabled="true" 
                    name="diskofferingid" required="" class="form-control" ></td>
                <td>e5c28a18-e683-48b9-af69-38f8f81cf60e</td>
            </tr>
            <tr>
                <td>7</td>
                <td>displayname</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" ></td><td><input type="text" id="text2" 
                    name="displayname" disabled="true" required="" class="form-control" ></td>
                <td>Offer</td>
            </tr>
             <tr>
                <td>8</td>
                <td>displayvm</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="displayvm" required="" disabled="true" class="form-control"></td>
                <td>dispvm</td>
            </tr>
            <tr>
                <td>9</td>
                <td>domainid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="domainid" disabled="true" required="" class="form-control"></td>
                <td>e5c28a18-e683-48b9-af69-38f8f81cf60e</td>
            </tr>
            <tr>
                <td>10</td>
                <td>group</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="group" disabled="true" required="" class="form-control"></td>
                <td>optional group</td>
            </tr>
            <tr>
                <td>11</td>
                <td>hostid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="hostid" disabled="true" required="" class="form-control"></td>
                <td>destination hostid</td>
            </tr>
             <tr>
                <td>12</td>
                <td>hypervisor</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc" value="cpunumber" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" disabled="true" 
                    name="hypervisor" required="" class="form-control" ></td>
                <td>hypervisor name</td>
            </tr>
            <tr>
                <td>13</td>
                <td>ip6address</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" ></td><td><input type="text" id="text2" 
                    name="ip6address" disabled="true" required="" class="form-control" ></td>
                <td>ipv6address</td>
            </tr>
            <tr>
                <td>14</td>
                <td>ipaddress</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="ipaddress" disabled="true" required="" class="form-control"></td>
                <td>ipaddress</td>
            </tr>
            <tr>
                <td>15</td>
                <td>iptonetworklist</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="iptonetworklist" disabled="true" required="" class="form-control"></td>
                <td>ip to network mapping</td>
            </tr>
            <tr>
                <td>16</td>
                <td>keyboard</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="keyboard" disabled="true" required="" class="form-control"></td>
                <td>valid Keyboard{uk,us}</td>
            </tr>
            <tr>
                <td>17</td>
                <td>keypair</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="keypair" disabled="true" required="" class="form-control"></td>
                <td>ssh keypair</td>
            </tr>
            <tr>
                <td>18</td>
                <td>name</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="name" disabled="true" required="" class="form-control"></td>
                <td>host name</td>
            </tr>
                        <tr>
                <td>19</td>
                <td>networkids</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="networkids" disabled="true" required="" class="form-control"></td>
                <td>networkids used by VM</td>
            </tr>
            <tr>
                <td>20</td>
                <td>projectid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="projectid" disabled="true" required="" class="form-control"></td>
                <td>vm for project</td>
            </tr>
            <tr>
                <td>21</td>
                <td>securitygroupids</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="securitygroupids" disabled="true" required="" class="form-control"></td>
                <td>securitygroupids for vm</td>
            </tr>
            <tr>
                <td>22</td>
                <td>securitygroupnames</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="securitygroupnames" disabled="true" required="" class="form-control"></td>
                <td>securitygroupnames for vm</td>
            </tr>
            <tr>
                <td>23</td>
                <td>size</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="size" disabled="true" required="" class="form-control"></td>
                <td>disk size</td>
            </tr>
            <tr>
                <td>24</td>
                <td>startvm</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="startvm" disabled="true" required="" class="form-control"></td>
                <td>{true,false}</td>
            </tr>
            <tr>
                <td>25</td>
                <td>userdata</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="userdata" disabled="true" required="" class="form-control"></td>
                <td>userdata to vm</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >