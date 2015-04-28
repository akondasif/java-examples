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

import es.devcircus.udp.example.util.TEnviaUDP;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
public class PruebaEnvia extends Object {

    static final int tamanioMaximoMensaje = 20;
    Frame marco;
    Panel panel;
    Label etiquetaMensaje;
    TextField campoMensaje;
    TextField hostDestino;
    TextField puerto;
    TEnviaUDP enviaFrase;

    /**
     * Método principal que se encarga de lanzar el programa que envía los
     * mensajes.
     *
     * @param args Argumentos del programa.
     */
    public static void main(String[] args) {
        PruebaEnvia miClaseEnvia = new PruebaEnvia();
    }

    /**
     * Método que contiene de lógica de envío del programa.
     */
    public PruebaEnvia() {
        // Creamos el frame de la aplicación.
        marco = new Frame("EnvioUDP");
        panel = new Panel();
        // Creamos los componentes del formulario.
        etiquetaMensaje = new Label("Mensaje: ");
        campoMensaje = new TextField(tamanioMaximoMensaje);
        hostDestino = new TextField("127.0.0.1", 15);
        puerto = new TextField("5000", 4);
        // Modificamos el marco y añadimos los elementos de la interfaz.
        marco.setSize(500, 200);
        marco.add(panel);
        panel.add(hostDestino);
        panel.add(puerto);
        panel.add(etiquetaMensaje);
        panel.add(campoMensaje);
        // Mostramos la interfaz.
        marco.show();
        // Instanciamos la clase auxiliar que se encargará de la gestión de las
        // comunicaciones.
        enviaFrase = new TEnviaUDP();
        // Asociamos los actionlistener.
        campoMensaje.addActionListener(new RespuestaAEnviar());
        panel.addMouseMotionListener(new MovimientoDelRaton());
    }

    /**
     *
     */
    private class RespuestaAEnviar extends Object implements ActionListener {

        /**
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String mensaje = campoMensaje.getText();
            enviaFrase.Envia(mensaje, mensaje.length(), hostDestino.getText(), Integer.parseInt(puerto.getText()));
            System.out.println("Datagrama enviado: " + mensaje + " , " + mensaje.length() + " Caracteres");
            if (mensaje.length() == 0) {
                // Al mandar un mensaje vacio se abandona el programa
                System.exit(1);
            }
        }
    }

    /**
     *
     */
    private class MovimientoDelRaton extends Object implements MouseMotionListener {

        /**
         *
         * @param e
         */
        @Override
        public void mouseMoved(MouseEvent e) {
            String mensaje = "*" + String.valueOf(e.getX()) + " " + String.valueOf(e.getY());
            enviaFrase.Envia(mensaje, mensaje.length(), hostDestino.getText(), Integer.parseInt(puerto.getText()));
            System.out.println("Datagrama enviado: " + mensaje + " , " + mensaje.length() + " Caracteres");
            if (mensaje.length() == 0) {
                System.exit(1);   // Al mandar un mensaje vacio se abandona el programa
            }
        } // mouseMoved

        /**
         *
         * @param e
         */
        @Override
        public void mouseDragged(MouseEvent e) {
        }

    }
}
