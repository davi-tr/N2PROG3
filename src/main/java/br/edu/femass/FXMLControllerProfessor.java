package br.edu.femass;

import java.net.URL;
import java.util.ResourceBundle;

import java.util.List;

import br.edu.femass.dao.DaoProfessor;
import br.edu.femass.model.Professor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    private ListView<Professor> LstProfessores;

    @FXML
    private Button BtnSalvar;

    @FXML
    private Button BtnIncluir;

    @FXML
    private Button BtnAlterar;
    
    @FXML
    private Button BtnExcluir;

    @FXML
    private Button refreshButton;

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
        BtnIncluir.setStyle(null);
        BtnAlterar.setStyle(null);
        BtnExcluir.setStyle(null);
    }
    
    @FXML
    private void altera_click(ActionEvent event) {
        editar(true);
        incluindo = true;
        BtnAlterar.setStyle("-fx-background-color: Yellow");
        BtnExcluir.setStyle(null);
    }

    @FXML
    private void incluir_click(ActionEvent event) {
        editar(true);
        incluindo = true;
        professor =  new Professor();
        TxtEndereco.setText("");
        TxtNome.setText("");
        TxtNome.requestFocus();
        BtnIncluir.setStyle("-fx-background-color: MediumSeaGreen");
        BtnExcluir.setStyle(null);

    }

    @FXML
    private void excluir_click(ActionEvent event) {
        dao.apagar(professor);
        preencherLista();
        BtnExcluir.setStyle(null);
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
        LstProfessores.setDisable(habilitar);
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
        this.professor =  LstProfessores.getSelectionModel().getSelectedItem();
        if(professor == null){
        BtnExcluir.setStyle(null);
        return;
        }
        BtnExcluir.setStyle("-fx-background-color: Red");
        TxtNome.setText(professor.getNome());
        TxtEndereco.setText(professor.getEndereco());
    }

    private void preencherLista(){
        List<Professor> professores = dao.buscarTodos();

        ObservableList<Professor> data =  FXCollections.observableList(professores);
        LstProfessores.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherLista();
    }    
}
