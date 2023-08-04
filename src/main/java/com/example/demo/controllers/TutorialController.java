package com.example.demo.controllers;

import com.example.demo.Tutorial;
import com.example.demo.TutorialRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tutorials")
public class TutorialController {

    @Autowired
    private TutorialRepository tutorialRepository;

    @GetMapping
    public List<Tutorial> getAllTutorials() {
        return tutorialRepository.findAll();
    }

    @GetMapping("/{id}")
    public Tutorial getTutorialById(@PathVariable Long id) {
        return tutorialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tutorial not found with id: " + id));
    }



    @PostMapping
    public Tutorial createTutorial(@RequestBody Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    @PutMapping("/{id}")
    public Tutorial updateTutorial(@PathVariable Long id, @RequestBody Tutorial tutorialDetails) {
        Tutorial tutorial = tutorialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tutorial not found with id: " + id));

        tutorial.setTitle(tutorialDetails.getTitle());
        tutorial.setDescription(tutorialDetails.getDescription());
        tutorial.setPublished(tutorialDetails.isPublished());

        return tutorialRepository.save(tutorial);
    }

    @DeleteMapping("/{id}")
    public void deleteTutorial(@PathVariable Long id) {
        Tutorial tutorial = tutorialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tutorial not found with id: " + id));

        tutorialRepository.delete(tutorial);
    }
}
