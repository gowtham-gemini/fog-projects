/*
	Copyright (c) 2004-2012, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/

//>>built
define("dojo/request/node","require,./util,./handlers,../errors/RequestTimeoutError,../node!http,../node!https,../node!url,../node!stream".split(","),function(q,g,i,j,k,l,m,n){function f(d,a){var b=g.parseArgs(d,g.deepCreate(o,a),a&&a.data instanceof p),d=b.url,a=b.options,e=g.deferred(b,function(a,b){b.clientRequest.abort()}),d=m.parse(d),c=b.requestOptions={hostname:d.hostname,port:d.port,socketPath:a.socketPath,method:a.method,headers:a.headers,agent:a.agent,pfx:a.pfx,key:a.key,passphrase:a.passphrase,
cert:a.cert,ca:a.ca,ciphers:a.ciphers,rejectUnauthorized:!1===a.rejectUnauthorized?!1:!0};if(d.path)c.path=d.path;if(a.user||a.password)c.auth=(a.user||"")+":"+(a.password||"");c=b.clientRequest=("https:"===d.protocol?l:k).request(c);if(a.socketOptions&&("timeout"in a.socketOptions&&c.setTimeout(a.socketOptions.timeout),"noDelay"in a.socketOptions&&c.setNoDelay(a.socketOptions.noDelay),"keepAlive"in a.socketOptions)){var f=a.socketOptions.keepAlive;c.setKeepAlive(0<=f,f||0)}c.on("socket",function(){b.hasSocket=
!0;e.progress(b)});c.on("response",function(a){b.clientResponse=a;b.status=a.statusCode;b.getHeader=function(b){return a.headers[b.toLowerCase()]||null};var c=[];a.on("data",function(a){c.push(a)});a.on("end",function(){h&&clearTimeout(h);b.text=c.join("");try{i(b),e.resolve(b)}catch(a){e.reject(a)}})});c.on("error",e.reject);a.data?"string"===typeof a.data?c.end(a.data):a.data.pipe(c):c.end();if(a.timeout)var h=setTimeout(function(){e.cancel(new j(b))},a.timeout);return e.promise}var p=n.Stream,
o={method:"GET",query:null,data:void 0,headers:{}};g.addCommonMethods(f);return f});