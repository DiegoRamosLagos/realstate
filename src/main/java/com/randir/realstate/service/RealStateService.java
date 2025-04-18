package com.randir.realstate.service;

import java.util.List;

import com.randir.realstate.dto.request.RealStateRequestDTO;
import com.randir.realstate.dto.response.RealStateResponseDTO;

public interface RealStateService {

    public List<RealStateResponseDTO> listAllRealStates();

    public RealStateResponseDTO listRealStateById(Integer id);

    public RealStateResponseDTO createRealState(RealStateRequestDTO input);

    public RealStateResponseDTO updateRealState(Integer id, RealStateRequestDTO input);

    public Integer deleteRealState(Integer id);

}
