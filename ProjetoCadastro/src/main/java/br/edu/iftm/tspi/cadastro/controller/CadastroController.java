package br.edu.iftm.tspi.cadastro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import br.edu.iftm.tspi.cadastro.dao.CadastroDAO;
import br.edu.iftm.tspi.cadastro.domain.Cadastro;

@Controller
public class CadastroController {

    private CadastroDAO dao;
    static final String ATRIBUTO_USUARIO = "usuario";
    static final String ATRIBUTO_CADASTROS = "cadastros";

    public CadastroController(CadastroDAO dao) {
        this.dao = dao;
    }

    @GetMapping("")
    public String index() {
        return "redirect:/listagem";
    }

    @GetMapping("cadastro")
    public ModelAndView cadastro() {
        ModelAndView mv = new ModelAndView("cadastro");
        mv.addObject(ATRIBUTO_USUARIO, new Cadastro());
        mv.addObject(ATRIBUTO_CADASTROS, dao.getUsuarios());

        return mv;
    }

    @PostMapping("cadastro")
    public ModelAndView cadastro(Cadastro cad) {
        Cadastro co = dao.getUsuario(cad.getId());

        if (co == null) {
            dao.createUsuario(cad);
        } else {
            dao.updateUsuario(cad);
        }
        return getCadastro();
    }

    @GetMapping("listagem")
    public ModelAndView getCadastro() {
        ModelAndView mv = new ModelAndView("listagem");
        mv.addObject(ATRIBUTO_USUARIO, new Cadastro());
        mv.addObject(ATRIBUTO_CADASTROS, dao.getUsuarios());

        return mv;
    }

    @GetMapping("buscaCadastro")
    public ModelAndView getCadastro(@RequestParam(value = "nome", required = true) String nome) {
        ModelAndView mv = new ModelAndView("listagem");
        mv.addObject(ATRIBUTO_CADASTROS, dao.getUsuario(nome));
        return mv;
    }

    @GetMapping("removerCadastro")
    public ModelAndView removerCadastro(@RequestParam(value = "id", required = true) int id) {
        dao.removeUsuario(id);
        return getCadastro();
    }

    @GetMapping("editarCadastro")
    public ModelAndView editarCadastro(@RequestParam(value = "id", required = true) int id) {
        Cadastro co = dao.getUsuario(id);

        ModelAndView mv = new ModelAndView("cadastro");
        mv.addObject(ATRIBUTO_USUARIO, co);
        mv.addObject(ATRIBUTO_CADASTROS, dao.getUsuarios());

        return mv;
    }
}
