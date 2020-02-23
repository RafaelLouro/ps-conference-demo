package com.rafael.pluralsight.conferencedemo.controller;

import com.rafael.pluralsight.conferencedemo.model.Session;
import com.rafael.pluralsight.conferencedemo.repository.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/session")
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> list() {
        return sessionRepository.findAll();
    }

    @GetMapping(value = "{id}")
    public Session get(@PathVariable Long id) {
        return sessionRepository.getOne(id);
    }

    @PostMapping
    public Session create(@RequestBody final Session session) {
        return sessionRepository.saveAndFlush(session);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable Long id) {
        // Os speakers vão bloquear ao tentar excluir, pois está sem cascade
        sessionRepository.deleteById(id);
    }

    @PutMapping(value = "{id}")
    public Session update(@PathVariable Long id, @RequestBody final Session session) {
        // Isso vai esperar todos os atributos (PUT), se for para atualizar somente um atributo, usar o PATCH
        //TODO fazer uma validação para ver se o objeto veio totalmente preenchido
        Session existingSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(session, existingSession, "id");
        return sessionRepository.saveAndFlush(existingSession);
    }
}
