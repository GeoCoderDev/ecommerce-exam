CREATE TABLE T_Clientes (
    Id_Cliente SERIAL PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Apellido VARCHAR(100) NOT NULL,
    Correo_Electronico VARCHAR(150) UNIQUE NOT NULL,
    Numero_Telefono VARCHAR(20),
    Direccion VARCHAR(255),
    Fecha_Creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE T_Productos (
    Id_Producto SERIAL PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Descripcion TEXT,
    Precio DECIMAL(10, 2) NOT NULL,
    Cantidad_Stock INT NOT NULL,
    Fecha_Creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
