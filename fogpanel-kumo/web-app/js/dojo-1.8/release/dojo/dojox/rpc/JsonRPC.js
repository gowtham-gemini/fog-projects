//>>built
define("dojox/rpc/JsonRPC",["dojo","dojox","dojox/rpc/Service","dojo/errors/RequestError"],function(d,c,h,f){function e(b){return{serialize:function(a,g,c){a={id:this._requestId++,method:g.name,params:c};if(b)a.jsonrpc=b;return{data:d.toJson(a),handleAs:"json",contentType:"application/json",transport:"POST"}},deserialize:function(a){if("Error"==a.name||a instanceof f)a=d.fromJson(a.responseText);if(a.error){var b=Error(a.error.message||a.error);b._rpcErrorObject=a.error;return b}return a.result}}}
c.rpc.envelopeRegistry.register("JSON-RPC-1.0",function(b){return"JSON-RPC-1.0"==b},d.mixin({namedParams:!1},e()));c.rpc.envelopeRegistry.register("JSON-RPC-2.0",function(b){return"JSON-RPC-2.0"==b},e("2.0"))});