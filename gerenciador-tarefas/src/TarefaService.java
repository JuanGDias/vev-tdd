public class TarefaService {

    public Tarefa criarTarefa(String titulo, String descricao, String dataVencimento, String prioridade) {

        Tarefa tarefa = new Tarefa();

        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);
        tarefa.setDataVencimento(dataVencimento);
        tarefa.setPrioridade(prioridade);

        return tarefa;
    }
}