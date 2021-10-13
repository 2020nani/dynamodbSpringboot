package com.example.dynamodb.cadastrousuario;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;


@EnableScan
public interface UsuarioRepository extends CrudRepository<Usuario, String> {

}
