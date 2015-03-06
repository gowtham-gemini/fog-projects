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
                <td>host id</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="id" required="" class="form-control"  ></td>
                <td>49b00016-7748-463f-88f0-a3e7ca8fc21f</td>
            </tr>
             <tr>
                <td>2</td>
                <td>allocationstate</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="allocationstate" disabled="true" required="" class="form-control"  ></td>
                <td>{enable,disable}</td>
            </tr>
            <tr>
                <td>3</td>
                <td>hosttags</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="hosttags" disabled="true" required="" class="form-control"  ></td>
                <td>list of tags to be added to the host</td>
            </tr>
            <tr>
                <td>4</td>
                <td>oscategoryid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="oscategoryid" disabled="true" required="" class="form-control"  ></td>
                <td>c09f8892-7225-445e-a7cf-1564bed773a3</td>
            </tr>
            <tr>
                <td>5</td>
                <td>url</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="url" disabled="true" required="" class="form-control"  ></td>
                <td>nfs://host/path</td>
            </tr>
            
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >