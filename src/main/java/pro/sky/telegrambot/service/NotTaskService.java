package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NotTaskService {
    private TelegramBot telegramBot;
    private final NotificationTaskRepository taskRepository;
    Pattern datePattern = Pattern.compile("([0-9\\.\\:\\s]{16})(\\s)([\\W+]+)");
    String dateTime;
    String notification;

    public NotTaskService(NotificationTaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public String saveNotificationTask(Long chatId, String string) {
        Matcher matcher = datePattern.matcher(string);
        if (matcher.matches()) {
            dateTime = matcher.group(1);
            notification = matcher.group(3);
            LocalDateTime dateTimeToNot = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            NotificationTask notificationTask = new NotificationTask(chatId, notification, dateTimeToNot);
            taskRepository.save(notificationTask);
            return "Напоминание добавлено!";
        }
        return "Ошибка при сохранении напоминания";
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    public void task() {
        taskRepository.findAllByLocalDateTime(
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)
        ).forEach(notificationTask -> {
            telegramBot.execute(
                    new SendMessage(notificationTask.getChatId(), "Напоминие о событии: " + notificationTask.getMessageText()));
            taskRepository.delete(notificationTask);
        });
    }
}
