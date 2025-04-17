package com.randir.realstate.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.randir.realstate.dto.request.RealStateRequestDTO;
import com.randir.realstate.dto.response.RealStateResponseDTO;
import com.randir.realstate.entity.RealState;
import com.randir.realstate.exception.ResourceNotFoundException;
import com.randir.realstate.repository.RealStateRepository;
import com.randir.realstate.service.RealStateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RealStateServiceImpl implements RealStateService {

    private RealStateRepository realStateRepository;

    @Override
    public List<RealStateResponseDTO> listAllRealStates() {
        return realStateRepository.findAll().stream().map(realState -> RealStateResponseDTO.builder()
                .id(realState.getId())
                .realStateType(realState.getRealStateType())
                .description(realState.getDescription())
                .rooms(realState.getRooms())
                .baths(realState.getBaths())
                .buildedSquareMeters(realState.getBuildedSquareMeters())
                .totalSquareMeters(realState.getTotalSquareMeters())
                .build()).toList();
    }

    @Override
    public RealStateResponseDTO listRealStateById(Integer id) {
        RealState realState = getRealStateById(id);

        return RealStateResponseDTO.builder()
                .id(realState.getId())
                .realStateType(realState.getRealStateType())
                .description(realState.getDescription())
                .rooms(realState.getRooms())
                .baths(realState.getBaths())
                .buildedSquareMeters(realState.getBuildedSquareMeters())
                .totalSquareMeters(realState.getTotalSquareMeters())
                .build();

    }

    @Override
    public RealStateResponseDTO createRealState(RealStateRequestDTO input) {
        RealState realState = new RealState();
        realState.setDescription(input.getDescription());
        realState.setRealStateType(input.getRealStateType());
        realState.setRooms(input.getRooms());
        realState.setBaths(input.getBaths());
        realState.setBuildedSquareMeters(input.getBuildedSquareMeters());
        realState.setTotalSquareMeters(input.getTotalSquareMeters());
        RealState onDB = realStateRepository.save(realState);
        return RealStateResponseDTO.builder()
                .id(onDB.getId()).build();
    }

    @Override
    public RealStateResponseDTO updateRealState(Integer id, RealStateRequestDTO input) {
        RealState onDbRealState = getRealStateById(id);

        onDbRealState.setRealStateType(input.getRealStateType());
        onDbRealState.setDescription(input.getDescription());
        onDbRealState.setBaths(input.getBaths());
        onDbRealState.setRooms(input.getRooms());
        onDbRealState.setBuildedSquareMeters(input.getBuildedSquareMeters());
        onDbRealState.setTotalSquareMeters(input.getTotalSquareMeters());

        RealState updatedRealState = realStateRepository.save(onDbRealState);

        return RealStateResponseDTO.builder()
                .id(updatedRealState.getId())
                .updatedAt(updatedRealState.getUpdatedAt().toString())
                .build();
    }

    private RealState getRealStateById(Integer id) {
        return realStateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property id: " + id + " not found"));
    }

}