package br.com.apideteste.projeto.service;

import br.com.apideteste.projeto.model.Usuario;
import br.com.apideteste.projeto.model.Usuarios;
import br.com.apideteste.projeto.repository.IUsuario;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.Writer;
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

    public void exportarXml(Writer writer) {
        List<Usuario> lista = (List<Usuario>) repository.findAll();
        Usuarios usuarios = new Usuarios(lista);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Usuarios.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(usuarios, writer);
        }
        catch (JAXBException e) {
            log.error ("Erro ao exportar XML", e);
        }
    }

}
