package com.leandroadal.vortasks.entities.backup.userprogress.dto;

import com.leandroadal.vortasks.entities.backup.userprogress.Skill;

public record SkillDTO(String id, String name, float xp) {

    public SkillDTO(Skill skill) {
        this(skill.getId(), skill.getName(), skill.getXp());
    }
}
