package com.leandroadal.vortasks.services.social.friendship;

import java.time.Instant;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.entities.social.friend.FriendInvite;
import com.leandroadal.vortasks.entities.social.friend.Friendship;
import com.leandroadal.vortasks.entities.user.User;
import com.leandroadal.vortasks.repositories.social.FriendshipRepository;
import com.leandroadal.vortasks.services.exception.ObjectNotFoundException;

@Service
public class FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private LogFriendService log;

    public List<Friendship> getFriendshipsByUser(User user) {
        return friendshipRepository.findFriendshipsByUser(user);
    }

    public Friendship findFriendshipById(String id) {
        try {
            return friendshipRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
        } catch (ObjectNotFoundException e) {
            log.findFriendshipById(id);
            throw e;
        }

    }

    public Friendship createFriendship(FriendInvite request) {
        User sender = request.getId().getSenderUser();
        User receiver = request.getId().getReceiverUser();
        Friendship friendship = new Friendship();
        friendship.setUsers(Set.of(sender, receiver));
        friendship.setFriendshipDate(Instant.now());

        friendshipRepository.save(friendship);

        linkUserToFriendship(sender, receiver, friendship);
        log.createFriendship(friendship.getId());
        return friendship;
    }

    private void linkUserToFriendship(User sender, User receiver, Friendship friendship) {
        sender.getFriendships().add(friendship);
        receiver.getFriendships().add(friendship);
    }

    public void deleteFriendship(String friendshipId) {
        Friendship friendship = findFriendshipById(friendshipId);
        friendship.getUsers().forEach(user -> user.getFriendships()
                                                    .remove(friendship));
        friendship.getUsers().clear();

        friendshipRepository.delete(friendship);
        log.deleteFriendship(friendshipId);
    }

}
