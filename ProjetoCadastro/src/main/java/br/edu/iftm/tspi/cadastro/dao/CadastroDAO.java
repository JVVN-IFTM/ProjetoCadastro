package br.edu.iftm.tspi.cadastro.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import br.edu.iftm.tspi.cadastro.domain.Cadastro;

@Component
public class CadastroDAO {
    JdbcTemplate db;

    public CadastroDAO(JdbcTemplate db) {
        this.db = db;
    }

    public List<Cadastro> getUsuarios() {
        String sql = "select id, nome, email, celular from tb_usuarios";

        return db.query(sql, (res, rowNum) -> new Cadastro(
                res.getInt("id"),
                res.getString("nome"),
                res.getString("email"),
                res.getString("celular")));
    }

    public List<Cadastro> getUsuario(String nome) {
        String sql = "select * from tb_usuarios where lower(nome) like ?";

        return db.query(sql,
                new BeanPropertyRowMapper<>(Cadastro.class), "%" + nome + "%");
    }

    public Cadastro getUsuario(int id) {
        String sql = "select * from tb_usuarios where id = ?";

        List<Cadastro> cads = db.query(sql,
                new BeanPropertyRowMapper<>(Cadastro.class), id);

        if (cads != null && !cads.isEmpty()) {
            return cads.get(0);
        } else {
            return null;
        }
    }

    public void createUsuario(Cadastro ca) {
        String sql = "insert into tb_usuarios(nome, email, celular) values (?, ?, ?)";

        db.update(sql,
                ca.getNome(),
                ca.getEmail(),
                ca.getCelular());
    }

    public void updateUsuario(Cadastro cad) {
        String sql = "update tb_usuarios set nome = ?, email = ?, celular = ? where id = ?";

        db.update(sql,
                cad.getNome(),
                cad.getEmail(),
                cad.getCelular(),
                cad.getId());
    }

    public void removeUsuario(int id) {
        String sql = "delete from tb_usuarios where id = ?";

        db.update(sql, id);
    }
}
