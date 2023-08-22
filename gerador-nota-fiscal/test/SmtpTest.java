import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmtpTest {

    GeradorNotaFiscal geradorNotaFiscal;
    Smtp smtp;
    NotaFiscal notaFiscal;

    @BeforeEach
    void testCreateNotaFiscal(){
        geradorNotaFiscal = new GeradorNotaFiscal();
        smtp = new Smtp();
        Fatura fatura = new Fatura("Cliente A", "Endereço A", "CONSULTORIA", 1000.0);
        notaFiscal = geradorNotaFiscal.gerarNotaFiscal(fatura);
    }

    @Test
    void testEnviaSmtp(){
        String mensagemDeEnvio = smtp.envia(notaFiscal);
        assertEquals(mensagemDeEnvio, "enviando por email");
    }
}
