import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GeradorDeNotaFiscalTest {
    GeradorNotaFiscal geradorNotaFiscal;

    @BeforeEach
    void testCreateGerador(){
        geradorNotaFiscal = new GeradorNotaFiscal();

    }
    @Test
    void testGerarNotaFiscal(){
        Fatura fatura = new Fatura("Cliente", "Endereço", "EXEMPLO", 1000.0);
        NotaFiscal notaFiscal = geradorNotaFiscal.gerarNotaFiscal(fatura);

        assertEquals(fatura.getNomeCliente(), notaFiscal.getNomeCliente());
        assertEquals(fatura.getValorFatura(), notaFiscal.getValorNotaFiscal());
    }

    // Testes usando a técnica de Tabela de Decisões
    @Test
    void testGerarNotaFiscalTipoDeServicoConsultoria(){
        Fatura fatura = new Fatura("Cliente A", "Endereço A", "CONSULTORIA", 50.0);
        NotaFiscal notaFiscal = geradorNotaFiscal.gerarNotaFiscal(fatura);

        assertEquals(fatura.getValorFatura() * 0.25, notaFiscal.getValorImposto());
        // assertEquals(fatura.getValorFatura() * 0.25, 12.5);
    }

    @Test
    void testGerarNotaFiscalTipoDeServicoTreinamento(){
        Fatura fatura = new Fatura("Cliente B", "Endereço B", "TREINAMENTO", 50.0);
        NotaFiscal notaFiscal = geradorNotaFiscal.gerarNotaFiscal(fatura);

        assertEquals(fatura.getValorFatura() * 0.15, notaFiscal.getValorImposto());
        // assertEquals(fatura.getValorFatura() * 0.15, 7.5);
    }

    @Test
    void testGerarNotaFiscalTipoDeServicoOutro(){
        Fatura fatura = new Fatura("Cliente C", "Endereço C", "OUTRO", 50.0);
        NotaFiscal notaFiscal = geradorNotaFiscal.gerarNotaFiscal(fatura);

        assertEquals(fatura.getValorFatura() * 0.06, notaFiscal.getValorImposto());
        // assertEquals(fatura.getValorFatura() * 0.06, 3.00);
    }

    // Testes usando a técnica de Análise de Valores Limite e Partições de Equivalência
    @Test
    public void testGerarNotaFiscalConsultoriaValorMinimo() {
        Fatura fatura = new Fatura("Cliente", "Endereço", "CONSULTORIA", 0.00);
        NotaFiscal notaFiscal = geradorNotaFiscal.gerarNotaFiscal(fatura);
        assertEquals(notaFiscal.getValorImposto(), 0.00);
    }
    @Test
    public void testGerarNotaFiscalConsultoriaValorProximoMinimo() {
        Fatura fatura = new Fatura("Cliente", "Endereço", "CONSULTORIA", 0.01);
        NotaFiscal notaFiscal = geradorNotaFiscal.gerarNotaFiscal(fatura);
        assertEquals(notaFiscal.getValorImposto(), 0.0025);
    }
    @Test
    public void testGerarNotaFiscalConsultoriaValorMenorQueMinimo() {
        assertThrows(IllegalArgumentException.class, () -> {
            Fatura fatura = new Fatura("Cliente", "Endereço", "CONSULTORIA", -1);
        });
    }
    @Test
    public void testGerarNotaFiscalConsultoriaValorAleatorio() {
        Fatura fatura = new Fatura("Cliente", "Endereço", "CONSULTORIA", 100.00);
        NotaFiscal notaFiscal = geradorNotaFiscal.gerarNotaFiscal(fatura);
        assertEquals(notaFiscal.getValorImposto(), 25.00);
    }

    @Test
    public void testGerarNotaFiscalTreinamentoValorMinimo() {
        Fatura fatura = new Fatura("Cliente", "Endereço", "TREINAMENTO", 0.00);
        NotaFiscal notaFiscal = geradorNotaFiscal.gerarNotaFiscal(fatura);
        assertEquals(notaFiscal.getValorImposto(), 0.00);
    }
    @Test
    public void testGerarNotaFiscalTreinamentoValorProximoMinimo() {
        Fatura fatura = new Fatura("Cliente", "Endereço", "TREINAMENTO", 0.01);
        NotaFiscal notaFiscal = geradorNotaFiscal.gerarNotaFiscal(fatura);
        assertEquals(notaFiscal.getValorImposto(), 0.0015);
    }
    @Test
    public void testGerarNotaFiscalTreinamentoValorMenorQueMinimo() {
        assertThrows(IllegalArgumentException.class, () -> {
            Fatura fatura = new Fatura("Cliente", "Endereço", "TREINAMENTO", -1);
        });
    }
    @Test
    public void testGerarNotaFiscalTreinamentoValorAleatorio() {
        Fatura fatura = new Fatura("Cliente", "Endereço", "TREINAMENTO", 100.00);
        NotaFiscal notaFiscal = geradorNotaFiscal.gerarNotaFiscal(fatura);
        assertEquals(notaFiscal.getValorImposto(), 15.00);
    }

    @Test
    public void testGerarNotaFiscalOutroValorMinimo() {
        Fatura fatura = new Fatura("Cliente", "Endereço", "OUTRO", 0.00);
        NotaFiscal notaFiscal = geradorNotaFiscal.gerarNotaFiscal(fatura);
        assertEquals(notaFiscal.getValorImposto(), 0.00);
    }
    @Test
    public void testGerarNotaFiscalOutroValorProximoMinimo() {
        Fatura fatura = new Fatura("Cliente", "Endereço", "OUTRO", 0.01);
        NotaFiscal notaFiscal = geradorNotaFiscal.gerarNotaFiscal(fatura);
        assertEquals(notaFiscal.getValorImposto(), 0.0006);
    }
    @Test
    public void testGerarNotaFiscalOutroValorMenorQueMinimo() {
        assertThrows(IllegalArgumentException.class, () -> {
            Fatura fatura = new Fatura("Cliente", "Endereço", "OUTRO", -1);
        });
    }
    @Test
    public void testGerarNotaFiscalOutroValorAleatorio() {
        Fatura fatura = new Fatura("Cliente", "Endereço", "OUTRO", 100.00);
        NotaFiscal notaFiscal = geradorNotaFiscal.gerarNotaFiscal(fatura);
        assertEquals(notaFiscal.getValorImposto(), 6.00);
    }
}