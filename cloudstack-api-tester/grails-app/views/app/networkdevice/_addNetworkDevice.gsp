
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
                <td>networkdevice
                    parameter list</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="networkdeviceparameterlist" disabled="true" required="" class="form-control"  ></td>
                <td>parameters for network device</td>
            </tr>
            <tr>
                <td>2</td>
                <td>networkdevice
                    type</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="networkdevicetype" disabled="true" required="" class="form-control"  ></td>
                <td>{ExternalDhcp,
                    PxeServer,
                    JuniperSRXFirewall}</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >