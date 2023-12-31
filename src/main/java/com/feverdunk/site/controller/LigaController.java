package com.feverdunk.site.controller;

import com.feverdunk.site.exceptions.ObjectNotFoundException;
import com.feverdunk.site.models.Liga;
import com.feverdunk.site.service.LigaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/liga")
public class LigaController {

    private final LigaService ligaService;

    @Autowired
    public LigaController(LigaService ligaService){ this.ligaService = ligaService;}

    @GetMapping
    public List<Liga> getLiga(){ return ligaService.getLiga(); }

    @GetMapping("/{id}")
    public Liga getById(Long id){ return ligaService.findById(id);}

    @GetMapping("/time/{id}")
    public ResponseEntity<List<Liga>> getLigaByTimeId(@PathVariable Long id){
        List<Liga> ligas = ligaService.findAllByTimeId(id);
        return ResponseEntity.ok(ligas);
    }

    @GetMapping("/manager/{id}")
    public ResponseEntity<List<Liga>> getLigaByManagerId(@PathVariable Long id){
        List<Liga> ligas = ligaService.findAllByManagerId(id);
        return ResponseEntity.ok(ligas);
    }

    @PostMapping
    public ResponseEntity<Liga> post(@RequestBody @Validated Liga liga){ return criarLiga(liga);}

    @PutMapping
    public ResponseEntity<Liga> put(@RequestBody @Validated Liga liga){
        try{
            ligaService.findById(liga.getId());

            Liga ligaAtualizada = ligaService.update(liga);
            return ResponseEntity.ok(ligaAtualizada);
        }catch(ObjectNotFoundException e){

            return criarLiga(liga);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Liga> delete(@PathVariable Long id){
        ligaService.delete(id);

        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Liga> criarLiga(Liga liga) {
        Liga ligaCriada = ligaService.create(liga);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("{/id}").buildAndExpand(ligaCriada.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
