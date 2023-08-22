public class Fatura {
    private String nomeCliente;
    private String enderecoCliente;
    private String tipoServico;
    private double valorFatura;

    public Fatura(String nomeCliente, String enderecoCliente, String tipoServico, double valorFatura) {
        this.nomeCliente = nomeCliente;
        this.enderecoCliente = enderecoCliente;
        this.tipoServico = tipoServico;
        this.valorFatura = valorFatura;
    }
}