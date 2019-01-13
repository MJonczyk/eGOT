package com.jestgit.egot.decyzja;

import lombok.Data;

import java.util.Date;

@Data
public class DecyzjaDTO {
    private Long numerDecyzji;
    private Long numerWycieczki;
    private String numerLegitymacji;
    private String czyZatwierdzona;
    private Date data;
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
