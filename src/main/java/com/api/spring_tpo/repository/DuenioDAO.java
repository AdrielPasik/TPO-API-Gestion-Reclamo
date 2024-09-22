package com.api.spring_tpo.repository;

import com.api.spring_tpo.entity.Duenio;
import com.api.spring_tpo.repository.DuenioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DuenioDAO {

    private static DuenioDAO instancia;

    @Autowired
    private DuenioRepository duenioRepository;

    // Constructor privado para el patrón Singleton
    private DuenioDAO() {}

    // Método para obtener la instancia del DAO
    public static DuenioDAO getInstancia() {
        if (instancia == null) {
            instancia = new DuenioDAO();
        }
        return instancia;
    }

    // Método para obtener todos los dueños
    public List<Duenio> getAllDuenios() {
        return duenioRepository.findAll();
    }

    // Método para obtener un dueño por su ID
    public Optional<Duenio> getDuenioById(int id) {
        return duenioRepository.findById(id);
    }

    // Método para guardar un nuevo dueño
    public Duenio guardarDuenio(Duenio duenio) {
        return duenioRepository.save(duenio);
    }

    // Método para actualizar un dueño
    public Duenio actualizarDuenio(Duenio duenio) {
        if (duenio.getId() > 0 && duenioRepository.existsById(duenio.getId())) {
            return duenioRepository.save(duenio);
        }
        return null;
    }

    // Método para eliminar un dueño por su ID
    public void eliminarDuenio(int id) {
        duenioRepository.deleteById(id);
    }

    // Puedes agregar métodos personalizados si es necesario
}
