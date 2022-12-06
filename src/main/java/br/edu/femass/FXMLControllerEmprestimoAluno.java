package br.edu.femass;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.dao.DaoEmprestimo;
import br.edu.femass.dao.DaoExemplar;
import br.edu.femass.model.Aluno;
import br.edu.femass.model.Emprestimo;
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
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

// TODO: 04/12/2022 Finalizar implementação classe Exemplar
public class FXMLControllerEmprestimoAluno implements Initializable {

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
    private TextField TxtExemplares;

    @FXML
    private TableView<Emprestimo> tabelaEmprestimos;


    @FXML
    private TableColumn<Emprestimo, String> nome;
    @FXML
    private TableColumn<Emprestimo, LocalDate> data;
    @FXML
    private TableColumn<Emprestimo, LocalDate> devolucao;
    @FXML
    private TableColumn<Emprestimo, LocalDate> devolucaoPrevista;

    @FXML
    private ComboBox<Aluno> cboAlunos;
    @FXML
    private TableView<Exemplar> tabelaExemplar;
    @FXML
    private TableColumn<Livro, String> nomeDoAutor1;
    @FXML
    private TableColumn<Exemplar, String> titulo1;
    @FXML
    private TableColumn<Exemplar, Integer> qtd;
    @FXML
    private TableColumn<Exemplar, Integer> data1;
    @FXML
    private TableColumn<Exemplar, Long> idExemplar;
    @FXML
    private Button BtnAlterar;

    @FXML
    private ImageView finalizado;

    @FXML
    private TextField TxtMat;

    @FXML
    private Label exemplaresHeader;
    @FXML
    private Label emprestimoHeader;
    @FXML
    private Label labelQtd1;

    private Livro livro = new Livro();
    private Aluno aluno = new Aluno();
    private Emprestimo emprestimo = new Emprestimo();
    private Exemplar exemplar;
    DaoEmprestimo dao = new DaoEmprestimo();
    DaoAluno daoAluno = new DaoAluno();
    DaoExemplar daoExemplar = new DaoExemplar();

    private Boolean alterar;

    @FXML
    void Gravar_Click(ActionEvent event) {

//        editar(false);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setLeitor(aluno);
        emprestimo.setExemplar(tabelaExemplar.getSelectionModel().getSelectedItem());
        emprestimo.setDataDevolucaoPrevista(LocalDate.now().plusDays(aluno.getPrazoMaximoDev()));
        exemplar.setQuantidade(exemplar.getQuantidade()-1);
        daoExemplar.alterar(exemplar);
        if(alterar){
            dao.alterar(emprestimo);
        }else dao.inserir(emprestimo);

        editar(false);
        finalizado.setVisible(true);
        preencherTabela();
        preencherTabelaExemplares();
    }

    @FXML
    void Incluir_Click(ActionEvent event) {
        alterar = false;
        editar(true);
        finalizado.setVisible(false);
        labelQtd1.setText("Matricula");
        //txtQuantidade.setText("");
    }

    @FXML
    void Editar_Click(ActionEvent event) {
        alterar = true;
        editar(true);
        finalizado.setVisible(false);
        labelQtd1.setText("Matricula");
    }

    @FXML
    void Excluir_Click(ActionEvent event) {
       this.exemplar = emprestimo.getExemplar();
       exemplar.setQuantidade(exemplar.getQuantidade()+1);

        dao.apagar(emprestimo);
        daoExemplar.alterar(exemplar);

       preencherTabelaExemplares();
       preencherTabela();
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
    @FXML
    void keyPressed_teclaSelecionada_emprestimo(KeyEvent event) {
        dadosEmprestimo();
    }
    public void dadosEmprestimo(){
        this.emprestimo = tabelaEmprestimos.getSelectionModel().getSelectedItem();
        TxtExemplares.setText(String.valueOf(emprestimo.getExemplar().getQuantidade()));
        labelQtd1.setText("Titulo");
        TxtMat.setText(emprestimo.getExemplar().getTitulo());
        TxtNome.setText(emprestimo.getLeitor().getNome());
        TxtEndereco.setText(emprestimo.getLeitor().getEndereco());
        TxtTelefone.setText(emprestimo.getLeitor().getTelefone());
        TxtPrazo.setText(String.valueOf(emprestimo.getDataDevolucaoPrevista()));
        finalizado.setVisible(true);
    }

    @FXML
    void emprestimo_selecionado(MouseEvent event) {
        dadosEmprestimo();
    }
    public void preecherCombo(){
        List<Aluno> alunos = daoAluno.buscarTodos();


        ObservableList<Aluno> data = FXCollections.observableList(alunos);

        cboAlunos.setItems(data);

    }

    private void preencherTabela(){
    List<Emprestimo> emprestimos = dao.buscarTodos();
    ObservableList<Emprestimo> data = FXCollections.observableArrayList(emprestimos);
    tabelaEmprestimos.setItems(data);
    }

    private void preencherTabelaExemplares(){
        List<Exemplar> exemplares = daoExemplar.buscarTodos();
        ObservableList<Exemplar> data = FXCollections.observableArrayList(exemplares);
        tabelaExemplar.setItems(data);
        tabelaExemplar.refresh();
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
        this.exemplar = tabelaExemplar.getSelectionModel().getSelectedItem();
        TxtExemplares.setText(String.valueOf(exemplar.getQuantidade()));
    }

    private void editar(boolean habilitar){
        tabelaEmprestimos.setVisible(!habilitar);
        emprestimoHeader.setVisible(!habilitar);

        exemplaresHeader.setVisible(habilitar);
        tabelaExemplar.setVisible(habilitar);
        tabelaExemplar.setDisable(!habilitar);
        cboAlunos.setDisable(!habilitar);
        BtnSalvar.setDisable(!habilitar);
        BtnAlterar.setDisable(habilitar);
        BtnIncluir.setDisable(habilitar);
        BtnExcluir.setDisable(habilitar);
        BtnCancelar.setDisable(!habilitar);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        editar(false);
        finalizado.setVisible(false);
        preecherCombo();
        preencherTabelaExemplares();
        preencherTabela();

        nome.setCellValueFactory(new PropertyValueFactory<Emprestimo, String>("leitor"));
        idEmprestimo.setCellValueFactory(new PropertyValueFactory<Emprestimo, Long>("id"));
        titulo.setCellValueFactory(new PropertyValueFactory<Exemplar, String>("titulo"));
        devolucao.setCellValueFactory(new PropertyValueFactory<Emprestimo, LocalDate>("dataDevolucao"));
        data.setCellValueFactory(new PropertyValueFactory<Emprestimo,LocalDate>("dataEmprestimo"));
        devolucaoPrevista.setCellValueFactory(new PropertyValueFactory<Emprestimo, LocalDate>("dataDevolucaoPrevista"));

        nomeDoAutor1.setCellValueFactory(new PropertyValueFactory<Livro, String>("nomeDoautor"));
        idExemplar.setCellValueFactory(new PropertyValueFactory<Exemplar, Long>("id"));
        titulo1.setCellValueFactory(new PropertyValueFactory<Exemplar, String>("titulo"));
        qtd.setCellValueFactory(new PropertyValueFactory<Exemplar, Integer>("quantidade"));
        data1.setCellValueFactory(new PropertyValueFactory<Exemplar,Integer>("dataAq"));
    }
}
