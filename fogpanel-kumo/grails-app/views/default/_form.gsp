<form id="configForm" data-dojo-type="dijit.form.Form" data-dojo-id="configForm">
<div>
    <h1>Exam - Add</h1>
    <a href="#/!/config/index">Back</a>

    {% if param.id %}
    <input name="id" value="{{ param.id }}" type="hidden" />
    {% endif %}
    {% if param.version %}
    <input name="version" value="{{ param.version }}" type="hidden" />
    {% endif %} 
    
    <br /><br />
    Title:                  
    <input type="text" name="title" data-dojo-id="title" id="title"
            required="true" data-dojo-type="dijit.form.ValidationTextBox" 
            placeholder="type Title" trim="true" value="{{ param.title }}"/>
    <br /><br />        
    
    Total no. of Questions:                 
    <input type="text" name="totoalNumberOfQuestion" data-dojo-id="totoalNumberOfQuestion" id="totoalNumberOfQuestion"
            required="true" data-dojo-type="dijit.form.ValidationTextBox" 
            placeholder="type Total no. of Questions" trim="true" value="{{ param.totoalNumberOfQuestion }}"/>
    <br /><br />        

    Duration:                   
    <input type="text" name="duration" data-dojo-id="duration" id="duration"
            required="true" data-dojo-type="dijit.form.ValidationTextBox" 
            placeholder="00" trim="true" value="{{ param.duration }}"/>
    <br /><br />        
    
    Hard Level Percentage :                 
    <input type="text" name="levelHardPercent" data-dojo-id="levelHardPercent" id="levelHardPercent"
            required="true" data-dojo-type="dijit.form.ValidationTextBox" 
            placeholder="00" trim="true" value="{{ param.levelHardPercent }}"/>
    <br /><br />        

    Medium Level Percentage :                   
    <input type="text" name="levelMediumPercent" data-dojo-id="levelMediumPercent" id="levelMediumPercent"
            required="true" data-dojo-type="dijit.form.ValidationTextBox" 
            placeholder="00" trim="true" value="{{ param.levelMediumPercent }}"/>
    <br /><br />        

    Easy Level Percentage :                 
    <input type="text" name="levelEasyPercent" data-dojo-id="levelEasyPercent" id="levelEasyPercent"
            required="true" data-dojo-type="dijit.form.ValidationTextBox" 
            placeholder="00" trim="true" value="{{ param.levelEasyPercent }}"/>
    <br /><br />        

    Marks per Question :                    
    <input type="text" name="marksPerQuestion" data-dojo-id="marksPerQuestion" id="marksPerQuestion"
            required="true" data-dojo-type="dijit.form.ValidationTextBox" 
            placeholder="00" trim="true" value="{{ param.marksPerQuestion }}"/>
    <br /><br />        
    
    Is Active:
    {% if param.isActive %}
        <input type="checkbox" name="isActive" id="isActive" checked  data-dojo-type="dijit.form.CheckBox"
                data-dojo-props="checked: true" >
    {% else %}
        <input type="checkbox" name="isActive" id="isActive" checked  data-dojo-type="dijit.form.CheckBox"
                data-dojo-props="checked: false" >                  
    {% endif %}
    
    <br /><br />
    <button data-dojo-type="dijit.form.Button" onclick="exam.save('{{ param.id }}')">Save</button>
</div>
</form>