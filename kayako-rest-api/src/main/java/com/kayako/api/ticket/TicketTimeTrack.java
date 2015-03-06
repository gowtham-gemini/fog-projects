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

import com.kayako.api.enums.ColorEnum;
import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.KEntity;
import com.kayako.api.rest.RawArrayElement;
import com.kayako.api.user.Staff;
import com.kayako.api.util.Helper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Ticket time track.
 */
public class TicketTimeTrack extends KEntity {

    /**
     * The Controller.
     */
    static protected String controller = "/Tickets/TicketTimeTrack";
    /**
     * The Object xml name.
     */
    static protected String objectXmlName = "timetrack";

    /**
     * Ticket timetrack identifier.
     *
     * @apiField
     * @var int
     */
    protected int id;

    /**
     * Ticket identifier - if this timetrack is associated with ticket.
     *
     * @apiField required_create =true
     * @var int
     */
    protected int ticketId;

    /**
     * Time worked (in seconds) in this ticket time track.
     *
     * @apiField required_create =true alias=timespent
     * @var int
     */
    protected int timeWorked;

    /**
     * Billable time (in seconds) in this ticket time track.
     *
     * @apiField required_create =true
     * @var int
     */
    protected int timeBillable;

    /**
     * Bill timestamp of this ticket time track.
     *
     * @apiField required_create =true alias=billtimeline
     * @var int
     */
    protected int billDate;

    /**
     * Work timestamp of this ticket time track.
     *
     * @apiField required_create =true alias=worktimeline
     * @var int
     */
    protected int workDate;

    /**
     * Worker staff identifier.
     *
     * @apiField
     * @var int
     */
    protected int workerStaffId = 0;

    /**
     * Worker staff full name.
     *
     * @apiField
     * @var string
     */
    protected String workerStaffName;

    /**
     * Creator staff identifier.
     *
     * @apiField required_create =true alias=staffid
     * @var int
     */
    protected int creatorStaffId;

    /**
     * Creator staff full name.
     *
     * @apiField
     * @var string
     */
    protected String creatorStaffName;

    /**
     * Ticket time track note color.
     */
    protected ColorEnum noteColor;

    /**
     * Note contents of this ticket time track.
     *
     * @apiField required_create =true
     * @var String
     */
    protected String contents;

    /**
     * The ticket that this time track will be connected with.
     *
     * @var Ticket
     */
    private Ticket ticket;

    /**
     * Worker staff.
     *
     * @var Staff
     */
    private Staff workerStaff = null;

    /**
     * Creator staff.
     *
     * @var Staff
     */
    private Staff creatorStaff = null;

    /**
     * Instantiates a new Ticket time track.
     */
    public TicketTimeTrack() {
    }

    /**
     * Instantiates a new Ticket time track.
     *
     * @param ticket       the ticket
     * @param contents     the contents
     * @param staff        the staff
     * @param timeWorked   the time worked
     * @param timeBillable the time billable
     */
    public TicketTimeTrack(Ticket ticket, String contents, Staff staff, int timeWorked, int timeBillable) {
        this.setTicketId(ticket.getId()).setContents(contents).setCreatorStaffId(staff.getId());
        this.setTimeWorked(timeWorked).setTimeBillable(timeBillable).setWorkerStaff(staff);
    }

    /**
     * Instantiates a new Ticket time track.
     *
     * @param ticket       the ticket
     * @param contents     the contents
     * @param staff        the staff
     * @param timeWorked   the time worked
     * @param timeBillable the time billable
     */
    public TicketTimeTrack(Ticket ticket, String contents, Staff staff, String timeWorked, String timeBillable) {
        this.setTicketId(ticket.getId()).setContents(contents).setCreatorStaffId(staff.getId());
        this.setTimeWorked(timeWorked).setTimeBillable(timeBillable).setWorkerStaff(staff);
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
    public TicketTimeTrack setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public Boolean getReadOnly() {

        return readOnly;
    }

    @Override
    public TicketTimeTrack setReadOnly(Boolean readOnly) {
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
        TicketTimeTrack.objectXmlName = objectXmlName;
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
        TicketTimeTrack.controller = controller;
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
    public TicketTimeTrack setTicketId(int ticketId) {
        this.ticketId = ticketId;
        this.ticket = null;
        return this;
    }

    /**
     * Gets ticket.
     *
     * @return the ticket
     * @throws com.kayako.api.exception.KayakoException
     *          the kayako exception
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
    public TicketTimeTrack setTicket(Ticket ticket) {
        this.ticket = ticket;
        this.ticketId = ticket.getId();
        return this;
    }

    /**
     * Gets creator staff id.
     *
     * @return the creator staff id
     */
    public int getCreatorStaffId() {
        return creatorStaffId;
    }

    /**
     * Sets creator staff id.
     *
     * @param creatorStaffId the creator staff id
     * @return the creator staff id
     */
    public TicketTimeTrack setCreatorStaffId(int creatorStaffId) {
        this.creatorStaffId = creatorStaffId;
        return this;
    }

    /**
     * Gets creator staff name.
     *
     * @return the creator staff name
     */
    public String getCreatorStaffName() {
        return creatorStaffName;
    }

    /**
     * Sets creator staff name.
     *
     * @param creatorStaffName the creator staff name
     * @return the creator staff name
     */
    public TicketTimeTrack setCreatorStaffName(String creatorStaffName) {
        this.creatorStaffName = creatorStaffName;
        return this;
    }

    /**
     * Gets creator staff.
     *
     * @return the creator staff
     * @throws KayakoException the kayako exception
     */
    public Staff getCreatorStaff() throws KayakoException {
        if (this.getCreatorStaffId() == 0) {
            return null;
        }
        if (this.creatorStaff == null) {
            this.creatorStaff = (Staff) Staff.get(this.getCreatorStaffId());
        }
        return this.creatorStaff;
    }

    /**
     * Sets creator staff.
     *
     * @param creatorStaff the creator staff
     * @return the creator staff
     */
    public TicketTimeTrack setCreatorStaff(Staff creatorStaff) {
        this.creatorStaff = creatorStaff;
        this.setCreatorStaffId(creatorStaff.getId());
        this.setCreatorStaffName(creatorStaff.getFullName());
        return this;
    }

    /**
     * Gets time worked.
     *
     * @return the time worked
     */
    public int getTimeWorked() {
        return timeWorked;
    }

    /**
     * Sets time worked.
     *
     * @param timeWorked the time worked
     * @return the time worked
     */
    public TicketTimeTrack setTimeWorked(int timeWorked) {
        this.timeWorked = timeWorked;
        return this;
    }

    /**
     * Sets time worked.
     *
     * @param timeWorked the time worked
     * @return the time worked
     */
    public TicketTimeTrack setTimeWorked(String timeWorked) {
        this.setTimeWorked(stringToSeconds(timeWorked));
        return this;
    }

    //input String as "12:10" (hh:mm)
    private static int stringToSeconds(String timeWorked) {
        String[] timeArray = timeWorked.split(":");
        return (Helper.parseInt(timeArray[0]) * 60 * 60) + (Helper.parseInt(timeArray[1]) * 60);
    }

    /**
     * Gets time billable.
     *
     * @return the time billable
     */
    public int getTimeBillable() {
        return timeBillable;
    }

    /**
     * Sets time billable.
     *
     * @param timeBillable the time billable
     * @return the time billable
     */
    public TicketTimeTrack setTimeBillable(int timeBillable) {
        this.timeBillable = timeBillable;
        return this;
    }

    /**
     * Sets time billable.
     *
     * @param time the time
     * @return the time billable
     */
    public TicketTimeTrack setTimeBillable(String time) {
        this.setTimeBillable(stringToSeconds(time));
        return this;
    }

    /**
     * Gets bill date.
     *
     * @return the bill date
     */
    public int getBillDate() {
        return billDate;
    }

    /**
     * Sets bill date.
     *
     * @param billDate the bill date
     * @return the bill date
     */
    public TicketTimeTrack setBillDate(int billDate) {
        this.billDate = billDate;
        return this;
    }

    /**
     * Gets work date.
     *
     * @return the work date
     */
    public int getWorkDate() {
        return workDate;
    }

    /**
     * Sets work date.
     *
     * @param workDate the work date
     * @return the work date
     */
    public TicketTimeTrack setWorkDate(int workDate) {
        this.workDate = workDate;
        return this;
    }

    /**
     * Gets worker staff id.
     *
     * @return the worker staff id
     */
    public int getWorkerStaffId() {
        return workerStaffId;
    }

    /**
     * Sets worker staff id.
     *
     * @param workerStaffId the worker staff id
     * @return the worker staff id
     */
    public TicketTimeTrack setWorkerStaffId(int workerStaffId) {
        this.workerStaffId = workerStaffId;
        this.workerStaff = null;
        return this;
    }

    /**
     * Gets worker staff name.
     *
     * @return the worker staff name
     */
    public String getWorkerStaffName() {
        return workerStaffName;
    }

    /**
     * Sets worker staff name.
     *
     * @param workerStaffName the worker staff name
     * @return the worker staff name
     */
    public TicketTimeTrack setWorkerStaffName(String workerStaffName) {
        this.workerStaffName = workerStaffName;
        return this;
    }

    /**
     * Gets note color.
     *
     * @return the note color
     */
    public ColorEnum getNoteColor() {
        return noteColor;
    }

    /**
     * Sets note color.
     *
     * @param noteColor the note color
     * @return the note color
     */
    public TicketTimeTrack setNoteColor(ColorEnum noteColor) {
        this.noteColor = noteColor;
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
    public TicketTimeTrack setContents(String contents) {
        this.contents = contents;
        return this;
    }

    /**
     * Gets worker staff.
     *
     * @return the worker staff
     * @throws KayakoException the kayako exception
     */
    public Staff getWorkerStaff() throws KayakoException {
        return this.getWorkerStaff(false);
    }

    /**
     * Gets worker staff.
     *
     * @param refresh the refresh
     * @return the worker staff
     * @throws KayakoException the kayako exception
     */
    public Staff getWorkerStaff(Boolean refresh) throws KayakoException {
        if (this.workerStaff == null || refresh) {
            this.workerStaff = Staff.get(this.getWorkerStaffId());
            this.setWorkerStaffName(this.workerStaff.getFullName());
        }
        return workerStaff;
    }

    /**
     * Sets worker staff.
     *
     * @param workerStaff the worker staff
     * @return the worker staff
     */
    public TicketTimeTrack setWorkerStaff(Staff workerStaff) {
        this.workerStaff = workerStaff;
        this.setWorkerStaffId(workerStaff.getId());
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

    private static ArrayList<TicketTimeTrack> refineToArray(RawArrayElement element) throws KayakoException {
        ArrayList<TicketTimeTrack> TicketTimeTracks = new ArrayList<TicketTimeTrack>();
        for (RawArrayElement rawArrayElementTicketTimeTrack : element.getComponents()) {
            TicketTimeTracks.add(new TicketTimeTrack().populate(rawArrayElementTicketTimeTrack));
        }
        return TicketTimeTracks;
    }

    /**
     * Gets all time tracks.
     *
     * @param ticketId the ticket id
     * @return the all time tracks
     * @throws KayakoException the kayako exception
     */
    public static ArrayList<TicketTimeTrack> getAllTimeTracks(int ticketId) throws KayakoException {
        return refineToArray(getAll(ticketId));
    }

    /**
     * Get ticket time track.
     *
     * @param ticketId the ticket id
     * @param id       the id
     * @return the ticket time track
     * @throws KayakoException the kayako exception
     */
    public static TicketTimeTrack get(int ticketId, int id) throws KayakoException {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(Integer.toString(ticketId));
        arrayList.add(Integer.toString(id));
        return new TicketTimeTrack().populate(get(controller, arrayList));
    }

    @Override
    public KEntity update(String controller) throws KayakoException {
        throw new KayakoException("This method is not available on this type of objects.");
    }

    /**
     * Create ticket time track.
     *
     * @return the ticket time track
     * @throws KayakoException the kayako exception
     */
    public TicketTimeTrack create() throws KayakoException {
        return (TicketTimeTrack) super.create(controller);
    }

    //this function will populate the data of the ticket time track instance when supplied with RawArrayElement derived from the xml
    @Override
    public TicketTimeTrack populate(RawArrayElement element) throws KayakoException {
        if (!element.getElementName().equals(objectXmlName)) {
            throw new KayakoException();
        }
        this.setId(Helper.parseInt(element.getAttribute("id"))).setTicketId(Helper.parseInt(element.getAttribute("ticketid")));
        this.setTimeWorked(Helper.parseInt(element.getAttribute("timeworked"))).setTimeBillable(Helper.parseInt(element.getAttribute("timebillable")));
        this.setBillDate(Helper.parseInt(element.getAttribute("billdate"))).setWorkDate(Helper.parseInt(element.getAttribute("workdate")));
        this.setNoteColor(ColorEnum.getEnum(element.getAttribute("notecolor")));
        this.setCreatorStaffName(element.getAttribute("creatorstaffname")).setCreatorStaffId(Helper.parseInt(element.getAttribute("creatorstaffid")));
        this.setWorkerStaffName(element.getAttribute("workerstaffname")).setWorkerStaffId(Helper.parseInt(element.getAttribute("workerstaffid")));
        this.setContents(element.getContent());
        return this;
    }

    public HashMap<String, String> buildHashMap() {
        return buildHashMap(false);
    }

    /**
     * Build hash map.
     *
     * @param newTicketTimeTrack the new ticket time track
     * @return the hash map
     */
    public HashMap<String, String> buildHashMap(Boolean newTicketTimeTrack) {
        HashMap<String, String> ticketTimeTrackHashMap = new HashMap<String, String>();
        ticketTimeTrackHashMap.put("ticketid", Integer.toString(this.getTicketId()));
        ticketTimeTrackHashMap.put("staffid", Integer.toString(this.getCreatorStaffId()));
        ticketTimeTrackHashMap.put("contents", this.getContents());
        ticketTimeTrackHashMap.put("worktimeline", Integer.toString(this.getWorkDate()));
        ticketTimeTrackHashMap.put("billtimeline", Integer.toString(this.getBillDate()));
        ticketTimeTrackHashMap.put("timespent", Integer.toString(this.getTimeWorked()));
        ticketTimeTrackHashMap.put("timebillable", Integer.toString(this.getTimeBillable()));
        if (this.getWorkerStaffId() != 0) {
            ticketTimeTrackHashMap.put("workerstaffid", Integer.toString(this.getWorkerStaffId()));
        }
        ticketTimeTrackHashMap.put("notecolor", this.getNoteColor().getString());
        return ticketTimeTrackHashMap;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
    /**
     * Get raw array element.
     *
     * @param controller the controller
     * @param parameters ArrayList of Parameters (TicketID and TicketTimeTrackID)
     * @return the raw array element
     * @throws KayakoException the kayako exception
     */
    public static RawArrayElement get(String controller, ArrayList<String> parameters) throws KayakoException {
        return KEntity.getRESTClient().get(controller, parameters).getFirstComponent();
    }

}
