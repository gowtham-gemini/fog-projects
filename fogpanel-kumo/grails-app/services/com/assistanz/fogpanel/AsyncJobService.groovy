package com.assistanz.fogpanel

import grails.transaction.Transactional

@Transactional
class AsyncJobService {

    def springSecurityService; 
                
    def addJob(jobType) {

//        Console.print(springSecurityService.currentUser);
//        def user = springSecurityService.currentUser

        def asynchronousJobs = new AsynchronousJobs()
//        asynchronousJobs.user = user
//        asynchronousJobs.account = user.account
        asynchronousJobs.createdAt = new Date()
        asynchronousJobs.status = JobStatus.valueOf("SCHEDULED")
        asynchronousJobs.jobType = JobType.valueOf(jobType)
        asynchronousJobs.save(flush: true);
        if (!asynchronousJobs.save()) {
            asynchronousJobs.errors.allErrors.each { println it }
        }
        return asynchronousJobs.id

    }
    
    def getScheduledJob(jobType) {
                
        def asyncJobs = AsynchronousJobs.createCriteria()
        
        def job = asyncJobs.list {
            eq("status", JobStatus.valueOf("RUNNING"))
            eq("jobType", JobType.valueOf(jobType))
        }
        
        if(job) {
            return ["OK"]
        } else {
            return ["FALSE"]
        }
        
    }
    
}
