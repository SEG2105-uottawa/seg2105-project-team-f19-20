package com.example.segproject;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class SignUpPageTest {

    @Test
    public void hashPassword() throws NoSuchAlgorithmException {

        String password = "signup";
        String password_in_SHA256_hashed_string = "7c8718bdc78be44bf7f3e5554152c2e99216dcb93ac9aefb8857fd7f9d0212";

        SignUpPage signUpPageTest = new SignUpPage();
        String myFunctionHashedPasswordResult = signUpPageTest.hashPassword(password);

        assertEquals(myFunctionHashedPasswordResult, password_in_SHA256_hashed_string);

    }

    @Test
    public void invalidName() {

        String invalidName = "Ronaldo7";

        SignUpPage signUpPageTest = new SignUpPage();

        assertTrue(signUpPageTest.invalidName(invalidName));

    }

    @Test
    public void invalidEmail() {

        String invalidEmail = "123asd_-+=";

        SignUpPage signUpPageTest = new SignUpPage();

        assertTrue(signUpPageTest.invalidEmail(invalidEmail));

    }
}