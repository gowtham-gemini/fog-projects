package org.zenoss.client.common;


public class ApplicationException extends Exception {

    public ApplicationException() { super(); }

    public ApplicationException(java.lang.String s) { super(s); }

    public ApplicationException(java.lang.String s, java.lang.Throwable throwable) { super(s, throwable); }

    public ApplicationException(java.lang.Throwable throwable) { super(throwable); }

    protected ApplicationException(java.lang.String s, java.lang.Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
