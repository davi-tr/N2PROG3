package br.edu.femass;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.model.Aluno;
import br.edu.femass.model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
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
    private TableColumn<Livro, String> nomeDoAutor;

    @FXML
    private Button BtnAlterar;

    @FXML
    void Gravar_Click(ActionEvent event) {

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
    void Voltar_Click(ActionEvent event) {

    }

}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
}
