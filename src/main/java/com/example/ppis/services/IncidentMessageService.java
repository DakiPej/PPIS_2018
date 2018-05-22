package com.example.ppis.services;

import com.example.ppis.controllers.forms.MessageForm;
import com.example.ppis.dao.IncidentDAO;
import com.example.ppis.dao.IncidentMessageDAO;
import com.example.ppis.dao.RegisteredUserDAO;
import com.example.ppis.models.Incident;
import com.example.ppis.models.IncidentMessage;
import com.example.ppis.models.RegisteredUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class IncidentMessageService {

    @Autowired
    IncidentMessageDAO incidentMessageDAO;

    @Autowired
    RegisteredUserDAO registeredUserDAO;

    @Autowired
    IncidentDAO incidentDAO;

    final private String[] userTypes = { "Korisnik", "Odjel", "Admin" };

    public List<IncidentMessage> getIncidentMessagesByReceiverId(Long receiverId) {
        return incidentMessageDAO.getIncidentMessagesByReceiver(registeredUserDAO.one(receiverId));
    }

    public List<IncidentMessage> getIncidentMessagesBySenderId(Long senderId) {
        return incidentMessageDAO.getIncidentMessagesBySender(registeredUserDAO.one(senderId));
    }

    public List<IncidentMessage> getAllIncidentMessagesByUserId(Long id) {
        List<IncidentMessage> sentMessages = getIncidentMessagesBySenderId(id);
        List<IncidentMessage> receivedMessages = getIncidentMessagesByReceiverId(id);

        List<IncidentMessage> allMessages =
                Stream.concat(receivedMessages.stream(), sentMessages.stream()).collect(Collectors.toList());

        allMessages.sort(Comparator.comparing(IncidentMessage::getDate));
        return allMessages;
    }

    public IncidentMessage create(MessageForm messageForm) throws ServletException {
        return createWithUserTypes(messageForm, messageForm.getSenderRole(), messageForm.getReceiverRole());
    }

    private IncidentMessage createWithUserTypes(MessageForm messageForm, String senderType, String receiverType) throws ServletException {

        Incident incident = incidentDAO.one(messageForm.getRequestOrIncidentId());
        RegisteredUser sender = registeredUserDAO.findUserByUsername(messageForm.getUsername());
        RegisteredUser receiver = null;
        String message = messageForm.getMessage();

        if (senderType.equals("KORISNIK") && receiverType.equals("ODJEL")) {
            receiver = incident.getResolverUser();

            if (incident.getRegisteredUser().getId() != sender.getId())
                throw new ServletException("Zahtjev ne pripada korisniku");
        }
        else if (senderType.equals("ODJEL") && receiverType.equals("KORISNIK")) {
            receiver = incident.getRegisteredUser();

            if (incident.getResolverUser().getId() != sender.getId())
                throw new ServletException("Zahtjev ne pripada odgovornoj osobi");
        }
        else if (senderType.equals("ODJEL") && receiverType.equals("ADMIN")) {
            receiver = incident.getAdmin();

            if (incident.getResolverUser().getId() != sender.getId())
                throw new ServletException("Zahtjev ne pripada odgovornoj osobi");
        }
        else if (senderType.equals("ADMIN") && receiverType.equals("ODJEL")) {
            receiver = incident.getResolverUser();

            if (incident.getAdmin().getId() != sender.getId())
                throw new ServletException("Zahtjev ne pripada administratoru");
        }

        return incidentMessageDAO.create(new IncidentMessage(sender, receiver, incident, message));
    }
}
