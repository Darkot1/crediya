package com.pragma.model.user;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class User {

    private String id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Long documentNumber;
    private String phoneNumber;
    private Double baseSalary;

}
