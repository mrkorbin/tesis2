package ve.drkorbin.tesis.entities;

/**
 * Created by parcka on 19/09/16.
 */
public class Guide {

    String titulo;
    String descripcion;
    String url;
    String musculo;

    public Guide() {
    }

    public Guide(String titulo, String descripcion, String url, String musculo) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.url = url;
        this.musculo = musculo;
    }

    public String getMusculo() {
        return musculo;
    }

    public void setMusculo(String musculo) {
        this.musculo = musculo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
