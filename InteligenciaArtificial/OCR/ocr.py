from types import *
from typing import *
from PIL import Image
import numpy as np

DATA_DIR = "./data"

DEBUG = False

TEST_DIR = 'test/'
TEST_DATA_FILENAME = DATA_DIR + '/t10k-images-idx3-ubyte'
TEST_LABELS_FILENAME = DATA_DIR + '/t10k-labels-idx1-ubyte'
TRAIN_DATA_FILENAME = DATA_DIR + '/train-images-idx3-ubyte'
TRAIN_LABELS_FILENAME = DATA_DIR + '/train-labels-idx1-ubyte'


def bytes_to_int(byte_data) -> int:
    return int.from_bytes(byte_data, 'big')

def read_image(path):
    return np.asarray(Image.open(path).convert('L'))

def leer_imagenes(filename, limit_n_images) -> List[List[List[int]]]:
    images = []
    with open(filename, 'rb') as f:
        _ = f.read(4)  # Magic number ignoreado
        n_images = min(bytes_to_int(f.read(4)), limit_n_images)
        n_row = bytes_to_int(f.read(4))
        n_col = bytes_to_int(f.read(4))

        for image_idx in range(n_images):
            image: List[List[int]] = []
            for row_index in range(n_row):
                row: List[int] = []
                for col_index in range(n_col):
                    pixel: int = bytes_to_int(f.read(1))
                    row.append(pixel)
                image.append(row)
            images.append(image)

    return images


def leer_labels(filename, limit_n_labeks) -> List[bytes]:
    labels = []
    with open(filename, 'rb') as f:
        _ = f.read(4)  # Magic number ignoreado
        n_labels = bytes_to_int(f.read(4))
        for label_oodx in range(min(n_labels, limit_n_labeks)):
            label = f.read(1)
            labels.append(label)

    return labels


def aplanar_doble_lista(lista: List[List[any]]) -> List[int]:
    return [item for sublist in lista for item in sublist]


def aplanar_listas_de_dobles_listas(lista: List[List[List[any]]]) -> List[List[any]]:
    return [aplanar_doble_lista(item) for item in lista]


def distancia(x: List[int], y: List[int]):
    return sum(
        [(x_i - y_i) ** 2 for x_i, y_i in zip(x, y)]
    ) ** 0.5


def get_distancias_con_lista_entrenamiento(x_entrenamiento: List[List[int]], x_probar: List[int]):
    return [distancia(x_entrenamiento_item, x_probar) for x_entrenamiento_item in x_entrenamiento]


def get_elementos_mas_frequentes(lista):
    return max(lista, key=lista.count)


def knn(x_entrenamiento: List[List[int]], y_entrenamiento: List[bytes], x_probar: List[List[int]], k: int):
    y_predecciones = []
    for x_probar_index, x_probar in enumerate(x_probar):
        distancias_probar_con_entrenamiento = get_distancias_con_lista_entrenamiento(x_entrenamiento, x_probar)

        # Ordenamos distancias_probar_con_entrenamiento y sacamos sus indices
        distancias_ordenadas_indices = [par[0] for par in sorted(enumerate(distancias_probar_con_entrenamiento), key=lambda x: x[1])]

        # Sacamos los elementos hasta k
        candidatos = [
            y_entrenamiento[idx]
            for idx in distancias_ordenadas_indices[:k]
        ]

        # Sacamos los elementos más frecuentes
        top_candidatos = get_elementos_mas_frequentes(candidatos)
        y_predecciones.append(top_candidatos)

        print(f'Predicciones: {bytes_to_int(candidatos[0])}, {bytes_to_int(candidatos[1])}, {bytes_to_int(candidatos[2])}')
        print(f'Prediccion final: {top_candidatos[0]}')

    return y_predecciones


def main():
    x_entrenamiento = leer_imagenes(TRAIN_DATA_FILENAME, 1000)  # Imagenes dataset
    y_entrenamiento = leer_labels(TRAIN_LABELS_FILENAME, 1000)  # Numeros de las imagenes
    X_test = [read_image("test.png")]  # Leemos input

    # Aplanamos array de arrays de 2 dimensiones en una solo array.
    # Por ejemplo: [[[1, 2], [2, 1]], [[3, 4], [4, 3]]] -> [[1, 2], [2, 1], [3, 4], [4, 3]]
    x_entrenamiento = aplanar_listas_de_dobles_listas(x_entrenamiento)
    X_test = aplanar_listas_de_dobles_listas(X_test)

    knn(x_entrenamiento, y_entrenamiento, X_test, 3)


if __name__ == '__main__':
    main()
