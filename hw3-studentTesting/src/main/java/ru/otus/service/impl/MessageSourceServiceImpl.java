package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.configuration.ApplicationConfiguration;
import ru.otus.service.MessageSourceService;

@Service
@RequiredArgsConstructor
public class MessageSourceServiceImpl implements MessageSourceService {
    private final MessageSource messageSource;
    private final ApplicationConfiguration configuration;

    @Override
    public String getMessage(String message){
        return messageSource.getMessage(message, null, configuration.getLocale());
    }
}
