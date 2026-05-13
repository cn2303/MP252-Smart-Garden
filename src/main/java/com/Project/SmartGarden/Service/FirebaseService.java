package com.Project.SmartGarden.Service;

import com.Project.SmartGarden.Entity.Phone;
import com.Project.SmartGarden.Entity.SensorData;
import com.Project.SmartGarden.Repository.PhoneRepository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FirebaseService {
    private final PhoneRepository phoneRepository;
    @Autowired
    public FirebaseService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }
    public void sendDataToFirebase(Integer userId, SensorData sensorData) {
        try {
            Map<String, Object> firebaseData = new HashMap<>();
            firebaseData.put("id", sensorData.getId());
            firebaseData.put("deviceId", sensorData.getDeviceId());
            firebaseData.put("value", sensorData.getValue());
            firebaseData.put("type", sensorData.getType());
            firebaseData.put("unit",sensorData.getUnit());
            firebaseData.put("createdAt", sensorData.getCreatedAt().toString());

            DatabaseReference ref = FirebaseDatabase.getInstance()
                    .getReference("updates/" + userId);

            ref.setValueAsync(firebaseData);

            //System.out.println("Firebase write OK");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendNotification(Integer userId, String title, String body) {
        List<Phone> phones = this.phoneRepository.findByUserId(userId);
        for (Phone phone : phones) {
            sendPushNotification(phone.getToken(), title, body);
        }
    }
    public void sendPushNotification(String userToken, String title, String body) {
        Message message = Message.builder()
                .setToken(userToken)
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .build();

        try {
            FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            System.out.println("Error sending push notification");
        }
    }
}
