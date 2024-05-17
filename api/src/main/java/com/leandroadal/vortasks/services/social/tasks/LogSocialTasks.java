package com.leandroadal.vortasks.services.social.tasks;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LogSocialTasks {
    
    // ------------------------ Group Tasks ------------------------

    protected void notFoundGroupTaskById(String id) {
        log.error("Grupo de tarefas com ID: '"+ id +"' não foi encontrado!");
    }

    protected void invalidNumberUsers(String id) {
        log.error("O Grupo de tarefas com ID: '"+ id + "' recebeu com lista de usuários com tamanho fora do permito e foi recusada a alteração");
    }

    protected void addGroupTask(String id) {
        log.info("Grupo de tarefas com ID: '"+ id +"' criado com sucesso!");
    }
    
    protected void editGroupTask(String id) {
        log.info("Grupo de tarefas com ID: '"+ id +"' foi editado com sucesso!");
    }

    protected void partialEditGroupTask(String id) {
        log.info("Grupo de tarefas com ID: '"+ id +"' foi editado parcialmente com sucesso!");;
    }

    protected void deleteGroupTask(String id) {
        log.info("Grupo de tarefas com ID: '"+ id +"' foi deletado com sucesso!");
    }

    // ------------------------ Online Missions ------------------------

    protected void notFoundOnlineMission(String id) {
        log.error("Missão Online com ID: '"+ id +"' não foi encontrada!");
    }

    public void notFoundOnlineMissionTasks(String id) {
        log.error("A tarefa da Missão Online com ID: '"+ id +"' não foi encontrada!");
    }

    protected void failDeleteOnlineMission(String id) {
        log.error("Não foi possível deletar a entidade com ID: '"+ id +"' pois ainda há relação com outra(s) entidade(s)");;
    }

    protected void addOnlineMission(String id) {
        log.info("Missão Online com ID: '"+ id +"' criado com sucesso!");
    }

    protected void editOnlineMission(String id) {
        log.info("Missão Online com ID: '"+ id +"' foi editado com sucesso!");
    }

    protected void partialEditOnlineMission(String id) {
        log.info("Missão Online com ID: '"+ id +"' foi editado parcialmente com sucesso!");
    }

    protected void deleteOnlineMission(String id) {
        log.info("Missão Online com ID: '"+ id +"' foi deletado com sucesso!");
    }

    
}
