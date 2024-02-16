package com.movies.service;

import com.movies.entity.Participant;
import com.movies.entity.Subscription;
import com.movies.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;

    @Autowired
    public ParticipantServiceImpl(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ParticipantRepository participantRepository;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationMs}")
    private long jwtExpirationMs;

    private static final int INACTIVITY_THRESHOLD_MINUTES = 15; // Define your inactivity threshold
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 10; // Define the length of the generated password

    @Override
    public Participant register(Participant participant, CreditCardInfo creditCardInfo) {
        // Save participant and credit card info
        participant.setCreditCardInfo(creditCardInfo);
        return participantRepository.save(participant);
    }

    @Override
    public void resetPassword(String email) {
        Participant participant = participantRepository.findByEmail(email);
        if (participant != null) {
            // Generate new password
            String newPassword = generateNewPassword();
            // Update participant's password
            participant.setPassword(newPassword);
            participantRepository.save(participant);
            // Send reset password email
            sendResetPasswordEmail(email, newPassword);
        }
    }

    @Override
    public void sendResetPasswordEmail(String email, String newPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset Request");
        message.setText("Your new password is: " + newPassword);

        javaMailSender.send(message);
    }

    @Override
    public void logout(String email) {
        SecurityContextHolder.clearContext();
    }

    @Override
    public void automaticLogout(String email) {
        // Placeholder: Check last activity time and logout if inactive for a certain period
        // This method is called automatically based on a scheduler or timer

        Participant participant = participantRepository.findByEmail(email);
        if (participant != null) {
            LocalDateTime lastActivityTime = participant.getLastActivityTime();
            if (lastActivityTime != null) {
                LocalDateTime currentTime = LocalDateTime.now();
                if (currentTime.minusMinutes(INACTIVITY_THRESHOLD_MINUTES).isAfter(lastActivityTime)) {
                    logout(email); // Automatically log out if inactive for the defined threshold
                }
            }
        }
    }

    @Override
    public void refreshSession(String email) {
        // Placeholder: Implement logic to refresh the participant's session/token
        // For example, if using JWT token-based authentication, you can generate a new JWT token with an updated expiration time

        // Generate a new JWT token
        String refreshedToken = generateJwtToken(email);

        // Set the refreshed token as the authentication token for the participant's session
        SecurityContextHolder.getContext().setAuthentication(new JwtAuthenticationToken(refreshedToken));
    }

    @Override
    public String checkRegistrationStatus(String email) {
        Participant participant = participantRepository.findByEmail(email);
        if (participant != null) {
            return "REGISTERED";
        } else {
            return "NOT REGISTERED";
        }
    }

    // Method to generate a new random password
    private String generateNewPassword() {
        StringBuilder newPassword = new StringBuilder();
        Random random = new SecureRandom();

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            newPassword.append(CHARACTERS.charAt(randomIndex));
        }

        return newPassword.toString();
    }

    // Method to generate a new JWT token with an updated expiration time
    private String generateJwtToken(String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    @Override
    public void subscribeToService(String participantEmail, String serviceType) {
        // Implement logic to subscribe participant to the service
        Participant participant = participantRepository.findByEmail(participantEmail);
        if (participant != null) {
            Subscription subscription = new Subscription(serviceType);
            participant.addSubscription(subscription);
            participantRepository.save(participant);
            System.out.println("Subscribed participant " + participantEmail + " to " + serviceType + " service.");
        } else {
            System.out.println("Participant with email " + participantEmail + " not found.");
        }
    }

    @Override
    public void cancelSubscription(String participantEmail, String serviceType) {
        // Implement logic to cancel participant's subscription to the service
        Participant participant = participantRepository.findByEmail(participantEmail);
        if (participant != null) {
            participant.removeSubscription(serviceType);
            participantRepository.save(participant);
            System.out.println("Canceled subscription of participant " + participantEmail + " to " + serviceType + " service.");
        } else {
            System.out.println("Participant with email " + participantEmail + " not found.");
        }
    }

    @Override
    public void extendSubscription(String participantEmail, String serviceType, int additionalSessions) {
        // Implement logic to extend participant's subscription to the service
        Participant participant = participantRepository.findByEmail(participantEmail);
        if (participant != null) {
            Subscription subscription = participant.getSubscription(serviceType);
            if (subscription != null) {
                subscription.extendDuration(additionalSessions);
                participantRepository.save(participant);
                System.out.println("Extended subscription of participant " + participantEmail + " to " + serviceType + " service by " + additionalSessions + " sessions.");
            } else {
                System.out.println("Participant " + participantEmail + " is not subscribed to " + serviceType + " service.");
            }
        } else {
            System.out.println("Participant with email " + participantEmail + " not found.");
        }
    }
}
