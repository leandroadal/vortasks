package com.leandroadal.vortasks.dto.social;

import java.util.List;

public record GroupTaskDTO(
        String status,
        String name,
        String description,
        int xp,
        float coins,
        String type,
        int repetition,
        String reminder,
        int skillIncrease,
        int skillDecrease,
        String author,
        String editor,
        String category,
        List<String> usernames) {
}
