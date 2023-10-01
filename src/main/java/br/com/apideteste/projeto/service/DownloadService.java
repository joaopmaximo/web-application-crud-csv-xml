package br.com.apideteste.projeto.service;

import br.com.apideteste.projeto.model.Usuario;
import br.com.apideteste.projeto.repository.IUsuario;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@Service
public class DownloadService {
    private final IUsuario repository;
    private static final Logger log = getLogger(DownloadService.class);

    public DownloadService(IUsuario repository) {
        this.repository = repository;
    }

    public void exportarCsv(Writer writer) {
        List<Usuario> usuarios = (List<Usuario>) repository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("ID", "Nome", "Email","Senha","Telefone");
            for (Usuario usuario : usuarios) {
                csvPrinter.printRecord(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getTelefone());
            }
        } catch (IOException e) {
            log.error("Erro ao exportar CSV", e);
        }
    }

    public void exportarXml(Writer writer) throws IOException {
        List<Usuario> lista = new ArrayList<>((List<Usuario>) repository.findAll());
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<usuarios>\n");

        for (Usuario usuario : lista) {
            xml.append("  <usuario>\n");
            xml.append("    <nome>").append(usuario.getNome()).append("</nome>\n");
            xml.append("    <senha>").append(usuario.getSenha()).append("</senha>\n");
            xml.append("  </usuario>\n");
        }

        xml.append("</usuarios>");

        try {
            writer.write (xml.toString());
        }
        catch (IOException e) {
            log.error("Erro ao exportar XML");
        }
    }

}
