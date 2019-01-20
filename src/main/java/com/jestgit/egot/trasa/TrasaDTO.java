package com.jestgit.egot.trasa;


import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Class used to transfer data about Trasa between View layer and Controller layer.
 * @author Michał Jończyk
 * @version 1.2
 */
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
    @Pattern(regexp = "\\d{1,2}\\/\\d{1,2}", message = "Nieprawidłowy format punktów! (Spróbuj x/x)")
    private String punktyZaTrase;

    private String opis;

    private Object id;

    /**
     * Default constructor used to pass TrasaDTO object to the View
     */
    public TrasaDTO(){}

    /**
     * Parameterized constructor used to create TrasaDTO and initialize all of its fields
     * @param numerTrasy - ID of Trasa
     * @param grupa - Grupa related to this Trasa
     * @param punktPoczatkowy - starting Punkt of Trasa
     * @param punktKoncowy - end Punkt of Trasa
     * @param punktyZaTrase - number of points you get for completing Trasa
     * @param opis - description of  rasa
     * @param id - Id of Przewodnik who added this Trasa to database
     */
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
