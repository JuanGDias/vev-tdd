import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TarefaService {

    private Map<String, Tarefa> tarefas;

    public TarefaService() {
        tarefas = new HashMap<>();
    }

    public Tarefa getTarefa(String id){
        return tarefas.get(id);
    }

    public Map<String, Tarefa> getTarefas(){
        return tarefas;
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

    public void atualizarTarefa(Tarefa tarefa, String tituloNovo, String descricaoNova, String dataVencimentoNova, String prioridadeNova) {
        tarefa.setTitulo(tituloNovo);
        tarefa.setDescricao(descricaoNova);
        tarefa.setDataVencimento(dataVencimentoNova);
        tarefa.setPrioridade(prioridadeNova);
    }

    public void excluirTarefa(Tarefa tarefa) {
        tarefas.remove(tarefa.getId());
    }
}