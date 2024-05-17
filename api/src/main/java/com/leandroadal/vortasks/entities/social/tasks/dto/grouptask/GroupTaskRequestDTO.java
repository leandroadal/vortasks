package com.leandroadal.vortasks.entities.social.tasks.dto.grouptask;

import java.time.Instant;
import java.util.Set;
import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;
import com.leandroadal.vortasks.entities.social.tasks.GroupTask;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record GroupTaskRequestDTO(
        @NotNull(message = "O status não pode ser nulo")
        Status status,

        @NotBlank(message = "O nome não pode estar em branco")
        String name,

        @NotBlank(message = "A descrição não pode estar em branco")
        String description,

        @PositiveOrZero(message = "O XP deve ser um número maior ou igual a zero")
        Integer xp,

        @PositiveOrZero(message = "As moedas devem ser um número maior ou igual a zero")
        Integer coins,

        @NotNull(message = "O tipo não pode ser nulo")
        Type type,

        @PositiveOrZero(message = "A repetição deve ser um número maior ou igual a zero")
        Integer repetition,

        Instant reminder,

        @PositiveOrZero(message = "O aumento de habilidade deve ser um número maior ou igual a zero")
        Integer skillIncrease,

        @PositiveOrZero(message = "A diminuição de habilidade deve ser um número maior ou igual a zero")
        Integer skillDecrease,

        @NotBlank(message = "O autor não pode estar em branco")
        String author,

        @NotBlank(message = "O editor não pode estar em branco")
        String editor,

        @NotNull(message = "A lista de nomes de usuário não pode ser nula")
        @Size(min = 1, max = 5, message = "A lista de nomes de usuário deve ter entre 1 e 5 elementos")
        Set<String> usernames) {

    public GroupTask toGroupTask(String id) {
        GroupTask task = new GroupTask();
        task.setId(id);
        task.setStatus(status);
        task.setTitle(name);
        task.setDescription(description);
        task.setXp(xp);
        task.setCoins(coins);
        task.setType(type);
        task.setRepetition(repetition);
        task.setReminder(reminder);
        task.setSkillIncrease(skillIncrease);
        task.setSkillDecrease(skillDecrease);
        task.setAuthor(author);
        task.setEditor(editor);
        return task;
    }
}
