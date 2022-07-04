package com.gamersdirectory.gamersapi.validation;

import com.gamersdirectory.gamersapi.dto.InterestDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class AccountForm {

    @NotNull @NotEmpty
    private String name;

    @NotNull @NotEmpty
    private String nickname;

    @NotNull @NotEmpty
    private String Location;

    private List<InterestDTO> interests;
}
