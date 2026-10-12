# SkyCampus — Monferno

### Reto 1. Streams & Lambdas: Estadísticas de misiones

#### Evidencia de ejecución

La siguiente evidencia muestra la ejecución exitosa del Reto 1 en IntelliJ IDEA, con los resultados de los cuatro puntos implementados.

![Evidencia de ejecución del Reto 1](docs/evidencias/Reto1.png)

**Resultado:** ejecución finalizada correctamente con `Process finished with exit code 0`.

## Reto 2. Git Merge: Resolución de conflictos y versionamiento

### Evidencia

Captura del historial de Git donde se observa el conflicto resuelto, el commit de integración y la convergencia de las ramas.

![Evidencia del Reto 2](docs/evidencias/Reto2.png)

# Reto 3 — Patrones de diseño Strategy y Observer

## Objetivo

Implementar los patrones de diseño **Strategy** y **Observer** en SkyCampus para permitir diferentes estrategias de asignación de drones y notificar automáticamente los cambios de estado de la flota, favoreciendo un código mantenible, extensible y desacoplado.

## 1. Patrón Strategy

Se implementó la interfaz `EstrategiaAsignacion`, que permite definir diferentes criterios para seleccionar un dron según las características de una misión.

Las tres implementaciones desarrolladas son:

- **AsignacionMayorBateria:** selecciona un dron compatible con la misión que tenga el mayor nivel de batería.
- **AsignacionMenorUso:** selecciona un dron compatible considerando su cantidad de usos acumulados.
- **AsignacionTipoCompatible:** selecciona el tipo de dron con la capacidad adecuada para transportar la carga.

La clase `GestorMisiones` utiliza la interfaz `EstrategiaAsignacion` y permite cambiar la estrategia de asignación sin modificar su lógica principal.

### Pruebas de Strategy

Se verificó la selección mediante las estrategias implementadas y el cambio de estrategia en tiempo de ejecución.

## 2. Patrón Observer

Se implementó la interfaz `ObservadorDrone`, que define el mecanismo de notificación cuando cambia el estado de un dron.

Los tres observadores desarrollados son:

- **PanelOperador:** recibe las notificaciones de cambios de estado de la flota.
- **SistemaLog:** registra los eventos relacionados con los cambios de estado.
- **AlertaTecnico:** reacciona ante los estados que requieren atención técnica, especialmente cuando un dron presenta un fallo.

La clase `GestorFlota` administra la suscripción de observadores y notifica los cambios mediante la interfaz común, sin depender directamente de las clases concretas.

## 3. Extensibilidad del patrón Observer

Se creó una prueba automatizada para demostrar que es posible agregar un cuarto observador sin modificar la clase `GestorFlota`.

La prueba registra los tres observadores originales, incorpora un nuevo observador y comprueba que este recibe la notificación cuando el estado de un dron cambia a `FALLO`.

Esto demuestra que el sistema permite incorporar nuevos observadores mediante suscripción, sin alterar el código del gestor de la flota.

## 4. Principios SOLID aplicados

- **Open/Closed Principle (OCP):** Strategy permite agregar nuevas estrategias de asignación sin modificar `GestorMisiones`.
- **Dependency Inversion Principle (DIP):** Observer permite que `GestorFlota` trabaje con la abstracción `ObservadorDrone` en lugar de depender de implementaciones concretas.
- **Single Responsibility Principle (SRP):** cada estrategia y observador tiene una responsabilidad específica.

## 5. Pruebas y validación

Las pruebas automatizadas contemplan:

- Selección de drones mediante las estrategias de asignación.
- Cambio de estrategia en `GestorMisiones`.
- Gestión de suscripciones y notificaciones de cambios de estado.
- Incorporación de un cuarto observador sin modificar `GestorFlota`.

Para ejecutar todas las pruebas del proyecto, desde la raíz del repositorio, utilizar:

```bash
mvn clean test
```

La validación global se considera satisfactoria cuando Maven finaliza con `BUILD SUCCESS`.

## 6. Evidencia del reto

La captura de evidencia se almacena en la siguiente ruta del repositorio:

![Evidencia del Reto 3](docs/evidencias/Reto3.png)

La evidencia debe mostrar la ejecución satisfactoria de las pruebas relacionadas con Strategy y Observer, incluida la prueba que verifica la incorporación del cuarto observador.

## Resultado

El reto permite aplicar dos patrones de diseño para separar los criterios de asignación de drones y desacoplar las notificaciones de cambios de estado. La solución facilita la extensión del sistema y reduce la necesidad de modificar clases existentes cuando se incorporan nuevos comportamientos.

## Reto 4. Aplicación de principios SOLID

### 1. Comparación entre el MVP y la arquitectura v2

#### SRP — Single Responsibility Principle (Responsabilidad única)

- **MVP:** `GestorDrone` violaba este principio porque concentraba múltiples responsabilidades, como asignar misiones, conectarse a MySQL, enviar correos, generar reportes PDF y calcular rutas.
- **Arquitectura v2:** las responsabilidades se distribuyeron en clases y servicios especializados. Por ejemplo, `AsignadorMision` se encarga de asignar misiones y `SistemaLog` de registrar eventos. De esta manera, cada componente tiene una responsabilidad más específica.

#### OCP — Open/Closed Principle (Abierto/cerrado)

- **MVP:** `GestorDrone` utilizaba condiciones para determinar el comportamiento según el tipo de ruta. Agregar un nuevo tipo requería modificar el código existente.
- **Arquitectura v2:** el patrón Strategy permite incorporar nuevas implementaciones de `EstrategiaAsignacion` sin modificar `GestorMisiones`. Así, el comportamiento puede extenderse mediante nuevas clases.

#### DIP — Dependency Inversion Principle (Inversión de dependencias)

- **MVP:** `GestorDrone` dependía directamente de detalles de infraestructura, como `DriverManager` y la conexión concreta a MySQL, lo que aumentaba el acoplamiento.
- **Arquitectura v2:** los componentes utilizan abstracciones como `EstrategiaAsignacion` y `ObservadorDrone`. Esto permite depender de contratos en lugar de acoplarse directamente a implementaciones concretas.

### 2. Evidencia de la prueba de extensibilidad

Se implementó una prueba automatizada que crea una nueva implementación de `EstrategiaAsignacion`, la inyecta en `GestorMisiones` y verifica que el gestor utiliza esa estrategia correctamente, sin necesidad de modificar su código.

**Captura de evidencia:**

![Evidencia del Reto 4](docs/evidencias/Reto4.png)
