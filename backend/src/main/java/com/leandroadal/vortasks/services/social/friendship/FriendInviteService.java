package com.leandroadal.vortasks.services.social.friendship;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.entities.social.friend.FriendInvite;
import com.leandroadal.vortasks.entities.social.friend.Friendship;
import com.leandroadal.vortasks.entities.social.friend.enums.FriendStatus;
import com.leandroadal.vortasks.entities.social.friend.pk.FriendInvitePK;
import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.repositories.social.FriendInviteRepository;
import com.leandroadal.vortasks.services.exception.ObjectNotFoundException;
import com.leandroadal.vortasks.services.social.friendship.exceptions.FriendException;
import com.leandroadal.vortasks.services.user.UserService;

@Service
public class FriendInviteService {

    @Autowired
    private FriendInviteRepository friendInviteRepository;

    @Autowired
    private FriendshipService friendshipService;

    @Autowired
    private UserService userService;

    @Autowired
    private LogFriendService log;

    public List<FriendInvite> friendInvitesForUser(String userId) {
        return friendInviteRepository.findByIdSenderUserIdOrIdReceiverUserId(userId, userId);
    }

    public List<FriendInvite> findAll() {
        return friendInviteRepository.findAll();
    }

    public FriendInvite findFriendInvite(String senderId, String receiverId) {
        try {
            return friendInviteRepository.findByIdSenderUserIdAndIdReceiverUserId(senderId, receiverId);

        } catch (ObjectNotFoundException e) {
            log.notFoundFriendInvite(senderId, receiverId);
            throw e;
        }
    }

    public FriendInvite sendFriendRequest(String senderId, String receiverId) {
        validateSenderReceiverIds(senderId, receiverId);
        User requestingUser = userService.findUserById(senderId);
        User receiverUser = userService.findUserById(receiverId);
        FriendInvite newInvite = new FriendInvite();

        newInvite.setId(new FriendInvitePK(requestingUser, receiverUser));

        if (friendInviteRepository.existsById(newInvite.getId())) {
            throw new FriendException("O convite de amizade com ID: " 
                + newInvite.getId().getSenderUser().getId() 
                + newInvite.getId().getReceiverUser().getId() + "' já existe");
        }

        newInvite.setRequestDate(Instant.now());
        newInvite.setStatus(FriendStatus.PENDING);

        requestingUser.getSenderFriendRequests().add(newInvite);
        receiverUser.getReceivedFriendRequests().add(newInvite);
        log.sendFriendRequest(newInvite.getId());
        return friendInviteRepository.save(newInvite);
    }

    private void validateSenderReceiverIds(String senderId, String receiverId) {
        if (senderId.equals(receiverId)) {
            throw new FriendException("O remetente e o destinatário devem ser diferentes");
        }
    }

    public Friendship acceptFriendRequest(String senderId, String receiverId) {
        FriendInvite invite = findFriendInvite(senderId, receiverId);

        validateStatus(invite);
        // TODO ativar a validação do receiver quando implementar a autenticação
        //validateReceiverUser(invite, user);

        invite.setStatus(FriendStatus.ACCEPTED);

        friendInviteRepository.save(invite);
        log.acceptFriendRequest(invite.getId());
        return friendshipService.createFriendship(invite);
    }    

    private void validateStatus(FriendInvite invite) {
        if (invite.getStatus() == FriendStatus.REJECTED) {
            throw new FriendException("O convite de amizade com ID: " + invite.getId() + "' ja foi rejeitado");
        }
    }

    private void validateReceiverUser(FriendInvite invite, User user) {
        if (invite.getId().getReceiverUser()!= user) {
            log.friendReceiverMismatch(invite.getId(), user.getId());
            throw new FriendException("Usuário incompatível com o usuário requerido na amizade: "+ invite.getId());
        }
    }

    public void refusedFriendInvite(String userId, String friendId) {
        FriendInvite invite = findFriendInvite(userId, friendId);

        // TODO ativar a validação do receiver quando implementar a autenticação
        //validateReceiverUser(invite, user);

        invite.setStatus(FriendStatus.REJECTED);
        friendInviteRepository.save(invite);
        log.refusedFriendInvite(invite.getId());
    }

    public void cancelFriendInvite(String userId, String friendId) {
        FriendInvite request = findFriendInvite(userId, friendId);
        request.getId().setReceiverUser(null);;
        request.getId().setSenderUser(null);
        friendInviteRepository.delete(request);
        log.cancelFriendInvite(userId,friendId);
    }

}
