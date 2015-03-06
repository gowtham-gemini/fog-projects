
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
                <td>cpunumber</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="cpunumber" required="" class="form-control"  ></td>
                <td>1-7 Cores</td>
            </tr>
            <tr>
                <td>2</td>
                <td>cpuspeed</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="text2" 
                    name="cpuspeed" required="" class="form-control" ></td>
                <td>100-1000 Mhz</td>
            </tr>
            <tr>
                <td>3</td>
                <td>displaytext</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="displaytext" required="" class="form-control"></td>
                <td>Display Text</td>
            </tr>
            <tr>
                <td>4</td>
                <td>memory</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="memory" required="" class="form-control"></td>
                <td>100-1000 MiB</td>
            </tr>
            <tr>
                <td>5</td>
                <td>name</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" checked="true"></td><td><input type="text" id="textt" 
                    name="name" required="" class="form-control"></td>
                <td>{Tiny,Test} Offering</td>
            </tr>
            <tr>
                <td>6</td>
                <td>bytesreadrate</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc" value="cpunumber" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" disabled="true" 
                    name="bytesreadrate" required="" class="form-control" ></td>
                <td>3</td>
            </tr>
            <tr>
                <td>7</td>
                <td>byteswriterate</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" ></td><td><input type="text" id="text2" 
                    name="byteswriterate" disabled="true" required="" class="form-control" ></td>
                <td>3</td>
            </tr>
            <tr>
                <td>8</td>
                <td>deploymentplanner</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="deploymentplanner" disabled="true" required="" class="form-control"></td>
                <td>Type of Deployment</td>
            </tr>
            <tr>
                <td>9</td>
                <td>domainid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="domainid" disabled="true" required="" class="form-control"></td>
                <td>15798d8f-e02b-4e43-a6ad-247fd48fc87b</td>
            </tr>
            <tr>
                <td>10</td>
                <td>hosttags</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="hosttags" disabled="true" required="" class="form-control"></td>
                <td></td>
            </tr>
             <tr>
                <td>11</td>
                <td>iopsreadrate</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc" value="cpunumber" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" disabled="true" 
                    name="iopsreadrate" required="" class="form-control" ></td>
                <td>3</td>
            </tr>
            <tr>
                <td>12</td>
                <td>iopswriterate</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" ></td><td><input type="text" id="text2" 
                    name="iopswriterate" disabled="true" required="" class="form-control" ></td>
                <td>4</td>
            </tr>
            <tr>
                <td>13</td>
                <td>issystem</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="issystem" disabled="true" required="" class="form-control"></td>
                <td>{true, false}</td>
            </tr>
            <tr>
                <td>14</td>
                <td>isvolatile</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="isvolatile" disabled="true" required="" class="form-control"></td>
                <td>{true, false}</td>
            </tr>
            <tr>
                <td>15</td>
                <td>limitcpuuse</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="limitcpuuse" disabled="true" required="" class="form-control"></td>
                <td>{true, false}</td>
            </tr>
            <tr>
                <td>16</td>
                <td>networkrate</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="networkrate" disabled="true" required="" class="form-control"></td>
                <td>{1, 2,....5}</td>
            </tr>
            <tr>
                <td>17</td>
                <td>offerha</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="offerha" disabled="true" required="" class="form-control"></td>
                <td>{true, false}</td>
            </tr>
                        <tr>
                <td>18</td>
                <td>serviceofferingdetails</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="serviceofferingdetails" disabled="true" required="" class="form-control"></td>
                <td>paramters</td>
            </tr>
            <tr>
                <td>19</td>
                <td>storagetype</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="storagetype" disabled="true" required="" class="form-control"></td>
                <td>{local, shared}</td>
            </tr>
            <tr>
                <td>20</td>
                <td>systemvmtype</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="systemvmtype" disabled="true" required="" class="form-control"></td>
                <td>{console proxy, domainrouter}</td>
            </tr>
            <tr>
                <td>21</td>
                <td>tags</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="tags" disabled="true" required="" class="form-control"></td>
                <td>tag value</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >