package com.kayako.api.enums;

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

/**
 * The type Enumeration.
 */
public enum TicketNoteTypeEnum {
    TICKET, USER, USER_ORGANIZATION;

    /**
     * Gets string.
     *
     * @return the string
     */
    public String getString() {
        switch (this) {
            case TICKET:
                return "ticket";
            case USER:
                return "user";
            case USER_ORGANIZATION:
                return "userorganization";
            default:
                return "";
        }

    }

    /**
     * Gets enum.
     *
     * @param type the TicketNote
     * @return the enum
     */
    public static TicketNoteTypeEnum getEnum(String type) {
        if (type.equalsIgnoreCase("ticket")) {
            return TICKET;
        } else if (type.equalsIgnoreCase("user")) {
            return USER;
        } else if (type.equalsIgnoreCase("userorganization")) {
            return USER_ORGANIZATION;
        }
        return null;
    }
}
