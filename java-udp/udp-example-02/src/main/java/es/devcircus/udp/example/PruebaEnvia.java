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
import java.awt.event.*;

/**
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
public class PruebaEnvia extends Object {

    static final int TamanioMaximoMensaje = 20;
    Frame Marco;
    Panel panel;
    Label EtiquetaMensaje;
    TextField CampoMensaje;
    TextField HostDestino;
    TextField Puerto;
    TEnviaUDP EnviaFrase;

    /**
     * Método principal que se encarga de lanzar el programa que envía los
     * mensajes.
     *
     * @param args Argumentos del programa.
     */
    public static void main(String[] args) {
        PruebaEnvia MiClaseEnvia = new PruebaEnvia();
    }

    /**
     * Método que contiene de lógica de envío del programa.
     */
    public PruebaEnvia() {
        // Creamos el frame de la aplicación.
        Marco = new Frame("EnvioUDP");
        panel = new Panel();
        // Creamos los componentes del formulario.
        EtiquetaMensaje = new Label("Mensaje: ");
        CampoMensaje = new TextField(TamanioMaximoMensaje);
        HostDestino = new TextField("127.0.0.1", 15);
        Puerto = new TextField("5000", 4);
        // Modificamos el marco y añadimos los elementos de la interfaz.
        Marco.setSize(500, 200);
        Marco.add(panel);
        panel.add(HostDestino);
        panel.add(Puerto);
        panel.add(EtiquetaMensaje);
        panel.add(CampoMensaje);
        // Mostramos la interfaz.
        Marco.show();
        // Instanciamos la clase auxiliar que se encargará de la gestión de las
        // comunicaciones.
        EnviaFrase = new TEnviaUDP();
        // Asociamos los actionlistener.
        CampoMensaje.addActionListener(new RespuestaAEnviar());
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
            String Mensaje = CampoMensaje.getText();
            EnviaFrase.Envia(Mensaje, Mensaje.length(), HostDestino.getText(), Integer.parseInt(Puerto.getText()));
            System.out.println("Datagrama enviado: " + Mensaje + " , " + Mensaje.length() + " Caracteres");
            if (Mensaje.length() == 0) {
                System.exit(1);   // Al mandar un mensaje vacio se abandona el programa
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
            String Mensaje = "*" + String.valueOf(e.getX()) + " " + String.valueOf(e.getY());
            EnviaFrase.Envia(Mensaje, Mensaje.length(), HostDestino.getText(), Integer.parseInt(Puerto.getText()));
            System.out.println("Datagrama enviado: " + Mensaje + " , " + Mensaje.length() + " Caracteres");
            if (Mensaje.length() == 0) {
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
