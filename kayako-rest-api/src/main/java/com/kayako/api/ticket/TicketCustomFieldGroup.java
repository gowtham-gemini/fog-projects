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

import com.kayako.api.customfield.CustomFieldGroup;
import com.kayako.api.enums.CustomFieldGroupTypeEnum;
import com.kayako.api.exception.KayakoException;
import com.kayako.api.rest.RawArrayElement;

import java.util.ArrayList;

/**
 * The type Ticket custom field group.
 */
public class TicketCustomFieldGroup extends CustomFieldGroup {
    /**
     * The Controller.
     */
    static protected String controller = "/Tickets/TicketCustomField";
    /**
     * Ticket identifier.
     *
     * @var int
     */
    protected int ticketId;

    /**
     * Instantiates a new Ticket custom field group.
     *
     * @param ticketId the ticket id
     * @param element  the raw array element
     * @throws com.kayako.api.exception.KayakoException
     *          the kayako exception
     */
    public TicketCustomFieldGroup(int ticketId, RawArrayElement element) throws KayakoException {
        this.ticketId = ticketId;
        this.type = CustomFieldGroupTypeEnum.TICKET;
        this.populate(element);
    }

    /**
     * Instantiates a new Ticket custom field group.
     *
     * @param ticketId the ticket id
     */
    public TicketCustomFieldGroup(int ticketId) {
        this.ticketId = ticketId;
        this.type = CustomFieldGroupTypeEnum.TICKET;
    }

    /**
     * Fetches ticket custom fields groups from server.
     *
     * @param ticketId Ticket identifier.
     * @return RawArrayElement all
     */
    static public RawArrayElement getAll(int ticketId) {
        ArrayList<String> params = new ArrayList<String>();
        params.add(Integer.toString(ticketId));
        RawArrayElement element = getAll(controller, params);
        return element;
    }

    /**
     * Returns identifier of the ticket that this group is associated with.
     *
     * @return int ticket id
     */
    public int getTicketId() {
        return this.ticketId;
    }

    /**
     * Gets controller.
     *
     * @return the controller
     */
    public static String getController() {
        return controller;
    }
}
