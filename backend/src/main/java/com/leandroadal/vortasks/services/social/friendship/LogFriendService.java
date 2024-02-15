package com.leandroadal.vortasks.services.social.friendship;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LogFriendService {

    public void logFindFriendInvite(String friendRequestId) {
        log.warn("Pedido de amizade com ID: '{}' ", friendRequestId);;
    }

    public void logFriendReceiverMismatch(String friendRequestId, String userId) {
        log.debug("Usuário '{}' incompatível com o usuário receptor requerido na amizade: {}", userId , friendRequestId);
    }

    protected void findFriendshipById(String id) {
        log.error("A amizade com ID: " + id + "' não foi encontrada!");
    }

    public void deleteFriendship(String id) {
        log.info("A amizade com ID: '"+ id + "' foi deletada com sucesso!");
    }

    public void createFriendship(String id) {
        log.info("A amizade com ID: '"+ id + "' foi criada com sucesso!");;
    }

    public void cancelFriendInvite(String id) {
        log.info("Solicitação de amizade com ID: '"+ id + "' foi cancelada com sucesso!");
    }

    public void refusedFriendInvite(String id) {
        log.info("Solicitação de amizade com ID: '"+ id + "' foi recusada com sucesso!");
    }

    public void acceptFriendRequest(String id) {
        log.info("Solicitação de amizade com ID: '"+ id + "' foi aceita com sucesso!");
    }

    public void sendFriendRequest(String newInviteId) {
        log.info("Solicitação de amizade com ID: '"+ newInviteId + "' foi criada com sucesso!");
    }

}
