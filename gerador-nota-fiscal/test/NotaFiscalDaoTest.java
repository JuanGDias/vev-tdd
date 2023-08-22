import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class NotaFiscalDaoTest {
    GeradorNotaFiscal geradorNotaFiscal;
    NotaFiscalDao notaFiscalDao;
    NotaFiscal notaFiscal;

    @BeforeEach
    void createNotaFiscal(){
        geradorNotaFiscal = new GeradorNotaFiscal();
        notaFiscalDao = new NotaFiscalDao();
        Fatura fatura = new Fatura("Cliente A", "Endereço A", "CONSULTORIA", 1000.0);
        notaFiscal = geradorNotaFiscal.gerarNotaFiscal(fatura);
    }

    @Test
    void salvar(){
        String mensagemDeSalvamento = notaFiscalDao.salva(notaFiscal);
        assertEquals(mensagemDeSalvamento, "salvando no banco");
    }
}