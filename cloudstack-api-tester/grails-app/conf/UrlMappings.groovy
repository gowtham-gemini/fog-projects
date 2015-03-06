class UrlMappings {

	static mappings = {
                
        "/$controller/$action?/$service?/$apiCommand"{
            constraints {
            // apply constraints here
            }
        } 
         
        //        "/$controller/$action?/$id?(.$format)?"{
        //            constraints {
        //            // apply constraints here
        //            }
        //        }
        //        
        //        "/$controller/$action?/$service?/$name"{
        //            constraints {
        //            // apply constraints here
        //            }
        //        }
        
        "/"(view:"/index")
        "500"(view:'/error')
	}
}
