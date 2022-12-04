package br.edu.femass;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.dao.DaoAutor;
import br.edu.femass.model.Aluno;
import br.edu.femass.model.Autor;
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
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLControllerAutor implements Initializable {
    
    @FXML
    private TextField TxtNome;
    
    @FXML
    private TextField TxtSobrenome;

    @FXML
    private TextField TxtNacionalidade;

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
    private TableView<Autor> TabelaAutores = new TableView<Autor>();
    @FXML
    private TableColumn<Autor, Long> id = new TableColumn<>();
    @FXML
    private TableColumn<Autor, String> sobrenome = new TableColumn<>();
    @FXML
    private TableColumn<Autor, String> nacionalidade = new TableColumn<>();
    @FXML
    private TableColumn<Autor, String> nome = new TableColumn<>();

    private DaoAutor dao = new DaoAutor();
    private Autor autor;
    private Boolean incluindo;

    @FXML
    private void Gravar_Click(ActionEvent event) {
        autor.setNome(TxtNome.getText());
        autor.setSobreNome(TxtSobrenome.getText());
        autor.setNacionalidade(TxtNacionalidade.getText());

        if(incluindo){
            dao.inserir(autor);
        } else{
            dao.alterar(autor);
        }

        preencherLista();
        editar(false);
    }
    @FXML
    void Voltar_Click(ActionEvent event) {
        Stage stage = (Stage) BtnVoltar.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void altera_click(ActionEvent event) {
        editar(true);
        incluindo = true;
    }

    @FXML
    private void incluir_click(ActionEvent event) {
        editar(true);
        incluindo = true;
        autor =  new Autor();
        TxtNacionalidade.setText("");
        TxtNome.setText("");
        TxtNome.requestFocus();

    }

    @FXML
    private void excluir_click(ActionEvent event) {
        dao.apagar(autor);
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
        TabelaAutores.setDisable(habilitar);
        TxtNacionalidade.setDisable(!habilitar);
        TxtNome.setDisable(!habilitar);
        TxtSobrenome.setDisable(!habilitar);
        BtnSalvar.setDisable(!habilitar);
        BtnAlterar.setDisable(habilitar);
        BtnIncluir.setDisable(habilitar);
        BtnExcluir.setDisable(habilitar);
    }
    
    private void exibirDados(){
        this.autor =  TabelaAutores.getSelectionModel().getSelectedItem();
        if(autor == null){
        BtnExcluir.setStyle(null);
        return;
        }
        TxtNome.setText(autor.getNome());
        TxtSobrenome.setText(autor.getNome());
        TxtNacionalidade.setText(autor.getNacionalidade());
    }

    private void preencherLista(){
        List<Autor> autores = dao.buscarTodos();

        ObservableList<Autor> data =  FXCollections.observableList(autores);
        TabelaAutores.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherLista();
        id.setCellValueFactory(new PropertyValueFactory<Autor, Long>("id"));
        nome.setCellValueFactory(new PropertyValueFactory<Autor, String>("nome"));
        sobrenome.setCellValueFactory(new PropertyValueFactory<Autor, String>("sobreNome"));
        nacionalidade.setCellValueFactory(new PropertyValueFactory<Autor, String>("nacionalidade"));
    }    
}
