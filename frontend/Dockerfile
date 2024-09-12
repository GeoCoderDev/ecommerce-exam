# Usa una imagen base de Node.js
FROM node:22.8-alpine3.20

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos de configuración de npm
COPY package*.json ./

# Instala Angular CLI globalmente y las dependencias del proyecto
RUN npm install -g @angular/cli@latest && npm install

# Copia el resto de la aplicación
COPY . .

# Expone el puerto 4200
EXPOSE 4200

# Comando para iniciar la aplicación Angular
CMD ["sh", "-c", "ng serve --host 0.0.0.0 --port 4200 --poll=2000"]

