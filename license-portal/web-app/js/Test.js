var Application = {
    
    generateRow : function() {
        document.getElementById('textBlock').style.display = "block";
        document.getElementById('reply').style.display = "none";
        document.getElementById('send').style.display = "block";
        document.getElementById('cancel').style.display = "block";
    },
    
    cancel : function() {
        document.getElementById('textBlock').style.display = "none";
        document.getElementById('reply').style.display = "block";
        document.getElementById('send').style.display = "none";
        document.getElementById('cancel').style.display = "none";
    },
    editPriority : function() {
        document.getElementById("listBox").style.display = "inline-block";
        document.getElementById("Edit").style.display = "none";
        document.getElementById("saveButton").style.display = "block";
        document.getElementById("value").style.display = "none";
        
    }
};
window.Application = Application;
