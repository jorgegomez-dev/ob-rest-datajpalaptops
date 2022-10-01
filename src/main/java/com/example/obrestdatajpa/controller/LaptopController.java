package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Laptop;
import com.example.obrestdatajpa.repository.LaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    private final Logger log = LoggerFactory.getLogger(LaptopController.class);

    // Atributos
    private LaptopRepository laptopRepository;

    // Constructores
    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    // CRUD sobre entidad Laptop

    /**
     * http://localhost:8080/api/laptops
     * @return
     */
    // Buscar todas las Laptops (lista de laptops)
    @GetMapping("/api/laptops")
    public List<Laptop> findAll() {
        //recuperar y devolver laptops de base de datos

        return laptopRepository.findAll();
    }


    /**
     * Request
     * Response
     * @param id
     * @return
     */
    // Buscar una sola laptop en base de datos segun su id
    @GetMapping("/api/laptops/{id}")
    public ResponseEntity <Laptop> findOneById(@PathVariable Long id) {

        Optional<Laptop> laptopOpt = laptopRepository.findById(id);
        // Opcion 1
        if(laptopOpt.isPresent())
            return ResponseEntity.ok(laptopOpt.get());
        else
            return ResponseEntity.notFound().build();
    }


    /**
     * Crear una nueva laptop en base de datos
     * Metodo POST, no colisiona con metodo findAll(), porque son diferentes metodos HTTP: GET vs. POST
     *
     * @param laptop
     * @param headers
     * @return
     */
    @PostMapping("/api/laptops")
    public ResponseEntity<Object> create(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers) {
        System.out.println(headers.get("User-Agent"));

        // guardar la laptop recibida por parametros en la base de datos
        if(laptop.getId()!= null){ //quiere decir que existe el id, entonces no hay que crearlo sino actualizarlo
            log.warn("trying to create a laptop with id");
            System.out.println("trying to create a laptop with id");
            return ResponseEntity.badRequest().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result); // la laptop devuelta tiene una clave primaria (id)
    }

    /**
     * Actualizar una laptop existente en base de datos
     */

    @PutMapping("/api/laptops")
    public ResponseEntity<Object> update(@RequestBody Laptop laptop){
        if(laptop.getId() == null){
            log.warn("trying to update a non existent laptop"); //si no tiene id, quiere decir que no existe
            return ResponseEntity.badRequest().build();
        }
        if(!laptopRepository.existsById(laptop.getId())){
            log.warn("trying to update a non existent laptop"); //si no tiene id, quiere decir que no existe
            return ResponseEntity.notFound().build();
        }

        //El proceso de actualizacion
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result); // la laptop devuelta tiene una clave primaria (id)
    }

    // Borrar una laptop en base de datos
    @DeleteMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){

        if(!laptopRepository.existsById(id)){
            log.warn("tryin to delete a non existent laptop");
            return ResponseEntity.notFound().build();
        }

        laptopRepository.deleteById(id);

        return ResponseEntity.noContent().build(); // Devuelve mensaje 204 No Content a la consulta, una vez borra la laptop por id

    }

    // Borrar todas las laptops (o vaciar la base de datos)
    @DeleteMapping("/api/laptops")
    public ResponseEntity<Laptop> deleteAll(){
        log.info("REST Request for delete all laptops");
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build(); // Devuelve mensaje 204 No Content a la consulta, una vez borra todas las laptops
    }

}
