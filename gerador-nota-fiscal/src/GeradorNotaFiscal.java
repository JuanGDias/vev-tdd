public class GeradorNotaFiscal {

    public NotaFiscal gerarNotaFiscal(Fatura fatura) {

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setNomeCliente(fatura.getNomeCliente());
        notaFiscal.setValorNotaFiscal(fatura.getValorFatura());
        notaFiscal.setValorImposto(this.calculaImposto(fatura.getValorFatura(), fatura.getTipoServico()));
        return notaFiscal;
    }

    public double calculaImposto(Double valorFatura, String tipoServico) {
        if (tipoServico.equals("CONSULTORIA")) {
            return valorFatura * 0.25;
        }
        return 0;
    }
}