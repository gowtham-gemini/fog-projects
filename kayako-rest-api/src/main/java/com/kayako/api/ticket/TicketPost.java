package com.kayako.api.ticket;

/*
Copyright (c) 2013 Kayako

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
*/

import com.kayako.api.enums.TicketPostCreatorEnum;
import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.KEntity;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.user.Staff;
import com.kayako.api.user.User;
import com.kayako.api.util.Helper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Ticket post.
 */
public class TicketPost extends KEntity {

    /**
     * The Controller.
     */
    static protected String controller = "/Tickets/TicketPost";
    /**
     * The Object xml name.
     */
    static protected String objectXmlName = "post";

    /**
     * Ticket post identifier.
     *
     * @param int
     */
    protected int id;
    /**
     * Ticket identifier.
     * <p/>
     * required_create=true
     *
     * @param int
     */
    protected int ticketId;

    /**
     * Timestamp of creation date and time.
     *
     * @param int
     */
    protected int dateLine;

    /**
     * Identifier of the user who created this post.
     * <p/>
     * Applicable if the post was created by a known user through an email queue or through the web interface.
     *
     * @param int
     */
    protected int userId = 0;

    /**
     * The full name of the person who created the ticket post.
     *
     * @param String
     */
    protected String fullName;

    /**
     * The email address of the person who created the ticket post.
     *
     * @param string
     */
    protected String email;

    /**
     * The email address of the user associated with the ticket.
     * <p/>
     * Applicable when the 'send email' option is used by the a staff user when creating the ticket post.
     *
     * @param string
     */
    protected String emailTo;

    /**
     * IP address from which this post was created.
     *
     * @param string
     */
    protected String IPAddress;

    /**
     * Whether this ticket post has attachments.
     *
     * @param bool
     */
    protected Boolean hasAttachment;

    /**
     * Type of this ticket post creator.
     * <p/>
     * getter=getCreatorType
     */
    protected TicketPostCreatorEnum creator;

    /**
     * Whether this post was created by owner of e-mail marked as Third Party in ticket properties.
     *
     * @param bool
     */
    protected Boolean isThirdParty;

    /**
     * Whether this ticket post contains HTML data.
     *
     * @param bool
     */
    protected Boolean isHTML;

    /**
     * Whether this post was created through an email queue.
     *
     * @param bool
     */
    protected Boolean isEmailed;

    /**
     * Staff user identifier.
     * <p/>
     * Applicable if the post was created by staff user.
     *
     * @param int
     */
    protected int staffId = 0;

    /**
     * Whether this post is a survey comment.
     *
     * @param bool
     */
    protected Boolean isSurveyComment;

    /**
     * Ticket post contents.
     * <p/>
     * required_create=true
     *
     * @param string
     */
    protected String contents;

    /**
     * The subject this ticket post.
     * <p/>
     * If the ticket post was created through an e-mail queue this is subject of the email message that resulted in the creation of the post.
     *
     * @param string
     */
    protected String subject = "";

    /**
     * Whether the ticket post should be created as private (hidden from the customer) or not.
     *
     * @param bool
     */
    protected Boolean isPrivate = false;

    /**
     * Ticket post attachments.
     *
     * @param TicketAttachment[]
     */
    private ArrayList<TicketAttachment> attachments = new ArrayList<TicketAttachment>();

    /**
     * User, the creator of this post.
     *
     * @param User
     */
    private User user = null;

    /**
     * Staff user, the creator of this post.
     * <p/>
     * Applicable if the post was created by staff user.
     *
     * @param Staff
     */
    private Staff staff = null;

    /**
     * Ticket that this post is connected to.
     *
     * @param Ticket
     */
    private Ticket ticket = null;

    TicketPost() {

    }

    @Override
    public ArrayList<String> getIdArray() {
        ArrayList<String> ids = new ArrayList<String>();
        ids.add(Integer.toString(this.getTicketId()));
        ids.add(Integer.toString(this.getId()));
        return ids;
    }

    public int getId() {

        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     * @return the id
     */
    public TicketPost setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public Boolean getReadOnly() {

        return readOnly;
    }

    @Override
    public TicketPost setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    /**
     * Gets object xml name.
     *
     * @return the object xml name
     */
    public static String getObjectXmlName() {

        return objectXmlName;
    }

    /**
     * Sets object xml name.
     *
     * @param objectXmlName the object xml name
     */
    public static void setObjectXmlName(String objectXmlName) {
        TicketPost.objectXmlName = objectXmlName;
    }

    /**
     * Gets controller.
     *
     * @return the controller
     */
    public static String getController() {

        return controller;
    }

    /**
     * Sets controller.
     *
     * @param controller the controller
     */
    public static void setController(String controller) {
        TicketPost.controller = controller;
    }

    /**
     * Gets ticket id.
     *
     * @return the ticket id
     */
    public int getTicketId() {
        return ticketId;
    }

    /**
     * Sets ticket id.
     *
     * @param ticketId the ticket id
     * @return the ticket id
     */
    public TicketPost setTicketId(int ticketId) {
        this.ticketId = ticketId;
        this.ticket = null;
        return this;
    }

    /**
     * Gets full name.
     *
     * @return the full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets full name.
     *
     * @param fullName the full name
     * @return the full name
     */
    public TicketPost setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     * @return the user id
     */
    public TicketPost setUserId(int userId) {
        this.userId = userId;
        if (userId > 0) {
            this.creator = TicketPostCreatorEnum.USER;
            this.staffId = 0;
            this.staff = null;
            return this;
        }
        this.user = null;
        return this;

    }

    /**
     * Gets date line.
     *
     * @return the date line
     */
    public int getDateLine() {
        return dateLine;
    }

    /**
     * Sets date line.
     *
     * @param dateLine the date line
     * @return the date line
     */
    public TicketPost setDateLine(int dateLine) {
        this.dateLine = dateLine;
        return this;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     * @return the email
     */
    public TicketPost setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Gets email to.
     *
     * @return the email to
     */
    public String getEmailTo() {
        return emailTo;
    }

    /**
     * Sets email to.
     *
     * @param emailTo the email to
     * @return the email to
     */
    public TicketPost setEmailTo(String emailTo) {
        this.emailTo = emailTo;
        return this;
    }

    /**
     * Gets iP address.
     *
     * @return the iP address
     */
    public String getIPAddress() {
        return IPAddress;
    }

    /**
     * Sets iP address.
     *
     * @param IPAddress the iP address
     * @return the iP address
     */
    public TicketPost setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
        return this;
    }

    /**
     * Gets has attachment.
     *
     * @return the has attachment
     */
    public Boolean getHasAttachment() {
        return hasAttachment;
    }

    /**
     * Sets has attachment.
     *
     * @param hasAttachment the has attachment
     * @return the has attachment
     */
    public TicketPost setHasAttachment(Boolean hasAttachment) {
        this.hasAttachment = hasAttachment;
        return this;
    }

    /**
     * Gets creator.
     *
     * @return the creator
     */
    public int getCreator() {
        switch (this.creator) {
            case USER:
                return this.userId;
            case STAFF:
                return this.staffId;
		default:
			break;
        }
        return 0;
    }

    public TicketPostCreatorEnum getCreatorType() {
        return this.creator;
    }

    /**
     * Sets creator.
     *
     * @param creatorId the creator id
     * @param type      the type
     * @return the creator
     */
    public TicketPost setCreator(int creatorId, TicketPostCreatorEnum type) {
        switch (type) {
            case USER:
                this.setUserId(creatorId);
                break;
            case STAFF:
                this.setStaffId(creatorId);
                break;
		default:
			break;
        }
        return this;
    }

    /**
     * Sets creator.
     *
     * @param creator the creator
     * @return the creator
     */
    public TicketPost setCreator(TicketPostCreatorEnum creator) {
        this.creator = creator;
        return this;
    }

    /**
     * Sets creator.
     *
     * @param creator the creator
     * @return the creator
     */
    public TicketPost setCreator(Staff creator) {
        this.setStaff(creator);
        return this;
    }

    /**
     * Sets creator.
     *
     * @param creator the creator
     * @return the creator
     */
    public TicketPost setCreator(User creator) {
        this.setUser(creator);
        return this;
    }

    /**
     * Gets hTML.
     *
     * @return the hTML
     */
    public Boolean getHTML() {
        return isHTML;
    }

    /**
     * Sets hTML.
     *
     * @param HTML the hTML
     * @return the hTML
     */
    public TicketPost setHTML(Boolean HTML) {
        isHTML = HTML;
        return this;
    }

    /**
     * Gets third party.
     *
     * @return the third party
     */
    public Boolean getThirdParty() {
        return isThirdParty;
    }

    /**
     * Sets third party.
     *
     * @param thirdParty the third party
     * @return the third party
     */
    public TicketPost setThirdParty(Boolean thirdParty) {
        isThirdParty = thirdParty;
        return this;
    }

    /**
     * Gets emailed.
     *
     * @return the emailed
     */
    public Boolean getEmailed() {
        return isEmailed;
    }

    /**
     * Sets emailed.
     *
     * @param emailed the emailed
     * @return the emailed
     */
    public TicketPost setEmailed(Boolean emailed) {
        isEmailed = emailed;
        return this;
    }

    /**
     * Gets staff id.
     *
     * @return the staff id
     */
    public int getStaffId() {
        return staffId;
    }

    /**
     * Sets staff id.
     *
     * @param staffId the staff id
     * @return the staff id
     */
    public TicketPost setStaffId(int staffId) {
        this.staffId = staffId;
        if (staffId > 0) {
            this.creator = TicketPostCreatorEnum.STAFF;
            this.user = null;
            this.userId = 0;
            return this;
        }
        this.staff = null;
        return this;
    }

    /**
     * Gets survey comment.
     *
     * @return the survey comment
     */
    public Boolean getSurveyComment() {
        return isSurveyComment;
    }

    /**
     * Sets survey comment.
     *
     * @param surveyComment the survey comment
     * @return the survey comment
     */
    public TicketPost setSurveyComment(Boolean surveyComment) {
        isSurveyComment = surveyComment;
        return this;
    }

    /**
     * Gets contents.
     *
     * @return the contents
     */
    public String getContents() {
        return contents;
    }

    /**
     * Sets contents.
     *
     * @param contents the contents
     * @return the contents
     */
    public TicketPost setContents(String contents) {
        this.contents = contents;
        return this;
    }

    /**
     * Gets subject.
     *
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets subject.
     *
     * @param subject the subject
     * @return the subject
     */
    public TicketPost setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * Is private.
     *
     * @return the boolean
     */
    public Boolean isPrivate() {
        return isPrivate;
    }

    /**
     * Sets private.
     *
     * @param aPrivate the a private
     * @return the private
     */
    public TicketPost setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
        return this;
    }

    /**
     * Gets attachments.
     *
     * @return the attachments
     * @throws com.kayako.api.exception.KayakoException
     *          the kayako exception
     */
    public ArrayList<TicketAttachment> getAttachments() throws KayakoException {
        return this.getAttachments(false);
    }

    /**
     * Gets attachments.
     *
     * @param refresh the refresh
     * @return the attachments
     * @throws KayakoException the kayako exception
     */
    public ArrayList<TicketAttachment> getAttachments(Boolean refresh) throws KayakoException {
        if ((this.attachments.size() == 0 || refresh) && this.hasAttachment) {
            for (TicketAttachment attachment : TicketAttachment.getAllAttachments(this.getTicketId())) {
                if (attachment.getTicketPostId() == this.getId()) {
                    attachments.add(attachment);
                }
            }
        }
        return attachments;
    }

    /**
     * Sets attachments.
     *
     * @param attachments the attachments
     * @return the attachments
     */
    public TicketPost setAttachments(ArrayList<TicketAttachment> attachments) {
        this.attachments = attachments;
        return this;
    }

    /**
     * Gets user.
     *
     * @return the user
     * @throws KayakoException the kayako exception
     */
    public User getUser() throws KayakoException {
        return this.getUser(false);
    }

    /**
     * Gets user.
     *
     * @param refresh the refresh
     * @return the user
     * @throws KayakoException the kayako exception
     */
    public User getUser(Boolean refresh) throws KayakoException {
        if ((refresh || this.user == null) && this.getUserId() > 0) {
            this.user = User.get(this.getUserId());
        }
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     * @return the user
     */
    public TicketPost setUser(User user) {
        this.user = user;
        this.staff = null;
        this.userId = user.getId();
        this.staffId = 0;
        this.creator = TicketPostCreatorEnum.USER;
        return this;
    }

    /**
     * Gets staff.
     *
     * @return the staff
     * @throws KayakoException the kayako exception
     */
    public Staff getStaff() throws KayakoException {
        return this.getStaff(false);
    }

    /**
     * Gets staff.
     *
     * @param refresh the refresh
     * @return the staff
     * @throws KayakoException the kayako exception
     */
    public Staff getStaff(Boolean refresh) throws KayakoException {
        if ((this.staff == null || refresh) && this.staffId > 0) {
            staff = Staff.get(this.staffId);
        }
        return staff;
    }

    /**
     * Sets staff.
     *
     * @param staff the staff
     * @return the staff
     */
    public TicketPost setStaff(Staff staff) {
        this.staff = staff;
        this.staffId = staff.getId();
        this.userId = 0;
        this.user = null;
        this.creator = staff != null ? TicketPostCreatorEnum.STAFF : null;
        return this;
    }

    /**
     * Get ticket post.
     *
     * @param ticketId the ticket id
     * @param id       the id
     * @return the ticket post
     * @throws KayakoException the kayako exception
     */
    public static TicketPost get(int ticketId, int id) throws KayakoException {
        ArrayList<String> params = new ArrayList<String>();
        params.add(Integer.toString(ticketId));
        params.add(Integer.toString(id));
        return new TicketPost().populate(get(controller, params));
    }

    /**
     * Update ticket post.
     *
     * @return the ticket post
     * @throws KayakoException the kayako exception
     */
    public TicketPost update() throws KayakoException {
        throw new KayakoException("This method is not available for this type of objects.");
    }

    /**
     * Create ticket post.
     *
     * @return the ticket post
     * @throws KayakoException the kayako exception
     */
    public TicketPost create() throws KayakoException {
        return (TicketPost) super.create(controller);
    }

    /**
     * Delete boolean.
     *
     * @return the boolean
     * @throws KayakoException the kayako exception
     */
    public Boolean delete() throws KayakoException {
        return getRESTClient().delete(controller, this.getIdArray()) != null;
    }

    /**
     * Gets ticket.
     *
     * @return the ticket
     * @throws KayakoException the kayako exception
     */
    public Ticket getTicket() throws KayakoException {
        return this.getTicket(false);
    }

    /**
     * Gets ticket.
     *
     * @param refresh the refresh
     * @return the ticket
     * @throws KayakoException the kayako exception
     */
    public Ticket getTicket(Boolean refresh) throws KayakoException {
        if (this.ticket == null || refresh) {
            if (this.getTicketId() == 0) {
                return null;
            }
            this.ticket = new Ticket().populate(get(Ticket.getController(), this.getTicketId()));
        }
        return ticket;
    }

    /**
     * Sets ticket.
     *
     * @param ticket the ticket
     * @return the ticket
     */
    public TicketPost setTicket(Ticket ticket) {
        this.ticket = ticket;
        this.ticketId = ticket.getId();
        return this;
    }

    /**
     * Gets all.
     *
     * @param ticketId the ticket id
     * @return the all
     */
    public static RawArrayElement getAll(int ticketId) {
        ArrayList<String> searchParams = new ArrayList<String>();
        searchParams.add("ListAll");
        searchParams.add(Integer.toString(ticketId));
        return getAll(controller, searchParams);
    }

    private static ArrayList<TicketPost> refineToArray(RawArrayElement element) throws KayakoException {
        ArrayList<TicketPost> TicketPosts = new ArrayList<TicketPost>();
        for (RawArrayElement rawArrayElementTicketPost : element.getComponents()) {
            TicketPosts.add(new TicketPost().populate(rawArrayElementTicketPost));
        }
        return TicketPosts;
    }

    /**
     * Gets all posts.
     *
     * @param ticketId the ticket id
     * @return the all posts
     * @throws KayakoException the kayako exception
     */
    public static ArrayList<TicketPost> getAllPosts(int ticketId) throws KayakoException {
        return refineToArray(getAll(ticketId));
    }

    /**
     * Create new.
     *
     * @param ticket   the ticket
     * @param contents the contents
     * @param creator  the creator
     * @return the ticket post
     */
    public static TicketPost createNew(Ticket ticket, String contents, Staff creator) {
        TicketPost ticketPost = new TicketPost();
        ticketPost.setTicket(ticket);
        ticketPost.setContents(contents);
        ticketPost.setCreator(creator);
        return ticketPost;
    }

    /**
     * Create new.
     *
     * @param ticket   the ticket
     * @param contents the contents
     * @param creator  the creator
     * @return the ticket post
     */
    public static TicketPost createNew(Ticket ticket, String contents, User creator) {
        TicketPost ticketPost = new TicketPost();
        ticketPost.setTicket(ticket);
        ticketPost.setContents(contents);
        ticketPost.setCreator(creator);
        return ticketPost;
    }

    //this function will populate the data of the ticket post instance when supplied with RawArrayElement derived from the xml
    @Override
    public TicketPost populate(RawArrayElement element) throws KayakoException {
        if (!element.getElementName().equals(objectXmlName)) {
            throw new KayakoException();
        }

        ArrayList<RawArrayElement> components = element.getComponents();
        for (RawArrayElement component : components) {
            String elementName = component.getElementName();
            if (!component.isComposite() && component.getContent() == null) {
                break;
            }
            if (elementName.equals("id")) {
                this.setId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("ticketid")) {
                this.setTicketId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("dateline")) {
                this.setDateLine(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("userid")) {
                this.setUserId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("fullname")) {
                this.setFullName(component.getContent());
            } else if (elementName.equals("email")) {
                this.setEmail(component.getContent());
            } else if (elementName.equals("emailto")) {
                this.setEmailTo(component.getContent());
            } else if (elementName.equals("ipaddress")) {
                this.setIPAddress(component.getContent());
            } else if (elementName.equals("hasattachment")) {
                this.setHasAttachment(Helper.parseInt(component.getContent()) == 1);
            } else if (elementName.equals("creator")) {
                this.setCreator(TicketPostCreatorEnum.getEnum(component.getContent()));
            } else if (elementName.equals("isthirdparty")) {
                this.setThirdParty(Helper.parseInt(component.getContent()) == 1);
            } else if (elementName.equals("ishtml")) {
                this.setHTML(Helper.parseInt(component.getContent()) == 1);
            } else if (elementName.equals("isemailed")) {
                this.setEmailed(Helper.parseInt(component.getContent()) == 1);
            } else if (elementName.equals("staffid")) {
                this.setStaffId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("issurveycomment")) {
                this.setSurveyComment(Helper.parseInt(component.getContent()) == 1);
            } else if (elementName.equals("contents")) {
                this.setContents(component.getContent());
            } else if (elementName.equals("isprivate")) {
                this.setPrivate(Helper.parseInt(component.getContent()) == 1);
            }

        }

        return this;
    }

    /**
     * Build hash map.
     *
     * @return the hash map
     */
    public HashMap<String, String> buildHashMap() {
        return buildHashMap(false);
    }

    /**
     * Build hash map.
     *
     * @param newTicketPost the new ticket post
     * @return the hash map
     */
    public HashMap<String, String> buildHashMap(Boolean newTicketPost) {
        HashMap<String, String> ticketPostHashMap = new HashMap<String, String>();
        ticketPostHashMap.put("ticketid", Integer.toString(this.getTicketId()));
        ticketPostHashMap.put("subject", this.getSubject());
        ticketPostHashMap.put("contents", this.getContents());
        ticketPostHashMap.put("isprivate", this.isPrivate() ? "1" : "0");

        switch (this.getCreatorType()) {
            case STAFF:
                ticketPostHashMap.put("staffid", Integer.toString(this.getStaffId()));
                break;
            case USER:
                ticketPostHashMap.put("userid", Integer.toString(this.getUserId()));
                break;
		default:
			break;
        }

        return ticketPostHashMap;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Create ticket attachment.
     *
     * @param contents the contents
     * @param fileName the file name
     * @return the ticket attachment
     * @throws KayakoException the kayako exception
     */
    public TicketAttachment createTicketAttachment(byte[] contents, String fileName) throws KayakoException {
        return TicketAttachment.createTicketAttachment(this, contents, fileName);
    }

    /**
     * Create ticket attachment.
     *
     * @param file the file
     * @return the ticket attachment
     * @throws KayakoException the kayako exception
     */
    public TicketAttachment createTicketAttachment(File file) throws KayakoException {
        return TicketAttachment.createTicketAttachment(this, file);
    }
}
