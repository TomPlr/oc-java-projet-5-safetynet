package org.safetynet.domain;

import lombok.Getter;
import lombok.Setter;
import org.safetynet.dto.PersonDto;

import java.util.List;

@Getter
@Setter
public class Persons {

    private List<PersonDto> Persons;
    private int totalOver18;
    private int totalUnderOrEqual18;

}
