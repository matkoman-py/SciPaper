package com.example.Library.service;

import com.example.Library.domain.Message;
import com.example.Library.domain.Paper;
import com.example.Library.repository.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperService {

    @Autowired
    private PaperRepository paperRepository;


    public List<Paper> listPapers() {
        return paperRepository.findAll();
    }

    public void paperPublished(Message message){
        System.out.println("USAO SAM OVDE");

        Paper paper = new Paper(message.getId(), message.getTitle(), message.getAuthor());
        paperRepository.save(paper);
    }
}
