<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/cloud"><g:message code="menu.admin.services"/></a></li>
            <li>/</li>
            <li><a onclick="SecurityGroupRule.cancel();"><g:message code="menu.user.securityGroupRulesList"/></a></li>
            <li>/</li>
            <li><g:message code="common.create"/></li>
        </ul>
    </div>
</div>
<div class="new-user">
    <div class="row-fluid form-wrapper">
        <div class="row-fluid header">
            <h3 id="securityGrouprulePage"></h3><div class="span2 value_dollar pull-right" style="display: none;"><g:message code="default.valueIn"/> <span id="addRuleCurrencyValue"></span></div>
        </div>
        <div class="span6" id="securityGroupRulePage">
            <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="securityGroupRuleAddForm">
                <div id="securityGroupRuleContent" class="span12 field-box control-group">
                    <div class="span12 field-box">
                        <input type="hidden" id="ruleCurrentId" name="currentId" value="">
                        <label for="securityGroupRule" class="control-label">          
                            <g:message code="common.securityGroupRule"/> : <span class="require">*</span>
                        </label>
                        <div class="controls elements">
                            <select class="customSelectWidth" data-dojo-type="dijit.form.FilteringSelect" id="rule" 
                            value="tcp" onchange="SecurityGroupRule.showRuleFilteringSelect(this)">
                            <option value="tcp"><g:message code="common.securityGroup.customTcpRule"/></option>
                            <option value="udp"><g:message code="common.securityGroup.customUdpRule"/></option>
                            <option value="icmp"><g:message code="common.securityGroup.customIcmpRule"/></option>
                            <option value="custom"><g:message code="common.securityGroup.custom"/></option>
                            <option value="all_icmp"><g:message code="common.securityGroup.allIcmp"/></option>
                            <option value="all_tcp"><g:message code="common.securityGroup.allTcp"/></option>
                            <option value="all_udp"><g:message code="common.securityGroup.allUdp"/></option>
                            <option value="dns">DNS</option>
                            <option value="http">HTTP</option>
                            <option value="https">HTTPS</option>
                            <option value="imap">IMAP</option>
                            <option value="imaps">IMAPS</option>
                            <option value="ldap">LDAP</option>
                            <option value="ms_sql">MS SQL</option>
                            <option value="mysql">MYSQL</option>
                            <option value="pop3">POP3</option>
                            <option value="pop3s">POP3S</option>
                            <option value="rdp">RDP</option>
                            <option value="smtp">SMTP</option>
                            <option value="smtps">SMTPS</option>
                            <option value="ssh">SSH</option>
                            </select>
                            <div class="form_help_icon" style="top: 0; left: -30px;">
                                <i class="icon-info-sign" id="securityGroupRuleHelp"></i>
                                <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'securityGroupRuleHelp', showDelay: 1"><g:message code="common.help.message.securityGroupRule"/></div>
                            </div>
                        </div>
                    </div>
                    <div class="span12 field-box" id="directionDiv" style="display: block">
                        <label for="direction"  class="control-label">         
                            <g:message code="common.direction"/> :</label>
                        <div class="controls elements">
                            <select class="customSelectWidth" data-dojo-type="dijit.form.Select" id="direction">
                                <option value="ingress"><g:message code="common.securityGroup.ingress"/></option>
                                <option value="egress"><g:message code="common.securityGroup.egress"/></option>
                            </select>
                            <div class="form_help_icon" style="top: 0; left: -30px;">
                                <i class="icon-info-sign" id="directionHelp"></i>
                                <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'directionHelp', showDelay: 1"><g:message code="common.help.message.direction"/></div>
                            </div>
                        </div>
                    </div>
<!--                    <div class="span12 field-box control-group" id="portDiv" style="display: none">
                        <label for="port" class="control-label">          
                            <g:message code="common.port"/> :<span class="require">*</span>
                        </label>
                        <div class="controls elements" id="isPort" >
                            <input id="port" type="text" data-dojo-type="dijit/form/NumberTextBox" name= "port"
                            required="true" data-dojo-props="constraints:{min:1,max:65535}, placeHolder: '<g:message code="common.port"/>',
                            invalidMessage:'Please enter a numeric value.', rangeMessage:'Invalid range.', promptMessage:'<g:message code="common.port.promptMessage"/>'" />
                        </div>
                    </div>-->
                    <div class="span12 field-box" id="ipProtocolDiv" style="display: none">
                        <label for="ipProtocol" class="control-label">          
                            <g:message code="common.ipProtocol"/> : <span class="require">*</span>
                        </label>
                        <div class="controls elements" id="ipProtocol" >
                            <input id="ipProtocol" type="text" data-dojo-type="dijit/form/NumberTextBox" name= "ipProtocol"
                            data-dojo-props="constraints:{min:-1,max:255}, placeHolder: '<g:message code="common.ipProtocol"/>',
                            invalidMessage:'Please enter a numeric value.', rangeMessage:'Invalid range.', promptMessage:'<g:message code="common.ipProtocol.promptMessage"/>'" />
                            <div class="form_help_icon" style="top: 0; left: -30px;">
                                <i class="icon-info-sign" id="ipProtocolHelp"></i>
                                <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'ipProtocolHelp', showDelay: 1"><g:message code="common.help.message.ipProtocol"/></div>
                            </div>
                        </div>
                    </div>
                    <div class="span12 field-box" id="icmpTypeDiv" style="display: none">
                        <label for="icmpType" class="control-label">          
                            <g:message code="common.icmpType"/> : <span class="require">*</span>
                        </label>
                        <div class="controls elements" id="icmpType" >
                            <input id="icmpType" type="text" data-dojo-type="dijit/form/NumberTextBox" name= "icmpCode"
                            data-dojo-props="constraints:{min:-1,max:255}, placeHolder: '<g:message code="common.icmpType"/>',
                            invalidMessage:'Please enter a numeric value.', rangeMessage:'Invalid range.', promptMessage:'<g:message code="common.icmpType.promptMessage"/>'" />
                            <div class="form_help_icon" style="top: 0; left: -30px;">
                                <i class="icon-info-sign" id="icmpTypeHelp"></i>
                                <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'icmpTypeHelp', showDelay: 1"><g:message code="common.help.message.icmpType"/></div>
                            </div>
                        </div>
                    </div>
                    <div class="span12 field-box" id="icmpCodeDiv" style="display: none">
                        <label for="icmpCode" class="control-label">          
                            <g:message code="common.icmpCode"/> : <span class="require">*</span>
                        </label>
                        <div class="controls elements" id="icmptype" >
                            <input id="icmpCode" type="text" data-dojo-type="dijit/form/NumberTextBox" name= "icmpCode"
                            data-dojo-props="constraints:{min:-1,max:255}, placeHolder: '<g:message code="common.icmpCode"/>',
                            invalidMessage:'Please enter a numeric value.', rangeMessage:'Invalid range.', promptMessage:'<g:message code="common.icmpCode.promptMessage"/>'" />
                            <div class="form_help_icon" style="top: 0; left: -30px;">
                                <i class="icon-info-sign" id="icmpCodeHelp"></i>
                                <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'icmpCodeHelp', showDelay: 1"><g:message code="common.help.message.icmpCode"/></div>
                            </div>
                        </div>
                    </div>
                    <div class="span12 field-box" id="openPortDiv" style="display: block">
                        <label for="openPort"  class="control-label">         
                            <g:message code="common.openPort"/> : <span class="require">*</span>
                        </label>
                        <div class="controls elements">
                            <select class="customSelectWidth" data-dojo-type="dijit.form.Select" id="openPort" value="port" onchange="SecurityGroupRule.showOpenPortSelect(this)">
                                <option value="port"><g:message code="common.securityGroup.port"/></option>
                                <option value="range"><g:message code="common.securityGroup.portRange"/></option>
                            </select>
                            <div class="form_help_icon" style="top: 0; left: -30px;">
                                <i class="icon-info-sign" id="openPortHelp"></i>
                                <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'openPortHelp', showDelay: 1"><g:message code="common.help.message.openPort"/></div>
                            </div>
                        </div>
                    </div>
                    <div class="span12 field-box" id="portDiv" style="display: block">
                        <label for="port" class="control-label">          
                            <g:message code="common.port"/> : <span class="require">*</span>
                        </label>
                        <div class="controls elements" id="portRange" >
                            <input id="portRange" type="text" data-dojo-type="dijit/form/NumberTextBox" name= "portRange"
                            data-dojo-props="constraints:{min:-1,max:65535}, placeHolder: '<g:message code="common.port"/>',
                            invalidMessage:'Please enter a numeric value.', rangeMessage:'Invalid range.', promptMessage:'<g:message code="common.port.promptMessage"/>'" />
                            <div class="form_help_icon" style="top: 0; left: -30px;">
                                <i class="icon-info-sign" id="portRangeHelp"></i>
                                <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'portRangeHelp', showDelay: 1"><g:message code="common.help.message.portRange"/></div>
                            </div>
                        </div>
                    </div>
                    <div class="span12 field-box" id="fromPortDiv" style="display: none">
                        <label for="fromPort" class="control-label">          
                            <g:message code="common.securityGroup.fromPort"/> : <span class="require">*</span>
                        </label>
                        <div class="controls elements" >
                            <input id="fromPort" type="text" data-dojo-type="dijit/form/NumberTextBox" name= "fromPort"
                            data-dojo-props="constraints:{min:-1,max:65535}, placeHolder: '<g:message code="common.securityGroup.fromPort"/>',
                            invalidMessage:'Please enter a numeric value.', rangeMessage:'Invalid range.', promptMessage:'<g:message code="common.port.promptMessage"/>'" />
                            <div class="form_help_icon" style="top: 0; left: -30px;">
                                <i class="icon-info-sign" id="fromPortHelp"></i>
                                <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'fromPortHelp', showDelay: 1"><g:message code="common.help.message.fromPort"/></div>
                            </div>
                        </div>
                    </div>
                    <div class="span12 field-box"  id="toPortDiv" style="display: none">
                        <label for="toPort" class="control-label">          
                            <g:message code="common.securityGroup.toPort"/> : <span class="require">*</span>
                        </label>
                        <div class="controls elements" >
                            <input id="toPort" type="text" data-dojo-type="dijit/form/NumberTextBox" name= "toPort"
                            data-dojo-props="constraints:{min:-1,max:65535}, placeHolder: '<g:message code="common.securityGroup.toPort"/>',
                            invalidMessage:'Please enter a numeric value.', rangeMessage:'Invalid range.', promptMessage:'<g:message code="common.port.promptMessage"/>'" />
                            <div class="form_help_icon" style="top: 0; left: -30px;">
                                <i class="icon-info-sign" id="toPortHelp"></i>
                                <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'toPortHelp', showDelay: 1"><g:message code="common.help.message.toPort"/></div>
                            </div>
                        </div>
                    </div>
                    <div class="span12 field-box" >
                        <label for="remote"  class="control-label">         
                            <g:message code="common.remote"/> : <span class="require">*</span>
                        </label>
                        <div class="controls elements">
                            <select class="customSelectWidth" data-dojo-type="dijit.form.Select" id="remote" value="cidr" onchange="SecurityGroupRule.showRemoteSelect(this)">
                                <option value="cidr">CIDR</option>
                                <option value="sg"><g:message code="common.securityGroup"/></option>
                            </select>
                            <div class="form_help_icon" style="top: 0; left: -30px;">
                                <i class="icon-info-sign" id="remoteHelp"></i>
                                <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'remoteHelp', showDelay: 1"><g:message code="common.help.message.remote"/></div>
                            </div>
                        </div>
                    </div>
                    <div class="span12 field-box" id="cidrDiv">
                        <label for="cidr"  class="control-label">         
                            <g:message code="common.cidr"/> :</label>
                        <div class="controls elements">
                            <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                            data-dojo-props=" regExp: '[0-9/.]{0,16}', invalidMessage:'<g:message code="common.cidr.invalid"/>',
                            placeHolder: '0.0.0.0/0',promptMessage:'<g:message code="common.cidr.promptMessage"/>'" id="cidr">
                            <div class="form_help_icon" style="top: 0; left: -30px;">
                                <i class="icon-info-sign" id="cidrHelp"></i>
                                <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'cidrHelp', showDelay: 1"><g:message code="common.help.message.cidr"/></div>
                            </div>
                        </div>
                    </div>
                    <div class="span12 field-box" id="securityGroupsList" style="display:none">                        
                        <label for="securityGroupsList" class="control-label"><g:message code="common.securityGroup"/> :<span class="require">*</span></label>
                            <div class="controls updatable elements">
                                <div id="securityGroupsLists" class="selectOption"></div>     
                                <div class="form_help_icon" style="top: -25px; left: -30px;">
                                    <i class="icon-info-sign" id="securityGroupsListsHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'securityGroupsListsHelp', showDelay: 1"><g:message code="common.help.message.securityGroupsLists"/></div>
                                </div>
                            </div>
                    </div>
                    <div class="span12 field-box" id="etherTypeDiv" style="display:none ; margin-top: -20px">
                        <label for="etherType" class="control-label">          
                            <g:message code="common.etherType"/> :</label>
                        <div class="controls elements" id="etherType">
                            <select class="customSelectWidth" data-dojo-type="dijit.form.Select" id="ipVersion">
                                <option value="IPv4" selected="selected">IPv4</option>
                                <option value="IPv6">IPv6</option>
                            </select>
                            <div class="form_help_icon" style="top: 0; left: -30px;">
                                <i class="icon-info-sign" id="etherTypeHelp"></i>
                                <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'etherTypeHelp', showDelay: 1"><g:message code="common.help.message.etherType"/></div>
                            </div>
                        </div>
                    </div>               
                    <div class="span11 field-box"> 
                        <div class="span4"> </div>
                        <div id="securityGroupRuleCancelButton" class="span3 pull-right">
                            <button data-dojo-type="dijit.form.Button" onclick="SecurityGroupRule.cancel()" class="cancelbtn">
                                <g:message code="common.cancel"/>
                            </button>
                        </div>
                        <div id="securityGroupRuleAddButton" class="span1 pull-right" style="display: block;" >
                            <button id="addButton"  data-dojo-type="dijit.form.Button" onclick="SecurityGroupRule.add()" class="okbtn">
                                <g:message code="common.ok"/>
                            </button>
                        </div>        
                        <div id="securityGroupRuleUpdateButton" style="display: none;" class="span1 pull-right">
                            <button id="updateButton"  data-dojo-type="dijit.form.Button" onclick="SecurityGroupRule.update()" class="okbtn">
                                <g:message code="common.apply"/>
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </div>    
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="full_loader" id="securityGroupRuleLoader" class="span6">
    <div class="row-fluid">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.createSecurityGroupRuleInfoOne' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.createSecurityGroupRuleInfoTwo' /></p></div>              
        </div>          
    </div>
    <div class="row-fluid">        
        <a class="btn-flat default" onclick="SecurityGroupRule.gotoListFromCreatePage()"><g:message code='common.gotoSecurityGroupRuleList' /></a>
<!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->      
    </div>
</div>
