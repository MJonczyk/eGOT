package com.jestgit.egot;

import lombok.Data;

@Data
public class WeryfikujDTO {
    private Boolean isAccepted;

    public WeryfikujDTO(){}

    public WeryfikujDTO(Boolean isAccepted){
        this.isAccepted = isAccepted;
    }
}
