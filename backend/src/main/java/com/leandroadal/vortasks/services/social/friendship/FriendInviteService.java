package com.leandroadal.vortasks.services.social.friendship;

import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.entities.social.friend.FriendInvite;
import com.leandroadal.vortasks.entities.social.friend.Friendship;
import com.leandroadal.vortasks.entities.social.friend.enums.FriendStatus;
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

    public FriendInvite findFriendInvite(String friendRequestId) {
        try {
            return friendInviteRepository.findById(friendRequestId)
                    .orElseThrow(() -> new ObjectNotFoundException(friendRequestId));
        } catch (ObjectNotFoundException e) {
            log.logFindFriendInvite(friendRequestId);
            throw e;
        }
    }

    public FriendInvite sendFriendRequest(FriendInvite newInvite) {
        User requestingUser = userService.findUserById(newInvite.getSenderUser().getId());
        User receiverUser = userService.findUserById(newInvite.getReceiverUser().getId());

        newInvite.setSenderUser(requestingUser);
        newInvite.setReceiverUser(receiverUser);
        newInvite.setRequestDate(Instant.now());
        newInvite.setStatus(FriendStatus.PENDING);

        requestingUser.getSenderFriendRequests().add(newInvite);
        receiverUser.getReceivedFriendRequests().add(newInvite);
        log.sendFriendRequest(newInvite.getId());
        return friendInviteRepository.save(newInvite);
    }

    public Friendship acceptFriendRequest(String userId, String friendRequestId) {
        FriendInvite invite = findFriendInvite(friendRequestId);
        User user = userService.findUserById(userId);

        validateStatus(invite);
        validateReceiverUser(invite, user);

        invite.setStatus(FriendStatus.ACCEPTED);

        friendInviteRepository.save(invite);
        log.acceptFriendRequest(invite.getId());
        return friendshipService.createFriendship(user, invite);
    }    

    private void validateStatus(FriendInvite invite) {
        if (invite.getStatus() == FriendStatus.REJECTED) {
            throw new FriendException("O convite de amizade com ID: " + invite.getId() + "' ja foi rejeitado");
        }
    }

    private void validateReceiverUser(FriendInvite invite, User user) {
        if (invite.getReceiverUser()!= user) {
            log.logFriendReceiverMismatch(invite.getId(), user.getId());
            throw new FriendException("Usuário incompatível com o usuário requerido na amizade: "+ invite.getId());
        }
    }

    public void refusedFriendInvite(String userId, String friendRequestId) {
        FriendInvite invite = findFriendInvite(friendRequestId);
        User user = userService.findUserById(userId);

        validateReceiverUser(invite, user);

        invite.setStatus(FriendStatus.REJECTED);
        friendInviteRepository.save(invite);
        log.refusedFriendInvite(invite.getId());
    }

    public void cancelFriendInvite(String friendRequestId) {
        FriendInvite request = findFriendInvite(friendRequestId);
        request.setReceiverUser(null);;
        request.setSenderUser(null);
        friendInviteRepository.delete(request);
        log.cancelFriendInvite(friendRequestId);
    }

}
