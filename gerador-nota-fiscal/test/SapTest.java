import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SapTest {
    GeradorNotaFiscal geradorNotaFiscal;
    SAP sap;
    NotaFiscal notaFiscal;

    @BeforeEach
    void createNotaFiscal(){
        geradorNotaFiscal = new GeradorNotaFiscal();
        sap = new SAP();
        Fatura fatura = new Fatura("Cliente A", "Endereço A", "CONSULTORIA", 1000.0);
        notaFiscal = geradorNotaFiscal.gerarNotaFiscal(fatura);
    }

    @Test
    void envia(){
        String mensagemDeEnvio = sap.envia(notaFiscal);
        assertEquals(mensagemDeEnvio, "enviando pro sap");
    }
}