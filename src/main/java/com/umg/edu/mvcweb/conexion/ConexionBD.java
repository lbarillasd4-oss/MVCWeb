package com.umg.edu.mvcweb.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL =
            "jdbc:mysql://localhost:3306/tienda_poo";

    private static final String USUARIO =
            "root";

    private static final String PASSWORD =
            "NuevaClaveSegura_2026!";

    public static Connection conectar() throws SQLException {

        return DriverManager.getConnection(
                URL,
                USUARIO,
                PASSWORD
        );
    }
}