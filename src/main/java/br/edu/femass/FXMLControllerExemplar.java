package br.edu.femass;

import br.edu.femass.model.Exemplar;
import br.edu.femass.model.Livro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

// TODO: 04/12/2022 Finalizar implementação classe Exemplar
public class FXMLControllerExemplar implements Initializable {

    @FXML
    private Button BtnIncluir;

    @FXML
    private Button BtnCancelar;

    @FXML
    private ProgressIndicator progresso;

    @FXML
    private Button BtnSalvar;

    @FXML
    private Button BtnVoltar;

    @FXML
    private TableColumn<Exemplar, Long> idLivro;

    @FXML
    private TableColumn<Livro, String> titulo;

    @FXML
    private Button BtnExcluir;

    @FXML
    private TableView<Exemplar> tabelaExemplar;

    @FXML
    private TableColumn<Exemplar, String> nomeDoAutor;

    @FXML
    private Button BtnAlterar;

    @FXML
    private ImageView finalizado;


    @FXML
    void Gravar_Click(ActionEvent event) {
        finalizado.setVisible(true);
    }

    @FXML
    void Incluir_Click(ActionEvent event) {

    }

    @FXML
    void Editar_Click(ActionEvent event) {

    }

    @FXML
    void Excluir_Click(ActionEvent event) {

    }

    @FXML
    void livro_selecionado(ActionEvent event) {

    }

    @FXML
    void Cancelar_Click(ActionEvent event) {

    }

    @FXML
    void keyPressed_teclaSelecionada(ActionEvent event) {

    }

    @FXML
    void Voltar_Click(ActionEvent event) {
        Stage stage = (Stage) BtnVoltar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        finalizado.setVisible(false);
    }
}
