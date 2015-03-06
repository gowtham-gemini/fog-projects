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
 * The Salutation Enumeration.
 */
public enum SalutationEnum {
    /**
     * The YELLOW.
     */MR, /**
     * The PURPLE.
     */MS, /**
     * The BLUE.
     */MRS, /**
     * The GREEN.
     */DR;

    /**
     * Gets string.
     *
     * @return the string
     */
    public String getString() {
        switch (this) {
            case MR:
                return "Mr.";
            case MS:
                return "Ms.";
            case MRS:
                return "Mrs.";
            case DR:
                return "Dr.";
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
    public static SalutationEnum getEnum(String access) {
        if (access.equalsIgnoreCase("Mr.")) {
            return MR;
        } else if (access.equalsIgnoreCase("Ms.")) {
            return MS;
        } else if (access.equalsIgnoreCase("Mrs.")) {
            return MRS;
        } else if (access.equalsIgnoreCase("Dr.")) {
            return DR;
        }
        return null;
    }
}
