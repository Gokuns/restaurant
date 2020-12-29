package com.yp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String password;
    private boolean enabled;
    private Set<AuthorityDto> authorities = new HashSet<>();
}
