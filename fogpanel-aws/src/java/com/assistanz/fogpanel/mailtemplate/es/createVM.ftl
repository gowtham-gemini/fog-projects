<div class='mainarea' style='width:600px; height:auto; float:left; border:1px solid #ececec;'>
      <div class='maincontent' style='width:550px; height:auto; float:left; margin:15px 0 0 22px; padding:0 0 15px 0;'>
        <h1 style='font-family:Arial,Helvetica,sans-serif;font-size:21px;font-weight:normal;color:#ea5800;margin:0;padding:0;height:auto;width:400px'>
        <i><b> Create VM Info<br /></b></i></h1>
        <h2 style='font-family:Arial,Helvetica,sans-serif;font-size:17px;font-weight:bold;color:#666;margin:15px 0 0 0;padding:0;height:auto;width:400px'>
          Hello ${user.account.firstName},
        </h2>
        <p style='font-family:Arial,Helvetica,sans-serif;font-size:13px;font-weight:normal;color:#333;margin:15px 0 0 0;padding:0;height:auto;width:550px'>
          Your Vm - ${displayName} is created successfully on ${createdDate}.Please find below details of the VM.
        </p>
        <p style='margin: 15px 0px 0px; padding: 0px; height: auto; width: 550px;'>
          <b style='color: rgb(51, 51, 51); font-family: Arial, Helvetica, sans-serif; font-size: 13px;'>
            Virtual Machine Infra Summary 
          </b>
          <br />
          <font face='Arial, Helvetica, sans-serif' size='2'>Zone: ${vmZone}</font>
          <b style='color: rgb(51, 51, 51); font-family: Arial, Helvetica, sans-serif; font-size: 13px;'> <br /></b>
          <font face='Arial, Helvetica, sans-serif' size='2'>Host Name - ${hostName}</font><br />
          <font face='Arial, Helvetica, sans-serif' size='2'>Password: ${vmPassword}, </font><br />
          <font face='Arial, Helvetica, sans-serif' size='2'>Main IP Address: ${vmIp} </font><br />
          <font face='Arial, Helvetica, sans-serif' size='2'>OS Template -${vmOsType}</font><br />
          <font face='Arial, Helvetica, sans-serif' size='2'>Status - ${status}   </font>
          <font size='2'><font face='Arial, Helvetica, sans-serif'><br /></font></font><br /> 
          <b style='color: rgb(51, 51, 51); font-family: Arial, Helvetica, sans-serif; font-size: 13px;'>Virtual Machine Billing Summary</b><br />
          <b style='color: rgb(51, 51, 51); font-family: Arial, Helvetica, sans-serif; font-size: 13px;'>Computation -  </b>
          <font face='Arial, Helvetica, sans-serif' size='2'>${vmOfferName}, ${vmCpuCore} CPU Core, Speed: ${vmCpuSpeed} GHZ, </font>
          <br />
          <font face='Arial, Helvetica, sans-serif' size='2'>Memory:${vmOfferMemory} , Bandwidth: ${vmBandwidth} GB,  <br />
          Setup Cost: ${vmSetupCost}, Running Cost: ${vmRunningCost}</font>
        </p>
        <p style='margin: 15px 0px 0px; padding: 0px; height: auto; width: 550px;'>
          <font face='Arial, Helvetica, sans-serif' size='2'>
          <b style='color: rgb(51, 51, 51); font-family: Arial, Helvetica, sans-serif; font-size: 13px;'>Storage -
          </b>
          <font face='Arial, Helvetica, sans-serif' size='2'>  ${vmDiskName},  ${vmDiskCost}, ${vmDiskSize} 
           </font><br />
          <b style='color: rgb(51, 51, 51); font-family: Arial, Helvetica, sans-serif; font-size: 13px;'>
            Template  -
          </b>
          <font face='Arial, Helvetica, sans-serif' size='2'>  ${vmOsType}, Root Disk Size: ${vmTemplateSize} GB, </font>
          <br />
          <font face='Arial, Helvetica, sans-serif' size='2'>Cost : ${vmTemplateCost}</font>
          <font face='Arial, Helvetica, sans-serif' size='2'><span style='line-height: 1.6666666666666667;'><br /> </span></font>
          <span style='font-family: Arial, Helvetica, sans-serif; font-size: small; line-height: 1.6666666666666667;'>Regards,</span>
          </font>
        </p>
        <p style='margin: 15px 0px 0px; padding: 0px; height: auto; width: 550px;'>
          <font face='Arial, Helvetica, sans-serif' size='2'><font face='Arial, Helvetica, sans-serif' size='2'></font>
          <b style='color: rgb(51, 51, 51); font-family: Arial, Helvetica, sans-serif; font-size: 13px; line-height: 1.6666666666666667;'>${signature}</b>
          </font>
        </p>
      </div>          
    </div>
