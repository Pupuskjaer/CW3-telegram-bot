package pro.sky.telegrambot.model;

import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity

public class NotificationTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "chat_id",nullable = false)
    private long chatId;
    @Column(name = "messageText",nullable = false)
    private String messageText;
    @Column(name = "time",nullable = false)
    private LocalDateTime time;

    public NotificationTask() {
    }

    public NotificationTask(
            long chatId,
            String messageText,
            LocalDateTime time) {
        this.chatId = chatId;
        this.messageText = messageText;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTask that = (NotificationTask) o;
        return id == that.id && chatId == that.chatId && Objects.equals(messageText, that.messageText) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, messageText, time);
    }

    @Override
    public String toString() {
        return "NotificationTask{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", messageText='" + messageText + '\'' +
                ", time=" + time +
                '}';
    }
}
