package com.example.SciPaperService.controller;

import com.example.SciPaperService.domain.Paper;
import com.example.SciPaperService.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paper-service")
public class PaperController {


    @Autowired
    private PaperService paperService;

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity handleException(IllegalArgumentException illegalArgumentException) {
        return new ResponseEntity(illegalArgumentException.getMessage(), HttpStatus.CONFLICT);
    }

    @PostMapping(value="/paper", produces = MediaType.APPLICATION_JSON_VALUE, consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Paper> createPaper(@RequestBody Paper paper){
        return ResponseEntity.ok(paperService.createPaper(paper));
    }

    @GetMapping(value="/listpapers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Paper>> listPapers(){
        return ResponseEntity.ok(paperService.listPapers());
    }
    @GetMapping(value="/publish/{id}/{username}")
    public ResponseEntity<String> publish(@PathVariable Integer id, @PathVariable String username){
        return ResponseEntity.ok(paperService.publish(id, username));
    }
}
