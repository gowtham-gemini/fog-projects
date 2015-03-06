<div class="form-group">
    <label for="serverUrl" class="col-sm-2 control-label">Server URL:*</label>
    <div class="col-sm-10">
        <input type="text" name="serverUrl" required="true" class="form-control" id="serverUrl">
        <p class="help-block">Example: https://cloustack.com</p>
    </div>
    
</div>
<div class="form-group">
    <label for="apiUrl" class="col-sm-2 control-label">Api URL:*</label>
    <div class="col-sm-10">
        <input type="text" id="apiUrl" value="${apiUrl}" required="true" class="form-control" placeholder="Api URL" name="apiUrl">
      <p class="help-block">Example: /api/admin/Network/dedicatePublicIpRange</p>
    </div>
</div>
<div class="form-group">
    <label class="col-sm-2 control-label" for="apiKey">Api Key:*</label>
    <div class="col-sm-10">
          <input type="text" required="true" class="form-control" placeholder="Api Key" id="apiKey" name="apiKey">
    </div>
</div>
<div class="form-group">
    <label class="col-sm-2 control-label" for="secretKey">Secret Key:*</label>
    <div class="col-sm-10">
        <input onkeyup="ApiBase.getSignature()" type="text" required="true" class="form-control" placeholder="Secret Key" id="secretKey" name="secretKey">
    </div>
</div>
