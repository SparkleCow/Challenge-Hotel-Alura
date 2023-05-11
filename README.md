# Challenge ONE | Java | Back-end | Hotel Alura

<p align="center" >
     <img width="300" heigth="300" src="https://user-images.githubusercontent.com/91544872/189419040-c093db78-c970-4960-8aca-ffcc11f7ffaf.png">
</p>

---

## 🖥️ Tecnologías Utilizadas:

- Java
- Eclipse
- Biblioteca JCalendar
- MySql
- Plugin WindowBuilder </br>

---
## ⚠️ Importante! ⚠️

☕ Use Java versión 8 o superior para compatibilidad. </br></br>
📝 Recomendamos usar el editor de Eclipse para compatibilidad con la Interfaz Gráfica. </br></br>
🎨 La interfaz contiene dos métodos importantes:
- setResizable(false): determina el tamaño de la ventana, y a través del parámetro <strong>false</strong>, la pantalla no se puede maximizar;
- setLocationRelativeTo(null): determina la ubicación de la ventana, y a través del parámetro <strong>null</strong> la mantiene centrada en la pantalla.

## MySQL
### Importante

📝 La base de datos contiene 3 tablas:
     
     1 - Login: Contiene los datos del administrador para dar acceso a la aplicación.
     2 - Reservas: Guarda las reservas creadas por cada huesped.
     3 - Huespedes: Guarda la información de cada huesped registrado en la plataforma.
---

## 🔍 Repositorio base.

#### 🔹 (Imagenes y codigo de la liberia SWING fue otorgado por ALURA - LATAM)
#### 🔹 src/views: carpeta con toda la interfaz gráfica de las pantallas necesarias para desarrollar el programa;
#### 🔹 src/imagenes: carpeta con imágenes por defecto para el proyecto.
</br>

## 🚧 Proyecto

<p align="center" >
     <img width="700" heigth="700" src="https://user-images.githubusercontent.com/91544872/189419249-06b539da-7cf2-4d40-a711-618a5c872096.png">
</p>

<p align="center" >
     <img width="600" heigth="600" src="https://user-images.githubusercontent.com/91544872/189419556-20b67f67-003c-47ac-a0ae-02cf814a6ccb.png">
</p>

<p align="center" >
     <img width="200" heigth="200" src="https://user-images.githubusercontent.com/101413385/173693126-8e2fec8b-91b1-4007-bbc5-010bb454f440.png">
</p>

<p align="center" >
     <img width="500" heigth="500" src="https://user-images.githubusercontent.com/101413385/173691963-1e3d966e-5162-4393-9232-d5d395d5440f.png">
</p>

## 📊 Base de Dados

### ¿Cómo importar MySqlConnector al proyecto?

<p align="center" >
     <img width="500" heigth="500" src="https://user-images.githubusercontent.com/101413385/173169394-a67b528a-c8b7-4f7a-b374-a1da81b1cc5d.png">
</p>

- MySQL
<p align="center" >
     <img width="500" heigth="500" src="https://user-images.githubusercontent.com/101413385/173169551-b32a9978-3a05-4b6e-b077-f586d0c41e31.png">
</p>

- My SQL Connector Java:
<p align="center" >
     <img width="500" heigth="500" src="https://user-images.githubusercontent.com/101413385/173169737-5f93868e-df8f-4177-90ba-faf9570758ac.png">
</p>

### Modelado de tablas:

Para este reto te proponemos dos tablas: <strong>Reservas</strong> y <strong>Huéspedes</strong>. La tabla de huéspedes debe contener la clave externa <em>(foreign key)</em> <strong>idReserva</strong>.

<p align="center" >
     <img width="500" heigth="500" src="https://user-images.githubusercontent.com/101413385/169529338-09a4d4c2-1b5a-41dc-b305-38498ebc29a8.png">
</p>
