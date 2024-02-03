import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[][] tablero = new String[9][9];
        Scanner teclado = new Scanner(System.in);
        String ANSI_BLACK = "\u001B[37m";
        String ANSI_BROWN = "\u001B[33m";
        String ANSI_WHITE = "\u001B[97m";
        String ANSI_RESET = "\u001B[0m";
        String espacioLibre = ANSI_BROWN + "L" + ANSI_RESET;
        String fichaBlanca = ANSI_WHITE + "W" + ANSI_RESET;
        String fichaNegra = ANSI_BLACK + "B" + ANSI_RESET;
        int contadorBlancas = 0;
        int contadorNegras = 0;
        int contadorLibres = 0;
        int movBlancasNormal;
        int movNegrasNormal;
        int movNormalDerecha;
        int movNormalIzquierda;
        int movBlancasComer;
        int movNegrasComer;
        int movComerDerecha;
        int movComerIzquierda;
        int posicionInicioX;
        int posicionInicioY;
        String posInicialXY;
        int direccion;
        int movCorrecto = 0;
        int turnosPasadosBlancas = 0;
        int turnosPasadosNegras = 0;
        int consecutivosPasados = 0;

        // Asignar encabezado numérico
        for (int j = 0; j < tablero.length; j++)
            tablero[0][j] = String.valueOf(j);
        for (int i = 0; i < tablero.length; i++)
            tablero[i][0] = String.valueOf(i);


        // Asignar las casillas al tablero (todas libres al inicio)
        for (int i = 1; i < tablero.length; i++)
            for (int j = 1; j < tablero[0].length; j++)
                tablero[i][j] = espacioLibre;



        // Asignar BLANCAS
        for (int i = 1; i < 4; i += 2)
            for (int j = 2; j < tablero.length; j += 2)
                tablero[i][j] = fichaBlanca;
        for (int j = 1; j < tablero.length; j += 2)
            tablero[2][j] = fichaBlanca;



        // Asignar NEGRAS
        for (int i = 6; i < tablero.length; i += 2)
            for (int j = 1; j < tablero.length; j += 2)
                tablero[i][j] = fichaNegra;
        for (int j = 2; j < tablero.length; j += 2)
            tablero[7][j] = fichaNegra;



        // Pinta el tablero justo antes de empezar el juego
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }



        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j] == fichaBlanca) {
                    contadorBlancas = contadorBlancas + 1;
                }
                else if (tablero[i][j] == fichaNegra) {
                    contadorNegras = contadorNegras + 1;
                }
                else if (tablero[i][j] == espacioLibre) {
                    contadorLibres = contadorLibres + 1;
                }
            }
        }

        //Elige nombre del Jugador que llevará las fichas blancas
        System.out.println("Las blancas empiezan.");
        System.out.println("Elige nombre del Jugador que llevará las fichas blancas.");
        String nombreJugadorBlancas = teclado.nextLine();


        //Elige nombre del Jugador que llevará las fichas negras
        System.out.println("Las negras jugarán segundas.");
        System.out.println("Elige nombre del Jugador que llevará las fichas negras.");
        String nombreJugadorNegras = teclado.nextLine();


        String turnoDe = nombreJugadorBlancas;
        System.out.println("Turno de " + turnoDe + " con las blancas.");
        System.out.println("Puedes pasar hasta 3 veces de turno en una partida antes de perderla.");
        System.out.println("Si dos jugadores pasan consecutivamente, finaliza el juego.");
        System.out.println("¿Deseas pasar de turno? y/n");
        String pasarTurno = teclado.nextLine();
        while (pasarTurno.equals("y")) {
            if (turnoDe.equals(nombreJugadorBlancas)) {
                ++turnosPasadosBlancas;
                ++consecutivosPasados;
                System.out.println("Las Blancas han pasado " + turnosPasadosBlancas + " veces.");
                turnoDe = nombreJugadorNegras;
                System.out.println("Turno de " + turnoDe + " con las negras.");
                System.out.println("Puedes pasar hasta 3 veces de turno en una partida antes de perderla.");
                System.out.println("Si dos jugadores pasan consecutivamente, finaliza el juego.");
                System.out.println("¿Deseas pasar de turno? y/n");
                pasarTurno = teclado.nextLine();
            } else if (turnoDe.equals(nombreJugadorNegras)) {
                ++turnosPasadosNegras;
                ++consecutivosPasados;
                System.out.println("Las Negras han pasado " + turnosPasadosNegras + " veces.");
                turnoDe = nombreJugadorBlancas;
                System.out.println("Turno de " + turnoDe + " con las blancas.");
                System.out.println("Puedes pasar hasta 3 veces de turno en una partida antes de perderla.");
                System.out.println("Si dos jugadores pasan consecutivamente, finaliza el juego.");
                System.out.println("¿Deseas pasar de turno? y/n");
                pasarTurno = teclado.nextLine();
            }
            ++consecutivosPasados;
            System.out.println(consecutivosPasados + " turnos consecutivos pasados.");
            if (consecutivosPasados == 2) {
                contadorBlancas = 0;
                pasarTurno = "n";
            }
        }


        while  (contadorBlancas > 0 && contadorNegras > 0 && turnosPasadosNegras < 3 && turnosPasadosBlancas < 3) {
            System.out.println("Quedan " + contadorBlancas + " fichas blancas.");
            System.out.println("Quedan " + contadorNegras + " fichas negras.");
            
            while (movCorrecto != 1) {
                    // Elige ficha que vas a mover
                    System.out.println("Elige ficha que vas a mover. Introduce el número de la fila.");
                    posicionInicioX = Integer.parseInt(teclado.nextLine());
                    System.out.println("Ahora introduce el número de la columna.");
                    posicionInicioY = Integer.parseInt(teclado.nextLine());

                    posInicialXY = tablero[posicionInicioX][posicionInicioY];

                    movBlancasNormal = posicionInicioX + 1;
                    movNegrasNormal = posicionInicioX - 1;
                    movNormalDerecha = posicionInicioY + 1;
                    movNormalIzquierda = posicionInicioY - 1;
                    movBlancasComer = posicionInicioX + 2;
                    movNegrasComer = posicionInicioX - 2;
                    movComerDerecha = posicionInicioY + 2;
                    movComerIzquierda = posicionInicioY - 2;

                    //¿Es una ficha del jugador?
                    if (turnoDe == nombreJugadorBlancas) {
                        while (posInicialXY != fichaBlanca) {
                            System.out.println("La ficha en ese espacio no es blanca.");
                            posInicialXY = tablero[posicionInicioX][posicionInicioY];
                            // Elige ficha que vas a mover
                            System.out.println("Elige ficha que vas a mover. Introduce el número de la fila.");
                            posicionInicioX = Integer.parseInt(teclado.nextLine());
                            System.out.println("Ahora introduce el número de la columna.");
                            posicionInicioY = Integer.parseInt(teclado.nextLine());

                            posInicialXY = tablero[posicionInicioX][posicionInicioY];

                            movBlancasNormal = posicionInicioX + 1;
                            movNegrasNormal = posicionInicioX - 1;
                            movNormalDerecha = posicionInicioY + 1;
                            movNormalIzquierda = posicionInicioY - 1;
                            movBlancasComer = posicionInicioX + 2;
                            movNegrasComer = posicionInicioX - 2;
                            movComerDerecha = posicionInicioY + 2;
                            movComerIzquierda = posicionInicioY - 2;
                        }
                    } else if (turnoDe == nombreJugadorNegras) {
                        while (posInicialXY != fichaNegra) {
                            System.out.println("La ficha en ese espacio no es negra.");
                            posInicialXY = tablero[posicionInicioX][posicionInicioY];
                            // Elige ficha que vas a mover
                            System.out.println("Elige ficha que vas a mover. Introduce el número de la fila.");
                            posicionInicioX = Integer.parseInt(teclado.nextLine());
                            System.out.println("Ahora introduce el número de la columna.");
                            posicionInicioY = Integer.parseInt(teclado.nextLine());

                            posInicialXY = tablero[posicionInicioX][posicionInicioY];

                            movBlancasNormal = posicionInicioX + 1;
                            movNegrasNormal = posicionInicioX - 1;
                            movNormalDerecha = posicionInicioY + 1;
                            movNormalIzquierda = posicionInicioY - 1;
                            movBlancasComer = posicionInicioX + 2;
                            movNegrasComer = posicionInicioX - 2;
                            movComerDerecha = posicionInicioY + 2;
                            movComerIzquierda = posicionInicioY - 2;
                        }
                    } else {
                        System.out.println("Error en esfichadeljugador");
                        // Elige ficha que vas a mover
                        System.out.println("Elige ficha que vas a mover. Introduce el número de la fila.");
                        posicionInicioX = Integer.parseInt(teclado.nextLine());
                        System.out.println("Ahora introduce el número de la columna.");
                        posicionInicioY = Integer.parseInt(teclado.nextLine());

                        posInicialXY = tablero[posicionInicioX][posicionInicioY];

                        movBlancasNormal = posicionInicioX + 1;
                        movNegrasNormal = posicionInicioX - 1;
                        movNormalDerecha = posicionInicioY + 1;
                        movNormalIzquierda = posicionInicioY - 1;
                        movBlancasComer = posicionInicioX + 2;
                        movNegrasComer = posicionInicioX - 2;
                        movComerDerecha = posicionInicioY + 2;
                        movComerIzquierda = posicionInicioY - 2;
                    }

                direccion = 3;
                do {
                    System.out.println("Pulsa 1 si quieres ir a Izquierda o 2 si quieres ir a derecha.");
                    direccion = Integer.parseInt(teclado.nextLine());
                } while (!((direccion == 1) || (direccion == 2)));

                if (direccion == 1) /* Mov a Izq */ {
                    if (turnoDe == nombreJugadorBlancas) {
                        if (tablero[movBlancasNormal][movNormalIzquierda] == espacioLibre) {
                            tablero[movBlancasNormal][movNormalIzquierda] = fichaBlanca;
                            tablero[posicionInicioX][posicionInicioY] = espacioLibre;
                            movCorrecto = 1;
                            System.out.println("Blancas mueven hacia la Izquierda.");
                        } else if (tablero[movBlancasNormal][movNormalIzquierda] == fichaNegra && tablero[movBlancasComer][movComerIzquierda] == espacioLibre) {
                            tablero[movBlancasComer][movComerIzquierda] = fichaBlanca;
                            tablero[movBlancasNormal][movNormalIzquierda] = espacioLibre;
                            tablero[posicionInicioX][posicionInicioY] = espacioLibre;
                            contadorNegras = contadorNegras - 1;
                            movCorrecto = 1;
                            System.out.println("Blancas comen hacia la izquierda.");
                        } else {
                            System.out.println("Movimiento incorrecto de Blancas a la Izquierda. Vuelve a elegir ficha y movimiento");
                            movCorrecto = 0;
                        }
                    } else /*Turno de Negras*/ {
                        if (tablero[movNegrasNormal][movNormalIzquierda] == espacioLibre) {
                            tablero[movNegrasNormal][movNormalIzquierda] = fichaNegra;
                            tablero[posicionInicioX][posicionInicioY] = espacioLibre;
                            movCorrecto = 1;
                            System.out.println("Negras mueven hacia la Izquierda.");
                        } else if (tablero[movNegrasNormal][movNormalIzquierda] == fichaBlanca && tablero[movNegrasComer][movComerIzquierda] == espacioLibre) {
                            tablero[movNegrasComer][movComerIzquierda] = fichaNegra;
                            tablero[movNegrasNormal][movNormalIzquierda] = espacioLibre;
                            tablero[posicionInicioX][posicionInicioY] = espacioLibre;
                            contadorBlancas = contadorBlancas - 1;
                            movCorrecto = 1;
                            System.out.println("Negras comen hacia la Izquierda.");
                        } else {
                            System.out.println("Movimiento incorrecto de Negras a la Izquierda. Vuelve a elegir ficha y movimiento");
                            movCorrecto = 0;
                        }
                    }
                } else /*Mov a derecha */ {
                    if (turnoDe == nombreJugadorBlancas) {
                        if (tablero[movBlancasNormal][movNormalDerecha] == espacioLibre) {
                            tablero[movBlancasNormal][movNormalDerecha] = fichaBlanca;
                            tablero[posicionInicioX][posicionInicioY] = espacioLibre;
                            movCorrecto = 1;
                            System.out.println("Blancas mueven hacia la Derecha.");
                        } else if (tablero[movBlancasNormal][movNormalDerecha] == fichaNegra && tablero[movBlancasComer][movComerDerecha] == espacioLibre) {
                            tablero[movBlancasComer][movComerDerecha] = fichaBlanca;
                            tablero[movBlancasNormal][movNormalDerecha] = espacioLibre;
                            tablero[posicionInicioX][posicionInicioY] = espacioLibre;
                            contadorNegras = contadorNegras - 1;
                            movCorrecto = 1;
                            System.out.println("Blancas comen hacia la Derecha.");
                        } else {
                            System.out.println("Movimiento incorrecto de Blancas a la Derecha. Vuelve a elegir ficha y movimiento");
                            movCorrecto = 0;
                        }
                    } else /*Turno de Negras*/ {
                        if (tablero[movNegrasNormal][movNormalDerecha] == espacioLibre) {
                            tablero[movNegrasNormal][movNormalDerecha] = fichaNegra;
                            tablero[posicionInicioX][posicionInicioY] = espacioLibre;
                            movCorrecto = 1;
                            System.out.println("Negras mueven hacia la Derecha.");
                        } else if (tablero[movNegrasNormal][movNormalDerecha] == fichaBlanca && tablero[movNegrasComer][movComerDerecha] == espacioLibre) {
                            tablero[movNegrasComer][movComerDerecha] = fichaNegra;
                            tablero[movNegrasNormal][movNormalDerecha] = espacioLibre;
                            tablero[posicionInicioX][posicionInicioY] = espacioLibre;
                            contadorBlancas = contadorBlancas - 1;
                            movCorrecto = 1;
                            System.out.println("Negras comen hacia la Derecha.");
                        } else {
                            System.out.println("Movimiento incorrecto de Negras a la Derecha. Vuelve a elegir ficha y movimiento");
                            movCorrecto = 0;
                        }
                    }
                }

            }

            // Pinta el tablero
            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero[0].length; j++) {
                    System.out.print(tablero[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();


            if (contadorBlancas == 0) {
                System.out.println("El jugador de las Blanca ha perdido. Fin de la partida.");
            } else if (contadorNegras == 0) {
                System.out.println("El jugador de las Negras ha perdido. Fin de la partida.");
            } else {
                if (turnoDe == nombreJugadorBlancas) {
                    turnoDe = nombreJugadorNegras;
                    System.out.println("Turno de " + turnoDe + " con las negras.");
                    System.out.println("Puedes pasar hasta 3 veces de turno en una partida antes de perderla.");
                    System.out.println("Si dos jugadores pasan consecutivamente, finaliza el juego.");
                    System.out.println("¿Deseas pasar de turno? y/n");
                    pasarTurno = teclado.nextLine();

                    while (pasarTurno.equals("y")) {
                        if (turnoDe.equals(nombreJugadorBlancas)) {
                            ++turnosPasadosBlancas;
                            ++consecutivosPasados;
                            System.out.println("Las Blancas han pasado " + turnosPasadosBlancas + " veces.");
                            turnoDe = nombreJugadorNegras;
                            System.out.println("Turno de " + turnoDe + " con las negras.");
                            System.out.println("Puedes pasar hasta 3 veces de turno en una partida antes de perderla.");
                            System.out.println("Si dos jugadores pasan consecutivamente, finaliza el juego.");
                            System.out.println("¿Deseas pasar de turno? y/n");
                            pasarTurno = teclado.nextLine();
                        } else if (turnoDe.equals(nombreJugadorNegras)) {
                            ++turnosPasadosNegras;
                            ++consecutivosPasados;
                            System.out.println("Las Negras han pasado " + turnosPasadosNegras + " veces.");
                            turnoDe = nombreJugadorBlancas;
                            System.out.println("Turno de " + turnoDe + " con las blancas.");
                            System.out.println("Puedes pasar hasta 3 veces de turno en una partida antes de perderla.");
                            System.out.println("Si dos jugadores pasan consecutivamente, finaliza el juego.");
                            System.out.println("¿Deseas pasar de turno? y/n");
                            pasarTurno = teclado.nextLine();
                        }
                        ++consecutivosPasados;
                        System.out.println(consecutivosPasados + " turnos consecutivos pasados.");
                        if (consecutivosPasados == 2) {
                            contadorBlancas = 0;
                            pasarTurno = "n";
                        }
                    }


                } else if (turnoDe == nombreJugadorNegras) {
                    turnoDe = nombreJugadorBlancas;
                    System.out.println("Turno de " + turnoDe + " con las blancas.");
                    System.out.println("Puedes pasar hasta 3 veces de turno en una partida antes de perderla.");
                    System.out.println("Si dos jugadores pasan consecutivamente, finaliza el juego.");
                    System.out.println("¿Deseas pasar de turno? y/n");
                    pasarTurno = teclado.nextLine();

                    while (pasarTurno.equals("y")) {
                        if (turnoDe.equals(nombreJugadorBlancas)) {
                            ++turnosPasadosBlancas;
                            ++consecutivosPasados;
                            System.out.println("Las Blancas han pasado " + turnosPasadosBlancas + " veces.");
                            turnoDe = nombreJugadorNegras;
                            System.out.println("Turno de " + turnoDe + " con las negras.");
                            System.out.println("Puedes pasar hasta 3 veces de turno en una partida antes de perderla.");
                            System.out.println("Si dos jugadores pasan consecutivamente, finaliza el juego.");
                            System.out.println("¿Deseas pasar de turno? y/n");
                            pasarTurno = teclado.nextLine();
                        } else if (turnoDe.equals(nombreJugadorNegras)) {
                            ++turnosPasadosNegras;
                            ++consecutivosPasados;
                            System.out.println("Las Negras han pasado " + turnosPasadosNegras + " veces.");
                            turnoDe = nombreJugadorBlancas;
                            System.out.println("Turno de " + turnoDe + " con las blancas.");
                            System.out.println("Puedes pasar hasta 3 veces de turno en una partida antes de perderla.");
                            System.out.println("Si dos jugadores pasan consecutivamente, finaliza el juego.");
                            System.out.println("¿Deseas pasar de turno? y/n");
                            pasarTurno = teclado.nextLine();
                        }
                        ++consecutivosPasados;
                        System.out.println(consecutivosPasados + " turnos consecutivos pasados.");
                        if (consecutivosPasados == 2) {
                            contadorBlancas = 0;
                            pasarTurno = "n";
                        }
                    }


                } else {
                    System.out.println("Error con turnoDE en mensajeFIN.");
                }
            }
            movCorrecto = 0;
        }

    }
}
