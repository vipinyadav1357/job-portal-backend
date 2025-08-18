package com.jobportal.service;

import com.jobportal.dtos.NotificationDto;
import com.jobportal.exception.JobPortalException;

import java.util.List;

public interface NotificationService {
    void sendNotification(NotificationDto notificationDto) throws JobPortalException;
    List<NotificationDto> getAllUnReadNotification(Long id) throws JobPortalException;

    boolean changeNotificationStatus(Long notiId) throws JobPortalException;;
}
