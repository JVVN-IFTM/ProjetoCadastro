package br.edu.iftm.tspi.cadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import br.edu.iftm.tspi.cadastro.dao.CadastroDAO;
import br.edu.iftm.tspi.cadastro.domain.Cadastro;



@Controller
public class CadastroController {

@Autowired
private CadastroDAO dao;
    @GetMapping("")
    public String index() {
        return "redirect:/listagem";
    }

    @GetMapping("cadastro")
    public ModelAndView cadastro() {
        ModelAndView mv = new ModelAndView("cadastro");
        mv.addObject("usuario", new Cadastro());
        mv.addObject("cadastros", dao.getUsuarios());

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
        mv.addObject("usuario", new Cadastro());
        mv.addObject("cadastros", dao.getUsuarios());

        return mv;
    }

    @GetMapping("buscaCadastro")
    public ModelAndView getCadastro(@RequestParam(value = "nome", required = true) String nome) {
        ModelAndView mv = new ModelAndView("listagem");
        mv.addObject("cadastros", dao.getUsuario(nome));
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
        mv.addObject("usuario", co);
        mv.addObject("cadastros", dao.getUsuarios());

        return mv;
    }
}
