
# Aplicación de Microservicios con Kubernetes en Google Cloud

Esta aplicación está compuesta por microservicios desplegados en **Google Kubernetes Engine (GKE)**, que permiten **subir y descargar archivos** desde **Google Cloud Storage**. Los servicios están containerizados con Docker y gestionados por Kubernetes.

## Arquitectura de la Aplicación

1. **Config Service**: Proporciona configuración centralizada para los microservicios.
2. **Eureka Service**: Gestiona el descubrimiento de servicios entre microservicios.
3. **Student Service**: Permite **subir y descargar archivos** a **Google Cloud Storage**.
4. **API Gateway**: Punto de entrada único que enruta las solicitudes a los microservicios correspondientes.

## Despliegue en GKE

Todos los microservicios están desplegados en **Google Kubernetes Engine (GKE)**. Cada servicio está gestionado mediante los siguientes recursos:

- **Deployment**: Define los contenedores y réplicas de cada microservicio.
- **Service**: Exponen los microservicios para la comunicación entre ellos. El **API Gateway** se expone externamente como un **LoadBalancer**.

## Comunicación entre Servicios

- **Student Service** maneja la subida y descarga de archivos a **Google Cloud Storage**.
- **Eureka** se encarga del descubrimiento y registro de los microservicios.
- El **API Gateway** enruta las solicitudes a los microservicios adecuados, actuando como punto de entrada único.

## Almacenamiento en Google Cloud Storage

Los archivos se almacenan en **Google Cloud Storage**. Cuando un archivo es subido a través del **Student Service**, se le asigna un identificador único que permite su descarga posterior.

## Tecnologías Usadas

- **Docker**: Para crear contenedores de microservicios.
- **Google Kubernetes Engine (GKE)**: Para la orquestación y despliegue de los microservicios en la nube.
- **Spring Boot**: Framework para desarrollar los microservicios.
- **Google Cloud Storage**: Para almacenar y gestionar los archivos subidos y descargados.

