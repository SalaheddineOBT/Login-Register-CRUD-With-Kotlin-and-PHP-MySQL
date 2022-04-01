# Login & Register & CRUD With Kotlin Android Studio
Système d’accès sécurisé des membres (Front-end mobile avec kotlin) (Bacj-end avec PHP)

Testing App :

QU’EST CE QU’UN TEST ? 
Un test est un ensemble de cas à tester éventuellement accompagné d'une procédure d'exécution. Il est lié à un objectif.

Différents type de tests Fonctionnalités :
réaliser des tests unitaires, 
des tests de non-régression, 
des tests d'intégration, 
des tests de validation des composants dans l'application; 

Performances : Test de charge mise à l'épreuve d’une application en émulant des “vrais” utilisateurs avec un outil de génération de Charge détecter les goulots d'étranglement sur les ressources systèmes à observer (temps de réponses, occupation mémoire, etc.) 

Réception : Réalisé par le client (acceptance, clean-room tests) S’assurer que le cahier des charges a été respecté.


Example function test :

public boolean testConcatene(){
  String t1 = "Bonjour " ;
  String t2 = "Toto";
  String attendu = "Bonjour Toto";
  String arrivee = concatene(t1, t2);
  Return arrivee.equals(attendu);
}

les question poser : 

Est-ce que ça marche comme prévu?
Quels sont les points à améliorer?
Est-ce que c’est conforme aux spécifications?
Est-ce que ça correspond aux attentes du client?

Un test unitaire est une procédure permettant de vérifier le bon fonctionnement d'une partie précise d'un logiciel (partie de code source),on teste au niveau des classes Pour chaque classe on a une classe de test.

QUELQUES RÈGLES : 
Doit être isolé 
il doit être indépendant N’est pas un test de bout en bout 
il agit que sur une portion de code Doit être déterministe 
le résultat doit être le même pour les mêmes entrées Est le plus petit et simple possible 

 AVANTAGE ET INTÉRÊT
Garantie la non régression 
Détection de bug plus facile 
Aide a isoler les fonctions 
Aide a voir l’avancement d’un projet (TDD)
Gagner du temps
Développer efficacement

Un test unitaire peux renvoyer 4 résultats différents : 
Success : test réussi
Error : erreur inattendue a l’exécution 
Failure : au moins une assertion est fausse
Incomplete / skipped ( à éviter)

