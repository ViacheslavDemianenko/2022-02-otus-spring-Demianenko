package ru.otus.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.core.io.ResourceLoader;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class InterviewServiceImplTest {
    @InjectMocks
    private QuestionServiceImpl questionService;
    @Mock
    private ResourceLoader resourceLoader;

    //Пока не получилось запустить тест, видимо не инициализируются какие-то библиотечные классы
    @Test
    void getQuestion(){
    }
}
