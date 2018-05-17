package com.example.ppis.services;

import com.example.ppis.controllers.forms.RequestMessageForm;
import com.example.ppis.dao.RegisteredUserDAO;
import com.example.ppis.dao.RequestDAO;
import com.example.ppis.dao.RequestMessageDAO;
import com.example.ppis.models.RegisteredUser;
import com.example.ppis.models.Request;
import com.example.ppis.models.RequestMessage;
import com.example.ppis.models.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RequestMessageService {

    @Autowired
    RequestMessageDAO requestMessageDAO;

    @Autowired
    RegisteredUserDAO registeredUserDAO;

    @Autowired
    RequestDAO requestDAO;

    final private String[] userTypes = { "Korisnik", "Odjel", "Admin" };

    public List<RequestMessage> getRequestMessagesByReceiverId(Long receiverId) {
        return requestMessageDAO.getRequestMessagesByReceiver(registeredUserDAO.one(receiverId));
    }

    public List<RequestMessage> getRequestMessagesBySenderId(Long senderId) {
        return requestMessageDAO.getRequestMessagesBySender(registeredUserDAO.one(senderId));
    }

    public List<RequestMessage> getAllRequestMessagesByUserId(Long id) {
        List<RequestMessage> sentMessages = getRequestMessagesBySenderId(id);
        List<RequestMessage> receivedMessages = getRequestMessagesByReceiverId(id);

        List<RequestMessage> allMessages =
                Stream.concat(receivedMessages.stream(), sentMessages.stream()).collect(Collectors.toList());

        allMessages.sort(Comparator.comparing(RequestMessage::getDate));
        return allMessages;
    }

    public RequestMessage create(RequestMessageForm requestMessageForm) throws ServletException {
        return createWithUserTypes(requestMessageForm, requestMessageForm.getFromRole(), requestMessageForm.getToRole());
    }

    private RequestMessage createWithUserTypes(RequestMessageForm requestMessageForm, String senderType, String receiverType) throws ServletException {

        Request request = requestDAO.one(requestMessageForm.getRequestId());
        RegisteredUser sender = registeredUserDAO.findUserByUsername(requestMessageForm.getUsername());
        RegisteredUser receiver = null;
        String message = requestMessageForm.getMessage();

        if (senderType.equals("KORISNIK") && receiverType.equals("ODJEL")) {
            receiver = request.getResolverUser();

            if (request.getRegisteredUser().getId() != sender.getId())
                throw new ServletException("Zahtjev ne pripada korisniku");
        }
        else if (senderType.equals("ODJEL") && receiverType.equals("KORISNIK")) {
            receiver = request.getRegisteredUser();

            if (request.getResolverUser().getId() != sender.getId())
                throw new ServletException("Zahtjev ne pripada odgovornoj osobi");
        }
        else if (senderType.equals("ODJEL") && receiverType.equals("ADMIN")) {
            receiver = request.getAdmin();

            if (request.getResolverUser().getId() != sender.getId())
                throw new ServletException("Zahtjev ne pripada odgovornoj osobi");
        }
        else if (senderType.equals("ADMIN") && receiverType.equals("ODJEL")) {
            receiver = request.getResolverUser();

            if (request.getAdmin().getId() != sender.getId())
                throw new ServletException("Zahtjev ne pripada administratoru");
        }

        return requestMessageDAO.create(new RequestMessage(sender, receiver, request, message));
    }
}
