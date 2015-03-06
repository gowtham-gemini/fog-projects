package com.assistanz.fogpanel.licensemanager

import grails.transaction.Transactional
import com.assistanz.QueryString;
import com.assistanz.KayakoConnector;
import com.assistanz.XmlParse;
import com.kayako.api.ticket.Ticket;
import com.kayako.api.department.Department;
import com.kayako.api.ticket.TicketPriority;
import org.springframework.web.multipart.MultipartFile
import org.codehaus.groovy.grails.commons.ApplicationHolder

@Transactional
class TicketService {
    
    def kayakoApiKey =  ApplicationHolder.getApplication().config.kayakoSupport.apiKey
    def kayakoSecretKey =  ApplicationHolder.getApplication().config.kayakoSupport.secretKey
    def kayakoBaseURL =  ApplicationHolder.getApplication().config.kayakoSupport.baseURL
    
    KayakoConnector kayakoConnector = new KayakoConnector(kayakoApiKey,  kayakoSecretKey, kayakoBaseURL)
    
    def createTicket(ticket, String currentUserName, String  currentUserEmail) {
        
        def departmentName = ticket.department;
        def priority = ticket.priority;
        def instanceId = ticket.instanceId;
        def subject = ticket.subject;
        def description = ticket.description;
        def fullName = currentUserName
        def email = currentUserEmail
        String createdTicket = kayakoConnector.createTicket(departmentName, priority, instanceId, subject, description, fullName, email)
        XmlParse parse = new XmlParse()
        String ticketId = parse.parseXml(createdTicket)
        return ticketId;
    }
    
    def findTicket(String ticketId) {
        Ticket ticket = kayakoConnector.searchTicket(ticketId)
        return ticket;
    }
    
    def listTickets(String currentUserEmail) {
        def tickets = kayakoConnector.getListofTickets(currentUserEmail)
        return tickets
    }
    
    def getTicketPriority () {
        def priorities = kayakoConnector.getTicketPriority()
        return priorities
    }
    
    def getTicketDepartments () {
        def departments = kayakoConnector.getTicketDepartments()
        return departments    
    }
    
    def addPosts(String ticketId, String posts, String currentUserEmail) {
        kayakoConnector.addPosts(ticketId, posts, currentUserEmail) 
    }
    
    def editPriority(String ticketId, String priority) {
        kayakoConnector.editPriority(ticketId, priority)
    }
}