package mediatheque

import java.util.*

/** Classe abstraite : tout ce qui se trouve dans la médiathèque.  */
abstract class Document protected constructor(titre: String, annee: Int) : Comparable<Document?> {
    val titre: String
    val annee: Int

    init {
        require(!(titre == null || titre.isBlank())) { "Le titre est obligatoire" }
        this.titre = titre
        this.annee = annee
    }

    /** Chaque sous-classe décrit son propre document (polymorphisme).  */
    abstract fun descriptionCourte(): String?

    /** Bonus : ordre naturel = ordre alphabétique des titres.  */
    override fun compareTo(autre: Document): Int {
        return titre.compareTo(autre.titre, ignoreCase = true)
    }

    override fun toString(): String {
        return descriptionCourte()!!
    }

    override fun equals(o: Any?): Boolean {
        return o is Document && titre == o.titre && annee == o.annee && javaClass == o.javaClass
    }

    override fun hashCode(): Int {
        return Objects.hash(titre, annee, javaClass)
    }
}