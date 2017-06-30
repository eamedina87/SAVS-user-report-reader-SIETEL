/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package savsreporteusuariosietel;

/**
 *
 * @author erick.medina
 */
public class Usuario {
    private String mes;
    private String provincia;
    private String ciudad;
    private String parroquia;
    private int juridica;
    private int natural;
    private int prepago;

    /**
     * @return the mes
     */
    public String getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(String mes) {
        this.mes = mes;
    }

    /**
     * @return the provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * @param provincia the provincia to set
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the parroquia
     */
    public String getParroquia() {
        return parroquia;
    }

    /**
     * @param parroquia the parroquia to set
     */
    public void setParroquia(String parroquia) {
        this.parroquia = parroquia;
    }

    /**
     * @return the juridica
     */
    public int getJuridica() {
        return juridica;
    }

    /**
     * @param juridica the juridica to set
     */
    public void setJuridica(int juridica) {
        this.juridica = juridica;
    }

    /**
     * @return the natural
     */
    public int getNatural() {
        return natural;
    }

    /**
     * @param natural the natural to set
     */
    public void setNatural(int natural) {
        this.natural = natural;
    }

    /**
     * @return the prepago
     */
    public int getPrepago() {
        return prepago;
    }

    /**
     * @param prepago the prepago to set
     */
    public void setPrepago(int prepago) {
        this.prepago = prepago;
    }
    
    
    
}
