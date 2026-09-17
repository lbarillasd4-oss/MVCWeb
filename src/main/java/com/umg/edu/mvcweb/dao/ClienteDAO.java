package com.umg.edu.mvcweb.dao;

import com.umg.edu.mvcweb.conexion.ConexionBD;
import com.umg.edu.mvcweb.modelo.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ClienteDAO {

    // =====================================================
    // GUARDAR CLIENTE
    // =====================================================
    public boolean guardar(Cliente cliente) {

        String sql = "INSERT INTO clientes "
                + "(nit, nombre, apellidos, email) "
                + "VALUES (?, ?, ?, ?)";

        try (
            Connection conexion = ConexionBD.conectar();
            PreparedStatement ps =
                    conexion.prepareStatement(sql)
        ) {

            ps.setString(1, cliente.getNit());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4, cliente.getEmail());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Error al guardar cliente: "
                    + e.getMessage()
            );

            return false;
        }
    }


    // =====================================================
    // LISTAR CLIENTES
    // =====================================================
    public List<Cliente> listar() {

        List<Cliente> clientes =
                new ArrayList<>();

        String sql =
                "SELECT * FROM clientes";

        try (
            Connection conexion = ConexionBD.conectar();
            PreparedStatement ps =
                    conexion.prepareStatement(sql);
            ResultSet rs =
                    ps.executeQuery()
        ) {

            while (rs.next()) {

                Cliente cliente =
                        new Cliente();

                cliente.setIdCliente(
                        rs.getInt("id_cliente")
                );

                cliente.setNit(
                        rs.getString("nit")
                );

                cliente.setNombre(
                        rs.getString("nombre")
                );

                cliente.setApellidos(
                        rs.getString("apellidos")
                );

                cliente.setEmail(
                        rs.getString("email")
                );

                clientes.add(cliente);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al listar clientes: "
                    + e.getMessage()
            );
        }

        return clientes;
    }


    // =====================================================
    // BUSCAR CLIENTE POR ID
    // =====================================================
    public Cliente buscarPorId(int id) {

        String sql =
                "SELECT * FROM clientes "
                + "WHERE id_cliente = ?";

        try (
            Connection conexion = ConexionBD.conectar();
            PreparedStatement ps =
                    conexion.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                Cliente cliente =
                        new Cliente();

                cliente.setIdCliente(
                        rs.getInt("id_cliente")
                );

                cliente.setNit(
                        rs.getString("nit")
                );

                cliente.setNombre(
                        rs.getString("nombre")
                );

                cliente.setApellidos(
                        rs.getString("apellidos")
                );

                cliente.setEmail(
                        rs.getString("email")
                );

                return cliente;
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al buscar cliente: "
                    + e.getMessage()
            );
        }

        return null;
    }


    // =====================================================
    // ACTUALIZAR CLIENTE
    // =====================================================
    public boolean actualizar(Cliente cliente) {

        String sql =
                "UPDATE clientes SET "
                + "nit = ?, "
                + "nombre = ?, "
                + "apellidos = ?, "
                + "email = ? "
                + "WHERE id_cliente = ?";

        try (
            Connection conexion = ConexionBD.conectar();
            PreparedStatement ps =
                    conexion.prepareStatement(sql)
        ) {

            ps.setString(1, cliente.getNit());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4, cliente.getEmail());
            ps.setInt(5, cliente.getIdCliente());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Error al actualizar cliente: "
                    + e.getMessage()
            );

            return false;
        }
    }


    // =====================================================
    // ELIMINAR CLIENTE
    // =====================================================
    public boolean eliminar(int id) {

        String sql =
                "DELETE FROM clientes "
                + "WHERE id_cliente = ?";

        try (
            Connection conexion = ConexionBD.conectar();
            PreparedStatement ps =
                    conexion.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Error al eliminar cliente: "
                    + e.getMessage()
            );

            return false;
        }
    }
}