package com.example.dynamodb.cadastrousuario;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "usuario")
public class Usuario {

    @DynamoDBHashKey(attributeName = "usuarioId")
    @DynamoDBAutoGeneratedKey
    private String usuarioId;

    @DynamoDBAttribute(attributeName = "name")
    private String name;

    @DynamoDBAttribute(attributeName = "email")
    private String email;

    public String getUsuarioId() {
        return usuarioId;
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Deprecated
    public Usuario() {
    }

    public Usuario(String usuarioId, String name, String email) {
        this.usuarioId = usuarioId;
        this.name = name;
        this.email = email;
    }
}
