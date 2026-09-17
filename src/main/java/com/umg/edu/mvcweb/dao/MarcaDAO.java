package com.umg.edu.mvcweb.dao;

import com.umg.edu.mvcweb.conexion.ConexionBD;
import com.umg.edu.mvcweb.modelo.Marca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class MarcaDAO {

    // =====================================================
    // GUARDAR MARCA
    // =====================================================

    public boolean guardar(Marca marca) {

        String sql =
                "INSERT INTO marcas (nombre) "
                + "VALUES (?)";

        try (
            Connection conexion =
                    ConexionBD.conectar();

            PreparedStatement ps =
                    conexion.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    marca.getNombre()
            );

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Error al guardar marca: "
                    + e.getMessage()
            );

            return false;
        }
    }


    // =====================================================
    // LISTAR MARCAS
    // =====================================================

    public List<Marca> listar() {

        List<Marca> marcas =
                new ArrayList<>();

        String sql =
                "SELECT * FROM marcas";

        try (
            Connection conexion =
                    ConexionBD.conectar();

            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery()
        ) {

            while (rs.next()) {

                Marca marca =
                        new Marca();

                marca.setIdMarcas(
                        rs.getInt("id_marcas")
                );

                marca.setNombre(
                        rs.getString("nombre")
                );

                marcas.add(marca);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al listar marcas: "
                    + e.getMessage()
            );
        }

        return marcas;
    }


    // =====================================================
    // BUSCAR MARCA POR ID
    // =====================================================

    public Marca buscarPorId(int id) {

        String sql =
                "SELECT * FROM marcas "
                + "WHERE id_marcas = ?";

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

                Marca marca =
                        new Marca();

                marca.setIdMarcas(
                        rs.getInt("id_marcas")
                );

                marca.setNombre(
                        rs.getString("nombre")
                );

                return marca;
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al buscar marca: "
                    + e.getMessage()
            );
        }

        return null;
    }


    // =====================================================
    // ACTUALIZAR MARCA
    // =====================================================

    public boolean actualizar(Marca marca) {

        String sql =
                "UPDATE marcas "
                + "SET nombre = ? "
                + "WHERE id_marcas = ?";

        try (
            Connection conexion =
                    ConexionBD.conectar();

            PreparedStatement ps =
                    conexion.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    marca.getNombre()
            );

            ps.setInt(
                    2,
                    marca.getIdMarcas()
            );

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Error al actualizar marca: "
                    + e.getMessage()
            );

            return false;
        }
    }


    // =====================================================
    // ELIMINAR MARCA
    // =====================================================

    public boolean eliminar(int id) {

        String sql =
                "DELETE FROM marcas "
                + "WHERE id_marcas = ?";

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
                    "Error al eliminar marca: "
                    + e.getMessage()
            );

            return false;
        }
    }
}
