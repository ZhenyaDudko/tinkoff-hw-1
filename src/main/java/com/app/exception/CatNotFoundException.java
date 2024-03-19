package com.app.exception;

public class CatNotFoundException extends BaseNotFoundException {

    public CatNotFoundException(Object... args) {
        super("exception.cat.not_found", args);
    }
}
