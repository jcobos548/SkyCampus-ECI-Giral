# SkyCampus-ECI-Giral
## Reto 6: Requerimientos funcionales, no funcionales y priorización MoSCoW

### 1. Requerimientos funcionales (RF)

Los requerimientos funcionales describen las acciones que el sistema debe permitir realizar a sus usuarios y los resultados observables que debe producir.

| ID | Actor | Requerimiento funcional | Prioridad MoSCoW | Justificación |
|---|---|---|---|---|
| RF-01 | Operador de drones | El sistema debe permitir al operador consultar la lista de drones disponibles y visualizar la batería actual de cada uno. | Must Have | Es indispensable conocer la disponibilidad y batería de la flota para gestionar las misiones manualmente. |
| RF-02 | Solicitante | El sistema debe permitir al solicitante registrar una solicitud de reparto indicando el origen, el destino y el tipo de carga, y mostrar un código de identificación de la solicitud. | Must Have | Registrar solicitudes es una función central del MVP y permite identificar cada petición de reparto. |
| RF-03 | Operador de drones | El sistema debe permitir al operador consultar el historial de misiones del día, mostrando su identificador, origen, destino y estado. | Should Have | Facilita el seguimiento de las operaciones, aunque el MVP puede funcionar inicialmente sin esta consulta histórica. |

### 2. Requerimientos no funcionales (RNF)

Los requerimientos no funcionales establecen condiciones de calidad medibles y verificables para el sistema.

| ID | Requerimiento no funcional | Prioridad MoSCoW | Justificación |
|---|---|---|---|
| RNF-01 | El panel de monitoreo debe reflejar un cambio de estado de un dron en un máximo de 3 segundos, medidos desde que el sistema registra el cambio hasta que aparece en el panel. | Must Have | Permite que el operador consulte información actualizada de la flota durante la asignación manual. |
| RNF-02 | El sistema debe responder a las consultas de disponibilidad de la flota en un máximo de 2 segundos en el 95 % de las solicitudes, bajo una carga de hasta 20 consultas simultáneas y con una flota de hasta 100 drones. | Should Have | Ofrece una respuesta ágil en un escenario de uso definido, sin exigir optimizaciones avanzadas desde la primera versión. |
| RNF-03 | El sistema debe permitir registrar solicitudes de reparto con una tasa de éxito de al menos 99 % en 1.000 intentos de prueba, cuando los datos sean válidos y la base de datos esté disponible. | Must Have | El registro de solicitudes es esencial; las pruebas permiten comprobar que funciona de forma consistente en condiciones controladas. |

**Nota:** Las métricas propuestas para los RNF son objetivos iniciales del MVP. Deben verificarse mediante pruebas y ajustarse si la infraestructura real lo requiere.

### 3. Priorización MoSCoW

La técnica MoSCoW permite clasificar los requerimientos según su importancia para la primera versión del sistema.

- **Must Have:** requerimientos indispensables para que el MVP cumpla sus objetivos principales.
- **Should Have:** requerimientos importantes que pueden aplazarse temporalmente sin impedir el funcionamiento básico.
- **Could Have:** funcionalidades opcionales que aportan valor, pero no son necesarias para la primera versión.
- **Won't Have:** funcionalidades excluidas del alcance actual y reservadas para versiones futuras.

| Categoría | Requerimientos | Justificación |
|---|---|---|
| Must Have | RF-01, RF-02, RNF-01 y RNF-03 | La consulta de la flota, el registro de solicitudes y sus condiciones esenciales de funcionamiento son fundamentales para el MVP. |
| Should Have | RF-03 y RNF-02 | El historial y el rendimiento bajo carga mejoran la operación, pero pueden abordarse después de asegurar las funciones principales. |
| Could Have | Ninguno de los seis requerimientos seleccionados | Se podrían incorporar funcionalidades adicionales, como exportar reportes de misiones, en una iteración posterior. |
| Won't Have | Ninguno de los seis requerimientos seleccionados | La asignación automática y la optimización de rutas quedan fuera del alcance actual del MVP, según lo definido para SkyCampus. |

### 4. Conclusión

La priorización de los requerimientos permite enfocar el desarrollo de SkyCampus MVP en las funcionalidades esenciales: consultar los drones disponibles, registrar solicitudes de reparto y garantizar condiciones mínimas de actualización y registro. Los requerimientos secundarios permiten mejorar el seguimiento y el rendimiento sin ampliar innecesariamente el alcance inicial.

Las funcionalidades de asignación automática y optimización de rutas se mantienen fuera del MVP y se reservan para versiones posteriores del proyecto.