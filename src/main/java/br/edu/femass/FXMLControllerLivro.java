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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
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
    private ListView<Autor> LstAutores;

    @FXML
    private ListView<Livro> LstLivro;
    @FXML
    private Button BtnSalvar;
    @FXML
    private Button refreshButton;
    DaoAutor dao2 = new DaoAutor();
    private DaoLivro dao = new DaoLivro();
    private Autor autor;
    private Livro livro;
    private Boolean incluindo;

    public FXMLControllerLivro() {
    }


    @FXML
    private void Gravar_Click(ActionEvent event) {
        livro.setTitulo(TxtTitulo.getText());
        livro.setAutores(LstAutores.getSelectionModel().getSelectedItems());

        if(incluindo){
            dao.inserir(livro);
        } else{
            dao.alterar(livro);
        }
        //preencherListaLivros();
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
    this.autor = LstAutores.getSelectionModel().getSelectedItem();
    TxtNomeAutor.setText(autor.getNome());
    TxtSobreNomeAutor.setText(autor.getSobreNome());
    TxtNacionalidade.setText(autor.getNacionalidade());
    }

    private void preencherListaLivros(){
        List<Livro> livros = dao.buscarTodos();

        ObservableList<Livro> data =  FXCollections.observableList(livros);
        LstLivro.setItems(data);
    }
    private void preencherLista(){
        List<Autor> autores = dao2.buscarTodos();
        ObservableList<Autor> data =  FXCollections.observableList(autores);
        LstAutores.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // preencherListaLivros();
        preencherLista();
        if(TxtTitulo.getText()!=null){
            BtnSalvar.setDisable(false);
        }
    }
}
