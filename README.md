¡Claro! A continuación te dejo una versión del README con un ejemplo de configuración de Kubernetes para los microservicios, incluyendo los recursos que mencionaste:

---

# Aplicación de Microservicios con Kubernetes en Google Cloud

Esta aplicación está compuesta por microservicios desplegados en **Google Kubernetes Engine (GKE)**, que permiten **subir y descargar archivos** desde **Google Cloud Storage**. Los servicios están containerizados con Docker y gestionados por Kubernetes.

## Arquitectura de la Aplicación

1. **Config Service**: Proporciona configuración centralizada para los microservicios.
2. **Eureka Service**: Gestiona el descubrimiento de servicios entre microservicios.
3. **Student Service**: Permite **subir y descargar archivos** a **Google Cloud Storage**.
4. **API Gateway**: Punto de entrada único que enruta las solicitudes a los microservicios correspondientes.

## Despliegue en GKE

Todos los microservicios están desplegados en **Google Kubernetes Engine (GKE)** utilizando configuraciones de **Kubernetes** para gestionar el ciclo de vida de los contenedores. A continuación se presentan ejemplos de los archivos de configuración de Kubernetes para los servicios.

### Ejemplo de configuración de Kubernetes para **Student Service**

```yaml
apiVersion: v1
kind: Service
metadata:
  name: student-service
spec:
  selector:
    app: student
  ports:
    - protocol: TCP
      port: 8090
      targetPort: 8090
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: student-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: student
  template:
    metadata:
      labels:
        app: student
    spec:
      containers:
      - name: student
        image: gcr.io/festive-vim-424816-r7/microservice-student
        ports:
        - containerPort: 8090
```

### Ejemplo de configuración de Kubernetes para **API Gateway**

```yaml
apiVersion: v1
kind: Service
metadata:
  name: gateway-service
spec:
  type: LoadBalancer
  selector:
    app: gateway
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8080
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: gateway-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: gateway
  template:
    metadata:
      labels:
        app: gateway
    spec:
      containers:
      - name: gateway
        image: gcr.io/festive-vim-424816-r7/microservice-gateway
        ports:
        - containerPort: 8080
```

### Ejemplo de configuración de Kubernetes para **Eureka Service**

```yaml
apiVersion: v1
kind: Service
metadata:
  name: eureka-service
spec:
  selector:
    app: eureka
  ports:
    - protocol: TCP
      port: 8761
      targetPort: 8761
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: eureka-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: eureka
  template:
    metadata:
      labels:
        app: eureka
    spec:
      containers:
      - name: eureka
        image: gcr.io/festive-vim-424816-r7/microservice-eureka
        ports:
        - containerPort: 8761
```

### Ejemplo de configuración de Kubernetes para **Config Service**

```yaml
apiVersion: v1
kind: Service
metadata:
  name: config-service
spec:
  selector:
    app: config
  ports:
    - protocol: TCP
      port: 8888
      targetPort: 8888
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: config-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: config
  template:
    metadata:
      labels:
        app: config
    spec:
      containers:
      - name: config
        image: gcr.io/festive-vim-424816-r7/microservice-config
        ports:
        - containerPort: 8888
```

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

---

Este README incluye ejemplos de las configuraciones de Kubernetes para los microservicios de la aplicación, como el **Student Service**, **API Gateway**, **Eureka Service** y **Config Service**. Estas configuraciones definen los despliegues y servicios asociados a cada uno de los microservicios. ¡Espero que ahora esté más completo y claro!
