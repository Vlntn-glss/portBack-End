/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portBackEnd.vi.service;

import com.portBackEnd.vi.entity.Persona;
import com.portBackEnd.vi.respository.IPersonaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author valen
 */
@Service
@Transactional
public class ImpPersonaService {

    @Autowired
    IPersonaRepository ipersonaRepository;

    public List<Persona> list() {
        return ipersonaRepository.findAll();
    }

    public Optional<Persona> getOne(int id) {
        return ipersonaRepository.findById(id);
    }

    public Optional<Persona> getByNombre(String nombre) {
        return ipersonaRepository.findByNombre(nombre);
    }

    public void save(Persona persona) {
        ipersonaRepository.save(persona);
    }

    public void delete(int id) {
        ipersonaRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return ipersonaRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return ipersonaRepository.existsByNombre(nombre);
    }

}
