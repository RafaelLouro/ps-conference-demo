package com.rafael.pluralsight.conferencedemo.controller;

import com.rafael.pluralsight.conferencedemo.model.Speaker;
import com.rafael.pluralsight.conferencedemo.repository.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speaker")
public class SpeakerController {

    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    public List<Speaker> list() {
        return speakerRepository.findAll();
    }

    @GetMapping("{id}")
    public Speaker get(@PathVariable Long id) {
        return speakerRepository.getOne(id);
    }

    @PostMapping
    public Speaker create(@RequestBody final Speaker speaker) {
        return speakerRepository.saveAndFlush(speaker);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable Long id) {
        // Os speakers vão bloquear ao tentar excluir, pois está sem cascade
        speakerRepository.deleteById(id);
    }

    @PutMapping(value = "{id}")
    public Speaker update(@PathVariable Long id, @RequestBody final Speaker speaker) {
        // Isso vai esperar todos os atributos (PUT), se for para atualizar somente um atributo, usar o PATCH
        //TODO fazer uma validação para ver se o objeto veio totalmente preenchido
        Speaker existingSpeaker = speakerRepository.getOne(id);
        BeanUtils.copyProperties(speaker, existingSpeaker, "id");
        return speakerRepository.saveAndFlush(existingSpeaker);
    }
}
