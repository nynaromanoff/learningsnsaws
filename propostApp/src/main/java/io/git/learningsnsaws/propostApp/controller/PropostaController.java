package io.git.learningsnsaws.propostApp.controller;

import io.git.learningsnsaws.propostApp.dto.PropostaRequestDto;
import io.git.learningsnsaws.propostApp.dto.PropostaResponseDto;
import io.git.learningsnsaws.propostApp.service.PropostaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/proposta")
@AllArgsConstructor
public class PropostaController {

    private PropostaService service;

    @PostMapping
    public ResponseEntity<PropostaResponseDto> criar(@RequestBody PropostaRequestDto requestDTO) {
        PropostaResponseDto response = service.criar(requestDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri())
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<PropostaResponseDto>> obterPropostas() {
        return ResponseEntity.ok(service.obterPropostas());
    }
}
