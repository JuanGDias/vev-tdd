import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeradorDeNotaFiscalTest {
    GeradorNotaFiscal geradorNotaFiscal;

    @BeforeEach
    void testCreateGeradorNotaFiscal() {
        geradorNotaFiscal = new GeradorNotaFiscal();

    }

    @Test
    void testGerarNotaFiscal() {
        Fatura fatura = new Fatura("Cliente", "Endereço", "EXEMPLO", 1000.0);
        NotaFiscal notaFiscal = geradorNotaFiscal.gerarNotaFiscal(fatura);

        assertEquals(fatura.getNomeCliente(), notaFiscal.getNomeCliente());
        assertEquals(fatura.getValorFatura(), notaFiscal.getValorNotaFiscal());
    }
}