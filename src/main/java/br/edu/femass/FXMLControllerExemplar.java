package br.edu.femass;

import br.edu.femass.dao.DaoExemplar;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.model.Exemplar;
import br.edu.femass.model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
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
    private TableColumn<Exemplar, Long> idExemplar;

    @FXML
    private TableColumn<Exemplar, String> titulo;

    @FXML
    private Button BtnExcluir;

    @FXML
    private TextField TxtTitulo;

    @FXML
    private TextField TxtNomeAutor;
    @FXML
    private TextField txtQuantidade;

    @FXML
    private TextField TxtNacionalidade;

    @FXML
    private TextField TxtSobreNomeAutor;

    @FXML
    private TableView<Exemplar> tabelaExemplar;

    @FXML
    private ComboBox<Livro> cboLivros;

    @FXML
    private TableColumn<Livro, String> nomeDoAutor;
    @FXML
    private TableColumn<Exemplar, Integer> qtd;
    @FXML
    private TableColumn<Exemplar, Integer> data;

    @FXML
    private Button BtnAlterar;

    @FXML
    private ImageView finalizado;

    private Livro livro = new Livro();
    private Exemplar exemplar = new Exemplar();
    DaoExemplar dao = new DaoExemplar();
    DaoLivro dao2 = new DaoLivro();
    private Boolean alterar;

    @FXML
    void Gravar_Click(ActionEvent event) {
//        editar(false);

        try{
        exemplar.setQuantidade(Integer.valueOf(txtQuantidade.getText()));
        }catch (NumberFormatException num){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Informe a quantidade de exemplares a ser adicionados");
            alert.show();
            return;
        }

        try {
            exemplar.setLivro(cboLivros.getSelectionModel().getSelectedItem());
            exemplar.setSelected(cboLivros.getSelectionModel().getSelectedIndex());
        }catch (NullPointerException n){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Informe o livro que deseja criar o exemplar");
            alert.show();
            return;
        }

        if(alterar){
            dao.alterar(exemplar);
        }else dao.inserir(exemplar);
        editar(false);
        finalizado.setVisible(true);
        preencherTabelaExemplar();
    }

    @FXML
    void Incluir_Click(ActionEvent event) {
        alterar = false;
        editar(true);
        txtQuantidade.setText("");
        this.exemplar = new Exemplar();
    }

    @FXML
    void Editar_Click(ActionEvent event) {
        alterar = true;
        editar(true);
        cboLivros.getSelectionModel().select(exemplar.getSelected());
        finalizado.setVisible(false);
    }

    @FXML
    void Excluir_Click(ActionEvent event) {
        dao.apagar(exemplar);
        preencherTabelaExemplar();
    }

    @FXML
    void livro_selecionado(ActionEvent event) {
        exibirDadosLivro();
    }

    @FXML
    void Cancelar_Click(ActionEvent event) {
        editar(false);
        BtnExcluir.setDisable(true);
    }
    @FXML
    void Voltar_Click(ActionEvent event) {
        Stage stage = (Stage) BtnVoltar.getScene().getWindow();
        stage.close();
    }
    @FXML
    void keyPressed_teclaSelecionada_exemplar(KeyEvent event) {
        exibirDadosExemplar();
    }

    @FXML
    void exemplar_selecionado(MouseEvent event) {
        exibirDadosExemplar();
        finalizado.setVisible(false);
    }
    private void preencherTabelaExemplar(){
        List<Exemplar> exemplares = dao.buscarTodos();

        ObservableList<Exemplar> data =  FXCollections.observableList(exemplares);

        tabelaExemplar.setItems(data);
        tabelaExemplar.refresh();
    }
    private void preencherCombo(){
        List<Livro> livros = dao2.buscarTodos();

        ObservableList<Livro> data =  FXCollections.observableList(livros);
        cboLivros.setItems(data);
    }
    private void exibirDadosLivro(){
       this.livro = cboLivros.getSelectionModel().getSelectedItem();
       TxtTitulo.setText(livro.getTitulo());
       TxtNomeAutor.setText(livro.getAutor().getNome());
       TxtSobreNomeAutor.setText(livro.getAutor().getSobreNome());
       TxtNacionalidade.setText(livro.getAutor().getNacionalidade());

    }
    private void exibirDadosExemplar(){
        this.exemplar = tabelaExemplar.getSelectionModel().getSelectedItem();
        TxtTitulo.setText(exemplar.getTitulo());
        txtQuantidade.setText(String.valueOf(exemplar.getQuantidade()));
        TxtNomeAutor.setText(exemplar.getLivro().getAutor().getNome());
        TxtSobreNomeAutor.setText(exemplar.getLivro().getAutor().getSobreNome());
        TxtNacionalidade.setText(exemplar.getLivro().getAutor().getNacionalidade());
    }

    private void editar(boolean habilitar){
        tabelaExemplar.setDisable(habilitar);
        cboLivros.setDisable(!habilitar);
        BtnSalvar.setDisable(!habilitar);
        txtQuantidade.setDisable(!habilitar);
        BtnAlterar.setDisable(habilitar);
        BtnIncluir.setDisable(habilitar);
        BtnExcluir.setDisable(habilitar);
        BtnCancelar.setDisable(!habilitar);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        finalizado.setVisible(false);
        preencherCombo();
        editar(false);
        preencherTabelaExemplar();
        nomeDoAutor.setCellValueFactory(new PropertyValueFactory<Livro, String>("nomeDoautor"));
        idExemplar.setCellValueFactory(new PropertyValueFactory<Exemplar, Long>("id"));
        titulo.setCellValueFactory(new PropertyValueFactory<Exemplar, String>("titulo"));
        qtd.setCellValueFactory(new PropertyValueFactory<Exemplar, Integer>("quantidade"));
        data.setCellValueFactory(new PropertyValueFactory<Exemplar,Integer>("dataAq"));
    }
}
