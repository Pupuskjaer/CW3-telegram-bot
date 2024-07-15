package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Commands;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

@Service
public class CommandService {

    private final Commands commands;
    private final NotificationTaskRepository taskRepository;

    public CommandService(Commands commands, NotificationTaskRepository taskRepository) {
        this.commands = commands;
        this.taskRepository = taskRepository;
    }

}
