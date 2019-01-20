package com.jestgit.egot.decyzja;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * A class used to transfer data about Decyzja between View layer and Controller layer.
 * @author Mateusz Wójcik
 * @version 1.2
 */
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

    /**
     * Default constructor used to pass DecyzjaDTO object to the View
     */
    public DecyzjaDTO(){}

    /**
     * Parameterized constructor used to create DecyzjaDTO and initialize all of its fields
     * @param numerWycieczki wycieczka this decyzja belongs to
     * @param numerLegitymacji Id of the Przodownik who accepted this Decyzja
     * @param czyZatwierdzona result of made decision
     * @param data date of the Decyzja acceptation
     * @param uzasadnienie description of the Decyzja
     */
    public DecyzjaDTO(Long numerWycieczki, String numerLegitymacji, String czyZatwierdzona, Date data, String uzasadnienie) {
        this.numerWycieczki = numerWycieczki;
        this.numerLegitymacji = numerLegitymacji;
        this.czyZatwierdzona = czyZatwierdzona;
        this.data = data;
        this.uzasadnienie = uzasadnienie;
    }
}
