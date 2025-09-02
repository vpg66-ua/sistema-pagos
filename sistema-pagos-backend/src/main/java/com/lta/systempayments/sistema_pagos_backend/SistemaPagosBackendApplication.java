package com.lta.systempayments.sistema_pagos_backend;

import com.lta.systempayments.sistema_pagos_backend.entities.Estudiante;
import com.lta.systempayments.sistema_pagos_backend.entities.Pago;
import com.lta.systempayments.sistema_pagos_backend.enums.PagoStatus;
import com.lta.systempayments.sistema_pagos_backend.enums.TypePagos;
import com.lta.systempayments.sistema_pagos_backend.repository.EstudianteRepository;
import com.lta.systempayments.sistema_pagos_backend.repository.PagoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class SistemaPagosBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaPagosBackendApplication.class, args);
	}

    @Bean
    CommandLineRunner commandLineRunner(EstudianteRepository estudianteRepository, PagoRepository pagoRepository){
        return args -> {
            estudianteRepository.save(Estudiante.builder()
                    .id(UUID.randomUUID().toString())
                    .nombre("Victor")
                    .apellidos("Padron Garcia")
                    .codigo("1234")
                    .programaId("LTA1")
                    .build());

            estudianteRepository.save(Estudiante.builder()
                    .id(UUID.randomUUID().toString())
                    .nombre("Rodri")
                    .apellidos("Padron Garcia")
                    .codigo("12345")
                    .programaId("LTA1")
                    .build());

            estudianteRepository.save(Estudiante.builder()
                    .id(UUID.randomUUID().toString())
                    .nombre("Raul")
                    .apellidos("Padron Garcia")
                    .codigo("12346")
                    .programaId("LTA2")
                    .build());

            estudianteRepository.save(Estudiante.builder()
                    .id(UUID.randomUUID().toString())
                    .nombre("Pepe")
                    .apellidos("Padron Garcia")
                    .codigo("12347")
                    .programaId("LTA2")
                    .build());

            estudianteRepository.save(Estudiante.builder()
                    .id(UUID.randomUUID().toString())
                    .nombre("Jose")
                    .apellidos("Padron Garcia")
                    .codigo("12348")
                    .programaId("LTA2")
                    .build());

            TypePagos types[] = TypePagos.values();
            Random random = new Random();

            for (Estudiante estudiante : estudianteRepository.findAll()) {
                for(int i = 0; i < 10; i++){
                    int index = random.nextInt(types.length);

                    Pago pago = Pago.builder()
                            .cantidad(1000+(int)(Math.random()*20000))
                            .type(types[index])
                            .status(PagoStatus.CREADO)
                            .fecha(LocalDate.now())
                            .estudiante(estudiante)
                            .build();
                    pagoRepository.save(pago);
                }
            }
        };
    }
}
