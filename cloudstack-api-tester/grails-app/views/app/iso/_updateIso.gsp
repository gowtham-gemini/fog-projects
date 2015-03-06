
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
                <td>imagefileid</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="id" required="" class="form-control"  ></td>
                <td>29486513-c5a3-476f-8bf0-8c476dfa74c5</td>
            </tr>
            <tr>
                <td>2</td>
                <td>bootable</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="bootable" enabled="true" required="" class="form-control"  ></td>
                <td>{true,false}</td>
            </tr>
            <tr>
                <td>3</td>
                <td>displaytext</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="displaytext" enabled="true" required="" class="form-control"  ></td>
                <td>displaytext of the iso</td>
            </tr>
            <tr>
                <td>4</td>
                <td>format</td>
                <td>Required</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="format" required="" class="form-control"  ></td>
                <td>format of the image</td>
            </tr>
            <tr>
                <td>5</td>
                <td>isdynamicallyscalable</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="isdynamicallyscalable" disabled="true" required="" class="form-control"></td>
                <td>{true, false}</td>
            </tr>
            <tr>
                <td>6</td>
                <td>isrouting</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="namec" value="name" 
                    onclick="performChange(this)" ></td><td><input type="text" id="textt" 
                    name="isrouting" disabled="true" required="" class="form-control"></td>
                <td>{true, false}</td>
            </tr>
            <tr>
                <td>7</td>
                <td>imagefile name</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="name" disabled="true" required="" class="form-control"  ></td>
                <td>29486513-c5a3-476f-8bf0-8c476dfa74c5</td>
            </tr>
            <tr>
                <td>8</td>
                <td>ostypeid</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" checked="true"></td><td><input type="text" id="textt" 
                    name="ostypeid" required="" class="form-control"  ></td>
                <td>29486513-c5a3-476f-8bf0-8c476dfa74c5</td>
            </tr>
            <tr>
                <td>9</td>
                <td>passwordenabled</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpunumberc"  value="cpunumber" 
                    onclick="performChange(this);" ></td><td><input type="text" id="textt" 
                    name="passswordenabled" disabled="true" required="" class="form-control"  ></td>
                <td>{true,false}</td>
            </tr>
            <tr>
                <td>10</td>
                <td>sortkey</td>
                <td>Optional</td>
                <td><input class="send" type="checkbox" id="cpuspeedc"  value="cpuspeed" 
                    onclick="performChange(this)"  ></td><td><input type="text" id="text2" 
                    name="sortkey" disabled="true" required="" class="form-control" ></td>
                <td>sortkey of template</td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" value="${params.apiCommand}" name="command" id="command" >