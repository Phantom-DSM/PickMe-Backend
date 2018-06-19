package com.phantom.pickme.dto.profile.patch;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class PatchBirthRequestDto {
    @NotNull
    private Integer birthDay;
    @NotNull
    private Integer birthMonth;
    @NotNull
    private Integer birthYear;
}
