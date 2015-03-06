package com.assistanz.fogpanel.licensemanager

import grails.plugin.springsecurity.annotation.Secured
import java.io.File

@Secured(['ROLE_USER'])
class DownloadController {

    def springSecurityService
    
    def index() { }
    
    def bundle(Long id) {
        Release release = Release.get(id);
        if (!release) {
            render(status: 404, text: 'Unable to find file for the release')
        }
        
        File file = new File(release.bundlePath)
          
        //redirect(action: "file", id: id, params: [type: "bundle", name: file.name])
        redirect(uri: "/download/file/${id}/bundle/${file.name}")
    }
    
    def vhd(Long id) {
        Release release = Release.get(id);
        if (!release) {
            render(status: 404, text: 'Unable to find file for the release')
        }
        
        File file = new File(release.vhdPath)
          
        redirect(uri: "/download/file/${id}/vhd/${file.name}")
    }
    
    @Secured(['permitAll'])
    def latest(Long id) {
        Product product = Product.get(id)
        if (!product) {
            render(status: 404, text: 'Unable to find file for the release')
        }
        
        Release release = Release.findByProduct(product).last();
        
        File file = new File(release.bundlePath)
        
        params.remove("id")
        params.remove("action")
        params.remove("controller")
          
        //redirect(action: "file", id: id, params: [type: "bundle", name: file.name])
        redirect(uri: "/download/file/${release.id}/bundle/${file.name}?" + params.collect { it }.join('&'))
    }    
    
    @Secured(['permitAll'])
    def file(Long id, String type, String fileName) {
        
        Release release = Release.get(id);
        
        if (!release) {
            render(status: 404, text: 'Unable to find file for the release')
        }
        
        def user = springSecurityService.currentUser
        if (!user && (!params.email || !User.findByUsername(params.email))) {
            render(status: 401, text: 'Should have valid account email')    
        }
        
        File file;
        if (type == "vhd") {
            file = new File(release.vhdPath)
        } else {
            file = new File(release.bundlePath)
        }
        
        if (file.name != fileName) {
            render(status: 404, text: 'Unable to find the specified file')
        }
        
        
        response.setContentType("application/octet-stream")
        response.setHeader("Content-disposition", "attachment;filename=${file.getName()}")

        response.outputStream << file.newInputStream()
    }
}
