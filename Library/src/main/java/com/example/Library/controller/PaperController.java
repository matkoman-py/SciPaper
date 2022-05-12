package com.example.Library.controller;


import com.example.Library.domain.Paper;
import com.example.Library.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/library-service")
public class PaperController {

    @Autowired
    private PaperService paperService;

    @GetMapping(value="/listpapers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Paper>> listPapers(){
        return ResponseEntity.ok(paperService.listPapers());
    }

}
