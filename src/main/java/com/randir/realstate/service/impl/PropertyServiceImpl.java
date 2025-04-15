package com.randir.realstate.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.randir.realstate.dto.request.PropertyRequestDTO;
import com.randir.realstate.dto.response.PropertyResponseDTO;
import com.randir.realstate.entity.Property;
import com.randir.realstate.enums.PropertyType;
import com.randir.realstate.exception.ResourceNotFoundException;
import com.randir.realstate.repository.PropertyRepository;
import com.randir.realstate.service.PropertyService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private PropertyRepository propertyRepository;

    @Override
    public List<PropertyResponseDTO> listAllProperties() {
        return propertyRepository.findAll().stream().map(property -> PropertyResponseDTO.builder()
                .propertyType(property.getPropertyType())
                .description(property.getDescription())
                .rooms(property.getRooms())
                .baths(property.getBaths())
                .buildedSquareMeters(property.getBuildedSquareMeters())
                .totalSquareMeters(property.getTotalSquareMeters())
                .build()).toList();
    }

    @Override
    public PropertyResponseDTO listPropertyById(Integer id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property id: " + id + " not found"));

        return PropertyResponseDTO.builder()
                .propertyType(property.getPropertyType())
                .description(property.getDescription())
                .rooms(property.getRooms())
                .baths(property.getBaths())
                .buildedSquareMeters(property.getBuildedSquareMeters())
                .totalSquareMeters(property.getTotalSquareMeters())
                .build();

    }

    @Override
    public PropertyResponseDTO createProperty(PropertyRequestDTO input) {
        Property property = new Property();
        property.setDescription(input.getDescription());
        property.setPropertyType(PropertyType.HOUSE);
        property.setRooms(input.getRooms());
        property.setBaths(input.getBaths());
        property.setBuildedSquareMeters(input.getBuildedSquareMeters());
        property.setTotalSquareMeters(input.getTotalSquareMeters());
        Property onDB = propertyRepository.save(property);
        return PropertyResponseDTO.builder()
                .id(onDB.getId()).build();
    }

}