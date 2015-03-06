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
 * The Color Enumeration.
 */
public enum ColorEnum {
    /**
     * The YELLOW.
     */YELLOW, /**
     * The PURPLE.
     */PURPLE, /**
     * The BLUE.
     */BLUE, /**
     * The GREEN.
     */GREEN, /**
     * The RED.
     */RED;

    /**
     * Gets string.
     *
     * @return the string
     */
    public String getString() {
        switch (this) {
            case YELLOW:
                return "1";
            case PURPLE:
                return "2";
            case BLUE:
                return "3";
            case GREEN:
                return "4";
            case RED:
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
    public static ColorEnum getEnum(String access) {
        if (access.equalsIgnoreCase("1")) {
            return YELLOW;
        } else if (access.equalsIgnoreCase("2")) {
            return PURPLE;
        } else if (access.equalsIgnoreCase("3")) {
            return BLUE;
        } else if (access.equalsIgnoreCase("4")) {
            return GREEN;
        } else if (access.equalsIgnoreCase("5")) {
            return RED;
        }
        return null;
    }
}
