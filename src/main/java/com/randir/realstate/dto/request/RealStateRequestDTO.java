package com.randir.realstate.dto.request;

import com.randir.realstate.enums.RealStateType;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RealStateRequestDTO {

    private RealStateType realStateType;
    private String description;
    private Integer rooms;
    private Integer baths;
    private Float buildedSquareMeters;
    private Float totalSquareMeters;

}
