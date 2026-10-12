package com.skycampus.strategy;

import com.skycampus.model.Drone;
import com.skycampus.model.Mision;
import java.util.List;
import java.util.Optional;

public interface EstrategiaAsignacion {
    Optional<Drone> seleccionar(List<Drone> flota, Mision mision);
}
