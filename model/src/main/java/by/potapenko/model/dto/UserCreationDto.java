package by.potapenko.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationDto {
    private String firstName;
    private String middleName;
    private String surName;
    private String email;
    private List<RoleDto> roles = new ArrayList<>();

}
