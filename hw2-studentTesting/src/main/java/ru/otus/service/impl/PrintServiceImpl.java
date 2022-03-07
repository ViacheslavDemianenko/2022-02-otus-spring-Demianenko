package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.InterviewDao;
import ru.otus.model.Interview;
import ru.otus.service.PrintService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PrintServiceImpl implements PrintService {

    private final InterviewDao interviewDao;

    @Override
    public void printInterviewList(){
        List<Interview> interviews = interviewDao.getInterviewList();
        if(!interviews.isEmpty()){
            interviews.forEach(q -> System.out.println(q.toString()));
        }
    }
}
