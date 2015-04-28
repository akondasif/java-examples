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

import java.net.*;

/**
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
public class TRecibeUDP extends Object {
    
    DatagramSocket MiSocket;
    DatagramPacket Paquete;
    byte[] buffer;

    /**
     * Método que encapsula la lógica de negocio para la recepción de
     * informacion via UDP.
     *
     * Este método es para fines formativos y por tanto no es ni elegante ni
     * eficiente ya que como podemos observar cada vez que enviarmos un mensaje
     * abrimos y cerramos el socket.
     *
     * @param Puerto Puerto en el que vamos a hacer la recepción.
     * @param TamanioMaximoMensaje Tamaño máximo de mensaje.
     * @return String que contiene la información recivida.
     */
    public String Recibe(int Puerto, int TamanioMaximoMensaje) {
        try {
            // Abrimos el socket en el puerto definido.
            MiSocket = new DatagramSocket(Puerto);
            // Definimos el buffer donde vamos a leer el mensaje.
            buffer = new byte[TamanioMaximoMensaje];
            // Definimos el paquete que va a encapsular el mensaje recivido.
            Paquete = new DatagramPacket(buffer, buffer.length);
            // Solicitamos la recepción del mensaje.
            MiSocket.receive(Paquete);
            // Cerramos el socket.
            MiSocket.close();
        } catch (Exception e) {
            System.out.println("Error");
        } //try
        return new String(Paquete.getData()).substring(0, Paquete.getLength());
    }
}
