import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Testes para o GeradorDeNotaFiscal")
public class GeradorDeNotaFiscalJunit5Test {
    GeradorNotaFiscal geradorNotaFiscal;
    @BeforeAll
    public void setUp() {
        geradorNotaFiscal = new GeradorNotaFiscal();
    }
    @Test
    @DisplayName("Teste de geração de nota fiscal")
    public void testGerarNotaFiscal() {
        Fatura fatura = new Fatura("Cliente", "Endereço", "EXEMPLO", 1000.0);
        NotaFiscal notaFiscal = geradorNotaFiscal.gerarNotaFiscal(fatura);

        assertEquals(fatura.getNomeCliente(), notaFiscal.getNomeCliente());
        assertEquals(fatura.getValorFatura(), notaFiscal.getValorNotaFiscal());
    }

    @ParameterizedTest
    @CsvSource({
            "CONSULTORIA, 50.0, 12.5",
            "TREINAMENTO, 50.0, 7.5",
            "OUTRO, 50.0, 3.0"
    })
    @DisplayName("Teste de geração de nota fiscal com diferentes tipos de serviço")
    public void testGerarNotaFiscalComDiferentesTiposDeServico(
            String tipoServico, double valorFatura, double valorImpostoEsperado) {
        Fatura fatura = new Fatura("Cliente", "Endereço", tipoServico, valorFatura);
        NotaFiscal notaFiscal = geradorNotaFiscal.gerarNotaFiscal(fatura);

        assertEquals(valorImpostoEsperado, notaFiscal.getValorImposto());
    }

    @ParameterizedTest
    @CsvSource({
            "CONSULTORIA, 0.00, 0.00",
            "CONSULTORIA, 0.01, 0.0025",
            "TREINAMENTO, 0.00, 0.00",
            "TREINAMENTO, 0.01, 0.0015",
            "OUTRO, 0.00, 0.00",
            "OUTRO, 0.01, 0.0006"
    })
    @DisplayName("Teste de geração de nota fiscal com valores mínimos e próximos")
    public void testGerarNotaFiscalComValoresMinimosEProximos(
            String tipoServico, double valorFatura, double valorImpostoEsperado) {
        Fatura fatura = new Fatura("Cliente", "Endereço", tipoServico, valorFatura);
        NotaFiscal notaFiscal = geradorNotaFiscal.gerarNotaFiscal(fatura);

        assertEquals(valorImpostoEsperado, notaFiscal.getValorImposto());
    }

    @ParameterizedTest
    @CsvSource({
            "CONSULTORIA, -1",
            "TREINAMENTO, -1",
            "OUTRO, -1"
    })
    public void testGerarNotaFiscalComValorNegativo(String tipoServico, double valorFatura) {
        assertThrows(IllegalArgumentException.class, () -> {
            Fatura fatura = new Fatura("Cliente", "Endereço", tipoServico, valorFatura);
            geradorNotaFiscal.gerarNotaFiscal(fatura);
        });
    }
    @ParameterizedTest
    @CsvSource({
            "CONSULTORIA, 100.0, 25.00",
            "TREINAMENTO, 100.0, 15.00",
            "OUTRO, 100.0, 6.00"
    })
    @DisplayName("Teste de geração de nota fiscal com valor aleatorio")
    public void testGerarNotaFiscalComValorAleatorio(
            String tipoServico, double valorFatura, double valorImpostoEsperado) {
        Fatura fatura = new Fatura("Cliente", "Endereço", tipoServico, valorFatura);
        NotaFiscal notaFiscal = geradorNotaFiscal.gerarNotaFiscal(fatura);

        assertEquals(valorImpostoEsperado, notaFiscal.getValorImposto());
    }
}
