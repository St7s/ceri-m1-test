# BRUGEL Adrien (Classique)

* Dernier build CircleCI
[![CircleCI](https://circleci.com/gh/St7s/ceri-m1-test.svg?style=shield&circle-token=:circle-token)](https://circleci.com/gh/St7s/ceri-m1-test)
* Codacy qualité de code 
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/ce12b07fee33488db02392df54f0f00a)](https://www.codacy.com/app/St7s/ceri-m1-test?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=St7s/ceri-m1-test&amp;utm_campaign=Badge_Grade)
* Codacy couverture de test
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/ce12b07fee33488db02392df54f0f00a)](https://www.codacy.com/app/St7s/ceri-m1-test?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=St7s/ceri-m1-test&amp;utm_campaign=Badge_Coverage)

## Les tests

### ITest (api)
Tous les tests concernant l'**api** sont précédés d'un "I" et sont mocker avec Mockito, ils possédent un setUp ainsi que plusieurs méthodes de test pour tester l'ensemble

### Test (core)
Les tests d'implémentation du package **core** implémentent les tests de l'**api**
La méthode ***setUp*** est surchargée et seule 3 méthodes on était rajoutées dans `PokemonTrainerFactoryTest` et `PokedexTest` afin de tester la serialization

### Suite de tests
CircleCI au travers du **circle.yml** fait tourner 2 suites de tests, un pour l'**api** et un pour le **core**

`mvn test -Dtest=TestSuiteWithMock,TestSuiteWithMockImpl`

### Artifacts et test summary
Pour chaque build un résumé ainsi qu'un artifact(CIRCLE_TEST_REPORTS) ont était créés pour connaître les résultats des tests JUnit


## Les Factory 
Les 3 Factory implémentent le design pattern **Singleton Factory method** car elles sont indépendantes de l'objet créé


## Sérialisation et Observer / Observable

### Sérialisation
Toutes les classes utilisées dans un **Pokedex** ou un **Trainer** sont sérialisables, afin de pouvoir sauvegarder et restaurer les données associées à un **Trainer**.

### Observer / Observable
Un trainer est un **Observer** de son pokedex, et le pokedex le notifie à chaque ajout de pokemon, ainsi le trainer peut être sauvegardé à chaque mises à jour

### Création d'un Trainer
Lors de la création d'un **Trainer**, on vérifie dans le dossier de ressource `/pokemon/src/main/ressources/db/<TEAM>` si un fichier `<NOM_JOUEUR>.ser` existe. Si c'est le cas on le désérialise et on retourne l'objet sinon on créer un nouvel objet qui sera automatiquement sauvegardé


## Pokemon Metadata
Les données des pokemons proviennent du json : https://raw.githubusercontent.com/PokemonGo-Enhanced/node-pokemongo-data/master/data.json
PokemonMetadataProvider est un singleton et lors du premier appel, il récupère l'ensemble des données du json et les insère dans une liste de **PokemonMetadata**. Je parse avec les outils de **org.json**


## Calcul d'IV
Le calcul d'IV se fait à partir de ce site : https://pokeassistant.com/main/ivcalculator?locale=en 
J'ai utilisé Selenium (avec **io.github.bonigarcia**) afin de saisir les valeurs automatiques puis pour récupérer le résultat

### Difficultés rencontrés avec les webDrivers sur CircleCI
De nombreux build ont eu des échecs avec les webDrivers Chrome, Firefox, PhantomJS
Aprés de nombreux tests de version, j'ai opté pour specifier dans le  **circle.yml** de récupérer la dernière version de chrome afin que la dernière  version du driver soit compatible


## Logger slf4j

Un logger slf4j est implémenté dans quelque classe afin de pouvoir débug et ne pas utiliser de :

``` System.out.println() | System.err.println() ```

mais plutôt

``` LOGGER.info() | LOGGER.error() ```
