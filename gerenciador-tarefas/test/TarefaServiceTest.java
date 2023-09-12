import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class TarefaServiceTest {

    private TarefaService tarefaService;
    @BeforeEach
    void createTarefaService(){
        tarefaService = new TarefaService();
    }

    // Testes usando a técnica de Partições de Equivalência
    @Test
    void testCriarTarefaValida(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "20-09-2023", "ALTA");
        assertEquals(tarefa.getTitulo(), "Tarefa");
        assertEquals(tarefa.getDescricao(), "Descrição");
        assertEquals(tarefa.getDataVencimento(), "20-09-2023");
        assertEquals(tarefa.getPrioridade(), "ALTA");
    }
    @Test
    void testCriarTarefaComTituloEmBranco(){
        assertThrows(IllegalAccessError.class, () -> {
            Tarefa tarefa = tarefaService.criarTarefa("", "Descrição", "20-09-2023", "ALTA");
        });
    }
    @Test
    void testCriarTarefaComDescricaoEmBranco(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "", "20-09-2023", "ALTA");
        assertEquals(tarefa.getTitulo(), "Tarefa");
        assertEquals(tarefa.getDescricao(), "");
        assertEquals(tarefa.getDataVencimento(), "20-09-2023");
        assertEquals(tarefa.getPrioridade(), "ALTA");
    }
    @Test
    void testCriarTarefaComDataDeVencimentoEmBranco(){
        assertThrows(IllegalAccessError.class, () -> {
            Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "", "ALTA");
        });
    }
    @Test
    void testCriarTarefaComPrioridadeEmBranco(){
        assertThrows(IllegalAccessError.class, () -> {
            Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "20-09-2023", "");
        });
    }
    @Test
    void testCriarTarefaComDataDeVencimentoPassado(){
        assertThrows(IllegalAccessError.class, () -> {
            Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "01-01-1999", "ALTA");
        });
    }

    @Test
    void testCriarTarefaComPrioridadeInvalida(){
        assertThrows(IllegalAccessError.class, () -> {
            Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "20-09-2023", "INVÁLIDA");
        });
    }

    @Test
    void testAdicionarTarefaLista(){
        Tarefa tarefa = tarefaService.criarTarefa("Atividade 1", "Exercicio 1", "21-08-2023", "ALTA");
        Map<String, Tarefa> tarefas = tarefaService.getTarefas();
        assertEquals(1, tarefas.size());
    }

    @Test
    void testAtualizarTarefa(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "20-09-2023", "ALTA");
        tarefaService.atualizarTarefa(tarefa, "Tarefa Atualizada", "Descrição Atualizada", "26-10-2023", "MEDIA");

        assertEquals(tarefa.getTitulo(), "Tarefa Atualizada");
        assertEquals(tarefa.getDescricao(), "Descrição Atualizada");
        assertEquals(tarefa.getDataVencimento(), "26-10-2023");
        assertEquals(tarefa.getPrioridade(), "MEDIA");
    }
    @Test
    void testAtualizarTituloDeUmaTarefa(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa Atualizada", "Descrição Atualizada", "26-10-2023", "MEDIA");
        tarefaService.atualizarTarefa(tarefa, "Tarefa", "Descrição Atualizada", "26-10-2023", "MEDIA");

        assertEquals(tarefa.getTitulo(), "Tarefa");
        assertEquals(tarefa.getDescricao(), "Descrição Atualizada");
        assertEquals(tarefa.getDataVencimento(), "26-10-2023");
        assertEquals(tarefa.getPrioridade(), "MEDIA");
    }
    @Test
    void testAtualizarDescricaoDeUmaTarefa(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição Atualizada", "26-10-2023", "MEDIA");
        tarefaService.atualizarTarefa(tarefa, "Tarefa", "Descrição", "26-10-2023", "MEDIA");

        assertEquals(tarefa.getTitulo(), "Tarefa");
        assertEquals(tarefa.getDescricao(), "Descrição");
        assertEquals(tarefa.getDataVencimento(), "26-10-2023");
        assertEquals(tarefa.getPrioridade(), "MEDIA");
    }

    @Test
    void testAtualizarDataDeVencimentoDeUmaTarefa(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "26-10-2023", "MEDIA");
        tarefaService.atualizarTarefa(tarefa, "Tarefa", "Descrição", "30-11-2023", "MEDIA");

        assertEquals(tarefa.getTitulo(), "Tarefa");
        assertEquals(tarefa.getDescricao(), "Descrição");
        assertEquals(tarefa.getDataVencimento(), "30-11-2023");
        assertEquals(tarefa.getPrioridade(), "MEDIA");
    }

    @Test
    void testAtualizarPrioridadeDeUmaTarefa(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "26-10-2023", "MEDIA");
        tarefaService.atualizarTarefa(tarefa, "Tarefa", "Descrição", "30-11-2023", "BAIXA");

        assertEquals(tarefa.getTitulo(), "Tarefa");
        assertEquals(tarefa.getDescricao(), "Descrição");
        assertEquals(tarefa.getDataVencimento(), "30-11-2023");
        assertEquals(tarefa.getPrioridade(), "BAIXA");
    }
    @Test
    void testAtualizarTituloDeUmaTarefaParaBranco(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "26-10-2023", "BAIXA");
        assertThrows(IllegalAccessError.class, () -> {
            tarefaService.atualizarTarefa(tarefa, "", "Descrição", "30-11-2023", "BAIXA");

        });
    }
    @Test
    void testAtualizarDescricaoDeUmaTarefaParaBranco(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "26-10-2023", "BAIXA");
        tarefaService.atualizarTarefa(tarefa, "Tarefa", "", "30-11-2023", "BAIXA");

        assertEquals(tarefa.getTitulo(), "Tarefa");
        assertEquals(tarefa.getDescricao(), "");
        assertEquals(tarefa.getDataVencimento(), "30-11-2023");
        assertEquals(tarefa.getPrioridade(), "BAIXA");
    }

    @Test
    void testAtualizarDataDeVencimentoDeUmaTarefaParaBranco(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "30-11-2023", "BAIXA");
        assertThrows(IllegalAccessError.class, () -> {
            tarefaService.atualizarTarefa(tarefa, "Tarefa", "Descrição", "", "BAIXA");

        });
    }
    @Test
    void testAtualizarPrioridadeDeUmaTarefaParaBranco(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "30-11-2023", "BAIXA");
        assertThrows(IllegalAccessError.class, () -> {
            tarefaService.atualizarTarefa(tarefa, "Tarefa", "Descrição", "30-11-2023", "");

        });
    }
    @Test
    void testAtualizarUmaTarefaInexistente(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "30-11-2023", "BAIXA");
        String id = tarefa.getId();
        tarefaService.excluirTarefa(tarefa);
        assertThrows(NullPointerException.class, () -> {
            tarefaService.atualizarTarefa(tarefaService.getTarefa(id), "Tarefa", "Descrição", "30-11-2023", "BAIXA");

        });
    }

    @Test
    void testExcluirTarefa(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "30-11-2023", "BAIXA");
        tarefaService.excluirTarefa(tarefa);
        assertNull(tarefaService.getTarefa(tarefa.getId()));
    }
    @Test
    void testExcluirUmaTarefaInexistente(){
        Tarefa tarefa = new Tarefa("Tarefa", "Descrição", "30-11-2023", "BAIXA");
        assertThrows(NullPointerException.class, () -> {
            tarefaService.excluirTarefa(tarefa);

        });
    }

    @Test
    void testExcluirTarefaLista(){
        Tarefa tarefa = tarefaService.criarTarefa("Exercício 1", "Quiz 1", "21-08-2023", "MEDIA");
        Map<String, Tarefa> tarefas = tarefaService.getTarefas();
        assertEquals(1, tarefas.size());

        tarefaService.excluirTarefa(tarefa);
        tarefas = tarefaService.getTarefas();
        assertEquals(0, tarefas.size());
    }

    @Test
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
    void testlistarTarefasOrdenadasPorPrioridade(){
        Tarefa tarefa1 = tarefaService.criarTarefa("Quiz 1", "Quiz 1", "29-08-2023", "BAIXA");
        Tarefa tarefa2 = tarefaService.criarTarefa("Quiz 2", "Quiz 2", "29-08-2023", "ALTA");
        Tarefa tarefa3 = tarefaService.criarTarefa("Quiz 3", "Quiz 3", "29-08-2023", "MEDIA");

        List<Tarefa> tarefasOrdenadas = tarefaService.listarTarefasOrdenadas();

        assertEquals(tarefa2.getTitulo(), tarefasOrdenadas.get(0).getTitulo());
        assertEquals(tarefa3.getTitulo(), tarefasOrdenadas.get(1).getTitulo());
        assertEquals(tarefa1.getTitulo(), tarefasOrdenadas.get(2).getTitulo());
    }

    @Test
    void testAlterarPrioridadeParaAlta(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa 1", "Tarefa 1", "21-08-2023", "BAIXA");
        tarefaService.alterarPrioridade(tarefa, "ALTA");

        assertEquals(tarefa.getPrioridade(), "ALTA");
    }
    @Test
    void testAlterarPrioridadeParaMedia(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa 1", "Tarefa 1", "21-08-2023", "ALTA");
        tarefaService.alterarPrioridade(tarefa, "MEDIA");

        assertEquals(tarefa.getPrioridade(), "MEDIA");
    }
    @Test
    void testAlterarPrioridadeParaBaixa(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa 1", "Tarefa 1", "21-08-2023", "MEDIA");
        tarefaService.alterarPrioridade(tarefa, "BAIXA");

        assertEquals(tarefa.getPrioridade(), "BAIXA");
    }

    @Test
    void testAlterarPrioridadeParaTarefaInexistente(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa 1", "Tarefa 1", "21-08-2023", "MEDIA");
        tarefaService.excluirTarefa(tarefa);
        assertThrows(NullPointerException.class, () -> {
            tarefaService.alterarPrioridade(tarefa, "BAIXA");

        });
    }

    // Testes usando a técnica de Tabela de Decisões
    @Test
    void testAlterarPrioridadeParaBranco(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa 1", "Tarefa 1", "21-08-2023", "MEDIA");
        assertThrows(IllegalArgumentException.class, () -> {
            tarefaService.alterarPrioridade(tarefa, "");
        });

    }
    @Test
    void testAlterarPrioridadeParaInvalida(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa 1", "Tarefa 1", "21-08-2023", "MEDIA");
        assertThrows(IllegalArgumentException.class, () -> {
            tarefaService.alterarPrioridade(tarefa, "INVALIDA");
        });

    }

   /* @Test
    void testCriarTarefaQueJaExiste(){
        Tarefa tarefa = tarefaService.criarTarefa("Tarefa", "Descrição", "20-09-2023", "ALTA");
        assertThrows(TarefaAlreadyExistsException.class, () -> {
            tarefaService.criarTarefa("Tarefa", "Descrição", "20-09-2023", "ALTA");
        });
    }
*/

}