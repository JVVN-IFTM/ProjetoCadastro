package br.edu.iftm.tspi.cadastro.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.iftm.tspi.cadastro.model.Cadastro;

@Controller
public class CadastroController {

    List<Cadastro> cadastros = new ArrayList<>();

    @GetMapping("/cadastro")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("cadastro");
        mv.addObject("cadastro", new Cadastro());
        return mv;
    }

    @PostMapping("/cadastro")
    public String cadastro(Cadastro cad) {
        if (cad.getId() == null) {
            Long id = cadastros.size() + 1L;
            cadastros.add(new Cadastro(id, cad.getNome(), cad.getEmail(), cad.getCelular()));

        } else {
            Cadastro achaCadastro = cadastros.stream().filter(numCad -> cad.getId().equals(numCad.getId())).findFirst().get();
            cadastros.set(cadastros.indexOf(achaCadastro), cad);
        }
         
        return "redirect:/lista";

    }

    @GetMapping("/lista")
    public ModelAndView lista() {
        ModelAndView mv = new ModelAndView("listagem");
        mv.addObject("cadastros", cadastros);
        return mv; 
    }

    @GetMapping("/editarCadastro/{id}")
    public ModelAndView editarCadastro (@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("cadastro");
        Cadastro pegaUsuario = cadastros.stream().filter(cadastro -> id.equals(cadastro.getId())).findFirst().get();
        
        mv.addObject("cadastro", pegaUsuario);
        return mv;
    }

    @GetMapping("/removerCadastro/{id}")
    public String removerCadastro (@PathVariable("id") Long id) {
        cadastros.removeIf(idCad -> id.equals(idCad.getId()));
        return "redirect:/lista";
    }
}
