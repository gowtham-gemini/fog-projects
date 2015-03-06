class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/index" (view:"/index")
        "/" (controller:"sensitiveContent", action: "index")
        "500"(view:'/error')
	}
}
