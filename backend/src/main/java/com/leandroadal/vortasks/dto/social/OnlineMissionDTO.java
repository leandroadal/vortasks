package com.leandroadal.vortasks.dto.social;

import com.leandroadal.vortasks.dto.userprogress.MissionDTO;

public record OnlineMissionDTO(
    MissionDTO missionDTO,
    int likes
) {

}
