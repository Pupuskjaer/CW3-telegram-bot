package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.service.CommandService;
import pro.sky.telegrambot.service.NotTaskService;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);



    @Autowired
    private TelegramBot telegramBot;

    @Autowired
    private CommandService commandService;

    @Autowired
    private NotTaskService notTaskService;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    private void sendMessage(long chatId, String messageText) {
        SendMessage message = new SendMessage(chatId, messageText);
        SendResponse response = telegramBot.execute(message);
        if (!response.isOk()) {
            logger.error("Error sending message {}", response.description());
        }
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            // Process your updates here
            Message message = update.message();
            Long chatId = message.chat().id();
            String userText = message.text();
            if (!(userText.isEmpty()||userText.isBlank())) {
                if (commandService.checkIsCommand(userText)) {
                    sendMessage(chatId,commandService.getValueOfCommand(userText));
                }
                else {
                    sendMessage(chatId,notTaskService.saveNotificationTask(chatId, userText));
                }
            }
            else {
                sendMessage(chatId,"Введите команду или уведомление правильного формата");
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }



}
