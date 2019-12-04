package hu.unideb.RFTDatingSite.Model;

public class UserMessage {
    String username;
    String message;
    String userId;

    MessageType messageType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "username='" + username + '\'' +
                ", message='" + message + '\'' +
                ", userId='" + userId + '\'' +
                ", messageType=" + messageType +
                '}';
    }
}
