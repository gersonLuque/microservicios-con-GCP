# Usa la imagen base de Node.js
FROM node:14

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el package.json y package-lock.json al directorio de trabajo
COPY package*.json ./


# Instala las dependencias
RUN npm install

# Copia el resto de los archivos de la aplicación al directorio de trabajo
COPY . .

# Expone el puerto 8080
EXPOSE 8080

# Define el comando para ejecutar tu aplicación
CMD ["npm", "start"]
