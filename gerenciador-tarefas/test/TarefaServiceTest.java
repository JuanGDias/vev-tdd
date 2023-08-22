import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}