/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesAyuda;

/**
 * Clase que almacena los distintos tipos de mensajes que se pueden obtener a través de la interfaz
 * @author Mecho
 */
public class Mensajes {
    
    String mensajePredeterminado = "Gracias por reaccionar a mi tweet";
    String mensajeReacciones;
    String mensajeTweet;
    String mensajePrivado;
    String usuarioFollow;
    String usuario;
    String like;
    String retweet;
    long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getRetweet() {
        return retweet;
    }

    public void setRetweet(String retweet) {
        this.retweet = retweet;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMensajePredeterminado() {
        return mensajePredeterminado;
    }

    public String getMensajeTweet() {
        return mensajeTweet;
    }

    public void setMensajeTweet(String mensajeTweet) {
        this.mensajeTweet = mensajeTweet;
    }

    public String getUsuarioFollow() {
        return usuarioFollow;
    }

    public void setUsuarioFollow(String usuarioFollow) {
        this.usuarioFollow = usuarioFollow;
    }

    public String getMensajeReacciones() {
        return mensajeReacciones;
    }

    public void setMensajeReacciones(String mensajeReacciones) {
        this.mensajeReacciones = mensajeReacciones;
    }

    public String getMensajePrivado() {
        return mensajePrivado;
    }

    public void setMensajePrivado(String mensajePrivado) {
        this.mensajePrivado = mensajePrivado;
    }
    
    
}

