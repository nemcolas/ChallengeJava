package com.example.ChallengeSprint1.controller;

import com.example.ChallengeSprint1.dto.ConsultaDTO;
import com.example.ChallengeSprint1.producer.ConsultaProducer;
import com.example.ChallengeSprint1.service.ConsultaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/consulta")
public class ConsultaController {

    private final ConsultaService consultaService;
    private final ConsultaProducer consultaProducer;

    public ConsultaController(ConsultaService consultaService, ConsultaProducer consultaProducer) {
        this.consultaService = consultaService;
        this.consultaProducer = consultaProducer;
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
    public ModelAndView cadastrarConsulta(@Valid ConsultaDTO consultaDTO, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("consulta/consultaCadastro");
            mv.addObject("consultaDTO", consultaDTO);
            return mv;
        }
        
        try {
            consultaService.cadastrarConsulta(consultaDTO);
            consultaProducer.enviarMensagem("✅ Nova consulta cadastrada com sucesso.");
            attributes.addFlashAttribute("mensagem", "Consulta cadastrada com sucesso!");
            return new ModelAndView("redirect:/consulta/lista");
        } catch (Exception e) {
            ModelAndView mv = new ModelAndView("consulta/consultaCadastro");
            mv.addObject("consultaDTO", consultaDTO);
            mv.addObject("erro", "Erro ao cadastrar consulta: " + e.getMessage());
            return mv;
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/lista")
    public ModelAndView listaConsultas() {
        ModelAndView mv = new ModelAndView("consulta/listaConsultas");
        try {
            mv.addObject("consultas", consultaService.listarConsultas());
        } catch (Exception e) {
            mv.addObject("erro", "Erro ao listar consultas: " + e.getMessage());
        }
        return mv;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/atualizar/{id}")
    public ModelAndView atualizarConsulta(@PathVariable Long id, @Valid ConsultaDTO consultaDTO, 
                                         BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("consulta/listaConsultas");
            mv.addObject("consultas", consultaService.listarConsultas());
            mv.addObject("erro", "Erro de validação ao atualizar consulta");
            return mv;
        }
        
        try {
            consultaService.atualizarConsulta(id, consultaDTO);
            consultaProducer.enviarMensagem("🔁 Consulta atualizada: ID = " + id);
            attributes.addFlashAttribute("mensagem", "Consulta atualizada com sucesso!");
            return new ModelAndView("redirect:/consulta/lista");
        } catch (Exception e) {
            ModelAndView mv = new ModelAndView("consulta/listaConsultas");
            mv.addObject("consultas", consultaService.listarConsultas());
            mv.addObject("erro", "Erro ao atualizar consulta: " + e.getMessage());
            return mv;
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deletar/{id}")
    public ModelAndView deletarConsulta(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            consultaService.deletarConsulta(id);
            consultaProducer.enviarMensagem("🗑️ Consulta deletada: ID = " + id);
            attributes.addFlashAttribute("mensagem", "Consulta deletada com sucesso!");
            return new ModelAndView("redirect:/consulta/lista");
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "Erro ao deletar consulta: " + e.getMessage());
            return new ModelAndView("redirect:/consulta/lista");
        }
    }
}
