import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    }
}