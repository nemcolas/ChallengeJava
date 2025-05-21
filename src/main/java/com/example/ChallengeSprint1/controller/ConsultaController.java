package com.example.ChallengeSprint1.controller;

import com.example.ChallengeSprint1.dto.ConsultaDTO;
import com.example.ChallengeSprint1.service.ConsultaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/consulta")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/cadastro")
    public ModelAndView consultaCadastro() {
        ModelAndView mv = new ModelAndView("consulta/consultaCadastro");
        mv.addObject("consultaDTO", new ConsultaDTO());
        return mv;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/cadastrar")
    public ModelAndView cadastrarConsulta(ConsultaDTO consultaDTO) {
        consultaService.cadastrarConsulta(consultaDTO);
        return new ModelAndView("redirect:/consulta/lista");
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/lista")
    public ModelAndView listaConsultas() {
        ModelAndView mv = new ModelAndView("consulta/listaConsultas");
        mv.addObject("consultas", consultaService.listarConsultas());
        return mv;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/atualizar/{id}")
    public ModelAndView atualizarConsulta(@PathVariable Long id, ConsultaDTO consultaDTO) {
        consultaService.atualizarConsulta(id, consultaDTO);
        return new ModelAndView("redirect:/consulta/lista");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deletar/{id}")
    public ModelAndView deletarConsulta(@PathVariable Long id) {
        consultaService.deletarConsulta(id);
        return new ModelAndView("redirect:/consulta/lista");
    }
}
