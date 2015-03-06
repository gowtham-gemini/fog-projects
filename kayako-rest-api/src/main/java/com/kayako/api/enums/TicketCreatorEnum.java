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
 * The Creator Enumeration.
 */
public enum TicketCreatorEnum {

    /**
     * The AUTO.
     */AUTO, /**
     * The STAFF.
     */STAFF, /**
     * The USER.
     */USER, /**
     * The CLIENT.
     */CLIENT;

    /**
     * Gets string.
     *
     * @return the string
     */
    public String getString() {
        switch (this) {
            case AUTO:
                return "0";
            case STAFF:
                return "1";
            case USER:
                return "2";
            case CLIENT:
                return "2";
            default:
                return "";
        }
    }

    /**
     * Gets enum.
     *
     * @param access the access
     * @return the enum
     */
    public static TicketCreatorEnum getEnum(String access) {
        if (access.equalsIgnoreCase("0")) {
            return AUTO;
        } else if (access.equalsIgnoreCase("1")) {
            return STAFF;
        } else if (access.equalsIgnoreCase("2")) {
            return USER;
        }
        return null;
    }
}
