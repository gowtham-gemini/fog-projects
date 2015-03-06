<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sample title</title>
    </head>
    <body>
        <form method="POST" action="https://stg.gateway.payulatam.com/ppp-web-gateway/">
            <input type="text" id="merchantId" value="500238"/>
            <input type="text" id="referenceCode" value="TestPayU"/>
            <input type="text" id="ApiKey" value="6u39nqhq8ftd0hlvnjfs66eh8c"/>
            <input type="text" id="description" value="My Description"/>
            <input type="text" id="amount" value="3"/>
            <input type="text" id="tax" value="0"/>
            <input type="text" id="taxReturnBase" value="0"/>
            <input type="text" id="accountId" value="500538"/>
            <input type="text" id="currency" value="COP"/>
            <input type="text" id="buyerEmail" value="muthulakshmi.sk@assistanz.com"/>
            <input type="text" id="signature" value="be2f083cb3391c84fdf5fd6176801278"/>
            <input type="submit" value="Pay"/>
        </form>
    </body>
</html>
