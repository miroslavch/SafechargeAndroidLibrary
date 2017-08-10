package com.safecharge.safechargesdk.service.model;

public class BillingAddress {

    private String city;
    private String country;
    private String zip;
    private String email;
    private String firstName;
    private String lastName;
    private String state;

    public BillingAddress(String city,
                          String country,
                          String zip,
                          String email,
                          String firstName,
                          String lastName,
                          String state)
    {
        this.city = city;
        this.country = country;
        this.zip = zip;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.state = state;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCity()
    {
        return this.city;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCountry()
    {
        return this.country;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }

    public String getZip()
    {
        return this.zip;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getState()
    {
        return this.state;
    }

    @Override
    public String toString()
    {
        return "CardData [city = " + city +
                ", country = " + country +
                ", zip = " + zip +
                ", firstName = " + firstName +
                ", lastName = " + lastName +
                ", state " + state;
    }

}
