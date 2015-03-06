<g:if test="${accountStatus.equals("ACTIVE")}">
<sec:ifAnyGranted roles="ROLE_ADMIN">
    <g:render template="responsiveAdmin"/>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles="ROLE_PAID_USER">
  <g:render template="user"/>
</sec:ifAnyGranted></g:if>
<g:elseif test="${accountStatus.equals("DISABLED")}">
  <g:render template="paymentScreen"/>
</g:elseif>
<g:elseif test="${accountStatus.equals("NOT_VERIFIED")}">
  <g:render template="accountNotVreified"/>
</g:elseif>
<g:elseif test="${accountStatus.equals("SUSPENDED")}">
  <g:render template="suspendScreen"/>
</g:elseif>
<g:elseif test="${accountStatus.equals("CANCELED")}">
    <g:if test="${accountDue > 0}">
        <g:render template="cancelPaymentScreen"/>
    </g:if>
    <g:else>
      <g:render template="cancelRefundScreen"/>
    </g:else>
</g:elseif>
<g:elseif test="${accountStatus.equals("CLOSED")}">
  <g:render template="closeScreen"/>
</g:elseif>
