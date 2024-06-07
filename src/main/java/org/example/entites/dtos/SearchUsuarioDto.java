package org.example.entites.dtos;

import org.example.entites.Usuario;
import java.util.List;

public record SearchUsuarioDto(String NOME, String EMAIL,int CONTATO,int CEP,String LOGIN,String orderBy, String direction, int limit, int offset, int totalItems, List<Usuario> usuarios){

}