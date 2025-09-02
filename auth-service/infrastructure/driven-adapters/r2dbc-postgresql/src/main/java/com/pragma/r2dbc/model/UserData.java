package com.pragma.r2dbc.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

@Data
@NoArgsConstructor
@Table("users")
public class UserData {

    @Id
    private String id;

    // Con @Column, eliminamos cualquier ambigüedad en el mapeo
    @Column("name")
    private String name;

    @Column("last_name")
    private String lastName;

    @Column("email")
    private String email;

    @Column("document_number")
    private Long documentNumber;

    @Column("phone_number")
    private String phoneNumber;

    @Column("base_salary")
    private Double baseSalary;

    @Column("password")
    private String password;
}