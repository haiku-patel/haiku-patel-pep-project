package Service;
import DAO.MessageDAO;
import DAO.AccountDAO;
import Model.Message;

import java.util.List;

public class MessageService {
    private MessageDAO messageDAO;
    private AccountDAO accountDAO;
    
    public MessageService() {
        messageDAO = new MessageDAO();
        accountDAO = new AccountDAO();
    }
    
    public MessageService(MessageDAO messageDAO, AccountDAO accountDAO) {
        this.messageDAO = messageDAO;
        this.accountDAO = accountDAO;
    }

    public Message addMessage(Message message) {
        
        if (message.getMessage_text() == null || 
            message.getMessage_text().trim().isEmpty() || 
            message.getMessage_text().length() > 255) {
            return null;
        }
        
        if (!accountDAO.accountExists(message.getPosted_by())) {
            return null;
        }
        
        return messageDAO.insertMessage(message);
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int messageId) {
        return messageDAO.getMessageById(messageId);
    }

    public Message deleteMessage(int messageId) {
        return messageDAO.deleteMessage(messageId);
    }

    public Message updateMessage(int messageId, String newMessageText) {
        if (newMessageText == null || 
            newMessageText.trim().isEmpty() || 
            newMessageText.length() > 255) {
            return null;
        }
         
        if (messageDAO.getMessageById(messageId) == null) {
            return null;
        }
        
        return messageDAO.updateMessage(messageId, newMessageText);
    }

    public List<Message> getMessagesByUserId(int accountId) {
        return messageDAO.getMessagesByUserId(accountId);
    }
}
