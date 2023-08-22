public class GeradorNotaFiscal {
    public NotaFiscal gerarNotaFiscal(Fatura fatura) {

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setNomeCliente(fatura.getNomeCliente());
        notaFiscal.setValorNotaFiscal(fatura.getValorFatura());
        notaFiscal.setValorImposto(fatura.getValorFatura());
        return notaFiscal;
    }
}