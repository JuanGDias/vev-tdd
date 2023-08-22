import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TarefaService {

    private Map<String, Tarefa> tarefas;

    public TarefaService(){
        this.tarefas = new HashMap<>();
    }

    public Tarefa criarTarefa(String titulo, String descricao, String dataVencimento, String prioridade) {

        Tarefa tarefa = new Tarefa();

        tarefa.setId(UUID.randomUUID().toString());
        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);
        tarefa.setDataVencimento(dataVencimento);
        tarefa.setPrioridade(prioridade);

        tarefas.put(tarefa.getId(), tarefa);

        return tarefa;
    }

    public Map<String, Tarefa> getTarefas(){
        return tarefas;
    }
}