### Diagrama de clases
[Diagrama de clases](diagramas/clases.png)
```
@startuml
class Mundo
abstract class Entidad
abstract class Bloque
class Main
class RenderizadorMundo
interface SeMueveSolo
enum Direccion {
   ARRIBA (0, 1)
   ABAJO (0, -1)
   IZQUIERDA (-1, 0)
   DERECHA (1, 0)
}

RenderizadorMundo : void renderizar(Mundo mundo)
SeMueveSolo : Vector getVectorMovimientoSolo(Entidad entidad)

Main --> Mundo : Crea y inicia

Mundo --> Bloque : esta formado por
Mundo --> RenderizadorMundo : Contiene

Bloque : double velocidad()
Bloque : boolean todasEntidadesPuedenTransitar()
Bloque : List<tipo Entidad> soloTrasitableCon()

Mundo -> Entidad : contiene
Mundo : RenderizadorMundo renderizador
Mundo : int tiempoTranscurrido
Mundo : Bloque[][] bloques
Mundo : Entidad[] entidades
Mundo : Personaje personaje
Mundo : Mundo(Bloque[][], Entidad[], RenderizadorMundo)
Mundo : void iniciar()
Mundo : void desmontarsePersonaje()
Mundo : void moverPersonaje(Direccion direccion)
Mundo : Bloque getBloque(Posicion posicion)
Mundo : Entidad getEntidad(Posicion posicion)

Mundo : void leerInputUsuarioYAplicar()
Mundo : void actualizarEntidades()
Mundo : boolean mover(Entidad entidad, Vector vector)

Entidad : Posicion posicion
Entidad : Entidad entidadSobreLaQueEstoyMontado
Entidad : Entidad entidadMontadaSobreMi
Entidad : boolean puedeMontarseEnOtraEntidad()
Entidad : boolean otraEntidadPuedeMontarse()
Entidad : void desmontarse()
Entidad : void montarme(Entidad entidad)
Entidad : void setPosicion(Posicion posicion)

Entidad <|-- NPC
Entidad <|-- Personaje
Entidad <|-- Barca
Entidad <|-- Alfombra
Entidad <|-- Caballo
NPC --|> SeMueveSolo
@enduml
```

### Diagrama de colaboración
[Diagrama de colaboracion](diagramas/colaboracion.png)
```
@startuml
actor Personaje
control Main
entity Entidad
entity Bloque
entity RenderizadorMundo


Personaje --> (Mundo) : 4.Pulsa tecla para moverse y \nse actualizan las posiciones de las entidades
Main --> (Mundo) : 1. Crea lista entidades\n2.Crea bloques\n3.Pasa renderizor mundo
Mundo --> Bloque : 5. Me puedo mover?
Mundo --> Personaje: 6. Cambiar posicion
Personaje --> Entidad: 7. Si estoy montado, \ncambio de posicion de la entidad sobre la que se estoy montando.
Mundo --> RenderizadorMundo: 8. Renderizar mundo

@enduml
```
