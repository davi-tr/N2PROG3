package br.edu.femass;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.model.Aluno;
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

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLControllerAluno implements Initializable {
    
    @FXML
    private TextField TxtNome;
    
    @FXML
    private TextField TxtEndereco;

    @FXML
    private TextField TxtTelefone;
    @FXML
    private TextField TxtMatricula;
    @FXML
    private ListView<Aluno> LstAlunos;

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

    private DaoAluno dao = new DaoAluno();
    private Aluno aluno;
    private Boolean incluindo;

    @FXML
    private void Gravar_Click(ActionEvent event) {
        aluno.setNome(TxtNome.getText());
        aluno.setEndereco(TxtEndereco.getText());
        aluno.setTelefone(TxtTelefone.getText());
        aluno.setMatricula(TxtMatricula.getText());

        if(incluindo){
            dao.inserir(aluno);
        } else{
            dao.alterar(aluno);
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
        aluno =  new Aluno();
        TxtEndereco.setText("");
        TxtNome.setText("");
        TxtNome.requestFocus();
        BtnIncluir.setStyle("-fx-background-color: MediumSeaGreen");
        BtnExcluir.setStyle(null);

    }

    @FXML
    private void excluir_click(ActionEvent event) {
        dao.apagar(aluno);
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
        LstAlunos.setDisable(habilitar);
        TxtEndereco.setDisable(!habilitar);
        TxtNome.setDisable(!habilitar);
        TxtTelefone.setDisable(!habilitar);
        TxtMatricula.setDisable(!habilitar);
        BtnSalvar.setDisable(!habilitar);
        BtnAlterar.setDisable(habilitar);
        BtnIncluir.setDisable(habilitar);
        BtnExcluir.setDisable(habilitar);
    }
    
    private void exibirDados(){
        this.aluno =  LstAlunos.getSelectionModel().getSelectedItem();
        if(aluno == null){
        BtnExcluir.setStyle(null);
        return;
        }
        BtnExcluir.setStyle("-fx-background-color: Red");
        TxtNome.setText(aluno.getNome());
        TxtMatricula.setText(aluno.getMatricula());
        TxtTelefone.setText(aluno.getTelefone());
        TxtEndereco.setText(aluno.getEndereco());
    }

    private void preencherLista(){
        List<Aluno> alunos = dao.buscarTodos();

        ObservableList<Aluno> data =  FXCollections.observableList(alunos);
        LstAlunos.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherLista();
    }    
}
