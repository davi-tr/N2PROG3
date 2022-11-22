package br.edu.femass;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.dao.DaoAutor;
import br.edu.femass.model.Aluno;
import br.edu.femass.model.Autor;
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

public class FXMLControllerAutor implements Initializable {
    
    @FXML
    private TextField TxtNome;
    
    @FXML
    private TextField TxtSobrenome;

    @FXML
    private TextField TxtNacionalidade;

    @FXML
    private ListView<Autor> LstAutores;

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
        autor =  new Autor();
        TxtNacionalidade.setText("");
        TxtNome.setText("");
        TxtNome.requestFocus();
        BtnIncluir.setStyle("-fx-background-color: MediumSeaGreen");
        BtnExcluir.setStyle(null);

    }

    @FXML
    private void excluir_click(ActionEvent event) {
        dao.apagar(autor);
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
        LstAutores.setDisable(habilitar);
        TxtNacionalidade.setDisable(!habilitar);
        TxtNome.setDisable(!habilitar);
        TxtSobrenome.setDisable(!habilitar);
        BtnSalvar.setDisable(!habilitar);
        BtnAlterar.setDisable(habilitar);
        BtnIncluir.setDisable(habilitar);
        BtnExcluir.setDisable(habilitar);
    }
    
    private void exibirDados(){
        this.autor =  LstAutores.getSelectionModel().getSelectedItem();
        if(autor == null){
        BtnExcluir.setStyle(null);
        return;
        }
        BtnExcluir.setStyle("-fx-background-color: Red");
        TxtNome.setText(autor.getNome());
        TxtSobrenome.setText(autor.getNome());
        TxtNacionalidade.setText(autor.getNacionalidade());
    }

    private void preencherLista(){
        List<Autor> autores = dao.buscarTodos();

        ObservableList<Autor> data =  FXCollections.observableList(autores);
        LstAutores.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherLista();
    }    
}
