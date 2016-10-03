package ve.drkorbin.tesis.entities;

/**
 * Created by parcka on 01/10/16.
 */
public enum MuscleEnum {

    BICEP("Bicep"),
    TRICEP("Tricep"),
    CUADRICEP("Cuadricep"),
    FEMORAL("Femoral"),
    PECTORAL("Pectoral"),
    ADVANCE_GUIDE("AdvanceGuide"),
    NEWBIE_GUIDE("NewbieGuide");

    private final String descripcion;

    MuscleEnum(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion(){
        return descripcion;
    }

}
