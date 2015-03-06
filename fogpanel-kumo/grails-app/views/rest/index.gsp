<!DOCTYPE html>
<html>
    <head>
    <title>Rest Test</title>
 
    <link rel="stylesheet" href="http://download.dojotoolkit.org/release-1.8.0rc1/dojo-release-1.8.0rc1/dijit/themes/claro/claro.css"  media="screen" type="text/css" />
    <link rel="stylesheet" type="text/css" href="http://download.dojotoolkit.org/release-1.8.0rc1/dojo-release-1.8.0rc1/dojox/widget/Toaster/Toaster.css" >
    <link rel="stylesheet" type="text/css" href="http://download.dojotoolkit.org/release-1.8.0rc1/dojo-release-1.8.0rc1/dojox/widget/Wizard/Wizard.css" >
    <link rel="stylesheet" type="text/css" href="http://download.dojotoolkit.org/release-1.8.0rc1/dojo-release-1.8.0rc1/dojox/grid/resources/claroGrid.css" >
    <style>
        html, body {
            height: 100%;
            margin: 0;
            overflow: hidden;
            padding: 0;
        }

        #appLayout {
            height: 100%;
        }
        #leftCol {
            width: 14em;
        }

        #loader {
            width: 100%;
            position: static;
            height: 100%;
            left: 0px;
            top: 0px;
            display: block;
            background: #f0f0f0;
        }
        
    </style>
    
    <script type="text/javascript" src="http://download.dojotoolkit.org/release-1.8.0rc1/dojo-release-1.8.0rc1/dojo/dojo.js" 
        data-dojo-config="async: true, parseOnLoad:true">
    </script>
    <script>
        require([
            "dojo/dom", 
            "dojo/ready",
            "dojo/parser",  
            "dijit/registry",
            "dijit/TitlePane",
            "dijit/Editor",
            "dijit/form/Button", 
            "dijit/form/TextBox",
            "dijit/form/ValidationTextBox",
            "dojox/widget/Wizard",
            "dojox/widget/WizardPane",
            "dojo/store/JsonRest"
        ]);
    </script>
</head>
<body class="claro">
	<div data-dojo-type="dojox.widget.Wizard" data-dojo-props="style:'height:300px; width:400px'">
		<div data-dojo-type="dojox.widget.WizardPane">
			<p>The next two (hidden) children container AccordionContainers. You'll have to be
				more careful about the styling / sizing, as the child wants to take 100% of
				it's height. You can size explicitly if you like, or add margin-bottom to
				leave room for the buttons
			</p>
		</div>
		<div data-dojo-type="dojox.widget.WizardPane" style="padding:8px">
				<p>Test Content</p>
				<div>
				   <label id="name">Name</label><input id="name" name="name" data-dojo-type="dijit.form.TextBox" type="text" />
				   <button onclick="restSubmit()">Submit</button>
				</div>
		</div>
	</div>
	<script>
	require(["dojo/store/JsonRest"], function(JsonRestStore){


		  window.store = new JsonRestStore({target: "/CloudPortal/api/account/signup" });		  
	});
	
	function restSubmit() {
		var formObj = dojo.dom.formToObjec("myForm")
		
		  store.add({"class": "MyAccount", name: "AC NAME", address: "my address"}).then(function(data) {
			  alert(data);
		  }); 
	  }
	</script>
</body>
</html>
