package com.movies.controller;

import com.movies.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
public class ServiceController {

    private final ParticipantService participantService;

    @Autowired
    public ServiceController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping("/types")
    public String getServiceTypes() {
        return "Weight Management Service (1 package), Fitness Maintenance Service";
    }

    @GetMapping("/menu/{serviceType}")
    public String getMenuInformation(@PathVariable String serviceType) {
        if ("weight-management".equals(serviceType)) {
            return "Price: $50 per session, Schedule: Monday to Friday, Duration: 10 sessions, Exercise List: Push-ups, Sit-ups, Squats";
        } else if ("fitness-maintenance".equals(serviceType)) {
            return "Price: $40 per session, Schedule: Saturday and Sunday, Duration: 8 sessions, Exercise List: Yoga, Cardio, Strength Training";
        } else {
            return "Service type not found";
        }
    }

    @PostMapping("/subscribe")
    public String subscribeToService(@RequestBody String serviceType) {
        String participantEmail = "participant@example.com"; // Placeholder for participant email
        participantService.subscribeToService(participantEmail, serviceType);
        return "Subscribed to " + serviceType + " service";
    }

    @DeleteMapping("/cancel/{serviceType}")
    public String cancelSubscription(@PathVariable String serviceType) {
        String participantEmail = "participant@example.com"; // Placeholder for participant email
        participantService.cancelSubscription(participantEmail, serviceType);
        return "Subscription to " + serviceType + " service canceled";
    }

    @PostMapping("/extend/{serviceType}")
    public String extendSubscription(@PathVariable String serviceType, @RequestParam int additionalSessions) {
        String participantEmail = "participant@example.com"; // Placeholder for participant email
        participantService.extendSubscription(participantEmail, serviceType, additionalSessions);
        return "Subscription to " + serviceType + " service extended by " + additionalSessions + " sessions";
    }
}
