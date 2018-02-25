Hibernate Examples
==================

He desarrollado este proyecto como banco de pruebas para el framework Hibernate.
El proyecto  está compuesto de subproyectos, cada uno de ellos enfocado a dar solución a una cuestión distinta. 

Estructura del proyecto
-----------------------

A continuación se listan los proyectos y se da una breve descripción de cada uno de ellos.

### Introducción

 * **hello-world-mapping**: Proyecto de introducción que muestra los elementos necesarios más básicos para el uso de hibernate mediante ficheros de mapping xml.
 * **hello-world-annotation**: Proyecto de introducción que muestra los elementos necesarios más básicos para el uso de hibernate mediante el uso de anotaciones. 

### Mapeo de Relaciones

 * **relationship-one-to-one-mapping:** Proyecto que muestra como llevar a cabo una relación uno a uno mediante ficheros de mapping xml.
 * **relationship-one-to-one-annotation:** Proyecto que muestra como llevar a cabo una relación uno a uno mediante el uso de anotaciones.
 * **relationship-one-to-many-mapping-set:** Proyecto que muestra como llevar a cabo una relación uno a varios mediante ficheros de mapping xml (Mapeo con set).
 * **relationship-one-to-many-mapping-bag:** Proyecto que muestra como llevar a cabo una relación uno a varios mediante ficheros de mapping xml (Mapeo con bag).
 * **relationship-one-to-many-mapping-list:** Proyecto que muestra como llevar a cabo una relación uno a varios mediante ficheros de mapping xml (Mapeo con list).
 * **relationship-one-to-many-mapping-array:** Proyecto que muestra como llevar a cabo una relación uno a varios mediante ficheros de mapping xml (Mapeo con array).
 * **relationship-one-to-many-annotation-set:** Proyecto que muestra como llevar a cabo una relación uno a varios mediante el uso de anotaciones (Mapeo con set).
 * **relationship-one-to-many-annotation-list:** Proyecto que muestra como llevar a cabo una relación uno a varios mediante el uso de anotaciones (Mapeo con list).
 * **relationship-many-to-many-mapping:** Proyecto que muestra como llevar a cabo una relación varios a varios mediante ficheros de mapping xml.
 * **relationship-many-to-many-annotation:** Proyecto que muestra como llevar a cabo una relación varios a varios mediante ficheros de mapping xml.
 * **relationship-self-join-one-to-many:** Proyecto que muestra como relacionar un objeto consigo mismo mediante una relación uno a varios.
 * **relationship-self-join-many-to-many:** Proyecto que muestra como relacionar un objeto consigo mismo mediante una relación varios a varios.

### Herencia y Patrones
 
 * **inheritance-one-table-per-class-mapping:** 
 * **inheritance-one-table-per-class-annotatioin:** 
 * **inheritance-one-table-per-subclass-mapping:** 
 * **inheritance-one-table-per-subclass-annotation:** 
 * **inheritance-one-table-per-concrete-class-mapping:** 
 * **inheritance-one-table-per-concrete-class-annotation:** 
 * **patterns-dao-mapping:** Proyecto que en el que empleamos el patron DAO para desacoplar la aplicación del acceso a los datos.
 * **patterns-facade-dao-mapping:** Proyecto similar al anterior, pero en este caso hacemos uso de una fachada para desacoplar el programa del acceso a los datos.
 
Licencia
--------

Hibernate Examples está protegido bajo licencia GNU General Public License, versión 2