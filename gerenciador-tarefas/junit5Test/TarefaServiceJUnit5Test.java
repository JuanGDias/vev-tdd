import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Testes para o TarefaService")
public class TarefaServiceJUnit5Test {

    private TarefaService tarefaService;

    @BeforeEach
    void createTarefaService(){
        tarefaService = new TarefaService();
    }

    @ParameterizedTest
    @CsvSource({
            "Tarefa, Descrição, 20-09-2023, ALTA",
            "Tarefa 2, Descrição 2, 25-12-2023, MEDIA",
            "Tarefa 3, Descrição 3, 30-11-2023, BAIXA"
    })
    @DisplayName("Teste de criação de uma tarefa válida")
    void testCriarTarefaValida(String titulo, String descricao, String dataVencimento, String prioridade) {
        Tarefa tarefa = tarefaService.criarTarefa(titulo, descricao, dataVencimento, prioridade);
        assertEquals(tarefa.getTitulo(), titulo);
        assertEquals(tarefa.getDescricao(), descricao);
        assertEquals(tarefa.getDataVencimento(), dataVencimento);
        assertEquals(tarefa.getPrioridade(), prioridade);
    }

    @Test
    @DisplayName("Teste de adição de uma tarefa na lista de tarefas")
    void testAdicionarTarefaLista(){
        Tarefa tarefa = tarefaService.criarTarefa("Atividade 1", "Exercicio 1", "21-08-2023", "ALTA");
        Map<String, Tarefa> tarefas = tarefaService.getTarefas();
        assertEquals(1, tarefas.size());
    }

    @Test
    @DisplayName("Teste de atualização de uma tarefa")
    void testAtualizarTarefa(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "20-09-2023", "ALTA");
        tarefaService.atualizarTarefa(tarefa, "Tarefa Atualizada", "Descrição Atualizada", "26-10-2023", "MEDIA");

        assertEquals(tarefa.getTitulo(), "Tarefa Atualizada");
        assertEquals(tarefa.getDescricao(), "Descrição Atualizada");
        assertEquals(tarefa.getDataVencimento(), "26-10-2023");
        assertEquals(tarefa.getPrioridade(), "MEDIA");
    }

    @Test
    @DisplayName("Teste para atualização de um título de uma tarefa")
    void testAtualizarTituloDeUmaTarefa(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa Atualizada", "Descrição Atualizada", "26-10-2023", "MEDIA");
        tarefaService.atualizarTarefa(tarefa, "Tarefa", "Descrição Atualizada", "26-10-2023", "MEDIA");

        assertEquals(tarefa.getTitulo(), "Tarefa");
        assertEquals(tarefa.getDescricao(), "Descrição Atualizada");
        assertEquals(tarefa.getDataVencimento(), "26-10-2023");
        assertEquals(tarefa.getPrioridade(), "MEDIA");
    }

    @Test
    @DisplayName("Teste para atualização de uma descrição de uma tarefa")
    void testAtualizarDescricaoDeUmaTarefa(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição Atualizada", "26-10-2023", "MEDIA");
        tarefaService.atualizarTarefa(tarefa, "Tarefa", "Descrição", "26-10-2023", "MEDIA");

        assertEquals(tarefa.getTitulo(), "Tarefa");
        assertEquals(tarefa.getDescricao(), "Descrição");
        assertEquals(tarefa.getDataVencimento(), "26-10-2023");
        assertEquals(tarefa.getPrioridade(), "MEDIA");
    }

    @Test
    @DisplayName("Teste para atualização de uma data de vencimento de uma tarefa")
    void testAtualizarDataDeVencimentoDeUmaTarefa(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "26-10-2023", "MEDIA");
        tarefaService.atualizarTarefa(tarefa, "Tarefa", "Descrição", "30-11-2023", "MEDIA");

        assertEquals(tarefa.getTitulo(), "Tarefa");
        assertEquals(tarefa.getDescricao(), "Descrição");
        assertEquals(tarefa.getDataVencimento(), "30-11-2023");
        assertEquals(tarefa.getPrioridade(), "MEDIA");
    }

    @ParameterizedTest
    @CsvSource({"ALTA", "MEDIA", "BAIXA"})
    @DisplayName("Teste para atualização de uma prioridade de uma tarefa")
    void testAtualizarPrioridadeDeUmaTarefa(String prioridadeValida){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "26-10-2023", "MEDIA");
        tarefaService.atualizarTarefa(tarefa, "Tarefa", "Descrição", "30-11-2023", prioridadeValida);

        assertEquals(tarefa.getTitulo(), "Tarefa");
        assertEquals(tarefa.getDescricao(), "Descrição");
        assertEquals(tarefa.getDataVencimento(), "30-11-2023");
        assertEquals(tarefa.getPrioridade(), prioridadeValida);
    }

    @ParameterizedTest
    @ValueSource(strings = {""})
    @DisplayName("Teste para atualizar título de uma tarefa para branco")
    void testAtualizarTituloDeUmaTarefaParaBranco(String tituloEmBranco){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "26-10-2023", "BAIXA");
        assertThrows(IllegalAccessError.class, () -> {
            tarefaService.atualizarTarefa(tarefa, tituloEmBranco, "Descrição", "30-11-2023", "BAIXA");
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {""})
    @DisplayName("Teste para atualizar descrição de uma tarefa para branco")
    void testAtualizarDescricaoDeUmaTarefaParaBranco(String descricaoEmBranco){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "26-10-2023", "BAIXA");
        tarefaService.atualizarTarefa(tarefa, "Tarefa", descricaoEmBranco, "30-11-2023", "BAIXA");

        assertEquals(tarefa.getTitulo(), "Tarefa");
        assertEquals(tarefa.getDescricao(), descricaoEmBranco);
        assertEquals(tarefa.getDataVencimento(), "30-11-2023");
        assertEquals(tarefa.getPrioridade(), "BAIXA");
    }

    @ParameterizedTest
    @ValueSource(strings = {""})
    @DisplayName("Teste para atualizar data de vencimento de uma tarefa para branco")
    void testAtualizarDataDeVencimentoDeUmaTarefaParaBranco(String dataVencimentoEmBranco){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "30-11-2023", "BAIXA");
        assertThrows(IllegalAccessError.class, () -> {
            tarefaService.atualizarTarefa(tarefa, "Tarefa", "Descrição", dataVencimentoEmBranco, "BAIXA");
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {""})
    @DisplayName("Teste para atualizar prioridade de uma tarefa para branco")
    void testAtualizarPrioridadeDeUmaTarefaParaBranco(String prioridadeEmBranco){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "30-11-2023", "BAIXA");
        assertThrows(IllegalAccessError.class, () -> {
            tarefaService.atualizarTarefa(tarefa, "Tarefa", "Descrição", "30-11-2023", prioridadeEmBranco);
        });
    }

    @Test
    @DisplayName("Teste para atualizar uma tarefa inexistente")
    void testAtualizarUmaTarefaInexistente(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "30-11-2023", "BAIXA");
        String id = tarefa.getId();
        tarefaService.excluirTarefa(tarefa);
        assertThrows(NullPointerException.class, () -> {
            tarefaService.atualizarTarefa(tarefaService.getTarefa(id), "Tarefa", "Descrição", "30-11-2023", "BAIXA");
        });
    }

    @Test
    @DisplayName("Teste para deletar uma tarefa")
    void testExcluirTarefa(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "30-11-2023", "BAIXA");
        tarefaService.excluirTarefa(tarefa);
        assertNull(tarefaService.getTarefa(tarefa.getId()));
    }

    @Test
    @DisplayName("Teste para deletar uma tarefa inexistente")
    void testExcluirUmaTarefaInexistente(){
        Tarefa tarefa = new Tarefa("Tarefa", "Descrição", "30-11-2023", "BAIXA");
        assertThrows(NullPointerException.class, () -> {
            tarefaService.excluirTarefa(tarefa);
        });
    }

    @Test
    @DisplayName("Teste para deletar uma tarefa existente")
    void testExcluirTarefaLista(){
        Tarefa tarefa = tarefaService.criarTarefa("Exercício 1", "Quiz 1", "21-08-2023", "MEDIA");
        Map<String, Tarefa> tarefas = tarefaService.getTarefas();
        assertEquals(1, tarefas.size());

        tarefaService.excluirTarefa(tarefa);
        tarefas = tarefaService.getTarefas();
        assertEquals(0, tarefas.size());
    }

    @Test
    @DisplayName("Teste para listar tarefas ordenadas")
    void testlistarTarefasOrdenadas(){
        Tarefa tarefa1 = tarefaService.criarTarefa("Quiz 1", "Quiz 1", "29-08-2023", "MEDIA");
        Tarefa tarefa2 = tarefaService.criarTarefa("Quiz 2", "Quiz 2", "30-08-2023", "ALTA");
        Tarefa tarefa3 = tarefaService.criarTarefa("Quiz 3", "Quiz 3", "31-08-2023", "BAIXA");

        List<Tarefa> tarefasOrdenadas = tarefaService.listarTarefasOrdenadas();

        assertEquals(tarefa2.getTitulo(), tarefasOrdenadas.get(0).getTitulo());
        assertEquals(tarefa1.getTitulo(), tarefasOrdenadas.get(1).getTitulo());
        assertEquals(tarefa3.getTitulo(), tarefasOrdenadas.get(2).getTitulo());
    }

    @Test
    @DisplayName("Teste para listar tarefas ordenadas por data de vencimento")
    void testlistarTarefasOrdenadasPorDataVencimento(){
        Tarefa tarefa1 = tarefaService.criarTarefa("Quiz 1", "Quiz 1", "29-08-2023", "ALTA");
        Tarefa tarefa2 = tarefaService.criarTarefa("Quiz 2", "Quiz 2", "30-08-2023", "ALTA");
        Tarefa tarefa3 = tarefaService.criarTarefa("Quiz 3", "Quiz 3", "31-08-2023", "ALTA");

        List<Tarefa> tarefasOrdenadas = tarefaService.listarTarefasOrdenadas();

        assertEquals(tarefa1.getTitulo(), tarefasOrdenadas.get(0).getTitulo());
        assertEquals(tarefa2.getTitulo(), tarefasOrdenadas.get(1).getTitulo());
        assertEquals(tarefa3.getTitulo(), tarefasOrdenadas.get(2).getTitulo());
    }

    @Test
    @DisplayName("Teste para listar tarefas ordenadas por prioridade")
    void testlistarTarefasOrdenadasPorPrioridade(){
        Tarefa tarefa1 = tarefaService.criarTarefa("Quiz 1", "Quiz 1", "29-08-2023", "BAIXA");
        Tarefa tarefa2 = tarefaService.criarTarefa("Quiz 2", "Quiz 2", "29-08-2023", "ALTA");
        Tarefa tarefa3 = tarefaService.criarTarefa("Quiz 3", "Quiz 3", "29-08-2023", "MEDIA");

        List<Tarefa> tarefasOrdenadas = tarefaService.listarTarefasOrdenadas();

        assertEquals(tarefa2.getTitulo(), tarefasOrdenadas.get(0).getTitulo());
        assertEquals(tarefa3.getTitulo(), tarefasOrdenadas.get(1).getTitulo());
        assertEquals(tarefa1.getTitulo(), tarefasOrdenadas.get(2).getTitulo());
    }

    @ParameterizedTest
    @CsvSource({"ALTA", "MEDIA", "BAIXA"})
    @DisplayName("Teste para alteração de prioridade de uma tarefa")
    void testAlterarPrioridade(String prioridade) {
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa 1", "Tarefa 1", "21-08-2023", "MEDIA");
        tarefaService.alterarPrioridade(tarefa, prioridade);
        assertEquals(tarefa.getPrioridade(), prioridade);
    }

    @Test
    @DisplayName("Teste para alteração de prioridade de uma tarefa inexistente")
    void testAlterarPrioridadeParaTarefaInexistente(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa 1", "Tarefa 1", "21-08-2023", "MEDIA");
        tarefaService.excluirTarefa(tarefa);
        assertThrows(NullPointerException.class, () -> {
            tarefaService.alterarPrioridade(tarefa, "BAIXA");
        });
    }

    @ParameterizedTest
    @CsvSource({"ALTA", "MEDIA", "BAIXA"})
    @DisplayName("Teste de criação de uma tarefa com prioridades válidas")
    void testCriarTarefaComPrioridadeValida(String prioridade) {
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "20-09-2023", prioridade);
        assertEquals(tarefa.getPrioridade(), prioridade);
    }

    @ParameterizedTest
    @ValueSource(strings = {""})
    @DisplayName("Teste de criação de tarefa com título em branco")
    void testCriarTarefaComTituloEmBranco(String tituloEmBranco) {
        assertThrows(IllegalAccessError.class, () -> {
            Tarefa tarefa = tarefaService.criarTarefa(tituloEmBranco, "Descrição", "20-09-2023", "ALTA");
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {""})
    @DisplayName("Teste de criação de tarefa com descrição em branco")
    void testCriarTarefaComDescricaoEmBranco(String descricaoEmBranco) {
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa", descricaoEmBranco, "20-09-2023", "ALTA");
        assertEquals(tarefa.getDescricao(), descricaoEmBranco);
    }

    @ParameterizedTest
    @ValueSource(strings = {""})
    @DisplayName("Teste de criação de tarefa com data de vencimento em branco")
    void testCriarTarefaComDataDeVencimentoEmBranco(String dataVencimentoEmBranco) {
        assertThrows(IllegalAccessError.class, () -> {
            Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", dataVencimentoEmBranco, "ALTA");
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {""})
    @DisplayName("Teste de criação de tarefa com prioridade em branco")
    void testCriarTarefaComPrioridadeEmBranco(String prioridadeEmBranco) {
        assertThrows(IllegalAccessError.class, () -> {
            Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "20-09-2023", prioridadeEmBranco);
        });
    }

    @ParameterizedTest
    @CsvSource({"01-01-1999", "15-05-2022"})
    @DisplayName("Teste de criação de tarefa com data de vencimento no passado")
    void testCriarTarefaComDataDeVencimentoPassado(String dataVencimentoPassado) {
        assertThrows(IllegalAccessError.class, () -> {
            Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", dataVencimentoPassado, "ALTA");
        });
    }

    @ParameterizedTest
    @CsvSource({"INVÁLIDA"})
    @DisplayName("Teste de criação de tarefa com prioridade inválida")
    void testCriarTarefaComPrioridadeInvalida(String prioridadeInvalida) {
        assertThrows(IllegalAccessError.class, () -> {
            Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "20-09-2023", prioridadeInvalida);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"INVÁLIDA", "INVALIDA"})
    @DisplayName("Teste de alteração de prioridade de uma tarefa para inválida")
    void testAlterarPrioridadeParaInvalida(String prioridadeInvalida) {
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa 1", "Tarefa 1", "21-08-2023", "MEDIA");
        assertThrows(IllegalArgumentException.class, () -> {
            tarefaService.alterarPrioridade(tarefa, prioridadeInvalida);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {""})
    @DisplayName("Teste de alteração de prioridade de uma tarefa para branco")
    void testAlterarPrioridadeParaBranco(String prioridadeEmBranco) {
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa 1", "Tarefa 1", "21-08-2023", "MEDIA");
        assertThrows(IllegalArgumentException.class, () -> {
            tarefaService.alterarPrioridade(tarefa, prioridadeEmBranco);
        });
    }
}