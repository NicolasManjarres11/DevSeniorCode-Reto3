# Sistema de Gestión de Biblioteca con Pruebas Unitarias en Java 📚

## Descripción 🧾

Este proyecto es una aplicación desarrollada para manejar un sistema de gestión de bibliotecas básico, donde se gestiona el préstamo de los libros según inventario. La finalidad del proyecyo, más allá de la gestión de la biblioteca, es realizar pruebas unitarias de los métodos implementados en el desarrollo con Junit 5 para validar la funcionalidad de la capa servicio y Mockito para simular la capa de repositorio. También se implementó el manejo de errores y excepciones en cada una de las pruebas del proyecto.

## Características del proyecto 📖

- **Creación de libros y usuarios**: Se tiene la funcionalidad de agregar usuarios y libros cuando se requiera.
- **Préstamo de libros**: La biblioteca puede prestar los libros agregados a un usuario específico y definir si el libro está prestado o no.
- **Validación de métodos**: El programa tiene unos test implementados con los que se validó el funcionamiento de cada uno de los métodos integrados en el proyecto.


## Requisitos Previos 📝

Antes de ejecutar el proyecto, asegúrate de tener instalados los siguientes componentes:

- Java JDK 11 o superior ☕
- [Opcional] IDE de tu preferencia (IntelliJ IDEA, Eclipse, VSCode, etc.) 👨‍💻

## Instalación 

Sigue estos pasos para configurar el entorno de desarrollo:

1. Clona el repositorio:
   ```bash
   git clone https://github.com/NicolasManjarres11/DevSeniorCode-Reto3.git

2. **Ejecutar los test:**

   ```bash
   Desde el IDE de tu preferencia, debemos ir a la carpeta Test y ejecutar el archivo LibraryServiceTest.java


## Estructura del proyecto 🏗️

```bash
DevSeniorCode-Reto3/
├── .vscode/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── devsenior/
│   │   │           └── nmanja/
│   │   │               ├── Application.java
│   │   │               ├── controller/
│   │   │               ├── exceptions/
│   │   │               ├── interfaces/
│   │   │               ├── model/
│   │   │               └── service/
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   └── test/
│       └── java/
│           └── com/
│               └── devsenior/
│                   └── nmanja/
│                       └── LibraryServiceTest.java
├── .gitignore
└── pom.xml

```

### ✨ ¡Esperamos que esta aplicacion sea de tu agrado! 🧪