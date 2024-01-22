package com.leandroadal.vortasks.dto;

import java.util.List;

public record GroupTaskDTO(
        TaskDTO taskDTO,
        String author,
        String editor,
        String category,
        List<String> usernames) {
}
