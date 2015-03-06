define("FogPanel/VolumeListItem", [
    "dojo/_base/lang",
    "dojo/_base/declare",   
    "dijit/_TemplatedMixin",
    "dijit/_Widget",     
    "dijit/_Templated",
    "dojo/i18n",
    "dojo/text!FogPanel/templates/VolumeListItem.html",
    "dojo/i18n!dijit/nls/common",
    "dojo/i18n!./nls/translator"
], function(lang, declare,  _TemplatedMixin, widget, templated, i18n, template) {
    
      return declare("FogPanel.VolumeListItem", [_TemplatedMixin, widget, templated], {
          templateString: template,
          diskSize: "",
          diskName: "",
          additionalProperties: { diskSize: '', diskName: '', id:''},
          postCreate : function() {
            // Using the attributes defined via dojoattachpoint
            var widget = this;
            
            this.diskSizeNode.innerHTML = this.diskSize;
            this.diskNameNode.innerHTML = this.diskName;
            this.snapshotNode.onclick = this.onSnapshotNodeClick; 
            this.detachNode.onclick = this.onDetachNodeClick;           
        },
        postMixInProperties: function() {
            this.inherited(arguments);
            var labels = lang.mixin({instanceStatus: i18n.getLocalization("FogPanel", "translator", this.lang).common},          
            i18n.getLocalization("FogPanel.themes.VolumeListItem", "VolumeListItemTranslator", this.lang));          
            var prop;          
            for(prop in labels) { 
                this[prop];
                if(!this[prop + "Title"]) {
                    this[prop + "Title"] = labels[prop];                
                }
            }
        },
        getData : function() {
             this.diskSizeNode.innerHTML = this.additionalProperties.diskSize;
             this.diskNameNode.innerHTML = this.additionalProperties.diskName;
        }, 
        onClick : function() {
            
        },
        onFocus : function() {  
            
        },
        onUpArrowClick : function() {
            
        },
        onDownArrowClick : function() {
            
        },
        getWidget : function() {
             return this;
        },
        getId: function() {
            return this.id;
        }, 
        deleteWidget: function() {
            this.volumeWidget.style.display = "none";
        },
        onSnapshotNodeClick: function() {

        },
        onDetachNodeClick: function() {

        },
        enableRootState: function() {
            this.rootNode.style.display = "block";
            this.detachNode.style.display = "none";
        },
        enableloder: function() {
            this.loder.style.display = "block";
            this.info.style.display = "none";
            this.volumeWidget.style.backgroundColor = "#E4E3E2";
        },
        enableInfo: function() {
            this.loder.style.display = "none";
            this.info.style.display = "block";
            this.volumeWidget.style.backgroundColor = "#309906";
        },
        disableOptions: function() {
            this.detachNode.style.display = "none";
        }
      
     });
 });

            


