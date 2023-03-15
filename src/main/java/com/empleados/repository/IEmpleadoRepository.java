package com.empleados.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.empleados.model.Empleado;

@Repository
public interface IEmpleadoRepository extends JpaRepository<Empleado, Long> {

}
