package com.example.ChallengeSprint1.controller;

import com.example.ChallengeSprint1.dto.SinistroDTO;
import com.example.ChallengeSprint1.service.SinistroService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/sinistro")
public class SinistroController {

    private final SinistroService sinistroService;

    public SinistroController(SinistroService sinistroService) {
        this.sinistroService = sinistroService;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/cadastro")
    public ModelAndView sinistroCadastro() {
        ModelAndView mv = new ModelAndView("sinistro/sinistroCadastro");
        mv.addObject("sinistroDTO", new SinistroDTO());
        return mv;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/cadastrar")
    public ModelAndView cadastrarSinistro(@Valid SinistroDTO sinistroDTO, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("sinistro/sinistroCadastro");
            mv.addObject("sinistroDTO", sinistroDTO);
            return mv;
        }
        
        try {
            sinistroService.cadastrarSinistro(sinistroDTO);
            attributes.addFlashAttribute("mensagem", "Sinistro cadastrado com sucesso!");
            return new ModelAndView("redirect:/sinistro/lista");
        } catch (Exception e) {
            ModelAndView mv = new ModelAndView("sinistro/sinistroCadastro");
            mv.addObject("sinistroDTO", sinistroDTO);
            mv.addObject("erro", "Erro ao cadastrar sinistro: " + e.getMessage());
            return mv;
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/lista")
    public ModelAndView listaSinistros() {
        ModelAndView mv = new ModelAndView("sinistro/listaSinistros");
        try {
            mv.addObject("sinistros", sinistroService.listarSinistros());
        } catch (Exception e) {
            mv.addObject("erro", "Erro ao listar sinistros: " + e.getMessage());
        }
        return mv;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/atualizar/{id}")
    public ModelAndView atualizarSinistro(@PathVariable Long id, @Valid SinistroDTO sinistroDTO, 
                                         BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("sinistro/listaSinistros");
            mv.addObject("sinistros", sinistroService.listarSinistros());
            mv.addObject("erro", "Erro de validação ao atualizar sinistro");
            return mv;
        }
        
        try {
            sinistroService.atualizarSinistro(id, sinistroDTO);
            attributes.addFlashAttribute("mensagem", "Sinistro atualizado com sucesso!");
            return new ModelAndView("redirect:/sinistro/lista");
        } catch (Exception e) {
            ModelAndView mv = new ModelAndView("sinistro/listaSinistros");
            mv.addObject("sinistros", sinistroService.listarSinistros());
            mv.addObject("erro", "Erro ao atualizar sinistro: " + e.getMessage());
            return mv;
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deletar/{id}")
    public ModelAndView deletarSinistro(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            sinistroService.deletarSinistro(id);
            attributes.addFlashAttribute("mensagem", "Sinistro deletado com sucesso!");
            return new ModelAndView("redirect:/sinistro/lista");
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "Erro ao deletar sinistro: " + e.getMessage());
            return new ModelAndView("redirect:/sinistro/lista");
        }
    }
}
