package com.lta.systempayments.sistema_pagos_backend.web;

import com.lta.systempayments.sistema_pagos_backend.entities.Estudiante;
import com.lta.systempayments.sistema_pagos_backend.entities.Pago;
import com.lta.systempayments.sistema_pagos_backend.enums.PagoStatus;
import com.lta.systempayments.sistema_pagos_backend.enums.TypePagos;
import com.lta.systempayments.sistema_pagos_backend.repository.EstudianteRepository;
import com.lta.systempayments.sistema_pagos_backend.repository.PagoRepository;
import com.lta.systempayments.sistema_pagos_backend.services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
public class PagoController {
    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private PagoService pagoService;

    @GetMapping("/estudiantes")
    public List<Estudiante> listarEstudiantes(){
        return estudianteRepository.findAll();
    }

    @GetMapping("/estudiantes/{codigo}")
    public Estudiante getEstudiantePorCodigo(@PathVariable String codigo){
        return estudianteRepository.findByCodigo(codigo);
    }

    @GetMapping("/estudiantesPorPrograma")
    public List<Estudiante> listarEstudiantesPorPrograma(@RequestParam String programaId){
        return estudianteRepository.findByProgramaId(programaId);
    }

    @GetMapping("/pagos")
    public List<Pago> listarPagos(){
        return pagoRepository.findAll();
    }

    @GetMapping("/pago/{id}")
    public Pago getPagoPorId(@PathVariable Long id){
        return pagoRepository.findById(id).get();
    }

    @GetMapping("/estudiantes/{codigo}/pagos")
    public List<Pago> listarPagosPorCodigoEstudiante(@PathVariable String codigo){
        return pagoRepository.findByEstudianteCodigo(codigo);
    }

    @GetMapping("/pagosPorStatus")
    public List<Pago> listarPagosPorStatus(@RequestParam PagoStatus status){
        return pagoRepository.findByStatus(status);
    }

    @GetMapping("/pagosPorType")
    public List<Pago> listarPagosPorType(@RequestParam TypePagos type){
        return pagoRepository.findByType(type);
    }

    @PutMapping("/pago/{pagoId}/actualizarStatusPago")
    public Pago actualizarStatusDePago(@RequestParam PagoStatus status, @PathVariable Long pagoId){
        return pagoService.actualizarPagoStatus(status,pagoId);
    }

    @PostMapping(path = "/pago", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Pago guardarPago(@RequestParam MultipartFile file, double cantidad, TypePagos type, LocalDate fecha, String codigoEstudiante) throws IOException {
        return pagoService.savePago(file, cantidad, type, fecha, codigoEstudiante);
    }

    @GetMapping(value = "/pagoFile/{pagoId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] listarArchivoPorId(@PathVariable Long pagoId) throws IOException {
        return pagoService.getArchivoPorId(pagoId);
    }
}
