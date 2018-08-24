package com.example.p.doctroseadmin;

/**
 * Created by P on 08-11-2017.
 */

public class Rate
{
    private String name;
    private String address;
    private String rate;
    private String speciality;
    private String qualification;

    public Rate(String rate, String address, String speciality, String qualification, String name) {
        this.rate = rate;
        this.address = address;
        this.speciality = speciality;
        this.qualification = qualification;
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public String getAddress() {
        return address;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getQualification() {
        return qualification;
    }

    public String getName() {
        return name;
    }
}