package com.randir.realstate.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.randir.realstate.dto.request.PropertyRequestDTO;
import com.randir.realstate.dto.response.PropertyResponseDTO;
import com.randir.realstate.entity.Property;
import com.randir.realstate.enums.PropertyType;
import com.randir.realstate.exception.ResourceNotFoundException;
import com.randir.realstate.repository.PropertyRepository;
import com.randir.realstate.service.impl.PropertyServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTest {

    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private PropertyServiceImpl propertyService;

    @Test
    public void ListAllProperties_GivenACallToRetrieveAllProperties_ShouldSameSize() {

        // Arrange
        List<PropertyResponseDTO> expectedResult = List.of(
                PropertyResponseDTO.builder()
                        .id(1)
                        .propertyType(PropertyType.HOUSE)
                        .description("A basic description about the property")
                        .rooms(3)
                        .baths(2)
                        .buildedSquareMeters(80F)
                        .totalSquareMeters(200F)
                        .build());
        Property property = new Property();
        property.setId(1);
        property.setDescription("A basic description about the property");
        property.setPropertyType(PropertyType.HOUSE);
        property.setRooms(3);
        property.setBaths(2);
        property.setBuildedSquareMeters(80F);
        property.setTotalSquareMeters(200F);

        List<Property> properties = List.of(property);

        when(propertyRepository.findAll()).thenReturn(properties);

        // Act
        List<PropertyResponseDTO> actualResult = propertyService.listAllProperties();

        // Assert
        assertEquals(expectedResult.size(), actualResult.size());

    }

    @Test
    public void ListPropertyById_GivenOneSpecificId_ShouldReturnTheSameDescription() {
        // Arrange
        PropertyResponseDTO expectedResult = PropertyResponseDTO.builder()
                .id(1)
                .propertyType(PropertyType.HOUSE)
                .description("A basic description about the property")
                .rooms(3)
                .baths(2)
                .buildedSquareMeters(80F)
                .totalSquareMeters(200F)
                .build();
        Property property = new Property();
        property.setId(1);
        property.setDescription("A basic description about the property");
        property.setPropertyType(PropertyType.HOUSE);
        property.setRooms(3);
        property.setBaths(2);
        property.setBuildedSquareMeters(80F);
        property.setTotalSquareMeters(200F);

        when(propertyRepository.findById(anyInt())).thenReturn(Optional.of(property));

        // Act
        PropertyResponseDTO actualResult = propertyService.listPropertyById(1);

        // Assert
        assertEquals(expectedResult.getDescription(), actualResult.getDescription());
    }

    @Test
    public void ListPropertyById_GivenOneSpecificIdAndNotFound_ShouldThrowsResourceNotFoundException() {

        assertThrows(ResourceNotFoundException.class, () -> {
            propertyService.listPropertyById(1);
        });
    }

    @Test
    public void CreatePropertyById_GivenOneSpecificId_ShouldReturnThePropertyResponse() {
        // Arrange
        PropertyRequestDTO mockedRequest = PropertyRequestDTO.builder()
                .propertyType(PropertyType.HOUSE)
                .description("A basic description about the property")
                .rooms(3)
                .baths(2)
                .buildedSquareMeters(80F)
                .totalSquareMeters(200F)
                .build();

        PropertyResponseDTO expectedResult = PropertyResponseDTO.builder()
                .id(1)
                .build();
        Property property = new Property();
        property.setId(1);
        property.setDescription("A basic description about the property");
        property.setPropertyType(PropertyType.HOUSE);
        property.setRooms(3);
        property.setBaths(2);
        property.setBuildedSquareMeters(80F);
        property.setTotalSquareMeters(200F);

        when(propertyRepository.save(any(Property.class))).thenReturn(property);

        // Act
        PropertyResponseDTO actualResult = propertyService.createProperty(mockedRequest);

        // Assert
        assertEquals(expectedResult.getId(), actualResult.getId());
    }

}
