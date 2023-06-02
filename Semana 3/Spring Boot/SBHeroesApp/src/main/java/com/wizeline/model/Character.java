package com.wizeline.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class Description goes here.
 * Created by jose.vazquez on 01/06/23
 */
@Entity
@Table(name = "Character")
public class Character {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String company;

    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
