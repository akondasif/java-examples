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
package es.devcircus.patterns.examples.creational.builder.example_01;

/**
 * Clase que modela un Usuario.
 *
 * Esta clase tiene como clase interna la clase Builder que se encarga de la
 * creación de nuevas instancias. Hay que fijarse que la clase no tiene método
 * setter, solamente métodos getter. Esto se debe a que la creación y seteado de
 * los datos lo delegamos en la clase Builder.
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
public class User {

    //All final attributes
    private final String firstName; // required
    private final String lastName; // required
    private final int age; // optional
    private final String phone; // optional
    private final String address; // optional

    /**
     * Constructor de la clase.
     *
     * @param builder Builder que contiene los datos de creación de la
     * instancia.
     */
    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    /**
     * Método que retorna el valor del atributo firstName.
     *
     * @return Valor del atributo firstName.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Método que retorna el valor del atributo lastName.
     *
     * @return Valor del atributo lastName.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Método que retorna el valor del atributo age.
     *
     * @return Valor del atributo age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Método que retorna el valor del atributo phone.
     *
     * @return Valor del atributo phone.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Método que retorna el valor del atributo address.
     *
     * @return Valor del atributo address.
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "User: " + this.firstName + ", " + this.lastName + ", "
                + this.age + ", " + this.phone + ", " + this.address;
    }

    /**
     * Clase insterna que se encarga de la creación de la instancias.s
     */
    public static class UserBuilder {

        private final String firstName;
        private final String lastName;
        private int age;
        private String phone;
        private String address;

        /**
         * Constructor para la creación de nuevas instancias.
         *
         * @param firstName
         * @param lastName
         */
        public UserBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        /**
         *
         * @param age
         * @return
         */
        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        /**
         *
         * @param phone
         * @return
         */
        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        /**
         *
         * @param address
         * @return
         */
        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        /**
         * Método que se encarga de la creación de la instancia de User
         * propiamente dicha.
         *
         * @return Instancia de User con los datos que le hemos pasado al
         * Builder.
         */
        public User build() {
            User user = new User(this);
            validateUserObject(user);
            return user;
        }

        /**
         * Método que nos permite llevar a cabo una validación de los datos
         * proporcionados al builder antes de retornar la instancia. En este
         * caso no se ha expecificado ninguna implementación para la función.
         *
         * @param user Instancia de User a validar.
         */
        private void validateUserObject(User user) {
            //Do some basic validations to check
            //if user object does not break any assumption of system
        }
    }
}
