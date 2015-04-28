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
package es.devcircus.udp.example.util;

import java.net.*;

/**
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
public class TRecibeUDP extends Object {

    DatagramSocket miSocket;
    DatagramPacket paquete;
    byte[] buffer;

    /**
     * Método que encapsula la lógica de negocio para la recepción de
     * informacion via UDP.
     *
     * Este método es para fines formativos y por tanto no es ni elegante ni
     * eficiente ya que como podemos observar cada vez que enviarmos un mensaje
     * abrimos y cerramos el socket.
     *
     * @param puerto Puerto en el que vamos a hacer la recepción.
     * @param tamanioMaximoMensaje Tamaño máximo de mensaje.
     * @return String que contiene la información recivida.
     */
    public String Recibe(int puerto, int tamanioMaximoMensaje) {
        try {
            // Abrimos el socket en el puerto definido.
            miSocket = new DatagramSocket(puerto);
            // Definimos el buffer donde vamos a leer el mensaje.
            buffer = new byte[tamanioMaximoMensaje];
            // Definimos el paquete que va a encapsular el mensaje recivido.
            paquete = new DatagramPacket(buffer, buffer.length);
            // Solicitamos la recepción del mensaje.
            miSocket.receive(paquete);
            // Cerramos el socket.
            miSocket.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
        return new String(paquete.getData()).substring(0, paquete.getLength());
    }
}
