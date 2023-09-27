@startuml
[*] --> Login : Trabajador o Gestión
Login --> Login : Validar credenciales
Login --> Home : sign In
Home --> Home : Ver viajes,\n Ver Justificantes,\n Ver solicitudes
Home --> VistaSolicitudes : Abrir solicitudes
Home --> VistaViajes : Abrir viajes
Home --> VistaJustificantes : Abrir Justificantes
Home --> Estadisticas: Abrir Estadisticas
VistaSolicitudes --> VistaSolicitudes : Ver Solicitudes,\n Gestionar solicitudes
VistaSolicitudes --> VistaViajes : Crear viaje
VistaViajes --> VistaViajes : Ver viajes
VistaJustificantes --> VistaJustificantes: Ver justificantes,\n validar justificantes,\n Registrar justificante
Estadisticas --> Estadisticas : Ver estadisticos,\n Generar estadisticas
VistaViajes --> VistaJustificantes : Abrir justificantes
Home --> [*]: LogOut
VistaSolicitudes --> [*]: LogOut
VistaViajes --> [*]: LogOut
VistaJustificantes --> [*]: LogOut
Estadisticas --> [*]: LogOut
VistaViajes --> [*]: LogOut
@enduml
