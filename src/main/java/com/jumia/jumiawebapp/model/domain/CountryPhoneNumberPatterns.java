package com.jumia.jumiawebapp.model.domain;

public enum CountryPhoneNumberPatterns {
     Cameroon ("\\(237\\)\\ ?[2368]\\d{7,8}$","+237"),
     Ethiopia ("\\(251\\)\\ ?[1-59]\\d{8}$","+251"),
     Morocco ("\\(212\\)\\ ?[5-9]\\d{8}$","+212"),
     Mozambique ("\\(258\\)\\ ?[28]\\d{7,8}$","+258"),
     Uganda ("\\(256\\)\\ ?\\d{9}$","+256");

    private String regex;
    private String country_code;

    CountryPhoneNumberPatterns(String regex, String country_code) {
        this.regex = regex;
        this.country_code = country_code;
    }

    public String getRegex() {
        return regex;
    }

    public String getCountry_code() {
        return country_code;
    }
}
