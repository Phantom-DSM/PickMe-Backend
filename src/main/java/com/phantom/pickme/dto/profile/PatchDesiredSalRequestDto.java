package com.phantom.pickme.dto.profile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchDesiredSalRequestDto {

    private Integer desiredMinSal;
    private Integer desiredMaxSal;
}
