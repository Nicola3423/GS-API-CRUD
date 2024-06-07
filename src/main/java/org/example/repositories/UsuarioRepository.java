package org.example.repositories;


import org.example.infrastructure.OracleDBConfiguration;
import org.example.entites.Usuario;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UsuarioRepository {


    public static final String TB_NAME = "TB_USUARIO";


    public static List<Usuario> getAll(){
        var usuarios = new ArrayList<Usuario>();
        try(var conn = new OracleDBConfiguration().getConnection();
            var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME +" ORDER BY ID")){
            var rs = stmt.executeQuery();
            while(rs.next()){
                usuarios.add(new Usuario(
                        rs.getString("NOME"),
                        rs.getString("EMAIL"),
                        rs.getInt("CONTATO"),
                        rs.getInt("CEP"),
                        rs.getString("LOGIN")));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


        return usuarios;
    }
    public List<Usuario> getAll(String NOME, String EMAIL,int CONTATO,int CEP, String LOGIN, String orderBy, String direction, int limit, int offset) {
        var usuarios = new ArrayList<Usuario>();
        try(var conn = new OracleDBConfiguration().getConnection();
            var stmt = conn.prepareStatement(
                    // Query para buscar os produtos
                    // O OFFSET é a quantidade de registros que serão pulados
                    // O FETCH NEXT é a quantidade de registros que serão retornados
                    // O ORDER BY é a coluna que será ordenada
                    // O ASC é a direção da ordenação
                    // O LIKE é um operador de comparação que busca registros que contenham o valor
                    // ex completo da query: SELECT * FROM PRODUTO WHERE NOME LIKE ? AND PRECO <= ? ORDER BY ID ASC OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY
                    // esta query busca produtos que contenham o nome passado no parâmetro nome e que tenham o preço menor ou igual ao preço passado no parâmetro preco
                    ("SELECT * FROM %s WHERE NOME LIKE ? AND EMAIL <= ? ORDER BY %s %s OFFSET %d " +
                            "ROWS FETCH NEXT %d ROWS ONLY")
                            .formatted(TB_NAME, orderBy, direction == null || direction.isEmpty() ? "ASC"
                                            : direction,
                                    offset, limit == 0 ? 10 : limit)
            )){
            stmt.setString(1, "%"+NOME+"%");
            stmt.setString(2, "%"+EMAIL+"%");
            stmt.setInt(4, CONTATO);
            stmt.setInt(5, CEP);
            stmt.setString(6,"%"+LOGIN+"%");
            var rs = stmt.executeQuery();
            while (rs.next()){
                usuarios.add(new Usuario(
                        rs.getString("NOME"),
                        rs.getString("EMAIL"),
                        rs.getInt("CONTATO"),
                        rs.getInt("CEP"),
                        rs.getString("LOGIN")));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }


    public List<Usuario> getAllByCategoria(int ID){
        var usuarios = new ArrayList<Usuario>();
        try(var conn = new OracleDBConfiguration().getConnection();
            var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " WHERE ID = ?");){
            stmt.setInt(1, ID);
            var rs = stmt.executeQuery();
            while (rs.next()){
                usuarios.add(new Usuario(
                        rs.getString("NOME"),
                        rs.getString("EMAIL"),
                        rs.getInt("CONTATO"),
                        rs.getInt("CEP"),
                        rs.getString("LOGIN")));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }


    public Optional<Usuario> get(int id){
        try(var conn = new OracleDBConfiguration().getConnection();
            var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " WHERE ID = ?")
        ){
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if(rs.next()){
                return Optional.of(new Usuario(
                        rs.getString("NOME"),
                        rs.getString("EMAIL"),
                        rs.getInt("CONTATO"),
                        rs.getInt("CEP"),
                        rs.getString("LOGIN")));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


        return Optional.empty();
    }


    public void create(Usuario usuario){
        try(var conn = new OracleDBConfiguration().getConnection();
            var stmt = conn.prepareStatement("INSERT INTO " + TB_NAME + " (NOME, EMAIL, CONTATO, CEP, LOGIN) VALUES (?,?,?,?,?)")){
            stmt.setString(1, usuario.getNOME());
            stmt.setString(2, usuario.getEMAIL());
            stmt.setInt(3, usuario.getCONTATO());
            stmt.setInt(4, usuario.getCEP());
            stmt.setString(5, usuario.getLOGIN());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void update(int id, Usuario usuario){
        try(var conn = new OracleDBConfiguration().getConnection();
            var stmt = conn.prepareStatement("UPDATE " + TB_NAME + " SET NOME = ?, EMAIL = ?, CONTATO = ?, CEP = ?, LOGIN = ? WHERE ID = ?");)
        {
            stmt.setString(1, usuario.getNOME());
            stmt.setString(2, usuario.getEMAIL());
            stmt.setInt(3, usuario.getCONTATO());
            stmt.setInt(4,usuario.getCEP());
            stmt.setString(5,usuario.getLOGIN());
            stmt.setInt(6, id);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int count(String NOME, String EMAIL){
        try(var conn = new OracleDBConfiguration().getConnection();
            var stmt = conn.prepareStatement("SELECT COUNT(*) FROM " +
                    TB_NAME + " WHERE NOME LIKE ? AND EMAIL <= ? ")){
            stmt.setString(1, "%"+NOME+"%");
            stmt.setString(1, "%"+EMAIL+"%");
            var result = stmt.executeQuery();
            if(result.next()){
                return result.getInt(1);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }


    public void delete(int id){
        try(var conn = new OracleDBConfiguration().getConnection();
            var stmt = conn.prepareStatement("DELETE FROM "+ TB_NAME + " WHERE ID = ?");)
        {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}