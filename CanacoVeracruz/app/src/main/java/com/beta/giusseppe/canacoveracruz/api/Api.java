package com.beta.giusseppe.canacoveracruz.api;

/**
 * Created by Giusseppe on 12/10/2017.
 */

public class Api {

    //en linea
    //contenido

    private static final String ROOT_PRINCIPAL = "http://canacoveracruz.com/web/";

    private static final String ROOT_URL = ROOT_PRINCIPAL + "Json/Api/Api.php?apicall=";

    //publico
    public static final String URL_READ_CONTENIDO = ROOT_URL + "getcontenido&cate=";
    public static final String URL_READ_SIEM = ROOT_URL + "getsiem";
    public static final String URL_READ_CATEGORIA = ROOT_URL + "getcategoria";
    public static final String URL_READ_DIRECTORIO = ROOT_URL + "getdirectorio&idc=";

    //privado
    public static final String URL_READ_CONTENIDO_CATEGORIA_PRIVADO = ROOT_URL + "getcontenidopublico&cate=";
    public static final String URL_READ_CONTENIDO_PRIVADO_ESTADO = "&esta=";

    //imagenes
    public static final String URL_GALERIA = ROOT_PRINCIPAL + "uploads/";

    //login
    private static final String LOGIN_URL = ROOT_PRINCIPAL + "Json/Login/Api.php?apicall=";

    public static final String URL_REGISTER = LOGIN_URL + "signup";
    public static final String URL_LOGIN = LOGIN_URL + "login";

    //documentos

    //Pdf:
    public static final String URL_DRIVER = "http://drive.google.com/viewerng/viewer?embedded=true&url=";
}
