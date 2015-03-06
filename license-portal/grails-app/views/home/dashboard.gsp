<sec:ifAnyGranted roles="ROLE_ADMIN">
     <g:render template="admin"/>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles="ROLE_USER">
  <g:render template="user"/>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles="ROLE_SUPPORT">
  <g:render template="support"/>
</sec:ifAnyGranted>