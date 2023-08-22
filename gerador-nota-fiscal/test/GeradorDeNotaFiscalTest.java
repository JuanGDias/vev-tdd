import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GeradorDeNotaFiscalTest {
    GeradorNotaFiscal geradorNotaFiscal;

    @BeforeEach
    void testCreateGeradorNotaFiscal(){
        geradorNotaFiscal = new GeradorNotaFiscal();
    }
    @Test
    void testGeradorDeNotaFiscal(){
        Fatura fatura = new Fatura("Cliente A", "Endereço A", "CONSULTORIA", 1000.0);
        NotaFiscal notaFiscal = geradorNotaFiscal.gerarNotaFiscal(fatura);
    }
}
