package br.com.apideteste.projeto.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Usuarios {
    private List<Usuario> usuarios;

    public Usuarios() {
    }

    public Usuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @XmlElement(name = "Usuario")
    public List<Usuario> getUsuarios() {
        return this.usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}
