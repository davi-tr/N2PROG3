package br.edu.femass;

import java.net.URL;
import java.util.ResourceBundle;

import java.util.List;

import br.edu.femass.dao.DaoProfessor;
import br.edu.femass.model.Autor;
import br.edu.femass.model.Professor;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class FXMLControllerProfessor implements Initializable {
    
    @FXML
    private TextField TxtNome;
    
    @FXML
    private TextField TxtEndereco;

    @FXML
    private TextField TxtTelefone;
    @FXML
    private TextField TxtDisciplina;


    @FXML
    private Button BtnSalvar;

    @FXML
    private Button BtnIncluir;

    @FXML
    private Button BtnAlterar;
    
    @FXML
    private Button BtnExcluir;
    @FXML
    private Button BtnVoltar;
    @FXML
    private Button refreshButton;

    @FXML
    private TableView<Professor> TabelaProfessores = new TableView<Professor>();
    @FXML
    private TableColumn<Professor, String> telefone = new TableColumn<>();
    @FXML
    private TableColumn<Professor, String> endereco = new TableColumn<>();
    @FXML
    private TableColumn<Professor, String> nome = new TableColumn<>();
    @FXML
    private TableColumn<Professor, String> disciplina = new TableColumn<>();
    @FXML
    private TableColumn<Professor, Long> id = new TableColumn<>();

    private DaoProfessor dao = new DaoProfessor();
    private Professor professor;
    private Boolean incluindo;

    @FXML
    private void Gravar_Click(ActionEvent event) {
        professor.setNome(TxtNome.getText());
        professor.setEndereco(TxtEndereco.getText());
        professor.setTelefone(TxtTelefone.getText());
        professor.setDisciplina(TxtDisciplina.getText());

        if(incluindo){
            dao.inserir(professor);
        } else{
            dao.alterar(professor);
        }

        preencherLista();
        editar(false);
    }
    @FXML
    void Voltar_Click(ActionEvent event) {
        Platform.exit();
    }
    
    @FXML
    private void altera_click(ActionEvent event) {
        editar(true);
        incluindo = true;
        BtnAlterar.setStyle("-fx-background-color: Yellow");
    }


    @FXML
    private void incluir_click(ActionEvent event) {
        editar(true);
        incluindo = true;
        professor =  new Professor();
        TxtEndereco.setText("");
        TxtNome.setText("");
        TxtDisciplina.setText("");
        TxtTelefone.setText("");
        TxtNome.requestFocus();

    }

    @FXML
    private void excluir_click(ActionEvent event) {
        dao.apagar(professor);
        preencherLista();
    }

    @FXML
    private void recarregar_click(ActionEvent event) {
        preencherLista();
    }
    @FXML
    private void keyPressed_teclaSelecionada(KeyEvent event){
        exibirDados();
    }

    @FXML
    private void valor_Selecionado(MouseEvent event){
        exibirDados();
    }

    private void editar(boolean habilitar){
        TabelaProfessores.setDisable(habilitar);
        TxtEndereco.setDisable(!habilitar);
        TxtNome.setDisable(!habilitar);
        TxtTelefone.setDisable(!habilitar);
        TxtDisciplina.setDisable(!habilitar);
        BtnSalvar.setDisable(!habilitar);
        BtnAlterar.setDisable(habilitar);
        BtnIncluir.setDisable(habilitar);
        BtnExcluir.setDisable(habilitar);
    }
    
    private void exibirDados(){
        this.professor =  TabelaProfessores.getSelectionModel().getSelectedItem();
        if(professor == null){
        return;
        }
        BtnExcluir.setStyle("-fx-background-color: Red");
        TxtNome.setText(professor.getNome());
        TxtDisciplina.setText(professor.getDisciplina());
        TxtTelefone.setText(professor.getTelefone());
        TxtEndereco.setText(professor.getEndereco());
    }

    private void preencherLista(){
        List<Professor> professores = dao.buscarTodos();

        ObservableList<Professor> data =  FXCollections.observableList(professores);
        TabelaProfessores.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherLista();
        id.setCellValueFactory(new PropertyValueFactory<Professor, Long>("id"));
        nome.setCellValueFactory(new PropertyValueFactory<Professor, String>("nome"));
        endereco.setCellValueFactory(new PropertyValueFactory<Professor, String>("endereco"));
        telefone.setCellValueFactory(new PropertyValueFactory<Professor, String>("telefone"));
        disciplina.setCellValueFactory(new PropertyValueFactory<Professor, String>("disciplina"));
    }    
}
