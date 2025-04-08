package com.mycompany.exp2_s4_diego_cruz;

import java.util.Scanner;

public class Exp2_S4_Diego_Cruz {

    public static void main(String[] args) {
        //Variables y scanner
        Scanner scanner = new Scanner(System.in);
        String[] opcionesMenu = {"Comprar entrada", "Salir"};
        boolean salir = false;
        double precioBase = 10000;
        double totalVentas = 0;

        // Al mover este item arriba del ciclo while, podemos conservar la información en caso de multiples compras.
        int numSectores = 3;
        int numAsientos = 10;
        boolean[] sector1 = new boolean[numAsientos];
        boolean[] sector2 = new boolean[numAsientos];
        boolean[] sector3 = new boolean[numAsientos];

        // Ciclo principal 
        while (!salir) {
            // Menú principal sistema
            System.out.println("==== MENU PRINCIPAL ====");
            for (int i = 0; i < opcionesMenu.length; i++) {
                System.out.println((i + 1) + ". " + opcionesMenu[i]);
            }
            System.out.println("Elija una opcion (1-2): ");
            int opcion = scanner.nextInt();

            // Casos según opción
            switch (opcion) {
                case 1: {
                    System.out.println("Bienvenid@ a nuestro sistema de venta.");
                    int sectorSeleccionado = -1;
                    int asientoSeleccionado = -1;

                    //Proceso seleccion sector y asiento
                    while (sectorSeleccionado != 0) {
                        System.out.println("Plano de asientos disponibles (X = ocupado, 0 = disponible):");
                        for (int s = 1; s <= numSectores; s++) {
                            boolean[] sectorTemporal;
                            if (s == 1) {
                                sectorTemporal = sector1;
                            } else if (s == 2) {
                                sectorTemporal = sector2;
                            } else {
                                sectorTemporal = sector3;
                            }
                            System.out.print("Sector " + s + ": ");
                            for (int a = 0; a < numAsientos; a++) {
                                System.out.print(sectorTemporal[a] ? "[X]" : "[0]");
                            }
                            System.out.println();
                        }

                        System.out.println("Seleccione un sector (1-" + numSectores + ") o ingrese 0 para cancelar la compra:");
                        if (scanner.hasNextInt()) {
                            sectorSeleccionado = scanner.nextInt();
                        } else {
                            scanner.next();
                            System.out.println("Por favor, ingrese un numero valido.");
                            continue;
                        }
                        if (sectorSeleccionado == 0) {
                            break;
                        }
                        if (sectorSeleccionado < 1 || sectorSeleccionado > numSectores) {
                            System.out.println("Sector invalido. Intente de nuevo.");
                            continue;
                        }

                        System.out.println("Ha seleccionado el Sector " + sectorSeleccionado + ".");
                        boolean[] sectorActual;
                        if (sectorSeleccionado == 1) {
                            sectorActual = sector1;
                        } else if (sectorSeleccionado == 2) {
                            sectorActual = sector2;
                        } else {
                            sectorActual = sector3;
                        }

                        boolean volver = false;
                        while (!volver) { //ciclo compra de asientos con validación
                            System.out.println("Asientos disponibles en el Sector " + sectorSeleccionado + ":");
                            for (int a = 0; a < numAsientos; a++) {
                                System.out.print((a + 1) + ": " + (sectorActual[a] ? "[X] " : "[0] "));
                            }
                            System.out.println();
                            System.out.println("Ingrese el numero de asiento (1-" + numAsientos + ") o 0 para volver:");
                            if (scanner.hasNextInt()) {
                                int asiento = scanner.nextInt();
                                if (asiento == 0) {
                                    volver = true;
                                } else if (asiento < 1 || asiento > numAsientos) {
                                    System.out.println("Numero de asiento invalido. Intente de nuevo.");
                                } else {
                                    int indiceAsiento = asiento - 1;
                                    if (sectorActual[indiceAsiento]) {
                                        System.out.println("El asiento esta ocupado. Seleccione otro.");
                                    } else {
                                        sectorActual[indiceAsiento] = true;
                                        asientoSeleccionado = asiento;
                                        System.out.println("Asiento " + asiento + " reservado exitosamente.");

                                        // Descuento por edad
                                        int edad;
                                        double descuento = 0;
                                        boolean edadValida = false;
                                        while (!edadValida) { //validación edad
                                            System.out.println("Ingrese su edad:");
                                            if (scanner.hasNextInt()) {
                                                edad = scanner.nextInt();
                                                if (edad > 0) {
                                                    if (edad < 18) {
                                                        descuento = 1.0;
                                                        System.out.println("Descuento aplicado: 100% (Menores de 18 años ingresan gratis).");
                                                    } else if (edad >= 60) {
                                                        descuento = 0.15;
                                                        System.out.println("Descuento aplicado: 15% (Tercera edad).");
                                                    } else if (edad >= 18 && edad <= 25) {
                                                        descuento = 0.10;
                                                        System.out.println("Descuento aplicado: 10% (Estudiante).");
                                                    } else {
                                                        System.out.println("No se aplica descuento. Precio normal.");
                                                    }
                                                    edadValida = true;
                                                } else {
                                                    System.out.println("Error: la edad debe ser un numero positivo.");
                                                }
                                            } else {
                                                scanner.next();
                                                System.out.println("Error: ingrese un numero valido.");
                                            }
                                        }

                                        // Calcular precio final
                                        double precioFinal = 0;
                                        do {
                                            precioFinal = precioBase * (1 - descuento);
                                        } while (false);

                                        // Si se compra más de una vez
                                        totalVentas += precioFinal;

                                        
                                        System.out.println("----- RESUMEN DE LA COMPRA -----");
                                        System.out.println("Ubicacion del asiento: Sector " + sectorSeleccionado + ", Asiento " + asientoSeleccionado);
                                        System.out.println("Precio base: " + precioBase);
                                        System.out.println("Descuento aplicado: " + (descuento * 100) + "%");
                                        System.out.println("Precio entrada: " + precioFinal);
                                        System.out.println("Total: " + totalVentas);
                                        System.out.println("--------------------------------");

                                        System.out.println("¿Desea realizar otra compra? (S/N)");
                                        String respuesta = scanner.next();
                                        if (respuesta.equalsIgnoreCase("N")) {
                                            salir = true;
                                        }
                                        volver = true;
                                        sectorSeleccionado = 0; 
                                    }
                                }
                            } else {
                                scanner.next();
                                System.out.println("Error: ingrese un número valido.");
                            }
                        } 
                    } 
                    break;
                }
                case 2:
                    salir = true;
                    System.out.println("Muchas gracias. Hasta pronto.");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
            }
        }
    }
}
