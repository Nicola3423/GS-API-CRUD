package org.example.services;


import org.example.entites.Usuario;
import org.example.entites.dtos.SearchUsuarioDto;
import org.example.repositories.UsuarioRepository;


public class UsuarioService {


    private static UsuarioRepository usuarioRepository;


    public UsuarioService(){
        usuarioRepository = new UsuarioRepository();
    }


    public static SearchUsuarioDto getAll(String NOME, String EMAIL, int CONTATO, int CEP, String LOGIN,
                                          String orderBy, String direction, int limit, int offset){
        var usuarios = usuarioRepository.getAll(NOME, EMAIL, CONTATO, CEP, LOGIN, orderBy, direction, limit, offset);
        var totalItems = usuarioRepository.count(NOME, EMAIL);
        return new SearchUsuarioDto(NOME, EMAIL, CONTATO, CEP, LOGIN, orderBy, direction, limit, offset, totalItems, usuarios);
    }


    public void create(Usuario usuario){
        var validation = usuario.validate();


        if(validation.containsKey(false))
            throw new IllegalArgumentException(validation.get(false).toString());
        else
            usuarioRepository.create(usuario);
    }


    public void update(int id, Usuario usuario){
        var validation = usuario.validate();


        if(validation.containsKey(false))
            throw new IllegalArgumentException(validation.get(false).toString());
        else
            usuarioRepository.update(id, usuario);
    }


    public void delete(int id){
        usuarioRepository.delete(id);
    }


}
