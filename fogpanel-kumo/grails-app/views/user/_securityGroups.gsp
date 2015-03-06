<%@ page contentType="text/html;charset=UTF-8" %>

<a href="#/user/dashboard">Back to Dashboard</a>
<div class="row-fluid">
  <div class="span8">
    <form data-dojo-type="dijit.form.Form" class="form-vertical" id="userSecurityGroupsForm">
      <input type="hidden" id="userSecurityCurrentWidgetId" name="currentComputingOfferId" value="">
      <h3> Security Groups </h3>
       <div class="row-fluid" id="userSecurityPage">    
         
      <div class="control-group span5"> 
        <label for="diskName" class="control-label">
          <span class="require">*</span>
          Name
        </label>
        <div class="controls updatable">
          <input type="text" 
                 data-dojo-type="dijit.form.ValidationTextBox" 
                 data-dojo-props="invalidMessage: 'invalid diskName',
                 required: 'required', placeHolder: 'Enter diskName', 
                 regExp: '[a-zA-Z]{4,20}', propercase: true" 
                 name="diskName" id="userSecurityGroupsName">  
          
        </div>
        <span id="userSecurityGroupsNameLabel" class="hide_lable"></span>
      </div> 
      <div class="control-group span5">
        <label for="diskDescription" class="control-label">
        <span class="require">
          *
        </span>
          Description
        </label>
        <div class="controls updatable">
          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                 data-dojo-props="promptMessage:'Enter Description here', 
                 invalidMessage: 'invalid Description', required: 'required',
                 placeHolder: 'Enter Description', regExp: '[|a-z0-9A-Z,]{4,20}'"
                 name="displayText" id="userSecurityGroupsDescription">
          
        </div>
        <span id="userSecurityGroupsDescriptionLabel" class="hide_lable"></span>
      </div>
       
  </div>
      <div class="row-fluid">
      <div class= "span3" id="">
         <button type="button" data-dojo-type= "dijit.form.Button" 
            onclick="UserSecurityGroups.add()" id="">
      OK
    </button>
    <button type="button" data-dojo-type= "dijit.form.Button" 
            onclick="UserSecurityGroups.cancel()" id="">
      Cancel
    </button>
    
      </div>
    </div>
      <div class="row-fluid" id="userSecurityGroups">
        <div data-dojo-type="dijit.layout.TabContainer"
         class="span12" style="width: 600px; height: 400px;">
          <div data-dojo-type="dijit.layout.ContentPane"
               title="Ingress Rule">
             <div class="row-fluid" id="userSecurityIngressRule">
          <div class="span12">
            <div class="control-group span3"> 
          
        <label for="" class="control-label">
          Protocol
        </label>
        <div class="controls">
          <select data-dojo-type="dijit.form.FilteringSelect"
                  data-dojo-props="placeHolder: 'Select a type'"
                  id="userSecurityIngressProtocol" onChange="UserSecurityGroups.changeIngressLabel(this)" value = "TCP">
            <option  selected>TCP</option>
            <option>UDP</option>
            <option>ICMP</option>
          </select>
        </div>
      </div>
        <div class="control-group span2">
        <label for="networkStratPort" id="userSecurityIngressStartPortLabel" class="control-label">
          Start Port
        </label>
        <div class="controls">
          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                 data-dojo-props=" 
                 invalidMessage: 'invalid Start Port', required: 'true',
                 placeHolder: 'Enter Start Port', constraints:{min:0,max:65535,places:0}"
                 name="displayText" id="userSecurityIngressStartPort">
        </div>
      </div>
          <div class="control-group span2">
            <label for="networkEndPort" id="userSecurityIngressLabel" class="control-label">
          End Port
        </label>
        <div class="controls">
          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                 data-dojo-props="invalidMessage: 'invalid End Port', required: 'true',
                 placeHolder: 'Enter End Port', constraints:{min:0,max:65535}"
                id="userSecurityIngressEndPort">
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
                              placeHolder: 'CIDR Address'"  
                              id="userSecurityIngressCidr" name="networkCidr">
        </div>
      </div>
       <div class="control-group span2">
        
        <div class="controls">
          <label for="networkCidr" class="control-label">Add</label>
          <button type="button" data-dojo-type="dijit.form.Button"
                  onclick="UserSecurityGroups.addIngress()"  
                  id="">Add</button>
        </div>
      </div>
    </div>
        
      </div>
            <div class="row-fluid">
          <div class="span12" id="userSecurityIngressGrid"></div>
        </div>
          </div>
          <div class="" data-dojo-type="dijit.layout.ContentPane"
           title="Egress Rule">
            <div class="row-fluid"  id="userSecurityEgressRule">
          <div class="span12">
            <div class="control-group span3"> 
          
        <label for="" class="control-label">
          Protocol
        </label>
        <div class="controls">
          <select data-dojo-type="dijit.form.FilteringSelect"
                  data-dojo-props="placeHolder: 'Select a type'"
                  id="userSecurityEgressProtocol"  value = "TCP"
                  onChange="UserSecurityGroups.changeEgressLabel(this)">
            <option  selected>TCP</option>
            <option>UDP</option>
            <option>ICMP</option>
          </select>
        </div>
      </div>
        <div class="control-group span2">
        <label for="networkStratPort" id="userSecurityEgressStartPort" class="control-label">
          Start Port
        </label>
        <div class="controls">
          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                 data-dojo-props=" 
                 invalidMessage: 'invalid Start Port', required: 'true',
                 placeHolder: 'Enter Start Port', constraints:{min:0,max:65535,places:0}"
                 name="displayText" id="userSecurityEgressStartPort">
        </div>
      </div>
          <div class="control-group span2">
            <label for="networkEndPort" id="userSecurityEgressEndPort" class="control-label">
          End Port
        </label>
        <div class="controls">
          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                 data-dojo-props="invalidMessage: 'invalid End Port', required: 'true',
                 placeHolder: 'Enter End Port', constraints:{min:0,max:65535}"
                id="userSecurityEgressEndPort">
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
                              placeHolder: 'CIDR Address'"
                              id="userSecurityEgressCidr" name="networkCidr">
        </div>
      </div>
            <div class="control-group span2">
        
        <div class="controls">
          <label for="" class="control-label">Add</label>
          <button type="button" data-dojo-type="dijit.form.Button"
                  onclick="UserSecurityGroups.addEgress()"  id="">Add</button>
        </div>
      </div>
    </div>
        
      </div>
        <div class="row-fluid">
          <div class="span12" id="userSecurityEgressGrid"></div>
        </div>
          </div>
        </div>
      </div>
   </form>
  </div>
  <div class="span4" id = "userSecurityCollection">
  <div id="userSecurityGroupsListItem"></div>
</div>
 
</div>