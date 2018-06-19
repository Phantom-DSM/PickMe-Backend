package com.phantom.pickme.dto.profile.patch;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class PatchBioRequestDto {
    @NotNull
    private String bio;
}
