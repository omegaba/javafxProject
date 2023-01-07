# Projet javafx

## Binôme:
BA Alpha Boubacar - n°etu: 22007282
BIGAND Theo - n°etu: 22012689


## Présentation
Ce projet consiste en un jeu permettant d'apprendre à taper au clavier et à s’améliorer tout en offrant la possibilité de jouer face à un autre joueur.


## Applications déjà existentes
Il s’agirait d’un hybride entre les logiciels d’entraînement dactylo classiques comme sur les sites web Monkeytype, fastfingers, Dactylotest  KeyBR et tetris.

## Modes de jeux
Le jeu comporte trois modes :
- un mode "normal", dans lequel l'utilisateur doit entrer un maximum de mots en un temps précis (il y a aussi une variante où l'utilisateur peut s'entraîner avec juste un nombre limité de mots)
  
- un mode "tetris", où le joueur dispose d'un certain temps pour écrire des mots. Ce temps évolue au cours de la partie, il diminue en particulier tout les cent mots tapés. Cependant, le joueur peut récupérer de la vie en tapant correctement du premier coup, certains mots qui apparaissent en bleu

- un mode multi-joueur qui ne se joue malheuresement qu'a deux joueurs. Dans ce mode, les deux joueurs écrivent chacun des mots et auront la chance de voir apparaître des mots en rouge qui, une fois tapé correctement du premier coup, permettront de remplir la file de mot de l'adversaire ou bien de lui faire perdre une vie dans le cas ou le mot qu'il écrivait était tapé ou non.
  
## Choix techniques
Pour implementer le jeu, nous utilisons des vues, liées à des contrôleurs.
Les modes, normal et tetris, disposent d'un timer qui est créé grâce à un objet ScheduledExecutor auquel on fourni un runnable, et qui effectue des actions à chaque seconde.
Dans tous les modes de jeu, une liste de mots est chargée depuis le fichier "wordList" que l'on recopie dans une ArrayList, et qui nous sert de tampon. Cette liste est mélangée à l'aide de List.shuffle afin de ne jamais avoir la même liste de mots.
L'utilisateur doit renter correctement le mot en surligné du premier coup afin qu'il soit compté comme valide, et ne peut en contrepartie donc pas revenir sur un mot déjà validé. Nous avons fait ce choix d'implémentation car nous tenions  à implémenter le mode tétris, et nous avons dû implémenter la liste de mot des autres mode de jeux de la même manière pour des raisons d'homogéniété du code et du jeu, mais aussi car nous manquions un peu de temps et de techniques.
En ce qui concerne le multi-joueur, il est organisé grâce au lien entre un client et un serveur. Un ServerSocket est créé par le server, qui attend qu'un utilisateur s'y connecte. Une fois fait, la communication se fait grâce à des Buffereader et des PrintWritter servant à lire et à écrire respectivement dans le socket.
L'entièreté de la structure est géré par une classe Reader qui récupère les attributs du server et du client et qui lance les actions liés à l'apparition d'un mot en rouge.



## Exécuter le projet
1. extraire le dossier de l'archive 
2. Entrer dans le dossier javafxProjet 
    ``` cd javafxProject ```
3. Entrer dans le dossier demo
    ``` cd demo ```
4. Exécuter la commande suivante :
    ``` mvn clean javafx:run ```
