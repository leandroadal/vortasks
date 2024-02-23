package com.leandroadal.vortasks.entities.social.tasks.dto.grouptask.create;

import java.util.Set;

import com.leandroadal.vortasks.entities.backup.userprogress.Status;
import com.leandroadal.vortasks.entities.backup.userprogress.Type;
import com.leandroadal.vortasks.entities.social.tasks.GroupTask;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Difficulty;
import com.leandroadal.vortasks.entities.social.tasks.enumerators.Theme;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record GroupTaskCreateDTO(
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

        @NotBlank(message = "O lembrete não pode estar em branco")
        String reminder,

        @PositiveOrZero(message = "O aumento de habilidade deve ser um número maior ou igual a zero") 
        Integer skillIncrease,

        @PositiveOrZero(message = "A diminuição de habilidade deve ser um número maior ou igual a zero") 
        Integer skillDecrease,

        @NotBlank(message = "O autor não pode estar em branco") 
        String author,

        @NotBlank(message = "O editor não pode estar em branco") 
        String editor,

        @NotNull(message = "O theme não pode ser nulo")
        Theme theme,

        @NotNull(message = "O difficulty não pode ser nulo")
        Difficulty difficulty,

        @NotEmpty(message = "A lista de nomes de usuário não pode estar vazia") @Size(min = 1, max = 5, message = "A lista de nomes de usuário deve ter entre 1 e 5 elementos") 
        Set<String> usernames) {

    public GroupTask toGroupTask() {
        GroupTask task = new GroupTask();
        task.setStatus(status);
        task.setName(name);
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
        task.setTheme(theme);
        task.setDifficulty(difficulty);
        return task;
    }

}
