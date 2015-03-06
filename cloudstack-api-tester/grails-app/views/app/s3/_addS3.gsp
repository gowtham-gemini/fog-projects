
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
                <td>access key</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="accesskey" required="" class="form-control"  ></td>
                <td>S3 access key</td>
            </tr>
            <tr>
                <td>2</td>
                <td>bucket</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="bucket" required="" class="form-control"  ></td>
                <td>name of the template 
                    storage bucket</td>
            </tr>
            <tr>
                <td>3</td>
                <td>secret key</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="secretkey" required="" class="form-control"  ></td>
                <td>S3 secret key</td>
            </tr>
            <tr>
                <td>4</td>
                <td>connection timeout</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="connectiontimeout" disabled="true" required="" class="form-control"  ></td>
                <td>connection timeout</td>
            </tr>
            <tr>
                <td>5</td>
                <td>endpoint</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="endpoint" disabled="true" required="" class="form-control"  ></td>
                <td>S3 host name</td>
            </tr>
            <tr>
                <td>6</td>
                <td>maxerror retry</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="maxerrorretry" disabled="true" required="" class="form-control"  ></td>
                <td>maximum number 
                    of times to
                    retry on error</td>
            </tr>
            <tr>
                <td>7</td>
                <td>socket timeout</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="sockettimeout" disabled="true" required="" class="form-control"  ></td>
                <td>socket timeout</td>
            </tr>
            <tr>
                <td>8</td>
                <td>usehttps</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="usehttps" disabled="true" required="" class="form-control"  ></td>
                <td>connection to 
                    S3 endpoint</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >