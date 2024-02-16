package com.movies.service;

public interface ParticipantService {
    void subscribeToService(String participantEmail, String serviceType);
    void cancelSubscription(String participantEmail, String serviceType);
    void extendSubscription(String participantEmail, String serviceType, int additionalSessions);
}
