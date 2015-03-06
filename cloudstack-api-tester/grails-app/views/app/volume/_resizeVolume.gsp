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
            <td>1</td>
                <td>diskofferingid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="diskofferingid" disabled="true" required="" class="form-control"></td>
                <td>34d1b31c-0e80-4f34-9a6e-02ee639d7dba</td>
            </tr>
            <tr>
                <td>2</td>
                <td>diskvolume id</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="id" disabled="true" required="" class="form-control"></td>
                <td>dc363418-5c05-48a6-8ca2-ed6685300e37</td>
            </tr>
            <tr>
                <td>3</td>
                <td>shrinkok</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="shrinkok" disabled="true" required="" class="form-control"></td>
                <td></td>
            </tr>
            <tr>
                <td>4</td>
                <td>size</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="size" disabled="true" required="" class="form-control"></td>
                <td>input new size in GBs</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >