/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portBackEnd.vi.controller;

import com.portBackEnd.vi.dto.dtohys;
import com.portBackEnd.vi.entity.hys;
import com.portBackEnd.vi.security.Controller.Mensaje;
import com.portBackEnd.vi.security.Service.Shys;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author valen
 */
@RestController
@CrossOrigin(origins = "https://portfolio-frontend-vi.web.app/")
@RequestMapping("/skill")
public class CHys {

    @Autowired
    Shys shys;

    @GetMapping("/lista")
    public ResponseEntity<List<hys>> list() {
        List<hys> list = shys.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id]")
    public ResponseEntity<hys> getById(@PathVariable("id") int id) {
        if (!shys.existsById(id)) {
            return new ResponseEntity(new Mensaje("El Id no Existe"), HttpStatus.BAD_REQUEST);
        }
        hys hYs = shys.getOne(id).get();
        return new ResponseEntity(hYs, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtohys dtohYs) {
        if (StringUtils.isBlank(dtohYs.getNombre())) {
            return new ResponseEntity(new Mensaje("Ingrese un nombre"), HttpStatus.BAD_REQUEST);
        }
        if (shys.existsByNombre(dtohYs.getNombre())) {
            return new ResponseEntity(new Mensaje("Existe una skill con el mismo nombre"), HttpStatus.BAD_REQUEST);
        }
        hys hYs = new hys(dtohYs.getNombre(), dtohYs.getPorcentaje());
        shys.save(hYs);
        return new ResponseEntity(new Mensaje("Skill agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtohys dtohYs) {
        if (!shys.existsById(id)) {
            return new ResponseEntity(new Mensaje("El Id no Existe"), HttpStatus.BAD_REQUEST);
        }
        if (shys.existsByNombre(dtohYs.getNombre()) && shys.getByNombre(dtohYs.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtohYs.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre esta vacio"), HttpStatus.BAD_REQUEST);
        }

        hys hYs = shys.getOne(id).get();
        hYs.setNombre(dtohYs.getNombre());
        hYs.setPorcentaje(dtohYs.getPorcentaje());

        shys.save(hYs);
        return new ResponseEntity(new Mensaje("Skill actualizada"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!shys.existsById(id)) {
            return new ResponseEntity(new Mensaje("El Id no Existe"), HttpStatus.BAD_REQUEST);
        }
        shys.delete(id);

        return new ResponseEntity(new Mensaje("Skill eliminada"), HttpStatus.OK);
    }
}
