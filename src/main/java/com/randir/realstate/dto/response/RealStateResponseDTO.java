package com.randir.realstate.dto.response;

import com.randir.realstate.enums.RealStateType;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RealStateResponseDTO {

    private Integer id;
    private RealStateType realStateType;
    private String description;
    private Integer rooms;
    private Integer baths;
    private Float buildedSquareMeters;
    private Float totalSquareMeters;
    private String updatedAt;

}
