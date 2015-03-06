<!doctype html>
<html>
<g:if test="${GrailsUtil.isDevelopmentEnv()}" >
  <head>
        <title>Grails Runtime Exception</title>
        <meta name="layout" content="main">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'errors.css')}" type="text/css">
  </head>
  <body>
      <g:renderException exception="${exception}" />
  </body>
</g:if >
<g:else >
 Please contact admin
</g:else>
</html>