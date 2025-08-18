package com.jobportal.service.impl;

import com.jobportal.dtos.NotificationDto;
import com.jobportal.entity.Notification;
import com.jobportal.enums.NotificationStatus;
import com.jobportal.exception.JobPortalException;
import com.jobportal.mapper.NotificationMapper;
import com.jobportal.repository.NotificationRepository;
import com.jobportal.service.NotificationService;
import com.jobportal.utils.SequenceUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("notificationService")
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    @Override
    public void sendNotification(NotificationDto notificationDto) throws JobPortalException {
    notificationDto.setId(SequenceUtilities.getNextSequence("notification"));
    notificationDto.setSentTime(LocalDateTime.now());
    notificationDto.setStatus(NotificationStatus.UNREAD);
    notificationRepository.save(notificationMapper.toNotification(notificationDto));
    }

    @Override
    public List<NotificationDto> getAllUnReadNotification(Long id) throws JobPortalException {
        return notificationRepository.findByReceiverIdAndStatus(id, NotificationStatus.UNREAD).stream().map(notificationMapper::toNotificationDto).toList();
    }

    @Override
    public boolean changeNotificationStatus(Long notiId) throws JobPortalException {
        var notification =notificationRepository.findById(notiId).orElseThrow();
        notification.setStatus(NotificationStatus.READ);
        notificationRepository.save(notification);
        return notification.getStatus().equals(NotificationStatus.READ);
    }
}
