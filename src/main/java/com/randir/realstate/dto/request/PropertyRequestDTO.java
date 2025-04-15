package com.randir.realstate.dto.request;

import com.randir.realstate.enums.PropertyType;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PropertyRequestDTO {

    private PropertyType propertyType;
    private String description;
    private Integer rooms;
    private Integer baths;
    private Float buildedSquareMeters;
    private Float totalSquareMeters;

}
