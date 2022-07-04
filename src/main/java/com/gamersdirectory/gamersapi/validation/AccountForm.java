package com.gamersdirectory.gamersapi.validation;

import com.gamersdirectory.gamersapi.dto.InterestDTO;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountForm {

    @NotNull @NotEmpty
    private String name;

    @NotNull @NotEmpty
    private String nickname;

    @NotNull @NotEmpty
    private String location;

    private List<InterestDTO> interests;

    public AccountForm(String name, String nickname, String location) {
        this.name = name;
        this.nickname = nickname;
        this.location = location;
    }
}
