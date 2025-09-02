package com.lta.systempayments.sistema_pagos_backend.services;

import com.lta.systempayments.sistema_pagos_backend.entities.Estudiante;
import com.lta.systempayments.sistema_pagos_backend.entities.Pago;
import com.lta.systempayments.sistema_pagos_backend.enums.PagoStatus;
import com.lta.systempayments.sistema_pagos_backend.enums.TypePagos;
import com.lta.systempayments.sistema_pagos_backend.repository.EstudianteRepository;
import com.lta.systempayments.sistema_pagos_backend.repository.PagoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
public class PagoService {
    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    public Pago savePago(MultipartFile file, double cantidad, TypePagos type, LocalDate fecha, String codigo) throws IOException {
        /*
            - Creamos una ruta donde se guardará el archivo del pago
            - System.getProperty("user.home"): obtiene la ruta del directorio personal del usuario del S.O
            - Paths.get(...): Crea un objeto Path apuntando a una carpeta llamada enset/pagos dentro del directorio usuario
         */
        Path folderPath = Paths.get(System.getProperty("user.home"), "enset", "pagos");

        if(!Files.exists(folderPath)){
            Files.createDirectories(folderPath);
        }

        String filename = UUID.randomUUID().toString(); // Generamos un identificador para evitar conflictos en el momento de crear el archivo

        // Creamos una ruta para el archivo pdf que se guardará en enset
        Path filePath = Paths.get(System.getProperty("user.home"), "enset", "pagos", filename + ".pdf");

        /*
          - file.getInputStream(): Obtiene el flujo de datos del archivo recibido desde la solicitud http
          - Files.copy(...): Copia los datos del archivo al destino filePath
         */
        Files.copy(file.getInputStream(), filePath);

        Estudiante estudiante = estudianteRepository.findByCodigo(codigo);

        Pago pago = Pago.builder()
                .type(type)
                .status(PagoStatus.CREADO)
                .fecha(fecha)
                .estudiante(estudiante)
                .cantidad(cantidad)
                .file(filePath.toUri().toString())
                .build();

        return pagoRepository.save(pago);
    }

    public byte[] getArchivoPorId(Long pagoId) throws IOException {
        Pago pago = pagoRepository.findById(pagoId).get();
        /*
            - pago.getFile(): Obtiene la URI del archivo guardado
            - URI.create(...): Convierte la cadena en un objeto URI
            - Path.of(...): Convierte la URI en un Path
            - Files.readAllBytes(...): Lee el contenido del archivo y lo devuelve como un array de bytes
         */
        return Files.readAllBytes(Path.of(URI.create(pago.getFile())));
    }

    public Pago actualizarPagoStatus(PagoStatus status, Long id){
        Pago pago = pagoRepository.findById(id).get();
        pago.setStatus(status);
        return pagoRepository.save(pago);
    }
}
