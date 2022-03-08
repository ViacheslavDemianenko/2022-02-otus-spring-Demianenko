package ru.otus.dao;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class InterviewDaoTest {
    @InjectMocks
    private InterviewDao interviewDao;
    @Mock
    private ResourceLoader resourceLoader;
    @Mock
    private Resource resource;

    private static final String CSV_RELATIVE_PATH = "classpath:Questions.csv";
    @BeforeEach
    void setUp(){
        ReflectionTestUtils.setField(interviewDao,"relativePath", CSV_RELATIVE_PATH);
    }

    @Test
    @SneakyThrows
    void getInterviewList(){
        //given
        var csvInputStream = new FileInputStream(CSV_RELATIVE_PATH);
        when(resourceLoader.getResource(anyString())).thenReturn(resource);
        when(resource.getInputStream()).thenReturn(csvInputStream);

        //when
        var interviewList = interviewDao.getInterviewList();

        //then
        assertNotNull(interviewList);
        assertFalse(interviewList.isEmpty());
    }
}
