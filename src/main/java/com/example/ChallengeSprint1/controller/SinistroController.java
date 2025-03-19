package com.example.ChallengeSprint1.controller;


import com.example.ChallengeSprint1.dto.SinistroDTO;
import com.example.ChallengeSprint1.service.SinistroService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/sinistro")
public class SinistroController {

    private final SinistroService sinistroService;

    public SinistroController(SinistroService sinistroService) {
        this.sinistroService = sinistroService;
    }

    @GetMapping("/cadastro")
    public ModelAndView sinistroCadastro() {
        ModelAndView mv = new ModelAndView("sinistro/sinistroCadastro");
        mv.addObject("sinistroDTO", new SinistroDTO());
        return mv;
    }

    @PostMapping("/cadastrar")
    public ModelAndView cadastrarSinistro(SinistroDTO sinistroDTO) {
        sinistroService.cadastrarSinistro(sinistroDTO);
        return new ModelAndView("redirect:/sinistro/lista");
    }

    @GetMapping("/lista")
    public ModelAndView listaSinistros() {
        ModelAndView mv = new ModelAndView("sinistro/listaSinistros");
        mv.addObject("sinistros", sinistroService.listarSinistros());
        return mv;
    }

    @PostMapping("/atualizar/{id}")
    public ModelAndView atualizarSinistro(@PathVariable Long id, SinistroDTO sinistroDTO) {
        sinistroService.atualizarSinistro(id, sinistroDTO);
        return new ModelAndView("redirect:/sinistro/lista");
    }

    @GetMapping("/deletar/{id}")
    public ModelAndView deletarSinistro(@PathVariable Long id) {
        sinistroService.deletarSinistro(id);
        return new ModelAndView("redirect:/sinistro/lista");
    }
}