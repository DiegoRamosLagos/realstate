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

import com.randir.realstate.dto.request.RealStateRequestDTO;
import com.randir.realstate.dto.response.RealStateResponseDTO;
import com.randir.realstate.entity.RealState;
import com.randir.realstate.enums.RealStateType;
import com.randir.realstate.exception.ResourceNotFoundException;
import com.randir.realstate.repository.RealStateRepository;
import com.randir.realstate.service.impl.RealStateServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTest {

    @Mock
    private RealStateRepository realStateRepository;

    @InjectMocks
    private RealStateServiceImpl realStateService;

    @Test
    public void ListAllProperties_GivenACallToRetrieveAllProperties_ShouldSameSize() {

        // Arrange
        List<RealStateResponseDTO> expectedResult = List.of(
                RealStateResponseDTO.builder()
                        .id(1)
                        .realStateType(RealStateType.HOUSE)
                        .description("A basic description about the property")
                        .rooms(3)
                        .baths(2)
                        .buildedSquareMeters(80F)
                        .totalSquareMeters(200F)
                        .build());
        RealState realState = new RealState();
        realState.setId(1);
        realState.setDescription("A basic description about the property");
        realState.setRealStateType(RealStateType.HOUSE);
        realState.setRooms(3);
        realState.setBaths(2);
        realState.setBuildedSquareMeters(80F);
        realState.setTotalSquareMeters(200F);

        List<RealState> properties = List.of(realState);

        when(realStateRepository.findAll()).thenReturn(properties);

        // Act
        List<RealStateResponseDTO> actualResult = realStateService.listAllRealStates();

        // Assert
        assertEquals(expectedResult.size(), actualResult.size());

    }

    @Test
    public void ListPropertyById_GivenOneSpecificId_ShouldReturnTheSameDescription() {
        // Arrange
        RealStateResponseDTO expectedResult = RealStateResponseDTO.builder()
                .id(1)
                .realStateType(RealStateType.HOUSE)
                .description("A basic description about the property")
                .rooms(3)
                .baths(2)
                .buildedSquareMeters(80F)
                .totalSquareMeters(200F)
                .build();
        RealState realState = new RealState();
        realState.setId(1);
        realState.setDescription("A basic description about the property");
        realState.setRealStateType(RealStateType.HOUSE);
        realState.setRooms(3);
        realState.setBaths(2);
        realState.setBuildedSquareMeters(80F);
        realState.setTotalSquareMeters(200F);

        when(realStateRepository.findById(anyInt())).thenReturn(Optional.of(realState));

        // Act
        RealStateResponseDTO actualResult = realStateService.listRealStateById(1);

        // Assert
        assertEquals(expectedResult.getDescription(), actualResult.getDescription());
    }

    @Test
    public void ListPropertyById_GivenOneSpecificIdAndNotFound_ShouldThrowsResourceNotFoundException() {

        assertThrows(ResourceNotFoundException.class, () -> {
            realStateService.listRealStateById(1);
        });
    }

    @Test
    public void CreatePropertyById_GivenOneSpecificId_ShouldReturnThePropertyResponse() {
        // Arrange
        RealStateRequestDTO mockedRequest = RealStateRequestDTO.builder()
                .realStateType(RealStateType.HOUSE)
                .description("A basic description about the property")
                .rooms(3)
                .baths(2)
                .buildedSquareMeters(80F)
                .totalSquareMeters(200F)
                .build();

        RealStateResponseDTO expectedResult = RealStateResponseDTO.builder()
                .id(1)
                .build();
        RealState realState = new RealState();
        realState.setId(1);
        realState.setDescription("A basic description about the property");
        realState.setRealStateType(RealStateType.HOUSE);
        realState.setRooms(3);
        realState.setBaths(2);
        realState.setBuildedSquareMeters(80F);
        realState.setTotalSquareMeters(200F);

        when(realStateRepository.save(any(RealState.class))).thenReturn(realState);

        // Act
        RealStateResponseDTO actualResult = realStateService.createRealState(mockedRequest);

        // Assert
        assertEquals(expectedResult.getId(), actualResult.getId());
    }

    public void UpdatePropertyById_GivenOneSpecificIdAndUpdateTheProperty_ShouldReturnThePropertyResponse() {

    }

}
