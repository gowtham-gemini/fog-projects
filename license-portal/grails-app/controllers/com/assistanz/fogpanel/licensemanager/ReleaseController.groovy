package com.assistanz.fogpanel.licensemanager



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN'])
class ReleaseController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Release.list(params), model:[releaseInstanceCount: Release.count()]
    }

    def show(Release releaseInstance) {
        respond releaseInstance
    }

    def create() {
        respond new Release(params)
    }

    @Transactional
    def save(Release releaseInstance) {
        if (releaseInstance == null) {
            notFound()
            return
        }

        if (releaseInstance.hasErrors()) {
            respond releaseInstance.errors, view:'create'
            return
        }

        releaseInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'release.label', default: 'Release'), releaseInstance.id])
                redirect releaseInstance
            }
            '*' { respond releaseInstance, [status: CREATED] }
        }
    }

    def edit(Release releaseInstance) {
        respond releaseInstance
    }

    @Transactional
    def update(Release releaseInstance) {
        if (releaseInstance == null) {
            notFound()
            return
        }

        if (releaseInstance.hasErrors()) {
            respond releaseInstance.errors, view:'edit'
            return
        }

        releaseInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Release.label', default: 'Release'), releaseInstance.id])
                redirect releaseInstance
            }
            '*'{ respond releaseInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Release releaseInstance) {

        if (releaseInstance == null) {
            notFound()
            return
        }

        releaseInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Release.label', default: 'Release'), releaseInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'release.label', default: 'Release'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
