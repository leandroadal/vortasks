package com.leandroadal.vortasks.entities.backup.userprogress.dto;

import java.util.List;

import com.leandroadal.vortasks.entities.backup.userprogress.Skill;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Theme;

public record SkillDTO(String id, String name, float xp, int level, List<Theme> themes) {

    public SkillDTO(Skill skill) {
        this(skill.getId(), skill.getName(), skill.getXp(), skill.getLevel(), skill.getThemes());
    }
}
