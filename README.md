# amount-words

Écrire un montant en toutes lettres, en français.

`amount-words` est le moteur "montant en lettres" de l'application Android
[Facture Maroc](https://factureapp.ma), extrait et publié comme une bibliothèque
Kotlin/JVM autonome, sans dépendance Android et sans dépendance externe.

Il convertit un montant en dirhams et centimes en mots français, tel qu'il
doit apparaître sur une facture, un devis, un avoir ou un reçu.

## Ce qu'il fait

```kotlin
AmountWords.amount(123.45)
// → "CENT VINGT-TROIS DIRHAMS ET QUARANTE-CINQ CENTIMES"

AmountWords.amount(1.0)
// → "UN DIRHAM"

AmountWords.amount(0.0)
// → "ZÉRO DIRHAMS"
```

Avec la phrase d'ouverture légale, adaptée au type de document :

```kotlin
AmountWords.sentence(936.0)
// → "ARRÊTÉE LA PRÉSENTE FACTURE À LA SOMME DE : NEUF CENT TRENTE-SIX DIRHAMS."

AmountWords.sentence(100.0, DocumentType.DEVIS)
// → "ARRÊTÉ LE PRÉSENT DEVIS À LA SOMME DE : CENT DIRHAMS."

AmountWords.sentence(100.0, DocumentType.AVOIR)
// → "ARRÊTÉ LE PRÉSENT AVOIR À LA SOMME DE : CENT DIRHAMS."
```

## Pourquoi cette bibliothèque

Elle a été écrite et corrigée dans une application de facturation réelle pour le
marché marocain (dirham, TVA, factures, devis, avoirs, reçus). Les cas limites
du français sont couverts et testés :

* `cent` prend un `s` seulement en fin de nombre : `DEUX CENTS` mais `DEUX CENT UN`
* `vingt` idem : `QUATRE-VINGTS` seul, `QUATRE-VINGT-UN` avant une unité
* `soixante et onze` garde son `et` ; `quatre-vingt-onze` aussi
* `mille` est invariable et ne prend jamais de `un` ni de `s` : `DEUX MILLE`, jamais `UN MILLE` ni `MILLES`
* `deux cent mille`, `quatre-vingt mille` (le `cent`/`vingt` perd son `s` avant `mille`)
* les nombres de 82 à 89 (`quatre-vingt-deux` … `quatre-vingt-neuf`) ne plantent plus
* les centimes ronds n'affichent pas "et zéro centimes"

## Installation

La bibliothèque est publiée sur Maven Central sous `com.github.beav0team:amount-words`.

**Gradle (Kotlin DSL)**
```kotlin
implementation("com.github.beav0team:amount-words:1.0.0")
```

**Gradle (Groovy)**
```groovy
implementation 'com.github.beav0team:amount-words:1.0.0'
```

**Maven**
```xml
<dependency>
  <groupId>com.github.beav0team</groupId>
  <artifactId>amount-words</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Utilisation

```kotlin
import com.beav0team.amountwords.AmountWords
import com.beav0team.amountwords.DocumentType

val montant = AmountWords.amount(2500.75)
// "DEUX MILLE CINQ CENTS DIRHAMS ET SEPTANTE-CINQ CENTIMES"

val avecPhrase = AmountWords.sentence(2500.75, DocumentType.FACTURE)
// "ARRÊTÉE LA PRÉSENTE FACTURE À LA SOMME DE : DEUX MILLE CINQ CENTS DIRHAMS ET SEPTANTE-CINQ CENTIMES."
```

* `amount(montant)` écrit le montant seul, sans phrase d'ouverture — pratique
  pour un reçu qui indique une somme reçue.
* `sentence(montant, typeDeDocument)` ajoute la phrase d'ouverture légale selon
  le type de document (facture, devis, bon de commande, ticket, avoir,
  proforma, acompte, régularisation, facture simplifiée, export).

## Installation depuis les sources

```bash
./gradlew build        # compile + lance les tests
./gradlew test         # lance uniquement la suite de tests
```

Nécessite JDK 17+. Aucun SDK Android requis.

## Tests

La suite couvre l'exhaustif des montants entiers de 0 à 9 999 dirhams, tous les
types de document, et les cas limites historiques du moteur (centimes, mille,
million, 82–89).

## Licence

MIT — voir [LICENSE](LICENSE).

## À propos

Porté depuis l'application Android **Facture Maroc** (facturation hors ligne,
marché marocain, dirham uniquement, interface française). Le code originel vit
dans `app/src/main/java/com/yassine/facturemaroc/data/AmountWords.kt`.

Ce projet est publié pour que quiconque puisse réutiliser le moteur "montant en
lettres" sans dépendre d'Android.
