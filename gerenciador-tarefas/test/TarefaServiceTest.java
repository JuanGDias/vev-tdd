import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TarefaServiceTest {

    private TarefaService tarefaService;
    @BeforeEach
    void createTarefaService(){
        tarefaService = new TarefaService();
    }

    @Test
    void testCriarTarefa(){
        Tarefa tarefa = tarefaService.criarTarefa("Atividade VeV", "Exercicio 2", "21-08-2023", "ALTA");
        assertEquals(tarefa.getTitulo(), "Atividade VeV");
        assertEquals(tarefa.getDescricao(), "Exercicio 2");
        assertEquals(tarefa.getDataVencimento(), "21-08-2023");
        assertEquals(tarefa.getPrioridade(), "ALTA");
    }

    @Test
    void testAdicionarTarefaLista(){
        Tarefa tarefa = tarefaService.criarTarefa("Atividade 1", "Exercicio 1", "21-08-2023", "ALTA");
        Map<String, Tarefa> tarefas = tarefaService.getTarefas();
        assertEquals(1, tarefas.size());
    }

    @Test
    void testAtualizarTarefa(){
        Tarefa tarefa = tarefaService.criarTarefa("Atividade 1", "Quiz 1", "21-08-2023", "MEDIA");
        tarefaService.atualizarTarefa(tarefa, "Atividade 2", "Quiz 1", "21-08-2023", "MEDIA");

        assertEquals(tarefa.getTitulo(), "Atividade 2");
        assertEquals(tarefa.getDescricao(), "Quiz 1");
        assertEquals(tarefa.getDataVencimento(), "21-08-2023");
        assertEquals(tarefa.getPrioridade(), "MEDIA");
    }

    @Test
    void testExcluirTarefa(){
        Tarefa tarefa = tarefaService.criarTarefa("Exercício", "Quiz 2", "21-08-2023", "BAIXA");
        tarefaService.excluirTarefa(tarefa);
        assertNull(tarefaService.getTarefa(tarefa.getId()));
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
}