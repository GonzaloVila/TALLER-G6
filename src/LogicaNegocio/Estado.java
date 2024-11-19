package LogicaNegocio;

/**
 * La enumeración Estado representa los posibles estados de una reserva en el sistema de gestión del restaurante.
 * Los estados indican el estado actual de una reserva, desde que ha sido reservada hasta su finalización o cancelación.
 */
public enum Estado {
    /**
     * Indica que la reserva ha sido creada y confirmada.
     */
    RESERVADA,

    /**
     * Indica que el cliente no se presentó a la reserva.
     */
    NO_ASISTIO,

    /**
     * Indica que la reserva ha concluido exitosamente.
     */
    FINALIZADA,

    /**
     * Indica que la reserva ha sido cancelada por el cliente o el sistema.
     */
    CANCELADA,

    /**
     * Indica que la reserva ha sido modificada después de su creación.
     */
    MODIFICADA,

    /**
     * Indica que la reserva se está llevando a cabo en este momento.
     */
    EN_CURSO;
}