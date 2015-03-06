package cs.api.tester

class ApplicationController {

    def api() { 
        
        def path = params.service + "/" + params.name 
        render ( view:"api",  model : [ path : path ] )
        
    }
    //    render (view: "createServiceOffering", 
    //       model: [
    //           menuDedicate: "highlight", method: "GET", 
    //           templatePath: "${params.name}"]);
    
    
    //    def deleteServiceOffering() { 
    //        render(view: "createServiceOffering", 
    //            model: [
    //                menuDedicate: "highlight", method: "GET", 
    //                templatePath: "/serviceOffering/deleteServiceOffering"]);
    //    }
    //
    //    def updateServiceOffering() { 
    //        render(view: "createServiceOffering", 
    //            model: [
    //                menuDedicate: "highlight", method: "GET",
    //                templatePath: "/serviceOffering/updateServiceOffering"]);
    //    }
    //
    //    def listServiceOfferings() { render(view: "listServiceOfferings",
    //            model: [menuDedicate: "highlight", method: "GET"]);
    //    }

}
