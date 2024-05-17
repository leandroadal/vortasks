package com.leandroadal.vortasks.entities.social.tasks.dto.onlinemission.create;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;
import com.leandroadal.vortasks.entities.social.tasks.OnlineMission;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Difficulty;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Theme;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record OnlineMissionCreateDTO(
        @NotNull(message = "O status não pode ser nulo")
        Status status,

        @NotBlank(message = "O título não pode estar em branco")
        String title,

        @NotBlank(message = "A descrição não pode estar em branco")
        String description,

        @PositiveOrZero(message = "O XP deve ser um número maior ou igual a zero")
        Integer xp,

        @PositiveOrZero(message = "As moedas devem ser um número maior ou igual a zero")
        Integer coins,

        @NotNull(message = "O tipo não pode ser nulo")
        Type type,

        @NotBlank(message = "A repetição não pode estar em branco")
        Integer repetition,

        @NotBlank(message = "O lembrete não pode estar em branco")
        Instant reminder,

        @PositiveOrZero(message = "O aumento de habilidade deve ser um número maior ou igual a zero")
        Integer skillIncrease,

        @PositiveOrZero(message = "A diminuição de habilidade deve ser um número maior ou igual a zero")
        Integer skillDecrease,

        @NotNull(message = "A data de início não pode ser nula")
        Instant startDate,

        @NotNull(message = "A data de término não pode ser nula")
        Instant endDate,

        @NotEmpty(message = "A lista de requisitos não pode estar vazia")
        @Valid
        Set<OnlineMissionTasksCreateDTO> requirements,

        @NotNull(message = "O theme não pode ser nulo")
        Theme theme,

        @NotNull(message = "O difficulty não pode ser nulo")
        Difficulty difficulty,

        @PositiveOrZero(message = "O número de curtidas deve ser um número maior ou igual a zero")
        Integer likes) {

    public OnlineMission toOnlineMission() {
        OnlineMission mission = new OnlineMission();
        mission.setStatus(status);
        mission.setTitle(title);
        mission.setDescription(description);
        mission.setXp(xp);
        mission.setCoins(coins);
        mission.setType(type);
        mission.setRepetition(repetition);
        mission.setReminder(reminder);
        mission.setSkillIncrease(skillIncrease);
        mission.setSkillDecrease(skillDecrease); 
        mission.setStartDate(startDate);
        mission.setEndDate(endDate);
        mission.setTheme(theme);
        mission.setDifficulty(difficulty);
        mission.getRequirements().addAll(requirements.stream().map(requirement -> requirement.toOnlineMissionTasks()).collect(Collectors.toSet()));
        mission.setLikes(likes);
        return mission;
    }
}
