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
                <td>loadbalancerrule id</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="id" required="" class="form-control"  ></td>
                <td>3bad2815-e3a1-4b79-966a-be96ee078ef7</td>
            </tr>
            <tr>
                <td>2</td>
                <td>algorithm</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="algorithm" disabled="true" required="" class="form-control"  ></td>
                <td>{source,
                    roundrobin,
                    leastconn}</td>
            </tr>
            <tr>
                <td>3</td>
                <td>description</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="description" disabled="true" required="" class="form-control"  ></td>
                <td>description of lbrule</td>
            </tr>
            <tr>
                <td>4</td>
                <td>name</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);"></td><td><input type="text" id="textt" 
                    name="name" disabled="true" required="" class="form-control"  ></td>
                <td>name of lbrule</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >