<%@ page contentType="text/html;charset=UTF-8" %>

<div class="span8" id="networkPage">
  <form id="" data-dojo-type="dijit.form.Form"
          class="form-vertical"> 
  <input type="hidden" id="currentWidgetOfferId" name="currentComputingOfferId" value="">
  <h3> Network </h3>
  <div class="row-fluid">    
      <div class="control-group span5"> 
        <label for="diskName" class="control-label">
          <span class="require">*</span>
          Name
        </label>
        <div class="controls">
          <input type="text" 
                 data-dojo-type="dijit.form.ValidationTextBox" 
                 data-dojo-props="invalidMessage: 'invalid diskName',
                 required: 'required', placeHolder: 'Enter diskName', 
                 regExp: '[a-zA-Z]{4,20}', propercase: true" 
                 name="diskName" id="networkPageName">  
        </div>
      </div> 
      <div class="control-group span5">
        <label for="diskDescription" class="control-label">
        <span class="require">
          *
        </span>
          Description
        </label>
        <div class="controls">
          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                 data-dojo-props="promptMessage:'Enter Description here', 
                 invalidMessage: 'invalid Description', required: 'required',
                 placeHolder: 'Enter Description', regExp: '[|a-z0-9A-Z,]{4,20}'"
                 name="displayText" id="networkPageDescription">
        </div>
      </div>
       
  </div>
  <div class="row-fluid">
      <div class= "span3" id="">
         <button type="button" data-dojo-type= "dijit.form.Button" 
            onclick="FogWizardNetwork.add()" id="">
      OK
    </button>
    
      </div>
    </div>
    
  <div class="row-fluid" id="networkSecurityGroups">
    <div data-dojo-type="dijit.layout.TabContainer"
         class="span12" style="width: 600px; height: 400px"
         tabStrip="true">
      <div data-dojo-type="dijit.layout.ContentPane"
           title="Ingress Rule" selected="true">
        <div class="row-fluid">
          <div class="span12">
            <div class="control-group span3"> 
          
        <label for="" class="control-label">
          Protocol
        </label>
        <div class="controls">
          <select data-dojo-type="dijit.form.FilteringSelect"
                  data-dojo-props="placeHolder: 'Select a type'"
                  id="networkProtocol" value = "TCP">
            <option  selected>TCP</option>
            <option>UDP</option>
            <option>ICMP</option>
          </select>
        </div>
      </div>
        <div class="control-group span2">
        <label for="networkStratPort" class="control-label">
          Start Port
        </label>
        <div class="controls">
          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                 data-dojo-props=" 
                 invalidMessage: 'invalid Start Port', required: 'true',
                 placeHolder: 'Enter Start Port', constraints:{min:0,max:65535,places:0}"
                 name="displayText" id="networkStartPort">
        </div>
      </div>
          <div class="control-group span2">
        <label for="networkEndPort" class="control-label">
          End Port
        </label>
        <div class="controls">
          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                 data-dojo-props="invalidMessage: 'invalid End Port', required: 'true',
                 placeHolder: 'Enter End Port', constraints:{min:0,max:65535}"
                id="networkEndPort">
        </div>
      </div>
        <div class="control-group span3">
        <label for="networkCidr" class="control-label">
          CIDR
        </label>
        <div class="controls">
          <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
             data-dojo-props="required:'true',
                              invalidMessage:'Invalid CIDR',
                              regExp: '[0-9/.]{0,16}',
                              placeHolder: 'CIDR Address'"  id="networkCidr" name="networkCidr">
        </div>
      </div>
            <div class="control-group span2">
        
        <div class="controls">
          <label for="networkCidr" class="control-label">Add</label>
          <button type="button" data-dojo-type="dijit.form.Button"
                  onclick="FogWizardNetwork.addIngress()"      id="">Add</button>
        </div>
      </div>
    </div>
        
      </div>
        <div class="row-fluid">
          <div class="span12" id="networkGrid"></div>
        </div>
        
      </div>
      <div class="" data-dojo-type="dijit.layout.ContentPane"
           title="Egress Rule">
           <div class="row-fluid">
          <div class="span12">
            <div class="control-group span3"> 
          
        <label for="" class="control-label">
          Protocol
        </label>
        <div class="controls">
          <select data-dojo-type="dijit.form.FilteringSelect"
                  data-dojo-props="placeHolder: 'Select a type'"
                  id="networkEgressProtocol" value = "TCP">
            <option  selected>TCP</option>
            <option>UDP</option>
            <option>ICMP</option>
          </select>
        </div>
      </div>
        <div class="control-group span2">
        <label for="networkStratPort" class="control-label">
          Start Port
        </label>
        <div class="controls">
          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                 data-dojo-props=" 
                 invalidMessage: 'invalid Start Port', required: 'true',
                 placeHolder: 'Enter Start Port', constraints:{min:0,max:65535,places:0}"
                 name="displayText" id="networkEgressStartPort">
        </div>
      </div>
          <div class="control-group span2">
        <label for="networkEndPort" class="control-label">
          End Port
        </label>
        <div class="controls">
          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                 data-dojo-props="invalidMessage: 'invalid End Port', required: 'true',
                 placeHolder: 'Enter End Port', constraints:{min:0,max:65535}"
                id="networkEgressEndPort">
        </div>
      </div>
        <div class="control-group span3">
        <label for="networkCidr" class="control-label">
          CIDR
        </label>
        <div class="controls">
          <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
             data-dojo-props="required:'true',
                              invalidMessage:'Invalid CIDR',
                              regExp: '[0-9/.]{0,16}',
                              placeHolder: 'CIDR Address'" id="networkEgressCidr" name="networkCidr">
        </div>
      </div>
            <div class="control-group span2">
        
        <div class="controls">
          <label for="" class="control-label">Add</label>
          <button type="button" data-dojo-type="dijit.form.Button"
                  onclick="FogWizardNetwork.addEgress()"      id="">Add</button>
        </div>
      </div>
    </div>
        
      </div>
        <div class="row-fluid">
          <div class="span12" id="networkEgressGrid"></div>
        </div>
        
    </div>
  </div>
  </div>
</form>
  </div>

<div class="span4" id = "networkPageCollection">
  <div id="networkPageList"></div>
</div>
