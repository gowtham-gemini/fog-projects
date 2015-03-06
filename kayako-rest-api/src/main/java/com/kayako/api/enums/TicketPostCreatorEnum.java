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
public enum TicketPostCreatorEnum {

    /**
     * The AUTO.
     */AUTO, /**
     * The STAFF.
     */STAFF, /**
     * The USER.
     */USER, /**
     * The CLIENT.
     */CLIENT, /**
     * The CC.
     */CC, /**
     * The BCC.
     */BCC, /**
     * The THIRD _ pARTY.
     */THIRD_PARTY;

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
            case CC:
                return "3";
            case BCC:
                return "4";
            case THIRD_PARTY:
                return "5";
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
    public static TicketPostCreatorEnum getEnum(String access) {
        if (access.equalsIgnoreCase("0")) {
            return AUTO;
        } else if (access.equalsIgnoreCase("1")) {
            return STAFF;
        } else if (access.equalsIgnoreCase("2")) {
            return USER;
        } else if (access.equalsIgnoreCase("3")) {
            return CC;
        } else if (access.equalsIgnoreCase("4")) {
            return BCC;
        } else if (access.equalsIgnoreCase("5")) {
            return THIRD_PARTY;
        }
        return null;
    }
}
