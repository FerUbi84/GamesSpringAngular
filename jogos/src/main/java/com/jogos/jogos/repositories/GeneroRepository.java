package com.jogos.jogos.repositories;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jogos.jogos.models.Genero;

@Repository
public class GeneroRepository {

    private final JdbcTemplate jdbcTemplate;

    
    public GeneroRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void InsertGenero(Genero genero) {
        String insert_genero = "INSERT INTO genero (nome_genero) VALUE (?)";
        jdbcTemplate.update(insert_genero, genero.getNome_genero());
    }

    public List<Genero> AllGeneros() {
        String select_all = "SELECT * FROM genero";

        try {
            return jdbcTemplate.query(select_all, (rs, rowNum) -> {
                Genero genero = new Genero();
                genero.setId_genero(rs.getInt("id_genero"));
                genero.setNome_genero(rs.getString("nome_genero"));
                return genero;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (Exception e) {

            throw new RuntimeException("Erro ao consultar os gêneros: " + e.getMessage(), e);
        }
    }

    public Genero GetGenero(int id_genero) {
        String select_genero = "SELECT * FROM genero WHERE id_genero =?";

        try {
            return jdbcTemplate.queryForObject(select_genero, new Object[] { id_genero }, (rs, rowNum) -> {
                Genero genero = new Genero();
                genero.setId_genero(rs.getInt("id_genero"));
                genero.setNome_genero(rs.getString("nome_genero"));
                return genero;

            });
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar os gêneros: " + e.getMessage(), e);
        }
    }

    public int DeleteGenero(int id_genero) {

        String delete_genero = "DELETE FROM genero WHERE id_genero = ?";

        int apagado = jdbcTemplate.update(delete_genero, id_genero);

        return apagado;
    }

    public int UpdateGenero(int id_genero, String nome_genero) {

        String update_genero = "UPDATE genero SET nome_genero=? WHERE id_genero = ?";

        int alterado = jdbcTemplate.update(update_genero, nome_genero ,id_genero);

        return alterado;  
    }
}
