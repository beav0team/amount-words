package com.beav0team.amountwords

/**
 * The kind of commercial document an amount is written out for.
 *
 * Each type carries its own legal opening phrase, used by
 * [AmountWords.sentence] ("ARRÊTÉE LA PRÉSENTE FACTURE À LA SOMME DE :" for an
 * invoice, "ARRÊTÉ LE PRÉSENT DEVIS À LA SOMME DE :" for a quote, etc.).
 *
 * Ported from the Facture Maroc Android app. Pure Kotlin — no Android
 * dependency.
 */
enum class DocumentType(val id: String, val label: String, val prefix: String) {

    FACTURE("facture", "Facture", "F"),
    DEVIS("devis", "Devis", "D"),
    BON_DE_COMMANDE("bon_commande", "Bon de commande", "BC"),
    TICKET("ticket", "Ticket (80mm)", "T"),
    AVOIR("avoir", "Avoir", "A"),
    PROFORMA("proforma", "Proforma", "PF"),
    ACOMPTE("acompte", "Acompte", "AC"),
    REGULARISATION("regul", "Régularisation", "REG"),
    SIMPLIFIEE("simplifiee", "Simplifiée", "FS"),
    EXPORT("export", "Export", "E");

    /** Documents that refer back to another document (avoir, régularisation, acompte). */
    val isReferenceDocument: Boolean
        get() = this == AVOIR || this == REGULARISATION || this == ACOMPTE

    companion object {
        /** Looks a type up by its id, falling back to [FACTURE]. */
        fun fromId(id: String?): DocumentType =
            entries.firstOrNull { it.id == id } ?: FACTURE
    }
}
