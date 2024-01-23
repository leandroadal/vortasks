package com.leandroadal.vortasks.dto.social;

import java.util.List;

import com.leandroadal.vortasks.dto.userprogress.TaskDTO;

public record GroupTaskDTO(
        TaskDTO taskDTO,
        String author,
        String editor,
        String category,
        List<String> usernames) {
}
