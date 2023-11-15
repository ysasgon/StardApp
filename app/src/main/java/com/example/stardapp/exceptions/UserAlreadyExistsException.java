package com.example.stardapp.exceptions;

public class UserAlreadyExistsException extends Exception{
        public UserAlreadyExistsException() {
        }

        public UserAlreadyExistsException(String msg) {
            super(msg);
        }
    }
