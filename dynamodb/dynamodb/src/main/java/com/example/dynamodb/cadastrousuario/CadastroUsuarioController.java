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

    private @Autowired
    DynamoRepository dynamoRepository;

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> criaUsuario(@RequestBody UsuarioForm usuarioform) {
        Usuario usuario = usuarioform.converte();
        try {

            Usuario response = dynamoRepository.criaUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(),
                    e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PutMapping("/usuario")
    public ResponseEntity<String> updateUsuario( @RequestBody UsuarioForm usuarioform) {
        Usuario usuario = usuarioform.converte();
        try {
            String response = dynamoRepository.updateUsuario(usuario.getUsuarioId(), usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(),
                    e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @DeleteMapping("/usuario/{usuarioId}")
    public ResponseEntity<String> deletaUsuario(@PathVariable String usuarioId) {
        try {
            String response = dynamoRepository.deletaUsuario(usuarioId);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(),
                    e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Usuario> buscaUsuario(@PathVariable String usuarioId) {
        try {
            Usuario response = dynamoRepository.buscaUsuarioPorId(usuarioId);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(),
                    e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
