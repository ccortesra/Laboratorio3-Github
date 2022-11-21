/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Modelo.Alerta;
import Modelo.Conexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tique
 */
public class ComentariosPostController implements Initializable {

    private Connection bd;
    PostController info = new PostController();
    @FXML
    private TableView<String> tablaComentarios;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResultSet rs;
        bd = Conexion.getBd();
        try {
            rs = bd.createStatement().executeQuery("SELECT * from COMENTARIO WHERE ID_POST = " + info.getID_postActual());
            if (rs.next()) {
                ResultSet rs2;
                ArrayList<String> Comentarios = new ArrayList<>();
                do {
                    rs2 = bd.createStatement().executeQuery("Select * from USUARIO where ID_USUARIO = " + rs.getInt("ID_USUARIO")); // buscamos los nombres de cada usuario que comentó el post
                    Collections.addAll(Comentarios, rs2.getString("NOMBREUSUARIO") + " dice: " + rs.getString("CONTENIDO"));
                } while (rs.next());
                tablaComentarios.setItems(FXCollections.observableArrayList(Comentarios));
            } else {
                new Alerta().Information("No hay comentarios, sé el primero!!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ComentariosPostController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
