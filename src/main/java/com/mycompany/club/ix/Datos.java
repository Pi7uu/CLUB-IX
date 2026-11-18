package com.mycompany.club.ix;

import com.mycompany.club.ix.model.Cuota;
import com.mycompany.club.ix.model.Socio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestor de datos en memoria para la aplicación CLUB-IX.
 */
public class Datos {

    private static final ObservableList<Socio> lista = FXCollections.observableArrayList();

    static {
        // Carga inicial en memoria
        Socio s1 = new Socio("Juan Fernández", "38123456", "ACTIVO", "Fútbol + tenis");
        s1.agregarCuota(new Cuota("Noviembre 2026", "PENDIENTE"));
        s1.agregarCuota(new Cuota("Octubre 2026", "PAGADA"));
        s1.agregarCuota(new Cuota("Septiembre 2026", "PAGADA"));

        Socio s2 = new Socio("María Gómez", "41987654", "ACTIVO", "Natación");
        s2.agregarCuota(new Cuota("Noviembre 2026", "PAGADA"));
        s2.agregarCuota(new Cuota("Octubre 2026", "PAGADA"));

        Socio s3 = new Socio("Lucas Rossi", "33456789", "SUSPENDIDO", "Básquet");
        s3.agregarCuota(new Cuota("Noviembre 2026", "IMPAGA"));
        s3.agregarCuota(new Cuota("Octubre 2026", "IMPAGA"));

        lista.addAll(s1, s2, s3);
    }

    /**
     * Devuelve todos los elementos de la lista en memoria.
     * @return Lista observable con todos los socios.
     */
    public static ObservableList<Socio> listar() {
        return lista;
    }

    /**
     * Agrega el parámetro elemento a la lista.
     * @param elemento Socio a agregar.
     */
    public static void agregar(Socio elemento) {
        if (elemento != null) {
            lista.add(elemento);
        }
    }

    /**
     * Filtra la lista por nombre o dni.
     * @param criterio Texto a buscar en nombre o dni.
     * @return Lista de socios que coinciden con el criterio.
     */
    public static List<Socio> buscar(String criterio) {
        List<Socio> resultados = new ArrayList<>();
        if (criterio == null || criterio.trim().isEmpty()) {
            return new ArrayList<>(lista);
        }

        String filtro = criterio.trim().toLowerCase();
        for (Socio socio : lista) {
            boolean coincideNombre = socio.getNombre() != null && socio.getNombre().toLowerCase().contains(filtro);
            boolean coincideDni = socio.getDni() != null && socio.getDni().toLowerCase().contains(filtro);

            if (coincideNombre || coincideDni) {
                resultados.add(socio);
            }
        }
        return resultados;
    }
}
