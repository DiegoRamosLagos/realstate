package com.randir.realstate.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.randir.realstate.enums.RealStateType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "real_states")
@Getter
@Setter
@NoArgsConstructor
public class RealState {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "real_state_type", nullable = false)
    private RealStateType realStateType;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "rooms", nullable = false)
    private Integer rooms;
    @Column(name = "baths", nullable = false)
    private Integer baths;
    @Column(name = "builded_square_meters", nullable = false)
    private Float buildedSquareMeters;
    @Column(name = "total_square_meters", nullable = false)
    private Float totalSquareMeters;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
