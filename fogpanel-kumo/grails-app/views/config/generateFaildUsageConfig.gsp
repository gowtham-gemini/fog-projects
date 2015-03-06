<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Regenerate Failed Usage</title>
        <link rel="stylesheet" href="${resource(dir: 'css/theme/fog-classic/', file: 'fog-classic.css')}"  media="screen" type="text/css" />
        <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap.css')}" media="screen">
        <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap-responsive.css')}" media="screen">
        <script type="text/javascript"> 
           
            function showorhide(data) {
                
                if(data === 'true') {
                    document.getElementById("srartDateTd").style.display = "";
                    document.getElementById("usageTypeTd").style.display = "none";
                    
                } else {
                    document.getElementById("srartDateTd").style.display = "none";
                    document.getElementById("usageTypeTd").style.display = "";
                }
            
            }
            
        </script>
    </head>
    
    
    <body class="fog-classic" style="background: #EEEEEE">
        <div class="navbar navbar-inverse">
            <div class="navbar-inner">
                <div class="navbar-inner">
                    <a>
                        <img src="${resource(dir: 'images', file: 'fog_logo.png')}" class="logo fogPanelLogo" style="height: 35px !important;"/>
                    </a> 

                </div>
            </div>
        </div>
        <div class="hero-unit">
            <div class="row-fluid span8"> 
                
                <h2 align="center"> Regenerate Failed Usage Data:</h2>
                <div style="margin-left: 400px; margin-top: 20px;">
                    <form class="form-horizontal" action="generateFaildUsage">
                        <div class="control-group">
                            <label class="control-label" for="updateFinalInvoice">Can we overwrite in finalized invoice?</label>
                            <div class="controls">
                                <input type="radio" name="updateFinalInvoice" checked value="true" onchange="showorhide('true')"> Yes
                                <input disabled type="radio" name="updateFinalInvoice" value="false" onchange="showorhide('false')"> No
                            </div>
                        </div>
                        <div class="control-group" style="display: none;">
                            <label class="control-label" for="startDate">Please Enter Regenerate Start Date</label>
                            <div class="controls">
                                <input type="date"  id="startDate" name="startDate">
                            </div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                                <input  class="btn-flat success new-product" type="submit" value="GENERATE"  align="right"/>
                                <input  class="btn-flat success new-product" type="reset" value="Reset" align="right" />  
                            </div>
                        </div>
                    </form>
                </div>
                <h2 align="center"> Regenerate Usage Records in Cloud Stack:</h2>
                <div style="margin-left: 400px; margin-top: 20px;">
                    <form class="form-horizontal" action="generateUsageRecord">
                        <div class="control-group">
                            <label class="control-label" for="updateFinalInvoice">Start and End date for Usage record generation:</label>
                            <div class="controls">
                                <input type="date"  id="startDate" name="startDate"> -
                                <input type="date"  id="startDate" name="endDate">
                            </div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                                <input  class="btn-flat success new-product" type="submit" value="GENERATE"  align="right"/>
                                <input  class="btn-flat success new-product" type="reset" value="Reset" align="right" />  
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript"> 
        document.getElementById("startDate").value = new Date().toISOString().substring(0, 10);
    </script>
</html>
