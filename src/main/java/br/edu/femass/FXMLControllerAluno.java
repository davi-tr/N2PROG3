package br.edu.femass;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.model.Aluno;
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
    private TableColumn<Aluno, String> telefone = new TableColumn<>();
    @FXML
    private TableColumn<Aluno, Long> id = new TableColumn<>();
    @FXML
    private TableColumn<Aluno, String> endereco = new TableColumn<>();
    @FXML
    private TableColumn<Aluno, String> nome = new TableColumn<>();
    @FXML
    private TableColumn<Aluno, String> matricula = new TableColumn<>();
    @FXML
    private TableColumn<Aluno, Integer> prazo = new TableColumn<>();
    @FXML
    private TableView<Aluno> tabelaAlunos = new TableView<Aluno>();
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

    private DaoAluno dao = new DaoAluno();
    private Aluno aluno = new Aluno();
    private Boolean alterar;

    @FXML
    private void Gravar_Click(ActionEvent event) {
        aluno.setNome(TxtNome.getText());
        aluno.setEndereco(TxtEndereco.getText());
        aluno.setTelefone(TxtTelefone.getText());
        aluno.setMatricula(TxtMatricula.getText());

        if(alterar){
            dao.alterar(aluno);
        }else {
            dao.inserir(aluno);
        }
        preencherTabela();
        editar(false);
    }
    @FXML
    private void altera_click(ActionEvent event) {
        editar(true);
        alterar = true;
    }

    @FXML
    private void incluir_click(ActionEvent event) {
        editar(true);
        alterar = false;
        aluno = new Aluno();
        TxtEndereco.setText("");
        TxtNome.setText("");
        TxtTelefone.setText("");
        TxtMatricula.setText("");
        TxtNome.requestFocus();

    }

    @FXML
    private void excluir_click(ActionEvent event) {
        dao.apagar(aluno);
        preencherTabela();
    }

    @FXML
    void Voltar_Click(ActionEvent event) {
        Stage stage = (Stage) BtnVoltar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void recarregar_click(ActionEvent event) {
        List<Aluno> alunos = dao.buscarTodos();

        ObservableList<Aluno> data =  FXCollections.observableList(alunos);
        tabelaAlunos.setItems(data);
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
        tabelaAlunos.setDisable(habilitar);
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
        this.aluno = tabelaAlunos.getSelectionModel().getSelectedItem();
        if(aluno == null) return;
        TxtNome.setText(aluno.getNome());
        TxtMatricula.setText(aluno.getMatricula());
        TxtTelefone.setText(aluno.getTelefone());
        TxtEndereco.setText(aluno.getEndereco());
    }

    private void preencherTabela(){
        List<Aluno> alunos = dao.buscarTodos();

        ObservableList<Aluno> data =  FXCollections.observableList(alunos);
        tabelaAlunos.setItems(data);

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherTabela();
        id.setCellValueFactory(new PropertyValueFactory<Aluno, Long>("id"));
        nome.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome"));
        telefone.setCellValueFactory(new PropertyValueFactory<Aluno, String>("telefone"));
        endereco.setCellValueFactory(new PropertyValueFactory<Aluno, String>("endereco"));
        matricula.setCellValueFactory(new PropertyValueFactory<Aluno, String>("matricula"));
        prazo.setCellValueFactory(new PropertyValueFactory<Aluno, Integer>("prazoMaximoDev"));
    }    
}
