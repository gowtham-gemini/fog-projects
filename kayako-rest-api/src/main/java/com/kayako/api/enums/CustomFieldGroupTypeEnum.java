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
 * The CustomFieldGroupType Enumeration.
 */
public enum CustomFieldGroupTypeEnum {
    TICKET, USER, USER_ORGANIZATION, USER_LIVE_CHAT, USER_TIME_TRACK;

    /**
     * Gets string.
     *
     * @return the string
     */
    public String getString() {
        switch (this) {
            case TICKET:
                return "0";
            case USER:
                return "1";
            case USER_ORGANIZATION:
                return "2";
            case USER_LIVE_CHAT:
                return "3";
            case USER_TIME_TRACK:
                return "4";
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
    public static CustomFieldGroupTypeEnum getEnum(String access) {
        if (access.equalsIgnoreCase("0")) {
            return TICKET;
        } else if (access.equalsIgnoreCase("1")) {
            return USER;
        } else if (access.equalsIgnoreCase("2")) {
            return USER_ORGANIZATION;
        } else if (access.equalsIgnoreCase("3")) {
            return USER_LIVE_CHAT;
        } else if (access.equalsIgnoreCase("4")) {
            return USER_TIME_TRACK;
        }
        return null;
    }
}
