class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
                "/download/file/$id/$type/$fileName" (controller:"download", action: "file")
                "/register" (controller:"validation", action: "register")
                "/verify" (controller:"validation", action: "validate")
                "/signup" (controller:"user", action: "signup")
		"/" (controller:"home", action: "index")
		"500"(view:'/error')
	}
}
