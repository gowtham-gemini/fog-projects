<!DOCTYPE html>
<html>
    <head>
    <title>Report Test</title>
</head>
<body>
      <table>
        <tr>
          <th style="width: 200px;">Invoice No.</th>
          <th>Account No.</th>
          <th>Customer name</th>
          <th>status</th>
          <th>Total Amount</th>
        </tr>
        <g:each in="${invoiceList}">
          <tr>
            <td>${it.id}</td>
            <td>${it.account.id}</td>
            <td>${it.customerName}</td>
            <td>${it.status.name()}</td>
            <td>${it.totalAmount}</td>
          </tr>
        </g:each>
    </table>
</body>
</html>
