package com.jestgit.egot.trasa;


import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class TrasaDTO {
    @Valid
    @NotEmpty(message = "Należy zdefiniować grupę górską!")
    private String nazwaGrupy;

    @Valid
    @NotEmpty(message = "Należy zdefiniować punkt początkowy!")
    @Size(min = 3, max = 30, message = "Nazwa powinna zawierać przynajmniej 3 litery!")
    private String punktPoczatkowy;

    @Valid
    @NotEmpty(message = "Należy zdefiniować punkt końcowy!")
    @Size(min = 3, max = 30, message = "Nazwa powinna zawierać przynajmniej 3 litery!")
    private String punktKoncowy;

    @Valid
    @NotEmpty(message = "Należy podać punkty za trasę!")
    private String punktyZaTrase;

    private String opis;

    private Object id;

    public TrasaDTO(){}

    public TrasaDTO(String grupa, String punktPoczatkowy, String punktKoncowy, String punktyZaTrase, String opis, Object id){
        this.nazwaGrupy = grupa;
        this.punktPoczatkowy = punktPoczatkowy;
        this.punktKoncowy = punktKoncowy;
        this.punktyZaTrase = punktyZaTrase;
        this.opis = opis;
        this.id = id;
    }
}
