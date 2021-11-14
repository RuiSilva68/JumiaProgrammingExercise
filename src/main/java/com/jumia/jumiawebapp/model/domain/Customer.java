package com.jumia.jumiawebapp.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {

    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="phone")
    private String phone;

    public Customer() {
    }

    public Customer(Integer id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
