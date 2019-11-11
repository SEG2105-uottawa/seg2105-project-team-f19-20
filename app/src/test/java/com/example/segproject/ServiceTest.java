package com.example.segproject;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceTest {

    Service serviceTest = new Service("bloodTest", "Nurse", "123");

    @Test
    public void getName() {

        assertEquals("bloodTest", serviceTest.getName());

    }

    @Test
    public void getRole() {

        assertEquals("Nurse", serviceTest.getRole());

    }

    @Test
    public void getID() {

        assertEquals("123", serviceTest.getID());

    }

    @Test
    public void setName() {

        serviceTest.setName("xray");
        assertEquals("xray", serviceTest.getName());

    }

    @Test
    public void setRole() {

        serviceTest.setRole("doctor");
        assertEquals("doctor", serviceTest.getRole());

    }

    @Test
    public void setID() {

        serviceTest.setID("1234");
        assertEquals("1234", serviceTest.getID());

    }
}