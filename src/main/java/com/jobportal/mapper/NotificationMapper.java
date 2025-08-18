package com.jobportal.mapper;

import com.jobportal.dtos.NotificationDto;
import com.jobportal.entity.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationMapper {
    public  Notification toNotification(NotificationDto dto) {
        if (dto == null) {
            return null;
        }

        return Notification.builder()
                .id(dto.getId())
                .senderId(dto.getSenderId())
                .receiverId(dto.getReceiverId())
                .action(dto.getAction())
                .route(dto.getRoute())
                .msg(dto.getMsg())
                .status(dto.getStatus())
                .sentTime(dto.getSentTime())
                .build();
    }

    public  NotificationDto toNotificationDto(Notification entity) {
        if (entity == null) {
            return null;
        }

        return NotificationDto.builder()
                .id(entity.getId())
                .senderId(entity.getSenderId())
                .receiverId(entity.getReceiverId())
                .action(entity.getAction())
                .route(entity.getRoute())
                .msg(entity.getMsg())
                .status(entity.getStatus())
                .sentTime(entity.getSentTime())
                .build();
    }

}
