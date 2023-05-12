package com.mzaletsin.selfstudy.imdbaggregator.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    private String username;
    private String firstName;
    private String lastName;
    private Integer age;
}
