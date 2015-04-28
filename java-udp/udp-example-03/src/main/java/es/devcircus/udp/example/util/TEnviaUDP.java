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
public class TEnviaUDP extends Object {

    /**
     * Método que encapsula la lógica de negocio para el envío de informacion
     * via UDP.
     *
     * Este método es para fines formativos y por tanto no es ni elegante ni
     * eficiente ya que como podemos observar cada vez que enviarmos un mensaje
     * abrimos y cerramos el socket.
     *
     * @param mensaje Mensaje que queremos enviar vía UDP.
     * @param tamanioMensaje Tamño máximo del mensaje a enviar.
     * @param hostDestino Dirección IP del host de destino.
     * @param puerto Puesto en el que vamos a llevar a cabo la conexión.
     */
    public void Envia(String mensaje, int tamanioMensaje, String hostDestino, int puerto) {
        try {
            // Instanciamos el socket.
            DatagramSocket miSocket = new DatagramSocket();
            // Definimos el buffer con el tamaño máximo especificado.
            byte[] buffer = new byte[tamanioMensaje];
            DatagramPacket paquete;
            buffer = mensaje.getBytes();
            // Instanciamos el paquete a enviar.
            paquete = new DatagramPacket(buffer, mensaje.length(), InetAddress.getByName(hostDestino), puerto);
            // Enviamos el paquete.
            miSocket.send(paquete);
            // Cerramos el socket.
            miSocket.close();
        } catch (Exception exc) {
            System.out.println("Error");
        }
    }
}
