package com.leandroadal.vortasks.entities.social.tasks.dto.onlinemission.request;

import java.time.Instant;

import org.hibernate.validator.constraints.UUID;

import com.leandroadal.vortasks.entities.backup.userprogress.Type;
import com.leandroadal.vortasks.entities.social.tasks.OnlineMissionTasks;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record OnlineMissionTasksRequestDTO(
    @UUID(message = "ID invalido")
    String id,

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
    Instant endDate
) {

    public OnlineMissionTasks toOnlineMissionTasks() {
        OnlineMissionTasks missionTasks = new OnlineMissionTasks();
        missionTasks.setId(id);
        missionTasks.setTitle(title);
        missionTasks.setDescription(description);
        missionTasks.setXp(xp);
        missionTasks.setCoins(coins);
        missionTasks.setType(type);
        missionTasks.setRepetition(repetition);
        missionTasks.setReminder(reminder);
        missionTasks.setSkillIncrease(skillIncrease);
        missionTasks.setSkillDecrease(skillDecrease);
        missionTasks.setStartDate(startDate);
        missionTasks.setEndDate(endDate);
        return missionTasks;
    }

}
