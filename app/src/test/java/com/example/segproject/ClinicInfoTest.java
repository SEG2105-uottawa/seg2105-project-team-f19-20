package com.example.segproject;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ClinicInfoTest {

    @Test
    public void invalidPhone() {

        String invalidPhone = "905-1449-2580";

        EmployeeClinicInformation clinicNumberTest = new EmployeeClinicInformation();

        assertTrue(clinicNumberTest.invalidName(invalidPhone));

    }
    @Test
    public void invalidName() {

        String invalidName = "Clinic343";

        EmployeeClinicInformation clinicNameTest = new EmployeeClinicInformation();

        assertTrue(clinicNameTest.invalidName(invalidName));

    }
    @Test
    public void invalidAddress() {

        String invalidAddress = "Clinic343";

        EmployeeClinicInformation clinicNameTest = new EmployeeClinicInformation();

        assertTrue(clinicNameTest.invalidName(invalidAddress));

    }
}
