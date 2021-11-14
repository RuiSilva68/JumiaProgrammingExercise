package com.jumia.jumiawebapp.model.domain;

public class CustomerDTO {
    private Integer id;
    private String name;
    private String number;
    private String country_code;
    private String country;
    private Boolean valid;

    public CustomerDTO() {
    }

    public CustomerDTO(Integer id, String name, String number, String country_code, String country, Boolean valid) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.country_code = country_code;
        this.country = country;
        this.valid = valid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
