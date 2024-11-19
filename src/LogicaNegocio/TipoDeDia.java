package LogicaNegocio;

/**
 * Enumeración que representa los diferentes tipos de días en el contexto del restaurante.
 */
public enum TipoDeDia {
    /**
     * Día feriado.
     */
    Feriado,

    /**
     * Día de evento privado.
     */
    EventoPrivado,

    /**
     * Día normal sin eventos especiales.
     */
    Normal,

    /**
     * Día con cambios inesperados en la operación.
     */
    CambioInesperado;
}