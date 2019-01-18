package com.jestgit.egot.decyzja;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class DecyzjaDTO {
    private Long numerDecyzji;
    private Long numerWycieczki;
    private String numerLegitymacji;
    private String czyZatwierdzona;
    private Date data;

    @Valid
    @NotEmpty(message = "Należy uzasadnić decyzję!")
    @Pattern(regexp = "^.{3,300}$", message = "Zbyt krótkie uzasadnienie!")
    private String uzasadnienie;

    public DecyzjaDTO(){}

    public DecyzjaDTO(Long numerWycieczki, String numerLegitymacji, String czyZatwierdzona, Date data, String uzasadnienie) {
        this.numerWycieczki = numerWycieczki;
        this.numerLegitymacji = numerLegitymacji;
        this.czyZatwierdzona = czyZatwierdzona;
        this.data = data;
        this.uzasadnienie = uzasadnienie;
    }
}
