@startuml
class Mundo
class JuegoLaberinto
abstract class Entidad
abstract class Bloque
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

JuegoLaberinto --> Mundo : Controla
JuegoLaberinto : Mundo mundo
JuegoLaberinto : RenderizadorMundo renderizador
JuegoLaberinto : void iniciar()

Mundo --> Bloque : esta formado por

Bloque : double velocidad()
Bloque : boolean todasEntidadesPuedenTransitar()
Bloque : List soloTrasitableCon()


Mundo -> Entidad : contiene
Mundo : int tiempoTranscurrido
Mundo : Bloque[][] bloques
Mundo : Entidad[] entidades
Mundo : Personaje personaje
Mundo : void insertarEntidades(Lista Entidad)
Mundo : void insertarBloques(Bloques[][] bloques)
Mundo : void moverPersonaje(Direccion direccion)
Mundo : getBloque(Posicion posicion)
Mundo : getEntidad(Posicion posicion)
Mundo : void Tick()
Mundo : void actualizarEntidades()
Mundo : int getAncho()
Mundo : int getLargo
Mundo : List getEntidades()

Entidad : Posicion
Entidad : Mundo
Entidad : Entidad entidadSobreLaQueEstoyMontado
Entidad : Entidad entidadMontadaSobreMi
Entidad : boolean puedeMontarseEnOtraEntidad()
Entidad : boolean otraEntidadPuedeMontarse()
Entidad : void Desmontarse()
Entidad : void Montarme(Entidad entidad)
Entidad : void Mover(Vector vector)

Entidad --> NPC
Entidad --> Personaje
Entidad --> Barca
Entidad --> Alfombra
Entidad --> Caballo
NPC --> SeMueveSolo

@enduml
