package br.edu.femass;

import br.edu.femass.dao.*;
import br.edu.femass.model.*;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

// TODO: 04/12/2022 Finalizar implementação classe Exemplar
public class FXMLControllerEmprestimo implements Initializable {

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
    private TableColumn<Emprestimo, Long> idEmprestimo;

    @FXML
    private TableColumn<Exemplar, String> titulo;

    @FXML
    private Button BtnExcluir;

    @FXML
    private TextField TxtNome;

    @FXML
    private TextField TxtEndereco;
    @FXML
    private TextField TxtTelefone;

    @FXML
    private TextField TxtPrazo;

    @FXML
    private TextField TxtSobreNomeAutor;

    @FXML
    private TableView<Emprestimo> tabelaEmprestimos;

    @FXML
    private ComboBox<Aluno> cboAlunos;

    @FXML
    private TableColumn<Livro, String> nomeDoAutor;
    @FXML
    private TableColumn<Emprestimo, LocalDate> data;
    @FXML
    private TableColumn<Emprestimo, LocalDate> devolucao;
    @FXML
    private TableColumn<Emprestimo, LocalDate> devolucaoPrevista;

    @FXML
    private Button BtnAlterar;

    @FXML
    private ImageView finalizado;

    @FXML
    private TextField TxtMat;

    private Livro livro = new Livro();
    private Aluno aluno = new Aluno();
    private Exemplar exemplar = new Exemplar();
    DaoEmprestimo dao = new DaoEmprestimo();
    DaoAluno daoAluno = new DaoAluno();
    private Boolean alterar;

    @FXML
    void Gravar_Click(ActionEvent event) {

//        editar(false);

//        try{
//        exemplar.setQuantidade(Integer.valueOf(txtQuantidade.getText()));
//        }catch (NumberFormatException num){
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Erro");
//            alert.setHeaderText("Informe a quantidade de exemplares a ser adicionados");
//            alert.show();
//            return;
//        }
//
//        try {
//            exemplar.setLivro(cboLivros.getSelectionModel().getSelectedItem());
//            exemplar.setSelected(cboLivros.getSelectionModel().getSelectedIndex());
//        }catch (NullPointerException n){
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Erro");
//            alert.setHeaderText("Informe o livro que deseja criar o exemplar");
//            alert.show();
//            return;
//        }
//
//        if(alterar){
//            dao.alterar(exemplar);
//        }else dao.inserir(exemplar);
//
//        finalizado.setVisible(true);
//        preencherTabelaExemplar();
    }

    @FXML
    void Incluir_Click(ActionEvent event) {
        alterar = false;
        //editar(true);
        //txtQuantidade.setText("");
        this.exemplar = new Exemplar();
    }

    @FXML
    void Editar_Click(ActionEvent event) {
        alterar = true;
        //editar(true);
        //cboLivros.getSelectionModel().select(exemplar.getSelected());
        finalizado.setVisible(false);
    }

    @FXML
    void Excluir_Click(ActionEvent event) {
       //dao.apagar(exemplar);
        //preencherTabelaExemplar();
    }

    @FXML
    void aluno_selecionado(ActionEvent event) {
        exibirDadosAluno();
    }

    @FXML
    void Cancelar_Click(ActionEvent event) {
        //editar(false);
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
    public void preecherCombo(){
        List<Aluno> alunos = daoAluno.buscarTodos();


        ObservableList<Aluno> data = FXCollections.observableList(alunos);

        cboAlunos.setItems(data);

    }
    private void preencherTabelaProfessor(){
//        List<Aluno> alunos = daoAluno.buscarTodos();
//        List<Professor> professores = daoProfessor.buscarTodos();
//
//        ObservableList<Professor> data = FXCollections.observableArrayList(professores);
//
//        tabelaEmprestimos.setItems(data);
//        tabelaEmprestimos.refresh();
    }

    private void preencherTabela(){
    List<Emprestimo> emprestimos = dao.buscarTodos();
    ObservableList<Emprestimo> data = FXCollections.observableArrayList(emprestimos);
    tabelaEmprestimos.setItems(data);
    }
    private void exibirDadosAluno(){
       this.aluno = cboAlunos.getSelectionModel().getSelectedItem();
       TxtMat.setText(aluno.getMatricula());
       TxtNome.setText(aluno.getNome());
       TxtEndereco.setText(aluno.getEndereco());
       TxtTelefone.setText(aluno.getTelefone());
       TxtPrazo.setText(String.valueOf(aluno.getPrazoMaximoDev()));

    }
    private void exibirDadosExemplar(){
//        this.exemplar = tabelaEmprestimos.getSelectionModel().getSelectedItem();
//        TxtTitulo.setText(exemplar.getTitulo());
//        txtQuantidade.setText(String.valueOf(exemplar.getQuantidade()));
//        TxtNomeAutor.setText(exemplar.getLivro().getAutor().getNome());
//        TxtSobreNomeAutor.setText(exemplar.getLivro().getAutor().getSobreNome());
//        TxtNacionalidade.setText(exemplar.getLivro().getAutor().getNacionalidade());
    }

//    private void editar(boolean habilitar){
//        tabelaExemplar.setDisable(habilitar);
//        cboLivros.setDisable(!habilitar);
//        BtnSalvar.setDisable(!habilitar);
//        txtQuantidade.setDisable(!habilitar);
//        BtnAlterar.setDisable(habilitar);
//        BtnIncluir.setDisable(habilitar);
//        BtnExcluir.setDisable(habilitar);
//        BtnCancelar.setDisable(!habilitar);
//    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        finalizado.setVisible(false);
        preecherCombo();
//        preencherTabelaLeitores();
        //editar(false);
        //preencherTabelaExemplar();
        nomeDoAutor.setCellValueFactory(new PropertyValueFactory<Livro, String>("nomeDoautor"));
        idEmprestimo.setCellValueFactory(new PropertyValueFactory<Emprestimo, Long>("id"));
        titulo.setCellValueFactory(new PropertyValueFactory<Exemplar, String>("titulo"));
        devolucao.setCellValueFactory(new PropertyValueFactory<Emprestimo, LocalDate>("quantidade"));
        data.setCellValueFactory(new PropertyValueFactory<Emprestimo,LocalDate>("dataDevolucao"));
        devolucaoPrevista.setCellValueFactory(new PropertyValueFactory<Emprestimo, LocalDate>("dataDevolucaoPrevista"));
    }
}
