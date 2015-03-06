
<form action="connect">
<!--<div class="row">
    <div class="form-group">
        <label class="col-sm-2 control-label" for="signature">Signature:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="signature" name="signature" disabled="true">
        </div>
    </div>
</div>
<div class="row">
    <div class="form-group">
        <label class="col-sm-2 control-label" for="captchaValue"></label>
        <div class="col-sm-3">
            <input type="text" class="form-control" id="captchaValue" name="captchaValue">
            <p class="help-block">Enter the text in the image</p>
        </div>
        <div class="col-sm-5">
            <img src="${createLink(controller: 'simpleCaptcha', action: 'captcha')}" id="capthaImage"/>
        </div>
    </div>
</div>-->


<input type="hidden" class="form-control" id="methodValue" name="methodValue" value="${method}">
<div class="row">
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <input type="button"  class="btn btn-primary" value="Make Request">
            <g:actionSubmit name="Make Request" value="connect" controller="App" action="connect"/>
        </div>
    </div>
</div>



<div class="row">
    <div class="form-group">
        <label class="col-sm-2 control-label" for="requestUrl">Request URL:</label>
        <div class="col-sm-10">
            <textarea class="form-control" rows="5" id="requestUrl" name="requestUrl" disabled="true"></textarea>
            <!--<input type="url" class="form-control" id="requestUrl" name="requestUrl" disabled="true">-->
        </div>
    </div>
</div>
<div class="row">
    <div class="form-group">
        <label class="col-sm-2 control-label" for="requestUrl">Request Body:</label>
        <div class="col-sm-10">
            <textarea class="form-control" rows="5" id="requestBody" name="requestBody" disabled="true"></textarea>
            <!--<input type="url" class="form-control" id="requestUrl" name="requestUrl" disabled="true">-->
        </div>
    </div>
</div>
<div class="row">
    <div class="form-group">
        <label class="col-sm-2 control-label" for="response">Response :</label>
        <div class="col-sm-10">
            <textarea class="form-control" rows="50" id="response" name="response" disabled="true"></textarea>
        </div>
    </div>
</div>
</form>
