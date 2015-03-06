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
                <td>intervaltype</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="textt" 
                    name="intervaltype" required="" class="form-control"></td>
                <td>HOURLY,DAILY,
                    WEEKLY,MONTHLY</td>
            </tr>
            <tr>
                <td>2</td>
                <td>maxsnaps</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="textt" 
                    name="maxsnaps" required="" class="form-control"></td>
                <td>maximum snaps</td>
            </tr>
            <tr>
                <td>3</td>
                <td>schedule</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="textt" 
                    name="schedule" required="" class="form-control"></td>
                <td>time the snapshot is 
                scheduled to be taken. 
                </td>
            </tr>
            <tr>
                <td>4</td>
                <td>timezone</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="textt" 
                    name="timezone" required="" class="form-control"></td>
                <td>specific to region</td>
            </tr>
            <tr>
                <td>5</td>
                <td>volumeid</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="displaytextc"  value="displaytext" 
                    onclick="performChange(this)" checked="true" ></td><td><input type="text" id="textt" 
                    name="volumeid" required="" class="form-control"></td>
                <td>67078a76-0963-4525-bd70-06705664ff57</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >