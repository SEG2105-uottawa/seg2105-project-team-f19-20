package com.example.segproject;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class LoginPageTest {

    @Test
    public void hashPassword() throws NoSuchAlgorithmException {

        String password = "test";
        String password_in_SHA256_hashed_string = "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2bb822cd15d6c15b0f0a8";

        LoginPage loginPageTest = new LoginPage();
        String myFunctionHashedPasswordResult = loginPageTest.hashPassword(password);

        assertEquals(myFunctionHashedPasswordResult, password_in_SHA256_hashed_string);

    }

    @Test
    public void isAdmin() {

        String username = "admin";
        String password = "5T5ptQ";

        LoginPage loginPageTest = new LoginPage();

        assertTrue(loginPageTest.isAdmin(username, password));

    }
}