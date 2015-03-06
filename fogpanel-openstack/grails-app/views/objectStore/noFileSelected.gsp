<r:layoutResources/> 
<link rel="stylesheet" href="${resource(dir: 'css/theme/fog-classic/', file: 'fog-classic.css')}"  media="screen" type="text/css" />
<g:if test="${GrailsUtil.isDevelopmentEnv()}" >
    <script>
     var dojoConfig = {
        async: 1,
        ioPublish: true,
        cacheBust: 1,
        parseOnLoad: false,
        tlmSiblingOfDojo: true,
        locale: '${lang}',              
      };
    </script>
    <script type="text/javascript" src="${resource(dir: 'js/dojo-1.8/dojo')}/dojo.js" data-dojo-config="async: true, parseOnLoad:false"></script>
</g:if >
<g:else >
    <script>
     var dojoConfig = {
        async: 1,
        ioPublish: true,
        cacheBust: 0,
        parseOnLoad: false,
        tlmSiblingOfDojo: true,
        locale: '${lang}', 
      };
    </script>
    <script type="text/javascript" src="${resource(dir: 'js/dojo-1.8/release/dojo/dojo')}/dojo.js"></script>
    <script type="text/javascript" src="${resource(dir: 'js/dojo-1.8/release/dojo/dojo')}/dojo-x-build.js"></script>
</g:else>
<!--<div class="row-fluid">
    <div id="noFileSelected" style="padding-left : 20px; padding-right : 0px; padding-top : 0px; padding-bottom : 0px; display: block;" class="alert alert-danger">
        <g:message code="common.upload.noFileSelected"/>
        <a onclick="window.parent.ObjectInfo.closeNoFileSelectedDialog()">
            <i class="icon-remove-sign"></i>
        </a>
    </div>
</div>-->
<body onload="window.parent.ObjectInfo.noFileSelected()"></body>