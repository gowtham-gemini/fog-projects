
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
                <td>virtualmachine id</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="id" required="" class="form-control"  ></td>
                <td>id of the virtualmachine</td>
            </tr>
            <tr>
                <td>2</td>
                <td>affinitygroup ids</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="affinitygroupids" disabled="true" required="" class="form-control"  ></td>
                <td>comma separated list 
                    of affinity groups id</td>
            </tr>
            <tr>
                <td>3</td>
                <td>affinitygroup names</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="memoryc"  value="memory" 
                    onclick="performChange(this)"></td><td><input type="text" id="textt" 
                    name="affinitygroupnames" disabled="true" required="" class="form-control"></td>
                <td>comma separated list 
                    of affinity groups names</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >