package ve.drkorbin.tesis.entities;

/**
 * Created by parcka on 01/10/16.
 */
public enum MuscleEnum {

    BICEP("Bicep"),
    TRICEP("Tricep"),
    CUADRICEP("Cuadricep"),
    FEMORAL("Femoral"),
    PECTORAL("Pectoral");

    private final String descripcion;

    MuscleEnum(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion(){
        return descripcion;
    }

}
