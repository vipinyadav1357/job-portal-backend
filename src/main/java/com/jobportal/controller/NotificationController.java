package com.jobportal.controller;

import com.jobportal.dtos.NotificationDto;
import com.jobportal.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    @GetMapping("/getAllNoti/{userId}")
    public ResponseEntity<List<NotificationDto>> getAllJob(@PathVariable Long userId)
    {
        return ResponseEntity.ok(notificationService.getAllUnReadNotification(userId));
    }
}
