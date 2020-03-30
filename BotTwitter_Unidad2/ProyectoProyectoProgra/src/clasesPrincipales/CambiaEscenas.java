/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesPrincipales;

/**
 * Interface utilizada para agilizar los cambios de escenas a traves de la 
 * visibilidad de los elementos de la clase controladora Inicio
 * @author javie
 */
public interface CambiaEscenas {
    public void mostrarInicio(boolean valor);
    public void mostrarTweetear(boolean valor);
    public void mostrarSeguir(boolean valor);
}
