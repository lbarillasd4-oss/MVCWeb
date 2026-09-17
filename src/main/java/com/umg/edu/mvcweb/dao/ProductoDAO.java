package com.umg.edu.mvcweb.dao;

import com.umg.edu.mvcweb.conexion.ConexionBD;
import com.umg.edu.mvcweb.modelo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ProductoDAO {

    public boolean guardar(Producto producto) {

        String sql =
                "INSERT INTO productos "
                + "(id_marca, nombre, precio, stock) "
                + "VALUES (?, ?, ?, ?)";

        try (
            Connection conexion =
                    ConexionBD.conectar();

            PreparedStatement ps =
                    conexion.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    producto.getIdMarca()
            );

            ps.setString(
                    2,
                    producto.getNombre()
            );

            ps.setDouble(
                    3,
                    producto.getPrecio()
            );

            ps.setInt(
                    4,
                    producto.getStock()
            );

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Error al guardar producto: "
                    + e.getMessage()
            );

            return false;
        }
    }

    public List<Producto> listar() {

        List<Producto> productos =
                new ArrayList<>();

        String sql =
                "SELECT * FROM productos";

        try (
            Connection conexion =
                    ConexionBD.conectar();

            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery()
        ) {

            while (rs.next()) {

                Producto producto =
                        new Producto();

                producto.setIdProductos(
                        rs.getInt("id_productos")
                );

                producto.setIdMarca(
                        rs.getInt("id_marca")
                );

                producto.setNombre(
                        rs.getString("nombre")
                );

                producto.setPrecio(
                        rs.getDouble("precio")
                );

                producto.setStock(
                        rs.getInt("stock")
                );

                productos.add(producto);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al listar productos: "
                    + e.getMessage()
            );
        }

        return productos;
    }

    public Producto buscarPorId(int id) {

        String sql =
                "SELECT * FROM productos "
                + "WHERE id_productos = ?";

        try (
            Connection conexion =
                    ConexionBD.conectar();

            PreparedStatement ps =
                    conexion.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                Producto producto =
                        new Producto();

                producto.setIdProductos(
                        rs.getInt("id_productos")
                );

                producto.setIdMarca(
                        rs.getInt("id_marca")
                );

                producto.setNombre(
                        rs.getString("nombre")
                );

                producto.setPrecio(
                        rs.getDouble("precio")
                );

                producto.setStock(
                        rs.getInt("stock")
                );

                return producto;
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al buscar producto: "
                    + e.getMessage()
            );
        }

        return null;
    }

    public boolean actualizar(Producto producto) {

        String sql =
                "UPDATE productos SET "
                + "id_marca = ?, "
                + "nombre = ?, "
                + "precio = ?, "
                + "stock = ? "
                + "WHERE id_productos = ?";

        try (
            Connection conexion =
                    ConexionBD.conectar();

            PreparedStatement ps =
                    conexion.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    producto.getIdMarca()
            );

            ps.setString(
                    2,
                    producto.getNombre()
            );

            ps.setDouble(
                    3,
                    producto.getPrecio()
            );

            ps.setInt(
                    4,
                    producto.getStock()
            );

            ps.setInt(
                    5,
                    producto.getIdProductos()
            );

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Error al actualizar producto: "
                    + e.getMessage()
            );

            return false;
        }
    }

    public boolean eliminar(int id) {

        String sql =
                "DELETE FROM productos "
                + "WHERE id_productos = ?";

        try (
            Connection conexion =
                    ConexionBD.conectar();

            PreparedStatement ps =
                    conexion.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Error al eliminar producto: "
                    + e.getMessage()
            );

            return false;
        }
    }
}
