package com.medicalrecord.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Patient implements Comparable<Patient> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String egn;

    @Column
    private String age;

    @Override
    public int compareTo(Patient o) {
        if(this.getFirstName().equals(o.getFirstName())) {
            return this.getFirstName().compareTo(o.getFirstName());
        } else {
            return this.getLastName().compareTo(o.getLastName());
        }
    }
}
