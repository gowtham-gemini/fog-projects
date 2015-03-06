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
                <td>api name</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)"  ></td><td><input type="text" id="textt" 
                    name="name" disabled="true" required="" class="form-control"></td>
                <td>34d1b31c-0e80-4f34-9a6e-02ee639d7dba</td>
            </tr>
            
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >