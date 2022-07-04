package com.gamersdirectory.gamersapi.dto;

import com.gamersdirectory.gamersapi.entity.Account;
import com.gamersdirectory.gamersapi.entity.Interest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AccountDTO {

    private Long id;
    private String name;
    private String nickname;
    private String location;
    private List<InterestDTO> interests;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.name = account.getName();
        this.nickname = account.getNickname();
        this.location = account.getLocation().getName();
        this.interests = mapInterests(account);
    }

    private List<InterestDTO> mapInterests(Account account) {
        List<InterestDTO> list = new ArrayList<>();

        List<Interest> interests = account.getInterests()
                .stream()
                .toList();

        for (Interest interest : interests) {
            InterestDTO interestDTO = new InterestDTO();
            interestDTO.setGame(interest.getGame().getName());
            interestDTO.setLevel(interest.getLevel().getName());
            list.add(interestDTO);
        }

        return list;
    }
}
