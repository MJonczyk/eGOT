package com.jestgit.egot.trasa;


import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class TrasaDTO {
    private Long numerTrasy;

    @Valid
    @NotEmpty(message = "Należy zdefiniować grupę górską!")
    private String nazwaGrupy;

//    @Size(min = 3, max = 30, message = "Nazwa powinna zawierać przynajmniej 3 litery!")
    private Long punktPoczatkowy;

//    @Size(min = 3, max = 30, message = "Nazwa powinna zawierać przynajmniej 3 litery!")
    private Long punktKoncowy;

    @Valid
    @NotEmpty(message = "Należy podać punkty za trasę!")
    private String punktyZaTrase;

    private String opis;

    private Object id;

    public TrasaDTO(){}

    public TrasaDTO(Long numerTrasy, String grupa, Long punktPoczatkowy, Long punktKoncowy, String punktyZaTrase, String opis, Object id){
        this.numerTrasy = numerTrasy;
        this.nazwaGrupy = grupa;
        this.punktPoczatkowy = punktPoczatkowy;
        this.punktKoncowy = punktKoncowy;
        this.punktyZaTrase = punktyZaTrase;
        this.opis = opis;
        this.id = id;
    }
}
