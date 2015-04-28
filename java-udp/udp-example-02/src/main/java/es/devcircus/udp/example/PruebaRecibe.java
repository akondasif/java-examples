/**
 * This file is part of Java Examples.
 *
 * Java Examples is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2, or (at your option) any later version.
 *
 * Java Examples is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; see the file COPYING. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package es.devcircus.udp.example;

import es.devcircus.udp.example.util.TRecibeUDP;
import java.awt.*;

/**
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
public class PruebaRecibe extends Object {

    /**
     * Método principal que lanza la ejecución del programa que se encarga de la
     * recepción de los mensajes.
     *
     * @param args Argumentos del programa.
     */
    public static void main(String[] args) {
        PruebaRecibe miClaseRecibe = new PruebaRecibe();
    }

    /**
     * Método que contiene la lógica de recepción del programa.
     */
    public PruebaRecibe() {
        // Definimos el puerto de las comunicaciones y el tiempo máximo del mensaje.
        final int puerto = 5000;
        final int tamanioMaximoMensaje = 20;
        // Variable en la que vamos a almacenar el mensaje recivido.
        String mensajeRecibido;
        // Instanciamos la clase auxiliar que se va a encargar de la gesetión de
        // las comunicaciones.
        TRecibeUDP instanciaRecibeUDP = new TRecibeUDP();
        // Creamos el frame de la aplicación.
        Frame marco = new Frame("EnvioUDP");
        Panel panel = new Panel();
        // Creamos el componente donde vamos a mostrar el mensaje recivido.
        Label etiquetaMensaje = new Label("Mensaje:");
        Label posicionXY = new Label("");
        // Configuramos la interfaz y añadimos los elementos.
        marco.setSize(200, 80);
        panel.setLayout(new GridLayout(2, 1));
        marco.add(panel);
        panel.add(etiquetaMensaje);
        panel.add(posicionXY);
        // Mostramos la interfaz.
        marco.show();
        // Mientras que el mensaje no tenga una longitud igual a cero, seguimos
        // escuchando a la espera de recivir nuevos mensajes.
        do {
            // Esperamos la recepción del mensaje.
            mensajeRecibido = instanciaRecibeUDP.Recibe(puerto, tamanioMaximoMensaje);
            try {
                // Procesamos el mensaje y lo colocamos en la interfaz.
                int i = mensajeRecibido.indexOf(" ");
                if (i != -1 && mensajeRecibido.indexOf("*") == 0) {
                    String X = mensajeRecibido.substring(1, i);
                    String Y = mensajeRecibido.substring(i + 1, mensajeRecibido.length());
                    posicionXY.setText("(" + X + ", " + Y + ")");
                } else {
                    etiquetaMensaje.setText("Mensaje: " + mensajeRecibido);
                }
            } catch (Exception e) {
            }
        } while (mensajeRecibido.length() != 0);
        // Salimos del programa.
        System.exit(1);
    }
}
