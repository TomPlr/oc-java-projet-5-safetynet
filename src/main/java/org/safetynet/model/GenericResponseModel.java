package org.safetynet.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GenericResponseModel {

    boolean success;
    String details;
}
