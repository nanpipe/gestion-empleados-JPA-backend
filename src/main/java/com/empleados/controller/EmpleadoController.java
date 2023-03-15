package com.empleados.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.empleados.exceptions.ResourceNotFoundException;
import com.empleados.model.Empleado;
import com.empleados.repository.IEmpleadoRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpleadoController {

	@Autowired
	private IEmpleadoRepository repositorio;

	@GetMapping("empleados")
	public List<Empleado> FindAllEmpleados() {
		return repositorio.findAll();

	}

	@PostMapping("empleados")
	public Empleado guardarEmpleado(@RequestBody Empleado empleado) {
		return repositorio.save(empleado);
	}

	@GetMapping("empleados/{id}")
	public ResponseEntity<Empleado> findEmpleadoById(@PathVariable Long id) {
		Empleado empleado = repositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Existe El Empleado Con ID " + id));
		return ResponseEntity.ok(empleado);
	}
	
	@PutMapping("empleados/{id}")
	public ResponseEntity<Empleado> actualizarEmpleadoById(@PathVariable Long id,@RequestBody Empleado detallesEmpleado) {
		Empleado empleado = repositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Existe El Empleado Con ID " + id));
		
		empleado.setNombre(detallesEmpleado.getNombre());
		empleado.setApellido(detallesEmpleado.getApellido());
		empleado.setEmail(detallesEmpleado.getEmail());
		
		Empleado empleadoActualizado = repositorio.save(empleado);
		return ResponseEntity.ok(empleadoActualizado);
	}
	
	@DeleteMapping("empleados/{id}")
	public void eliminarEmpleado(@PathVariable Long id) {
		 repositorio.deleteById(id);
		 this.FindAllEmpleados();

	}
	

}
