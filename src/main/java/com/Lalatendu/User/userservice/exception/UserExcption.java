package com.Lalatendu.User.userservice.exception;

public class UserExcption extends RuntimeException {

    private static final long serialVersionUID = -470180507998010368L;

    public UserExcption() {
        super();
    }

    public UserExcption(final String message) {
        super(message);
    }
}
