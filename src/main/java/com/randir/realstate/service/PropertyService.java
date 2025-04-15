package com.randir.realstate.service;

import java.util.List;

import com.randir.realstate.dto.request.PropertyRequestDTO;
import com.randir.realstate.dto.response.PropertyResponseDTO;

public interface PropertyService {

    public List<PropertyResponseDTO> listAllProperties();

    public PropertyResponseDTO listPropertyById(Integer id);

    public PropertyResponseDTO createProperty(PropertyRequestDTO input);

}
