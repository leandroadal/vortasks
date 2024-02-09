package com.leandroadal.vortasks.entities.social.dto;

import com.leandroadal.vortasks.entities.social.friend.FriendInvite;

public record FriendInviteResponseDTO(
    String friendInviteId,
    String userId,
    String friendUserId
) {
    public FriendInviteResponseDTO(FriendInvite data) {
        this(
            data.getId(),
            data.getSenderUser().getId(),
            data.getReceiverUser().getId());
        
    }
    
}
