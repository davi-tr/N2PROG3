package br.edu.femass;

import br.edu.femass.dao.DaoAutor;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.model.Autor;
import br.edu.femass.model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLControllerLivro implements Initializable {

    @FXML
    private TextField TxtTitulo;

    @FXML
    private TextField TxtNomeAutor;

    @FXML
    private TextField TxtSobreNomeAutor;

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
    private Button BtnCancelar;
    @FXML
    private Button refreshButton;

    @FXML
    private TableColumn<Livro, Long> idLivro = new TableColumn<>();

    @FXML
    private TableColumn<Autor, String> sobrenomeAutor = new TableColumn<>();

    @FXML
    private TableColumn<Livro, String> titulo = new TableColumn<>();

    @FXML
    private TableColumn<Autor, String> nacionalidadeAutor = new TableColumn<>();

    @FXML
    private TableView<Autor> tabelaAutores = new TableView<Autor>();
    @FXML
    private TableColumn<Autor, String> nomeAutor = new TableColumn<>();
    @FXML
    private TableView<Livro> tabelaLivros = new TableView<Livro>();

    @FXML
    private TableColumn<Livro, String> nomeDoAutor = new TableColumn<>();
    @FXML
    private ProgressIndicator progresso;
    DaoAutor dao2 = new DaoAutor();
    @FXML
    private Label tituloAutores;
    private DaoLivro dao = new DaoLivro();
    private Autor autor;
    private Livro livro = new Livro();
    private Boolean alterar;

    public FXMLControllerLivro() {
    }


    @FXML
    private void Gravar_Click(ActionEvent event) {
        livro.setTitulo(TxtTitulo.getText());
        livro.setAutor(tabelaAutores.getSelectionModel().getSelectedItem());
        if(autor == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Por Favor Selecione um Autor");
            alert.show();
            return;
        }
        if(alterar == true){
            dao.alterar(livro);
        }else dao.inserir(livro);
        tabelaAutores.setVisible(false);
        tituloAutores.setVisible(false);
        preencherTabelaLivros();
        editar(false);
        tabelaLivros.setDisable(false);
        progresso.setProgress(1);
        }
    @FXML
    void Cancelar_Click(ActionEvent event) {
        editar(false);
        autor = null;
        livro = null;
        BtnExcluir.setDisable(true);
        if (tabelaLivros.isDisable()){
            tabelaLivros.setDisable(false);
        }
        progresso.setProgress(0);
    }

    @FXML
    private void keyPressed_teclaSelecionada(KeyEvent event){
        exibirDados();
    }
    @FXML
    private void valor_Selecionado(MouseEvent event){
        exibirDados();
    }
    private void exibirDados(){
    this.autor = tabelaAutores.getSelectionModel().getSelectedItem();
    if(autor == null){
       return;
    }
    TxtNomeAutor.setText(autor.getNome());
    TxtSobreNomeAutor.setText(autor.getSobreNome());
    TxtNacionalidade.setText(autor.getNacionalidade());

    }
    private void exibirDadosLivro(){
        this.livro = tabelaLivros.getSelectionModel().getSelectedItem();
        if(livro == null){
            return;
        }
        TxtTitulo.setText(livro.getTitulo());
        TxtNomeAutor.setText(livro.getAutor().getNome());
        TxtSobreNomeAutor.setText(livro.getAutor().getSobreNome());
        TxtNacionalidade.setText(livro.getAutor().getNacionalidade());
    }
    @FXML
    private void livro_selecionado(MouseEvent event){
        if (this.livro !=null){
            BtnExcluir.setDisable(false);
        }
        BtnExcluir.setDisable(false);
        BtnAlterar.setDisable(false);
        exibirDadosLivro();
    }

    @FXML
    private void Incluir_Click(ActionEvent event) {
        editar(true);
        this.livro = new Livro();
        tabelaLivros.setDisable(true);
        alterar = false;
        tabelaAutores.setVisible(true);
        tituloAutores.setVisible(true);
        TxtNacionalidade.setText("");
        TxtTitulo.setText("");
        TxtSobreNomeAutor.setText("");
        TxtNomeAutor.setText("");
        progresso.setProgress(0);

    }
    @FXML
    private void Editar_Click(ActionEvent event) {
        editar(true);
        tabelaLivros.setDisable(false);
        alterar = true;
        tabelaAutores.setVisible(true);
        tituloAutores.setVisible(true);
        exibirDadosLivro();
        progresso.setProgress(.50);
    }
    @FXML
    private void Excluir_Click(ActionEvent event) {
        dao.apagar(livro);
        preencherTabelaLivros();
    }
    private void preencherTabelaLivros(){
        List<Livro> livros = dao.buscarTodos();

        ObservableList<Livro> data =  FXCollections.observableList(livros);

        tabelaLivros.setItems(data);
    }
    private void preencherTabela(){
        List<Autor> autores = dao2.buscarTodos();
        ObservableList<Autor> data =  FXCollections.observableList(autores);
        tabelaAutores.setItems(data);
    }

    private void editar(boolean habilitar){
        tabelaAutores.setDisable(!habilitar);
        TxtTitulo.setDisable(!habilitar);
        BtnSalvar.setDisable(!habilitar);
        BtnAlterar.setDisable(habilitar);
        BtnIncluir.setDisable(habilitar);
        BtnExcluir.setDisable(habilitar);
        BtnCancelar.setDisable(!habilitar);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // preencherListaLivros();
        nomeDoAutor.setCellValueFactory(
                new PropertyValueFactory<Livro, String>("nomeDoautor")
        );
        nomeAutor.setCellValueFactory(
                new PropertyValueFactory<Autor, String>("nome")
        );
        nacionalidadeAutor.setCellValueFactory(
                new PropertyValueFactory<Autor, String>("nacionalidade")
        );
        titulo.setCellValueFactory(
                new PropertyValueFactory<Livro, String>("titulo")
        );
        sobrenomeAutor.setCellValueFactory(
                new PropertyValueFactory<Autor, String>("sobreNome")
        );
        idLivro.setCellValueFactory(
                new PropertyValueFactory<Livro, Long>("id")
        );

        preencherTabela();
        preencherTabelaLivros();
        BtnIncluir.setDisable(false);
        tituloAutores.setVisible(false);
        tabelaAutores.setVisible(false);
    }
}
