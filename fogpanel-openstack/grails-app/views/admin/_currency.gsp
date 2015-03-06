<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/<li>
            <li><a href="#/admin/settings/billing"><g:message code="menu.admin.configuration.billing"/></a></li>
            <li>/</li>
            <li><g:message code="common.currency"/></li>    
        </ul>
    </div>
</div>

<div id="pad-wrapper" class="new-user">

    <div class="row-fluid form-wrapper">
      <!-- left column -->
        <div class="span7 with-sidebar">
            <div class="container">
                <form class="new_user_form inline-input" data-dojo-type="dijit.form.Form" id="">
                    <div class="span12 field-box">
                        <label><g:message code="common.currency"/>:</label>
                        <h4 id="cur"></h4>
                    </div>
                </form>
            </div>
        </div>
        <div class="span5">
            <div class="new_user_form inline-input">
                <div class="span12 field-box">
                    <span><g:message code="admin.defaultCurrencyInfo"/>
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>