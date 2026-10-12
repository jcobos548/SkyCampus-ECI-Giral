package com.skycampus.service;

import com.skycampus.model.Drone;
import com.skycampus.model.EstadoDrone;
import com.skycampus.model.EstadoMision;
import com.skycampus.model.Mision;
import com.skycampus.model.Prioridad;
import com.skycampus.model.TipoDrone;
import com.skycampus.observer.ObservadorDrone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AsignadorMisionTest {

    @Mock
    private ApiMeteorologica clima;

    @Mock
    private ObservadorDrone observador;

    @InjectMocks
    private AsignadorMision asignador;

    private List<Drone> flota;
    private Mision misionNormal;

    @BeforeEach
    void configurarDatos() {
        flota = List.of(
                new Drone("D1", TipoDrone.MINI, 75, true,
                        EstadoDrone.DISPONIBLE),
                new Drone("D2", TipoDrone.EXPRESS, 91, true,
                        EstadoDrone.DISPONIBLE),
                new Drone("D3", TipoDrone.CARGO, 80, true,
                        EstadoDrone.DISPONIBLE)
        );

        misionNormal = crearMision(
                "M1", Prioridad.NORMAL, 200);
    }

    @Test
    @DisplayName("1. Misión normal: asigna el dron apto con mayor batería")
    void misionNormal_asignaDroneMayorBateria() {
        when(clima.esApto()).thenReturn(true);

        Optional<Drone> resultado =
                asignador.asignar(flota, misionNormal);

        assertTrue(resultado.isPresent());
        assertEquals("D2", resultado.get().id());
        assertEquals(91, resultado.get().bateria());

        verify(observador).onEstadoCambiado(
                resultado.get(), EstadoDrone.EN_VUELO);
    }

    @Test
    @DisplayName("2. Clima adverso: no asigna ningún dron")
    void climaAdverso_noAsignaDron() {
        when(clima.esApto()).thenReturn(false);

        Optional<Drone> resultado =
                asignador.asignar(flota, misionNormal);

        assertTrue(resultado.isEmpty());
        verifyNoInteractions(observador);
    }

    @Test
    @DisplayName("3. Sin drones aptos: devuelve Optional vacío")
    void sinDronesAptos_noAsignaDron() {
        when(clima.esApto()).thenReturn(true);

        List<Drone> flotaNoApta = List.of(
                new Drone("D4", TipoDrone.MINI, 10, true,
                        EstadoDrone.DISPONIBLE),
                new Drone("D5", TipoDrone.EXPRESS, 80, false,
                        EstadoDrone.DISPONIBLE),
                new Drone("D6", TipoDrone.CARGO, 80, true,
                        EstadoDrone.MANTENIMIENTO)
        );

        Optional<Drone> resultado =
                asignador.asignar(flotaNoApta, misionNormal);

        assertTrue(resultado.isEmpty());
        verifyNoInteractions(observador);
    }

    @Test
    @DisplayName("4. Paquete demasiado pesado: no asigna un dron")
    void paqueteDemasiadoPesado_noAsignaDron() {
        when(clima.esApto()).thenReturn(true);

        Mision misionPesada =
                crearMision("M2", Prioridad.NORMAL, 15000);

        Optional<Drone> resultado =
                asignador.asignar(flota, misionPesada);

        assertTrue(resultado.isEmpty());
        verifyNoInteractions(observador);
    }

    @Test
    @DisplayName("5. Misión urgente: exige un dron EXPRESS")
    void misionUrgente_asignaDroneExpress() {
        when(clima.esApto()).thenReturn(true);

        Mision urgente =
                crearMision("M3", Prioridad.URGENTE, 200);

        Optional<Drone> resultado =
                asignador.asignar(flota, urgente);

        assertTrue(resultado.isPresent());
        assertEquals(TipoDrone.EXPRESS, resultado.get().tipo());

        verify(observador).onEstadoCambiado(
                resultado.get(), EstadoDrone.EN_VUELO);
    }

    private Mision crearMision(
            String id,
            Prioridad prioridad,
            int peso) {

        return new Mision(
                id,
                null,
                "BIBLIOTECA",
                peso,
                prioridad,
                EstadoMision.PENDIENTE,
                LocalDateTime.now()
        );
    }
}
