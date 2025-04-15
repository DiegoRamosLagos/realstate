package com.randir.realstate.dto.response;

import com.randir.realstate.enums.PropertyType;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PropertyResponseDTO {

    private Integer id;
    private PropertyType propertyType;
    private String description;
    private Integer rooms;
    private Integer baths;
    private Float buildedSquareMeters;
    private Float totalSquareMeters;

}
