### Diagrama de clases
[Diagrama de clases](diagramas/Clases.png)

https://docs.google.com/spreadsheets/d/1jAaldm2KdGVpMWjn9ixDJkPq4mqZVSDKXi6XWjTEUh0/edit#gid=0

```
class Mundo
abstract class Entidad
abstract class Bloque
class Main
class Simulador
class RenderizadorMundo
class MovedorEntidadesMundo
interface SeMueveSolo
interface SePuedeRenderizar

enum Direccion {
   ARRIBA (0, 1)
   ABAJO (0, -1)
   IZQUIERDA (-1, 0)
   DERECHA (1, 0)
}

RenderizadorMundo : void renderizar(Mundo mundo)
SeMueveSolo : Vector getVectorMovimientoSolo(Entidad entidad)
SePuedeRenderizar : String getDibujo(Mundo mundo)

Main --> Simulador  : Crea y inicia

Simulador : void simular(int numeroBarcas, int numeroAlfombras, int numeroCaballos, int numeroNPCs)

Simulador --> Mundo 

Mundo --> Bloque : esta formado por
Mundo --> RenderizadorMundo : Contiene

Bloque : double velocidad()
Bloque : boolean puedeTransitar(Entidad entidad)

MovedorEntidadesMundo : void mover(Entidad, Vector)

Mundo -> Entidad : contiene
Mundo --> InputUsuario : Lee
Mundo --> MovedorEntidadesMundo : mueve
Mundo : RenderizadorMundo renderizador
Mundo : MovedorEntidadesMundo movedor
Mundo : InputUsuario inputUsuario
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

Bloque --|> SePuedeRenderizar
Entidad --|>  SePuedeRenderizar

class InputUsuario
InputUsuario : String leer()
```

### Diagrama de colaboración
[Diagrama de colaboracion](diagramas/colaboracion.png)
```
actor Usuario
control Main
entity Mundo
entity Entidad
entity Bloque
entity RenderizadorMundo
entity MovedorEntidadesMundo
entity InputUsuario

Main --> Mundo : crea

Mundo --> (InputUsuario) : Leer opccion usuario
Mundo --> RenderizadorMundo : Renderiza
Mundo --> MovedorEntidadesMundo : Manda mover
MovedorEntidadesMundo --> Entidad : Actualiza posicion
MovedorEntidadesMundo --> Bloque : ¿Me puedo mover?
Usuario --> InputUsuario : Introducce opccion


@enduml
```
