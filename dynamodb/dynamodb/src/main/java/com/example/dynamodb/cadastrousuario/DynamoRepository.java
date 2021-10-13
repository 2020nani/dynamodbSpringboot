package com.example.dynamodb.cadastrousuario;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DynamoRepository {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    public DynamoRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Usuario buscaUsuarioPorId(String usuarioId) {
        return dynamoDBMapper.load(Usuario.class, usuarioId);
    }

//    public List<Usuario> getUsuario() {
//        return dynamoDBMapper.batchLoad(Usuario.class>,);
//    }

    public String deletaUsuario(String usuarioId){
        Usuario usuario = dynamoDBMapper.load(Usuario.class,usuarioId);
        dynamoDBMapper.delete(usuario);
        return "Usuario deletado com sucesso";
    }

    public Usuario criaUsuario(Usuario usuario) {
        dynamoDBMapper.save(usuario);
        return usuario;
    }

    public String updateUsuario(String usuarioId, Usuario usuario) {

        dynamoDBMapper.save(usuario,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("usuarioId",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(usuarioId)
                                )));
        return usuarioId;
    }
}
