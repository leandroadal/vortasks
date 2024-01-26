package com.leandroadal.vortasks.dto.userprogress;

import com.leandroadal.vortasks.entities.backup.userprogress.Skill;

public record SkillDTO(String name, float xp) {

    public SkillDTO(Skill skill) {
        this(skill.getName(), skill.getXp());
    }
}
