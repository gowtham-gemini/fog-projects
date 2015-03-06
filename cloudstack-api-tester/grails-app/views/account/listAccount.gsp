<html>
    <head>
        <meta name="layout" content="api_page" />
        <title>List Account</title>       
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <h4>List Account</h4>
            </div>
            <div class="row col-xs-12 col-md-8">
                <form class="form-horizontal">
                    <div id="form-data">
                        <div class="row">
                            <g:render contextPath="" template="/default/apiDefaultParam"/>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Arguments:</label>
                                <div class="col-sm-10">
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
                                                <td>status</td>
                                                <td>Optional</td>
                                                <td><input onchange="ApiBase.getSignature()" class="send" type="checkbox" id="sentStatus"></td>
                                                <td><input onkeyup="ApiBase.getSignature()" type="text" id="status" name="status" class="form-control"></td>
                                                <td>ACTIVE</td>
                                            </tr>
                                            <tr>
                                                <td>2</td>
                                                <td>fromDate</td>
                                                <td>Optional</td>
                                                <td><input onchange="ApiBase.getSignature()" class="send" type="checkbox" id="sentFromDate"></td>
                                                <td><input onkeyup="ApiBase.getSignature()" type="text" id="fromDate" name="fromDate" class="form-control"></td>
                                                <td>2014-02-14</td>
                                            </tr>
                                            <tr>
                                                <td>3</td>
                                                <td>toDate</td>
                                                <td>Optional</td>
                                                <td><input onchange="ApiBase.getSignature()" class="send" type="checkbox" id="sentToDate"></td>
                                                <td><input onkeyup="ApiBase.getSignature()" type="text" id="toDate" name="toDate" class="form-control"></td>
                                                <td>2015-03-1</td>
                                            </tr>
                                            <tr>
                                                <td>4</td>
                                                <td>page</td>
                                                <td>Optional</td>
                                                <td><input onchange="ApiBase.getSignature()" class="send" type="checkbox" id="sentPageNo" ></td>
                                                <td><input onkeyup="ApiBase.getSignature()" type="text" id="page" name="page" class="form-control"></td>
                                                <td>3</td>
                                            </tr>
                                            <tr>
                                                <td>5</td>
                                                <td>recordPerPage</td>
                                                <td>Optional</td>
                                                <td><input onchange="ApiBase.getSignature()" class="send" type="checkbox" id="sentRecordPerPage" ></td>
                                                <td><input onkeyup="ApiBase.getSignature()" type="text" id="recordPerPage" name="recordPerPage" class="form-control"></td>
                                                <td>5</td>
                                            </tr>                                                                                        
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <g:render contextPath="" template="/default/apiResponseContent"/>
                </form>
            </div> 
        </div>
    </body>    
</html>
