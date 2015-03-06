
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
                    name="diplaytext" required="" class="form-control"  ></td>
                <td>display text</td>
            </tr>
            <tr>
                <td>2</td>
                <td>guestiptype</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="text2" 
                    name="guestiptype" required="" class="form-control" ></td>
                <td>{shared,isolated}</td>
            </tr>
            <tr>
                <td>3</td>
                <td>networkoffering
                    name</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="text2" 
                    name="name" required="" class="form-control" ></td>
                <td>desired name</td>
            </tr>
            <tr>
                <td>4</td>
                <td>supportedservices</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="text2" 
                    name="supportedservices" required="" class="form-control" ></td>
                <td>services supported</td>
            </tr>
            <tr>
                <td>5</td>
                <td>traffictype</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="text2" 
                    name="supportedservices" required="" class="form-control" ></td>
                <td>GUEST</td>
            </tr>
            <tr>
                <td>6</td>
                <td>availability</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="availability" disabled="true" required="" class="form-control"></td>
                <td>{true,false}</td>
            </tr>
            <tr>
                <td>7</td>
                <td>conservemode</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="conservemode" disabled="true" required="" class="form-control"></td>
                <td>{true,false}</td>
            </tr>
            <tr>
                <td>8</td>
                <td>details</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="details" disabled="true" required="" class="form-control"></td>
                <td>{internallb provider,
                     publiclb provider}</td>
            </tr>
            <tr>
                <td>9</td>
                <td>egressdefault
                    policy</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="egressdefaultpolicy" disabled="true" required="" class="form-control"></td>
                <td>{true,false}</td>
            </tr>
            <tr>
                <td>10</td>
                <td>ispersistent</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="ispersistent" disabled="true" required="" class="form-control"></td>
                <td>{true,false}</td>
            </tr>
            <tr>
                <td>11</td>
                <td>maxconnections</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="maxconnections" disabled="true" required="" class="form-control"></td>
                <td>maximum number
                    of connections</td>
            </tr>
            <tr>
                <td>12</td>
                <td>networkrate</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="networkrate" disabled="true" required="" class="form-control"></td>
                <td>data rate 
                    in megabits</td>
            </tr>
            <tr>
                <td>13</td>
                <td>service
                    capability list</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="servicecapabilitylist" disabled="true" required="" class="form-control"></td>
                <td>data rate 
                    in megabits</td>
            </tr>
            <tr>
                <td>14</td>
                <td>service
                    offering id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="serviceofferingid" disabled="true" required="" class="form-control"></td>
                <td>3bad2815-e3a1-4b79-966a-be96ee078ef7</td>
            </tr>
            <tr>
                <td>15</td>
                <td>service
                    provider list</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="serviceproviderlist" disabled="true" required="" class="form-control"></td>
                <td>serviceprovider
                    mapping</td>
            </tr>
            <tr>
                <td>16</td>
                <td>specifyipranges</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="specifyipranges" disabled="true" required="" class="form-control"></td>
                <td>{true,false}</td>
            </tr>
            <tr>
                <td>16</td>
                <td>specifyvlan</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="specifyvlan" disabled="true" required="" class="form-control"></td>
                <td>{true,false}</td>
            </tr>
            <tr>
                <td>17</td>
                <td>tags</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="tags" disabled="true" required="" class="form-control"></td>
                <td>tags values</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >