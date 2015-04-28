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

    static final int TamanioMaximoMensaje = 90;

    TextArea AreaRecibir;
    TextArea AreaEnviar;
    TextField HostDestino;
    Button BotonEnviar;
    String MensajeRecibido;
    int PuertoOrigen, PuertoDestino;

    TRecibeUDP InstanciaRecibeUDP;
    TEnviaUDP InstanciaEnviaUDP;

    /**
     *
     * @param PuertoOrigen
     * @param PuertoDestino
     */
    public Talk(int PuertoOrigen, int PuertoDestino) {
        // Definimos los puertos origen y destino de nuestro programa.
        this.PuertoOrigen = PuertoOrigen;
        this.PuertoDestino = PuertoDestino;
        // Instanciamos las clases auxiliares que se encargaran del envío y 
        // recepción de los mensajes.
        InstanciaRecibeUDP = new TRecibeUDP();
        InstanciaEnviaUDP = new TEnviaUDP();
        // Definimos los elementos de la interfaz.
        Frame Marco = new Frame("Talk");
        Panel panel = new Panel();
        Label EtiquetaMensajeSaliente = new Label("Mensaje Saliente:");
        Label EtiquetaMensajeEntrante = new Label("Mensaje Entrante:");
        Label EtiquetaHostDestino = new Label("Host destino");
        AreaRecibir = new TextArea(3, 24);
        AreaEnviar = new TextArea(3, 24);
        HostDestino = new TextField("127.0.0.1");
        BotonEnviar = new Button("Enviar");
        // Configuramos los elementos de la interfaz.
        Marco.setSize(250, 280);
        Marco.setLayout(new BorderLayout());
        Marco.add("Center", panel);
        panel.add(EtiquetaHostDestino);
        panel.add(HostDestino);
        panel.add(BotonEnviar);
        panel.add(EtiquetaMensajeSaliente);
        panel.add(AreaEnviar);
        panel.add(EtiquetaMensajeEntrante);
        panel.add(AreaRecibir);
        // Mostramos la interfaz.
        Marco.show();
        // Instanciamos los threads que se encargaran de la recepción y envío de
        // la información.
        TalkRecibir HiloRecibir = new TalkRecibir();
        TalkEnviar HiloEnviar = new TalkEnviar();
        // Lanzamos la ejecución de los threads.
        HiloRecibir.start();
        HiloEnviar.start();
    }

    /**
     * Método principal que se encarga de arrancar el programa.
     *
     * @param args Parámetros de ejecución del programa.
     */
    public static void main(String[] args) {
//        Talk Instancia = new Talk(5000, 5002);
        Talk Instancia = new Talk(5002, 5000);
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
            BotonEnviar.addActionListener(new RespuestaAEnviar());
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
                String Mensaje = AreaEnviar.getText();
                // Solicitamos el envío del mensaje.
                InstanciaEnviaUDP.Envia(Mensaje, Mensaje.length(), HostDestino.getText(), PuertoDestino);
                // Vaciamos la caja de texto para que el usuario pueda especificar
                // un nuevo mensaje.
                AreaEnviar.setText("");
                // Si la longitud del mensaje especificado por el usuario es cero
                // finalizamos la ejecución del programa.
                if (Mensaje.length() == 0) {
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
                MensajeRecibido = InstanciaRecibeUDP.Recibe(PuertoOrigen, TamanioMaximoMensaje);
                // Mostramos el mensaje en la interfaz.
                AreaRecibir.setText(MensajeRecibido);
            } while (MensajeRecibido.length() != 0);
            // Salimos del sistema.
            System.exit(0);
        }
    }
}
