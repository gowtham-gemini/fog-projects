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
                <td>34d1b31c-0e80-4f34-9a6e-02ee639d7dba</td>
            </tr>
            <tr>
                <td>2</td>
                <td>mode</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="textt" 
                    name="mode" required="" class="form-control"></td>
                <td>HTTP_DOWNLOAD or FTP_UPLOAD</td>
            </tr>
             <tr>
                <td>3</td>
                <td>url</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="url" disabled="true" required="" class="form-control"></td>
                <td>http://google.com/file</td>
            </tr>
             <tr>
                <td>4</td>
                <td>zoneid</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="textt" 
                    name="zoneid" required="" class="form-control"></td>
                <td>34d1b31c-0e80-4f34-9a6e-02ee639d7dba</td>
            </tr>
            
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >