package com.randir.realstate.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
    public void ListAllRealStates_GivenACallToRetrieveAllRealStates_ShouldSameSize() {

        // Arrange
        List<RealStateResponseDTO> expectedResult = List.of(
                RealStateResponseDTO.builder()
                        .id(1)
                        .realStateType(RealStateType.HOUSE)
                        .description("A basic description about the RealState")
                        .rooms(3)
                        .baths(2)
                        .buildedSquareMeters(80F)
                        .totalSquareMeters(200F)
                        .build());
        RealState realState = new RealState();
        realState.setId(1);
        realState.setDescription("A basic description about the RealState");
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
    public void ListRealStateById_GivenOneSpecificId_ShouldReturnTheSameDescription() {
        // Arrange
        RealStateResponseDTO expectedResult = RealStateResponseDTO.builder()
                .id(1)
                .realStateType(RealStateType.HOUSE)
                .description("A basic description about the RealState")
                .rooms(3)
                .baths(2)
                .buildedSquareMeters(80F)
                .totalSquareMeters(200F)
                .build();
        RealState realState = new RealState();
        realState.setId(1);
        realState.setDescription("A basic description about the RealState");
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
    public void ListRealStateById_GivenOneSpecificIdAndNotFound_ShouldThrowsResourceNotFoundException() {

        assertThrows(ResourceNotFoundException.class, () -> {
            realStateService.listRealStateById(1);
        });
    }

    @Test
    public void CreateRealState_GivenOneSpecificInput_ShouldReturnThePropertyResponseIdField() {
        // Arrange
        RealStateRequestDTO mockedRequest = RealStateRequestDTO.builder()
                .realStateType(RealStateType.HOUSE)
                .description("A basic description about the RealState")
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
        realState.setDescription("A basic description about the RealState");
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

    @Test
    public void UpdateRealStateById_GivenOneSpecificRealState_ShouldReturnTheUpdatedRealStateResponseWithTheCorrectId() {

        RealStateRequestDTO input = RealStateRequestDTO.builder()
                .realStateType(RealStateType.OFFICE)
                .description("Updated Description")
                .baths(2)
                .rooms(2)
                .buildedSquareMeters(200F)
                .totalSquareMeters(200F)
                .build();

        RealState mockedFindRealState = new RealState();
        mockedFindRealState.setId(1);
        mockedFindRealState.setRealStateType(RealStateType.OFFICE);
        mockedFindRealState.setDescription("To be updated");
        mockedFindRealState.setBaths(2);
        mockedFindRealState.setRooms(2);
        mockedFindRealState.setBuildedSquareMeters(200F);
        mockedFindRealState.setTotalSquareMeters(200F);
        mockedFindRealState.setCreatedAt(LocalDateTime.now());

        RealState updatedRealState = new RealState();
        updatedRealState.setRealStateType(input.getRealStateType());
        updatedRealState.setDescription(input.getDescription());
        updatedRealState.setBaths(input.getBaths());
        updatedRealState.setRooms(input.getRooms());
        updatedRealState.setBuildedSquareMeters(input.getBuildedSquareMeters());
        updatedRealState.setTotalSquareMeters(input.getTotalSquareMeters());
        updatedRealState.setCreatedAt(mockedFindRealState.getCreatedAt());
        updatedRealState.setUpdatedAt(LocalDateTime.now());

        when(realStateRepository.findById(anyInt())).thenReturn(Optional.of(mockedFindRealState));
        when(realStateRepository.save(any(RealState.class))).thenReturn(updatedRealState);

        RealStateResponseDTO actualResult = realStateService.updateRealState(1, input);

        assertNotEquals(actualResult.getId(), mockedFindRealState.getId());

    }

    @Test
    public void DeleteRealState_GivenOneSpecificId_ShouldReturnTheId() {

        doNothing().when(realStateRepository).deleteById(anyInt());

        Integer actualResult = realStateService.deleteRealState(1);

        assertEquals(1, actualResult);

    }

    @Test
    public void DeleteRealState_GivenOneSpecificIdAndTheIdIsNull_ShouldThrowsAnIllegalArgumentException() {

        doThrow(IllegalArgumentException.class).when(realStateRepository).deleteById(null);

        IllegalArgumentException actualException = assertThrows(IllegalArgumentException.class, () -> {
            realStateService.deleteRealState(null);
        });

        assertEquals("Delete Operation not allowed", actualException.getMessage());

    }

}
