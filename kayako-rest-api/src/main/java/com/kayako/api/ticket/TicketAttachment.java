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

import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.KEntity;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.util.Helper;
import net.iharder.Base64;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Ticket attachment.
 */
public class TicketAttachment extends KEntity {

    /**
     * The Controller.
     */
    static protected String controller = "/Tickets/TicketAttachment";
    /**
     * The Object xml name.
     */
    static protected String objectXmlName = "attachment";

    /**
     * Ticket attachment identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;
    /**
     * Ticket identifier.
     *
     * @apiField required_create =true
     * @var int
     */
    protected int ticketId;

    /**
     * Identifier of ticket post that this attachment is attached to.
     *
     * @apiField required_create =true
     * @var int
     */
    protected int ticketPostId;

    /**
     * Attachment file name.
     *
     * @apiField required_create =true
     * @var string
     */
    protected String fileName;

    /**
     * Attachment size in bytes.
     *
     * @apiField
     * @var int
     */
    protected int fileSize;

    /**
     * Attachment MIME type.
     *
     * @apiField
     * @var string
     */
    protected String fileType;

    /**
     * Timestamp of when this attachment was created.
     *
     * @apiField
     * @var int
     */
    protected int dateLine;

    /**
     * Raw contents of attachment.
     *
     * @apiField required_create =true
     * @var string
     */
    protected byte[] contents;

    /**
     * Ticket with this attachment.
     *
     * @var Ticket
     */
    private Ticket ticket = null;

    /**
     * Ticket post with this attachment.
     *
     * @var TicketPost
     */
    private TicketPost ticketPost = null;

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
        TicketAttachment.controller = controller;
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
        TicketAttachment.objectXmlName = objectXmlName;
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
    public TicketAttachment setId(int id) {
        this.id = id;
        return this;
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
    public TicketAttachment setTicketId(int ticketId) {
        this.ticketId = ticketId;
        this.ticket = null;
        return this;
    }

    /**
     * Gets ticket post id.
     *
     * @return the ticket post id
     */
    public int getTicketPostId() {
        return ticketPostId;
    }

    /**
     * Sets ticket post id.
     *
     * @param ticketPostId the ticket post id
     * @return the ticket post id
     */
    public TicketAttachment setTicketPostId(int ticketPostId) {
        this.ticketPostId = ticketPostId;
        this.ticketPost = null;
        return this;
    }

    /**
     * Gets file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets file name.
     *
     * @param fileName the file name
     * @return the file name
     */
    public TicketAttachment setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    /**
     * Gets file size.
     *
     * @return the file size
     */
    public int getFileSize() {
        return fileSize;
    }

    /**
     * Sets file size.
     *
     * @param fileSize the file size
     * @return the file size
     */
    public TicketAttachment setFileSize(int fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    /**
     * Gets file type.
     *
     * @return the file type
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * Sets file type.
     *
     * @param fileType the file type
     * @return the file type
     */
    public TicketAttachment setFileType(String fileType) {
        this.fileType = fileType;
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
    public TicketAttachment setDateLine(int dateLine) {
        this.dateLine = dateLine;
        return this;
    }

    /**
     * Get contents.
     *
     * @return the byte [ ]
     * @throws com.kayako.api.exception.KayakoException the kayako exception
     */
    public byte[] getContents() throws KayakoException {
        return this.getContents(true);
    }

    /**
     * Get contents.
     *
     * @param autoFetch the auto fetch
     * @return the byte [ ]
     * @throws KayakoException the kayako exception
     */
    public byte[] getContents(Boolean autoFetch) throws KayakoException {
        if (this.contents == null && autoFetch) {
            this.setContents(get(this.getTicketId(), this.getId()).getContents());
        }
        return contents;
    }

    /**
     * Sets contents.
     *
     * @param contents the contents
     * @return the contents
     */
    public TicketAttachment setContents(byte[] contents) {
        this.contents = contents;
        return this;
    }

    /**
     * Sets content from file.
     *
     * @param file the file
     * @return the content from file
     */
    public TicketAttachment setContentFromFile(File file) {
        try {
            this.contents = Helper.readBytesFromFile(file);
            this.setFileName(file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
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
    public TicketAttachment setTicket(Ticket ticket) {
        this.ticket = ticket;
        this.ticketId = ticket.getId();
        return this;
    }

    /**
     * Gets ticket post.
     *
     * @return the ticket post
     * @throws KayakoException the kayako exception
     */
    public TicketPost getTicketPost() throws KayakoException {
        return this.getTicketPost(false);
    }

    /**
     * Gets ticket post.
     *
     * @param refresh the refresh
     * @return the ticket post
     * @throws KayakoException the kayako exception
     */
    public TicketPost getTicketPost(Boolean refresh) throws KayakoException {
        if (this.ticketPost == null || refresh) {
            if (this.getTicketPostId() == 0) {
                return null;
            }
            this.ticketPost = TicketPost.get(this.getTicketId(), this.getTicketPostId());
        }
        return ticketPost;
    }

    /**
     * Sets the ticket post this attachment will be attached to.
     * <p/>
     * Automatically sets the ticket.
     * @param ticketPost the ticket post
     * @return the ticket post
     * @throws KayakoException the kayako exception
     */
    public TicketAttachment setTicketPost(TicketPost ticketPost) throws KayakoException {
        this.ticketPost = ticketPost;
        this.ticketPostId = ticketPost.getId();
        this.ticket = ticketPost.getTicket();
        this.ticketId = this.ticket.getId();
        return this;
    }

    /**
     * Get ticket attachment.
     *
     * @param ticketId the ticket id
     * @param id the id
     * @return the ticket attachment
     * @throws KayakoException the kayako exception
     */
    public static TicketAttachment get(int ticketId, int id) throws KayakoException {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(Integer.toString(ticketId));
        arrayList.add(Integer.toString(id));
        return new TicketAttachment().populate(get(controller, arrayList));
    }

    /**
     * Update ticket attachment.
     *
     * @return the ticket attachment
     * @throws KayakoException the kayako exception
     */
    public TicketAttachment update() throws KayakoException {
        throw new KayakoException("This method is not available for this type of objects.");
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

    public ArrayList<String> getIdArray() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(Integer.toString(this.getTicketId()));
        arrayList.add(Integer.toString(this.getId()));
        return arrayList;
    }

    /**
     * Gets all.
     *
     * @param controller the controller
     * @return the all
     * @throws KayakoException the kayako exception
     */
    public static RawArrayElement getAll(String controller) throws KayakoException {
        throw new KayakoException("This method is not available");
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

    /**
     * Gets all attachments.
     *
     * @param ticketId the ticket id
     * @return the all attachments
     * @throws KayakoException the kayako exception
     */
    public static ArrayList<TicketAttachment> getAllAttachments(int ticketId) throws KayakoException {
        return refineToArray(getAll(ticketId));
    }

    private static ArrayList<TicketAttachment> refineToArray(RawArrayElement element) throws KayakoException {
        ArrayList<TicketAttachment> TicketAttachments = new ArrayList<TicketAttachment>();
        for (RawArrayElement rawArrayElementTicketAttachment : element.getComponents()) {
            TicketAttachments.add(new TicketAttachment().populate(rawArrayElementTicketAttachment));
        }
        return TicketAttachments;
    }

    //this function will populate the data of the ticket attachment instance when supplied with RawArrayElement derived from the xml
    @Override
    public TicketAttachment populate(RawArrayElement element) throws KayakoException {
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
            } else if (elementName.equals("ticketpostid")) {
                this.setTicketPostId(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("filename")) {
                this.setFileName(component.getContent());
            } else if (elementName.equals("filesize")) {
                this.setFileSize(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("filetype")) {
                this.setFileType(component.getContent());
            } else if (elementName.equals("dateline")) {
                this.setDateLine(Helper.parseInt(component.getContent()));
            } else if (elementName.equals("contents")) {
                try {
                    this.setContents(Base64.decode(component.getContent()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return this;
    }

    public HashMap<String, String> buildHashMap() {
        return buildHashMap(false);
    }

    /**
     * Build hash map.
     *
     * @param newTicketAttachment the new ticket attachment
     * @return the hash map
     */
    public HashMap<String, String> buildHashMap(Boolean newTicketAttachment) {
        HashMap<String, String> ticketAttachmentHashMap = new HashMap<String, String>();
        ticketAttachmentHashMap.put("ticketid", Integer.toString(this.getTicketId()));
        ticketAttachmentHashMap.put("ticketpostid", Integer.toString(this.getTicketPostId()));
        ticketAttachmentHashMap.put("filename", this.getFileName());
        try {
            ticketAttachmentHashMap.put("contents", Base64.encodeBytes(this.getContents()));
        } catch (KayakoException ke) {
            ke.printStackTrace();
        }

        return ticketAttachmentHashMap;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Create ticket attachment.
     *
     * @param ticketPost the ticket post
     * @param contents the contents
     * @param fileName the file name
     * @return the ticket attachment
     * @throws KayakoException the kayako exception
     */
    public static TicketAttachment createTicketAttachment(TicketPost ticketPost, byte[] contents, String fileName) throws KayakoException {
        TicketAttachment ticketAttachment = new TicketAttachment().setTicketPost(ticketPost).setContents(contents).setFileName(fileName);
        return ticketAttachment;
    }

    /**
     * Create ticket attachment.
     *
     * @param ticketPost the ticket post
     * @param file the file
     * @return the ticket attachment
     * @throws KayakoException the kayako exception
     */
    public static TicketAttachment createTicketAttachment(TicketPost ticketPost, File file) throws KayakoException {
        TicketAttachment ticketAttachment = new TicketAttachment().setTicketPost(ticketPost).setContentFromFile(file);
        return ticketAttachment;
    }

    /**
     * Create ticket attachment.
     *
     * @return the ticket attachment
     * @throws KayakoException the kayako exception
     */ //Though create() method might not be written everywhere, create(String controller) can be called on almost all objects
    public TicketAttachment create() throws KayakoException {
        return (TicketAttachment) super.create(controller);
    }
    
    /**
     * Get raw array element.
     *
     * @param controller the controller
     * @param parameters @param parameters ArrayList of Parameters (TicketID and TicketAttachmentID)
     * @return the raw array element
     * @throws KayakoException the kayako exception
     */ //These functions will return RawArrayElement, similar functions will be written in SubClasses to use these functions
    public static RawArrayElement get(String controller, ArrayList<String> parameters) throws KayakoException {
        return KEntity.getRESTClient().get(controller, parameters).getFirstComponent();
    }
}
