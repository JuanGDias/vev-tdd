public class GeradorNotaFiscal {
    private Smtp smtp;

    public GeradorNotaFiscal() {
        this.smtp = new Smtp();

    }

    public NotaFiscal gerarNotaFiscal(Fatura fatura) {
        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setNomeCliente(fatura.getNomeCliente());
        notaFiscal.setValorNotaFiscal(fatura.getValorFatura());
        notaFiscal.setValorImposto(this.calculaImposto(fatura.getValorFatura(), fatura.getTipoServico()));

        this.smtp.envia(notaFiscal);

        return notaFiscal;
    }
    public double calculaImposto(Double valorFatura, String tipoServico) {
        if (tipoServico.equals("CONSULTORIA")) {
            return valorFatura * 0.25;
        } else if (tipoServico.equals("TREINAMENTO")) {
            return valorFatura * 0.15;
        }
        return valorFatura * 0.06;
    }
}