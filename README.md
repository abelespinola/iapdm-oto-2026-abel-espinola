# Registro de Empleados - NeuraTech

Esta es una aplicación Android desarrollada como proyecto final para la asignatura "Introducción a la Programación para Dispositivos Móviles". Su propósito es gestionar el registro de empleados, en este caso particular para la empresa de servicios informáticos NeuraTech.

## Características Principales

* **Formulario de Ingreso:** Permite registrar nuevos empleados capturando su Nombre Completo, Cargo, Departamento, Salario y Fecha de Contratación. Las entradas de salario y fecha cuentan con transformaciones visuales automáticas para un mejor formato.

* **Visualización en Lista:** Muestra a todo el personal activo en una lista vertical (`LazyColumn`) ordenada y optimizada.

* **Detalle Estilizado:** Cada ítem de la lista destaca el nombre del empleado y utiliza un carrusel horizontal (`LazyRow`) para mostrar en etiquetas los demás detalles (cargo, departamento, etc.).

* **Gestión Rápida:** Incluye un botón para eliminar empleados individuales de la lista en tiempo real.

* **Diseño Adaptable:** Interfaz moderna y responsiva que soporta automáticamente tanto el **Modo Claro** como el **Modo Oscuro** del sistema.

* **Ciclo de Vida:** La aplicación registra en la consola (`Logcat`) los eventos principales del ciclo de vida de la Activity (`onStart`, `onStop`, `onDestroy`).


## Arquitectura y Tecnologías

El proyecto fue desarrollado utilizando las mejores prácticas recomendadas para el ecosistema Android actual:

* **Lenguaje:** Kotlin

* **Interfaz de Usuario (UI):** Jetpack Compose

* **Arquitectura:** Patrón **MVVM** (Model-View-ViewModel). El estado de la interfaz de usuario se gestiona centralizadamente a través de la clase `EmpleadoViewModel`, asegurando que la información sobreviva a los cambios de configuración.

* **Diseño:** Material Design 3 (Material You)


## Cómo ejecutar el proyecto


Para evaluar o probar esta aplicación, sigue estos pasos:


1.  **Clonar el repositorio:**

```bash
git clone <https://github.com/abelespinola/apdm-oto-2026-abel-espinola.git>
```
2.  **Abrir en Android Studio:**

 * Abre Android Studio y selecciona **"Open"** o **"Open an existing project"**.
   
 * Navega hasta la carpeta donde clonaste el repositorio y selecciónala.

 * Espera a que Gradle sincronice las dependencias del proyecto (puede tardar unos minutos la primera vez).

3.  **Ejecutar la aplicación:**

* Asegúrate de tener un emulador configurado o un dispositivo físico conectado con las opciones de desarrollador activadas.

* Haz clic en el botón verde **"Run app"** (Shift + F10) en la barra de herramientas superior de Android Studio.

4.  **Verificar Logs (Para el Profesor):**

* Para comprobar el cumplimiento del ciclo de vida, abre la pestaña **Logcat** en la parte inferior de Android Studio.

* En la barra de búsqueda del Logcat, filtra por la etiqueta `MainActivityLog` para ver los mensajes correspondientes a `onStart`, `onStop` y `onDestroy`.



*Desarrollado por Abel Alejandro Espínola Fernández - 2026*

