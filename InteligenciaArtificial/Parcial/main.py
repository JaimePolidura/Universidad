import math
import sys
import time

from CreadorMapa import convertirImagenAMatriz
from Robot import Robot
import matplotlib.pyplot as plt
import pygame
import numpy as np
from collections import deque
from Casilla import *
from typing import *

HEIGHT = 500
WIDTH = 750

RED = (255, 0, 0)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)
YELLOW = (255, 255, 0)
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
GREY = (105, 105, 105)


""" 957 Iteraciones """

ruta_imagen = "./Imagenes/parcial.bmp"
mapaGlobal = convertirImagenAMatriz(ruta_imagen)

filas = len(mapaGlobal)
columnas = len(mapaGlobal[0])

tamano_casilla = 5

pantalla = pygame.display.set_mode((columnas*tamano_casilla, filas*tamano_casilla))
pygame.display.set_caption("Matriz de Casillas")

radioVision = []
for i in range(-20, 20):
    for j in range(-20, 20, 1):
        # Evita agregar el punto (0, 0) a la lista de direcciones
        if (i, j) != (0, 0):
            # Calcula la distancia desde el punto central (0, 0) hasta el punto actual (i, j)
            distance = math.sqrt(i ** 2 + j ** 2)
            # Si la distancia es menor o igual al radio
            if distance < 20:
                radioVision.append((i, j))
campoVisionParaVerOtroRobot = 10

"""robot1 = Robot(mapaGlobal, (27, 27), radioVision)
robot2 = Robot(mapaGlobal, (92, 7), radioVision)
robot3 = Robot(mapaGlobal, (147, 9), radioVision)
robot4 = Robot(mapaGlobal, (96, 43), radioVision)
robot5 = Robot(mapaGlobal, (98, 43), radioVision)
robot6 = Robot(mapaGlobal, (27, 78), radioVision)
robot7 = Robot(mapaGlobal, (94, 67), radioVision)
robot8 = Robot(mapaGlobal, (86, 97), radioVision)
mapaGlobal[1][92].tipoObjetivo = TipoObjetivo.LIBRE
mapaGlobal[21][16].tipoObjetivo = TipoObjetivo.LIBRE
mapaGlobal[1][58].tipoObjetivo = TipoObjetivo.LIBRE
mapaGlobal[6][108].tipoObjetivo = TipoObjetivo.LIBRE
mapaGlobal[29][94].tipoObjetivo = TipoObjetivo.LIBRE
mapaGlobal[55][55].tipoObjetivo = TipoObjetivo.LIBRE
mapaGlobal[66][97].tipoObjetivo = TipoObjetivo.LIBRE
mapaGlobal[79][131].tipoObjetivo = TipoObjetivo.LIBRE
mapaGlobal[83][61].tipoObjetivo = TipoObjetivo.LIBRE
mapaGlobal[85][1].tipoObjetivo = TipoObjetivo.LIBRE"""

robot1 = Robot(mapaGlobal, (146, 4), radioVision)
robot2 = Robot(mapaGlobal, (146, 6), radioVision)
robot8 = Robot(mapaGlobal, (146, 8), radioVision)
robot3 = Robot(mapaGlobal, (40, 11), radioVision)
robot4 = Robot(mapaGlobal, (80, 24), radioVision)
robot5 = Robot(mapaGlobal, (24, 53), radioVision)
robot6 = Robot(mapaGlobal, (26, 53), radioVision)
robot7 = Robot(mapaGlobal, (96, 85), radioVision)
mapaGlobal[2][2].tipoObjetivo = TipoObjetivo.LIBRE
mapaGlobal[6][142].tipoObjetivo = TipoObjetivo.LIBRE
mapaGlobal[11][37].tipoObjetivo = TipoObjetivo.LIBRE
mapaGlobal[18][121].tipoObjetivo = TipoObjetivo.LIBRE
mapaGlobal[19][80].tipoObjetivo = TipoObjetivo.LIBRE
mapaGlobal[40][116].tipoObjetivo = TipoObjetivo.LIBRE
mapaGlobal[49][91].tipoObjetivo = TipoObjetivo.LIBRE
mapaGlobal[78][47].tipoObjetivo = TipoObjetivo.LIBRE
mapaGlobal[83][94].tipoObjetivo = TipoObjetivo.LIBRE
mapaGlobal[98][116].tipoObjetivo = TipoObjetivo.LIBRE

robots = [robot1, robot2, robot3, robot4, robot5, robot6, robot7, robot8]
# robots = [robot8]
iteraciones = 0
robotsAcabados = 0
nTotalRobots = len(robots)
contadorObjetivos = 0

while True:
    for robot in robots:
        if robot.moverse() is False:
            robots.remove(robot)
            robotsAcabados += 1

            if robotsAcabados == nTotalRobots:
                time.sleep(100)
                exit(1)

        for evento in pygame.event.get():
            if evento.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
        # Limpiar la pantalla
        pantalla.fill((255, 255, 255))

        # Dibujar la matriz en la pantalla
        for y in range(len(mapaGlobal)):
            for x in range(len(mapaGlobal[y])):
                casilla = mapaGlobal[y][x]

                if casilla.tipo is TipoCasilla.PARED:
                    color = BLACK
                elif (x, y) == robot.coordenadas and mapaGlobal[y][x].tipoObjetivo is TipoObjetivo.LIBRE:
                    mapaGlobal[y][x].tipoObjetivo = TipoObjetivo.CAPTURADO
                    contadorObjetivos +=1
                    color = BLUE
                elif (x, y) == robot.coordenadas:
                    color = BLUE
                elif casilla.tipoObjetivo is TipoObjetivo.LIBRE:
                    color = RED
                elif casilla.tipo is TipoCasilla.NIEBLA:
                    color = GREY
                elif casilla.tipoObjetivo is TipoObjetivo.CAPTURADO:
                    color=GREEN
                elif casilla.tipo is TipoCasilla.VISITADO or casilla.tipo is TipoCasilla.VISIONADA:
                    color = YELLOW
                else:
                    color = WHITE

                pygame.draw.rect(pantalla, color, (x * tamano_casilla, y * tamano_casilla, tamano_casilla, tamano_casilla))

        # Actualizar la pantalla
        pygame.display.flip()
        # time.sleep(0.1)
    iteraciones += 1
    print(iteraciones)

    if contadorObjetivos == 10:
        print("Se han encontrado todos los objetivos")
        break

    robotsIntercambiadoMapas: Set[Tuple[Robot, Robot]] = set()

    for robot in robots:
        for otroRobot in robots:
            if (robot.coordenadas is otroRobot.coordenadas
                    or (robot, otroRobot) in robotsIntercambiadoMapas
                    or (otroRobot, robot) in robotsIntercambiadoMapas):
                continue

            distanciaX = abs(robot.coordenadas[0] - otroRobot.coordenadas[0]) ** 2
            distanciaY = abs(robot.coordenadas[1] - otroRobot.coordenadas[1]) ** 2
            distancia = (distanciaX + distanciaY) ** 0.5

            if distancia <= campoVisionParaVerOtroRobot:
                otroRobot.compartirObjetvivos(robot)
                robot.compartirObjetvivos(robot)

                # No ayuda al rendimiento
                # otroRobot.mezclarMapaLocales(robot)
                # robot.mezclarMapaLocales(otroRobot)

                robotsIntercambiadoMapas.add((robot, otroRobot))
                robotsIntercambiadoMapas.add((otroRobot, otroRobot))
