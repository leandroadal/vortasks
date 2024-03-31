package com.leandroadal.vortasks.entities.social.tasks.dto.onlinemission.request;

import java.time.Instant;
import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;
import com.leandroadal.vortasks.entities.social.tasks.OnlineMission;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record OnlineMissionRequestDTO(
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

        /* 
        @NotEmpty(message = "A lista de requisitos não pode estar vazia")
        @Valid
        Set<OnlineMissionTasksDTO> requirements,
        */

        @PositiveOrZero(message = "O número de curtidas deve ser um número maior ou igual a zero")
        Integer likes) {

    public OnlineMission toOnlineMission(String id) {
        OnlineMission mission = new OnlineMission();
        mission.setId(id);
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
        /* 
        mission.getRequirements().addAll(requirements.stream().map(requirement -> requirement.toOnlineMissionTasks()).collect(Collectors.toSet()));
        */
        mission.setLikes(likes);
        return mission;
    }

}
