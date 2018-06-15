package com.phantom.pickme.dto.profile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class PatchEmailDto {
    @NotNull
    private String email;
}
