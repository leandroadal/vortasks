package com.leandroadal.vortasks.entities.backup.userprogress.dto.create;

import java.util.List;

import com.leandroadal.vortasks.entities.social.tasks.enumerators.Theme;

public record SkillCreateDTO(
        String name,
        float xp,
        int level,
        List<Theme> themes) {

}
