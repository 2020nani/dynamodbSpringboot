package com.example.dynamodb.cadastrousuario;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CadastroUsuarioController {

    @Autowired
    private UsuarioRepository dynamoRepository;

    public CadastroUsuarioController(UsuarioRepository dynamoRepository) {
        this.dynamoRepository = dynamoRepository;
    }

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> criaUsuario(@RequestBody UsuarioForm usuarioform) {
        Usuario usuario = usuarioform.converte();
        try {

            Usuario response = dynamoRepository.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(),
                    e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @DeleteMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> deletaUsuario(@PathVariable String usuarioId) {
        try {
             Usuario usuario = dynamoRepository.findById(usuarioId).get();
             dynamoRepository.delete(usuario);
            return ResponseEntity.ok().body("Ok");
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(),
                    e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @GetMapping("/usuario")
    public ResponseEntity<Iterable<Usuario>> buscaUsuario(@PathVariable String usuarioId) {
        try {
            Iterable<Usuario> response = dynamoRepository.findAll();
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(),
                    e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
