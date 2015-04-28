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
     * @param Mensaje Mensaje que queremos enviar vía UDP.
     * @param TamanioMensaje Tamño máximo del mensaje a enviar.
     * @param HostDestino Dirección IP del host de destino.
     * @param Puerto Puesto en el que vamos a llevar a cabo la conexión.
     */
    public void Envia(String Mensaje, int TamanioMensaje, String HostDestino, int Puerto) {
        try {
            // Instanciamos el socket.
            DatagramSocket MiSocket = new DatagramSocket();
            // Definimos el buffer con el tamaño máximo especificado.
            byte[] buffer = new byte[TamanioMensaje];
            DatagramPacket Paquete;
            buffer = Mensaje.getBytes();
            // Instanciamos el paquete a enviar.
            Paquete = new DatagramPacket(buffer, Mensaje.length(), InetAddress.getByName(HostDestino), Puerto);
            // Enviamos el paquete.
            MiSocket.send(Paquete);
            // Cerramos el socket.
            MiSocket.close();
        } catch (Exception exc) {
            System.out.println("Error");
        }
    }
}
