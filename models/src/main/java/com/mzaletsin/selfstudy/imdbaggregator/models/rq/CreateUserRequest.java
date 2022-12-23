package com.mzaletsin.selfstudy.imdbaggregator.models.rq;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    @Size(min = 3, max = 20)
    private String username;

    @Size(min = 1, max = 30)
    private String firstName;

    @Size(min = 1, max = 30)
    private String lastName;

    @Min(12)
    @Max(150)
    private Integer age;
}
