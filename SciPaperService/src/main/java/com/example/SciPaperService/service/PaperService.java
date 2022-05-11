package com.example.SciPaperService.service;

import com.example.SciPaperService.domain.Paper;
import com.example.SciPaperService.domain.Section;
import com.example.SciPaperService.repository.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaperService {

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Paper createPaper(Paper paper) {
        Boolean isLoggedIn = restTemplate
                .getForObject("http://localhost:9001/user-service/isloggedin/" + paper.getAuthor(), Boolean.class);

        if(!isLoggedIn){
            throw new IllegalArgumentException("User with username: " + paper.getAuthor() + "is not logged in!");
        }

        String authorFullName = restTemplate
                .getForObject("http://localhost:9001/user-service/getname/" + paper.getAuthor(), String.class);

        paper.setAuthor(authorFullName);
        paperRepository.save(paper);
        return paper;
    }

    public List<Paper> listPapers() {
        return paperRepository.findAll();
    }

    public String publish(String id, String username) {
//        if(!userClient.isLoggedIn(username)){
//            throw new IllegalArgumentException("User with username: " + username + "is not logged in!");
//        }
//
//        Optional<Paper> paperOpt = paperRepository.findById(id);
//        if(!paperOpt.isPresent()){
//            throw new IllegalArgumentException("Paper with id: " + id + "doesn't exist!");
//        }
//
//        Paper paper = paperOpt.get();
//
//        com.silvera.SciPaper.messages.scipapermsggroup.SciPaperPublished msg = new com.silvera.SciPaper.messages.scipapermsggroup.SciPaperPublished();
//        msg.setId(id);
//        msg.setTitle(paper.getTitle());
//        msg.setAuthor(paper.getAuthor());
//
//        scipapermsggroupSciPaperPublishedKafkaTemplate.send("EV_PAPER_PUBLISHED_CHANNEL", msg);

        return "Success!";
    }
}
