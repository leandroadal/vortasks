package com.leandroadal.vortasks.entities.social.tasks.enumerators;

public enum Theme {

    PRODUCTIVITY("Produtividade"),
    COLLABORATION("Colaboração"),
    LEARNING("Aprendizado"),
    WELLNESS("Bem-Estar"),
    COMMUNICATION("Comunicação"),
    CREATIVITY("Criatividade"),
    HEALTH("Saúde"),
    ORGANIZATION("Organização"),
    LANGUAGES("Idiomas"),
    FINANCE("Finanças"),
    HOUSEHOLD_TASKS("Tarefas Domésticas"),
    HOBBIES("Hobbies");

    private String displayName;

    Theme(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
