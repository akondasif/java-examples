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
import es.devcircus.udp.example.util.TRecibeUDP;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Los puertos origen y destino se deben cruzar en las dos instancias que se
 * creen del talk. Por ejemplo:
 *
 * Talk Instancia = new Talk(5000, 5002); 
 * Talk Instancia = new Talk(5002, 5000);
 *
 * La idea es que, el primer proceso envie al puerto 5000 y el segundo proceso
 * proceso escuche en ese mismo puerto. Y lo mismo al contrario, que el primer
 * proceso escuche en el puerto 5002 y el segundo envie a ese puerto.
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
public class Talk {

    static final int tamanioMaximoMensaje = 90;

    TextArea areaRecibir;
    TextArea areaEnviar;
    TextField hostDestino;
    Button botonEnviar;
    String mensajeRecibido;
    int puertoOrigen, puertoDestino;

    TRecibeUDP instanciaRecibeUDP;
    TEnviaUDP instanciaEnviaUDP;

    /**
     *
     * @param puertoOrigen
     * @param puertoDestino
     */
    public Talk(int puertoOrigen, int puertoDestino) {
        // Definimos los puertos origen y destino de nuestro programa.
        this.puertoOrigen = puertoOrigen;
        this.puertoDestino = puertoDestino;
        // Instanciamos las clases auxiliares que se encargaran del envío y 
        // recepción de los mensajes.
        instanciaRecibeUDP = new TRecibeUDP();
        instanciaEnviaUDP = new TEnviaUDP();
        // Definimos los elementos de la interfaz.
        Frame marco = new Frame("Talk");
        Panel panel = new Panel();
        Label etiquetaMensajeSaliente = new Label("Mensaje Saliente:");
        Label etiquetaMensajeEntrante = new Label("Mensaje Entrante:");
        Label etiquetaHostDestino = new Label("Host destino");
        areaRecibir = new TextArea(3, 24);
        areaEnviar = new TextArea(3, 24);
        hostDestino = new TextField("127.0.0.1");
        botonEnviar = new Button("Enviar");
        // Configuramos los elementos de la interfaz.
        marco.setSize(250, 280);
        marco.setLayout(new BorderLayout());
        marco.add("Center", panel);
        panel.add(etiquetaHostDestino);
        panel.add(hostDestino);
        panel.add(botonEnviar);
        panel.add(etiquetaMensajeSaliente);
        panel.add(areaEnviar);
        panel.add(etiquetaMensajeEntrante);
        panel.add(areaRecibir);
        // Mostramos la interfaz.
        marco.show();
        // Instanciamos los threads que se encargaran de la recepción y envío de
        // la información.
        TalkRecibir hiloRecibir = new TalkRecibir();
        TalkEnviar hiloEnviar = new TalkEnviar();
        // Lanzamos la ejecución de los threads.
        hiloRecibir.start();
        hiloEnviar.start();
    }

    /**
     * Método principal que se encarga de arrancar el programa.
     *
     * @param args Parámetros de ejecución del programa.
     */
    public static void main(String[] args) {
//        Talk instancia = new Talk(5000, 5002);
        Talk instancia = new Talk(5002, 5000);
    }

    /**
     * Thread que se encaga del envío de los mensajes del sistema.
     */
    private class TalkEnviar extends Thread {

        /**
         * Lógica de ejecución del thread. En este caso se encarga del envío de
         * los mensajes del sistema.
         */
        @Override
        public void run() {
            // Asociamos el evento que se encargará de la gestión del envío de los
            // mensajes.
            botonEnviar.addActionListener(new RespuestaAEnviar());
        }

        /**
         * Clase que implementa ActionListener y que será la encargada de la
         * gestión de la acción de enviar solicitada por el usuario.
         */
        private class RespuestaAEnviar implements ActionListener {

            /**
             * Método que encapsula la lógica de envío del mensajes especificado
             * por el usuario.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Recuperamos el mensaje de la caja de texto de la interfaz.
                String mensaje = areaEnviar.getText();
                // Solicitamos el envío del mensaje.
                instanciaEnviaUDP.Envia(mensaje, mensaje.length(), hostDestino.getText(), puertoDestino);
                // Vaciamos la caja de texto para que el usuario pueda especificar
                // un nuevo mensaje.
                areaEnviar.setText("");
                // Si la longitud del mensaje especificado por el usuario es cero
                // finalizamos la ejecución del programa.
                if (mensaje.length() == 0) {
                    // Al mandar un mensaje vacio se abandona el programa
                    System.exit(0);
                }
            }
        }

    }

    /**
     * Thread que se encarga de la recepción de los mensajes del sistema.
     */
    private class TalkRecibir extends Thread {

        /**
         * Lógica de ejecución del thread. En este caso se encarga de la
         * recepción de los mensajes del sistema.
         */
        @Override
        public void run() {
            // Permanecemos a la escucha de nuevos mensajes mientras no llegue
            // uno con longitud cero.
            do {
                // Escuchamos a la espera de la recepción de un nuevo mensajes.
                mensajeRecibido = instanciaRecibeUDP.Recibe(puertoOrigen, tamanioMaximoMensaje);
                // Mostramos el mensaje en la interfaz.
                areaRecibir.setText(mensajeRecibido);
            } while (mensajeRecibido.length() != 0);
            // Salimos del sistema.
            System.exit(0);
        }
    }
}
