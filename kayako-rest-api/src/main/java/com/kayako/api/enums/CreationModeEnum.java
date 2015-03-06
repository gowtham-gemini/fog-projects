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
 * The mode Enumeration.
 */
public enum CreationModeEnum {
    /**
     * The SUPPORT CENTRE.
     */SUPPORT_CENTRE, /**
     * The STAFF _ cP.
     */STAFF_CP, /**
     * The EMAIL.
     */EMAIL, /**
     * The API.
     */API, /**
     * The SITE BADGE.
     */SITE_BADGE;

    /**
     * Gets string.
     *
     * @return the string
     */
    public String getString() {
        switch (this) {
            case SUPPORT_CENTRE:
                return "1";
            case STAFF_CP:
                return "2";
            case EMAIL:
                return "3";
            case API:
                return "4";
            case SITE_BADGE:
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
    public static CreationModeEnum getEnum(String access) {
        if (access.equalsIgnoreCase("1")) {
            return SUPPORT_CENTRE;
        } else if (access.equalsIgnoreCase("2")) {
            return STAFF_CP;
        } else if (access.equalsIgnoreCase("3")) {
            return EMAIL;
        } else if (access.equalsIgnoreCase("4")) {
            return API;
        } else if (access.equalsIgnoreCase("5")) {
            return SITE_BADGE;
        }
        return null;
    }
}
