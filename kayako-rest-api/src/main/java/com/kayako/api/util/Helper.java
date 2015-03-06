package com.kayako.api.util;

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

import com.kayako.api.configuration.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Helper class to provide some useful utility functions.
 */
public class Helper {

    /**
     * Read bytes from file.
     *
     * @param file the file
     * @return the byte [ ]
     * @throws IOException the iO exception
     */
    public static byte[] readBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        // Get the size of the file
        long length = file.length();

        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            is.close();
            throw new IOException("Could not completely read file " + file.getName() + " as it is too long (" + length + " bytes, max supported " + Integer.MAX_VALUE + ")");
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            is.close();
            throw new IOException("Could not completely read file " + file.getName());
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;
    }

    /**
     * Parse int.
     *
     * @param string the string
     * @return the int
     */
    public static int parseInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }

    /**
     * Parse long.
     *
     * @param string the string
     * @return the long
     */
    public static long parseLong(String string) {
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }

    /**
     * Gets date string.
     *
     * @param timeStamp the time stamp
     * @return the date string
     */
    public static String getDateString(long timeStamp) {
        return getDateString(timeStamp, Configuration.getConfiguration().getDateFormat());
    }

    /**
     * Gets time stamp from date string.
     *
     * @param dateString the date string
     * @return the time stamp from date string
     * @throws ParseException the parse exception
     */
    public static long getTimeStampFromDateString(String dateString) throws ParseException {
        return getTimeStampFromDateString(dateString, Configuration.getConfiguration().getDateFormat());
    }

    /**
     * Gets time stamp from date string.
     *
     * @param dateString the date string
     * @param format     the format
     * @return the time stamp from date string
     * @throws ParseException the parse exception
     */
    public static long getTimeStampFromDateString(String dateString, String format) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = dateFormat.parse(dateString);
        return date.getTime();
    }

    /**
     * Gets date string.
     *
     * @param timeStamp the time stamp
     * @param format    the format
     * @return the date string
     */ //String format can be anything workable with SimpleDateFormat constructor
    // ref: http://docs.oracle.com/javase/6/docs/api/java/text/SimpleDateFormat.html
    public static String getDateString(long timeStamp, String format) {
        Date date = new Date(timeStamp);
        Format formatter = new SimpleDateFormat(format);
        String dateStr = formatter.format(date);
        return dateStr;
    }

    /**
     * Slurp an Input stream
     * 
     * @param is Input stream
     * @param bufferSize read buffer size
     * @return content
     */
    public static String slurp(final InputStream is, final int bufferSize)
    {
      final char[] buffer = new char[bufferSize];
      final StringBuilder out = new StringBuilder();
      try {
        final Reader in = new InputStreamReader(is, "UTF-8");
        try {
          for (;;) {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0)
              break;
            out.append(buffer, 0, rsz);
          }
        }
        finally {
          in.close();
        }
      }
      catch (UnsupportedEncodingException ex) {
      }
      catch (IOException ex) {
      }

      return out.toString();
    }
}
