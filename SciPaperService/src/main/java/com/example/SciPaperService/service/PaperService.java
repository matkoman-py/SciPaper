package com.example.SciPaperService.service;

import com.example.SciPaperService.domain.Message;
import com.example.SciPaperService.domain.Paper;
import com.example.SciPaperService.domain.Section;
import com.example.SciPaperService.repository.PaperRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaperService {

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RabbitTemplate template;

    public Paper createPaper(Paper paper) {
        Boolean isLoggedIn = restTemplate
                .getForObject("http://USER-SERVICE/user-service/isloggedin/" + paper.getAuthor(), Boolean.class);

        if(!isLoggedIn){
            throw new IllegalArgumentException("User with username: " + paper.getAuthor() + "is not logged in!");
        }

        String authorFullName = restTemplate
                .getForObject("http://USER-SERVICE/user-service/getname/" + paper.getAuthor(), String.class);

        paper.setAuthor(authorFullName);
        paperRepository.save(paper);
        return paper;
    }

    public List<Paper> listPapers() {
        return paperRepository.findAll();
    }

    public String publish(Integer id, String username) {
        Boolean isLoggedIn = restTemplate
                .getForObject("http://USER-SERVICE/user-service/isloggedin/" + username, Boolean.class);

        if(!isLoggedIn){
            throw new IllegalArgumentException("User with username: " + username + "is not logged in!");
        }

        Optional<Paper> paperOpt = paperRepository.findById(id);
        if(!paperOpt.isPresent()){
            throw new IllegalArgumentException("Paper with id: " + id + "doesn't exist!");
        }

        Paper paper = paperOpt.get();

        Message msg = new Message(id, paper.getTitle(), paper.getAuthor());
        template.convertAndSend("message_exchange", "routingKey", msg);

        return "Success!";
    }
}
