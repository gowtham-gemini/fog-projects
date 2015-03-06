define("FogPanel/StorageAction", [
    "dojo/_base/declare",
    "dojo/i18n",
    "dojo/i18n!js/dojo-1.8/FogPanel/themes/StorageAction/nls/StorageActionTranslator.js", 
    "dojo/dom-class",
    "dijit/_Widget",
    "dijit/form/Button",
    "dijit/form/DropDownButton",
    "dijit/_Templated",
    "dojo/text!FogPanel/templates/StorageAction.html"
], function(declare, i18n, StorageActionTranslator, domClass,  widget, Button, DropDownButton, templated, template) {
    
      return declare("FogPanel.StorageAction", [widget, templated], {
          templateString: template,
          tagName: StorageActionTranslator.diskDelete,
          tooltip: "",
          storageTagPath : "",
          updateTagName: StorageActionTranslator.diskUpdate,
          nameTag: StorageActionTranslator.diskNameTag,
          deleteTagName:StorageActionTranslator.diskDeleteTagName,
          attachTagName:StorageActionTranslator.diskAttachTagName,
          detachTagName:StorageActionTranslator.diskDetachTagName,
          createvolumeTagName:StorageActionTranslator.diskCreatevolumeTagName,
          postCreate : function() {               
              this.DetachButton = new Button({
                    onClick: this.onDetachButtonClick,
                    Title: this.detachTagName,
                    "class":'detach_icon'
                }, this.detachButton);
                
                this.DeleteButton = new Button({
                    onClick: this.onDeleteButtonClick,
                    Title : this.deleteTagName,
                    "class": 'delete_icon'
                }, this.deleteButton);
                
                this.attachButton = new Button({
                    onClick: this.onAttachButtonClick,
                    Title : this.attachTagName,
                    "class": 'attach_icon'
                }, this.attachButton);
                
                this.resizeButton = new Button({
                    onClick: this.onResizeButtonClick,   
                    Title: StorageActionTranslator.diskResize,
                    "class": "resize_icon"
                }, this.resizeButton);
                
                
                this.snashotButton = new Button({
                    onClick: this.onSnapshotButtonClick,
                    label : "Snapshot"
                }, this.snapshotButton);
                
                
                
//                this.tempButton = new Button({
//                    onClick: this.onTempButtonClick,
//                    label: "Create Template" 
//                }, this.tempButton);
                
                this.volumeButton = new Button({
                    onClick: this.onStorageButtonClick,
                    Title: this.createvolumeTagName,              
                    "class":"volume_icon"                    
                }, this.volumeButton);
                
                this.deleteSnapShotButton = new Button({
                    onClick: this.onDeleteSnapShotButtonClick,
                    "class":"delete_icon",
                    Title: StorageActionTranslator.diskDeleteSnapshot                   
                }, this.deleteSnapShotButton);
                this.deleteTag.onclick = this.deleteTagClick;
                this.deleteTag.innerHTML = this.tagName;
                this.deleteTag.title = this.tooltip;                                
                this.updateTag.onclick = this.updateTagClick;
                this.updateTag.innerHTML = this.updateTagName;
                this.nameTag.onclick = this.nameTagClick;                              
            },            
            nameTagClick : function() {
                
            },
            onDetachButtonClick : function() {
                
            },
            onDeleteButtonClick : function() {
                
            },
            onAttachButtonClick : function() {
                
            },
            onSnapshotButtonClick : function() {
                
            },
            onResizeButtonClick : function() {
               
            },
            onTempButtonClick : function() {
                
            },
            onStorageButtonClick : function() {
                
            },
            onDeleteSnapShotButtonClick : function() {
                
            },
            deleteTagClick : function() {
                
            },
           updateTagClick : function() {
                
            },
            enableMyTempMode : function() {
                this.attachButton.setAttribute('style', 'display: block');
                this.attachButton.setAttribute('style', 'float: left');
                
                this.DetachButton.setAttribute('style', 'display: block');
                this.DetachButton.setAttribute('style', 'float: left');
                
                this.DeleteButton.setAttribute('style', 'display: block');
                this.DeleteButton.setAttribute('style', 'float: left');
                this.resizeButton.setAttribute('style', 'display: none');
                this.snashotButton.setAttribute('style', 'display: none'); 
                
            },
            enableDetachMode : function() {
                this.attachButton.setAttribute('style', 'display: none');
                this.DeleteButton.setAttribute('style', 'display: none');
                this.snashotButton.setAttribute('style', 'display: none');
                //disable disk resize
                this.resizeButton.setAttribute('style', 'display: none');
            },
            enableAttachMode : function() {
               this.snashotButton.setAttribute('style', 'display: none'); 
               this.DetachButton.setAttribute('style', 'display: none');
               //disable disk resize
                this.resizeButton.setAttribute('style', 'display: none');
                        
            },
            showSnapShotAction : function() {
                this.storageProperties.style.display = "none";
                this.snapShotPropeties.style.display = "block";          
                
            },            
            enableSnapshotMode : function() {
                this.DetachButton.setAttribute('style', 'display: none');
                this.attachButton.setAttribute('style', 'display: none');
                this.DeleteButton.setAttribute('style', 'display: none');
                //disable disk resize
                this.resizeButton.setAttribute('style', 'display: none');
                
            },
            enableVMSnapMode : function() {
                this.DetachButton.setAttribute('style', 'display: none');
                this.attachButton.setAttribute('style', 'display: none');
                this.DeleteButton.setAttribute('style', 'display: none');
                //disable disk resize
                this.resizeButton.setAttribute('style', 'display: none');
                
            },
            disableAction : function() {
                this.DetachButton.setAttribute('disabled', true);
                this.DeleteButton.setAttribute('disabled', true);
                this.attachButton.setAttribute('disabled', true);
                this.snashotButton.setAttribute('disabled', true);
                this.resizeButton.setAttribute('disabled', true);
                //disable disk resize
                this.resizeButton.setAttribute('style', 'display: none');
            },
            disableSnapshotAction : function() {
//                this.tempButton.setAttribute('disabled', true);
                this.volumeButton.setAttribute('disabled', true);
                this.deleteSnapShotButton.setAttribute('disabled', true);
            },
            enableSnapshotDataMode : function() {
//                this.tempButton.setAttribute('style', 'display: none');
            },
            enableSnapshotRootMode : function() {
                this.volumeButton.setAttribute('style', 'display: none');
            },
            hideAddVolume: function() {
                this.volumeButton.setAttribute('style', 'display: none');
            },
            disableAll : function() {
                this.storageProperties.style.display = "none";
                this.snapShotPropeties.style.display = "none";
                this.deleteTag.style.display = "block";                
            },
            disableDelete : function() {
                this.deleteTag.style.display = "none";    
            },
            enableUpdate : function() {
                this.updateTag.style.display = "block";                
            },
            enableTageName : function() {
                this.nameTag.style.display = "inline";
                
            },
            setTagName : function(value) {
                this.nameTag.innerHTML = value;                
                this.nameTag.href = this.storageTagPath;                
            },
            setOsImage : function (url) {
                this.templateImageTag.src = url;
                this.templateImageTag.style.display = "inline";
            },
            setDeleteTagName : function(value) {
                this.deleteTag.innerHTML = value;
            }    
       });
  });
