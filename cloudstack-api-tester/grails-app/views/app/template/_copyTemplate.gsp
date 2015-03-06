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
                <td>templateid</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="textt" 
                    name="id" required="" class="form-control"></td>
                <td>dc363418-5c05-48a6-8ca2-ed6685300e37</td>
            </tr>
            <tr>
                <td>2</td>
                <td>destzoneid</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="textt" 
                    name="destzoneid" required="" class="form-control"></td>
                <td>dc363418-5c05-48a6-8ca2-ed6685300e37</td>
            </tr>
            <tr>
                <td>3</td>
                <td>sourcezoneid</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="textt" 
                    name="sourcezoneid" required="" class="form-control"></td>
                <td>dc363418-5c05-48a6-8ca2-ed6685300e37</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >