package com.phantom.pickme.dto.profile.patch;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class PatchAddressRequestDto {

    @NotNull
    private String postNumber;
    @NotNull
    private String baseAddr;
    @NotNull
    private String detailAddr;
}
