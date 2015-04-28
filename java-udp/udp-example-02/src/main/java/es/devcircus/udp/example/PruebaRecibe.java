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
        PruebaRecibe MiClaseRecibe = new PruebaRecibe();
    }

    /**
     * Método que contiene la lógica de recepción del programa.
     */
    public PruebaRecibe() {
        // Definimos el puerto de las comunicaciones y el tiempo máximo del mensaje.
        final int Puerto = 5000;
        final int TamanioMaximoMensaje = 20;
        // Variable en la que vamos a almacenar el mensaje recivido.
        String MensajeRecibido;
        // Instanciamos la clase auxiliar que se va a encargar de la gesetión de
        // las comunicaciones.
        TRecibeUDP InstanciaRecibeUDP = new TRecibeUDP();
        // Creamos el frame de la aplicación.
        Frame Marco = new Frame("EnvioUDP");
        Panel panel = new Panel();
        // Creamos el componente donde vamos a mostrar el mensaje recivido.
        Label EtiquetaMensaje = new Label("Mensaje:");
        Label PosicionXY = new Label("");
        // Configuramos la interfaz y añadimos los elementos.
        Marco.setSize(200, 80);
        panel.setLayout(new GridLayout(2, 1));
        Marco.add(panel);
        panel.add(EtiquetaMensaje);
        panel.add(PosicionXY);
        // Mostramos la interfaz.
        Marco.show();
        // Mientras que el mensaje no tenga una longitud igual a cero, seguimos
        // escuchando a la espera de recivir nuevos mensajes.
        do {
            // Esperamos la recepción del mensaje.
            MensajeRecibido = InstanciaRecibeUDP.Recibe(Puerto, TamanioMaximoMensaje);
            try {
                // Procesamos el mensaje y lo colocamos en la interfaz.
                int i = MensajeRecibido.indexOf(" ");
                if (i != -1 && MensajeRecibido.indexOf("*") == 0) {
                    String X = MensajeRecibido.substring(1, i);
                    String Y = MensajeRecibido.substring(i + 1, MensajeRecibido.length());
                    PosicionXY.setText("(" + X + ", " + Y + ")");
                } else {
                    EtiquetaMensaje.setText("Mensaje: " + MensajeRecibido);
                }
            } catch (Exception e) {
            }
        } while (MensajeRecibido.length() != 0);
        // Salimos del programa.
        System.exit(1);
    }
}
