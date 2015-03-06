grails-spring-security-oauth-fogpanel [![Build Status](https://api.travis-ci.org/donbeave/grails-spring-security-oauth-fogpanel.png?branch=master)](https://travis-ci.org/donbeave/grails-spring-security-oauth-fogpanel)
====================================

FogPanel extension for [Grails Spring Security OAuth][spring-security-oauth-plugin] plugin

Installation
------------

Add the following plugin definition to your BuildConfig:
```groovy
// ...
plugins {
  // ...
  compile ':spring-security-oauth:2.0.2'
  compile ':spring-security-oauth-fogpanel:0.1'
  // ...
}
```

Usage
-----

Add to your Config:
```groovy
oauth {
  // ...
  providers {
    // ...
    fogpanel {
      api = org.scribe.builder.api.FogPanelApi
      key = 'oauth_fogpanel_key'
      secret = 'oauth_fogpanel_secret'
      successUri = '/oauth/fogpanel/success'
      failureUri = '/oauth/fogpanel/error'
      callback = "${baseURL}/oauth/fogpanel/callback"
    }
    // ...
  }
}
```

In your view you can use the taglib exposed from this plugin and from OAuth plugin to create links and to know if the user is authenticated with a given provider:
```xml
<oauth:connect provider="fogpanel" id="fogpanel-connect-link">FogPanel</oauth:connect>

Logged with fogpanel?
<s2o:ifLoggedInWith provider="fogpanel">yes</s2o:ifLoggedInWith>
<s2o:ifNotLoggedInWith provider="fogpanel">no</s2o:ifNotLoggedInWith>
```

Copyright and license
---------------------

Copyright 2012-2014 Mihai Cazacu, Enrico Comiti and Alexey Zhokhov under the [Apache License, Version 2.0](LICENSE). Supported by [Polusharie][polusharie].

[polusharie]: http://www.polusharie.com
[spring-security-oauth-plugin]: https://github.com/enr/grails-spring-security-oauth
