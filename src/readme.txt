Projekt zrealizowany przez Juliusza Kocińskiego w ramach zajęć z Programowania Obiektowego w roku Akademickim 2020/2021.
Specyfikacja Oryginalna - A.Jabłonowski

Modyfikacje wprowadzone względem oryginalnej treści:
 (swoje, również inspirowane filmami z kanału "Primer" na youtubie - zajmującego się m.in. właśnie ewolucją w takim prostym systemie)
 - Dodany atrybut wył_instr - zawiera on oznaczenia literowe oznaczenia poleceń, które mutują w niekorzystny sposób - tzn. de facto nie istnieją, nie spełniają swojej roli (są negatywnymi mutacjami, gdyż mimo bezużyteczności zużywają energię)
 - Naliczanie jedzenia:
    Pole żywieniowe dla każdego Roba, który na nie stanie w turze, w której ono obrodzi (lub jednej z następnych przy braku odwiedzających) dostarcza zdefiniowaną w parametrze ilość pkt. energii. Ponieważ jednak najedzenie się wielu robów odbywa się kosztem zdolności regeneracyjnych - ilość czasu potrzebna do ponownego obrodzenia rośnie wraz z liczbą wykarmionych robów (z prędkością ok pierwiastkową). "ile_rośnie_jedzenie" określa tą długość dla wykarmienia jednego roba.
 - Naliczanie zużycia energii:
    Wiemy z prawdziwego życia, że wykonywanie szybkich i gwałtownych ruchów zużywa więcej energii, niż powolnych i przemyślanych. W związku zużycie energii rośnie nieliniowo dla odpowiednio długich programów. Limit od którego się to dzieje określa parametr "ruch_bez_kar" (róchów bez "karania").

    Powyższe 2 punkty sprawiają, że punkt równowagi zarówno jeżeli chodzi o populację, jak i długość programu - nie wytrzeliwuje do nieskończoności.

 - prawdopodobieństwa wg konwencji matematycznej - w przedziale [0-1].
 - Nie ma potrzeby podawać w parametrach wymiarów planszy. Prawidłowo napisany plik "plansza.txt" pozwala programowi "wydedukować" wymiary.


Obserwacje:
 - Wielkość populacji, oraz długość programów zależy od parametrów, ciężko jakieś nieoczywiste wnioski wyciągnąć
 - DO CZEGO DĄŻĄ PROGRAMY
     - jeżeli wszystkie instrukcje są dozwolone: program Robów dąży do postaci "jijijiji"
     - jeżeli instrukcja jedz jest bezużyteczna - program Robów dąży do postaci "wiwiwiwi"
     - jeżeli wyłączymy instrukcje jedz i wąchaj - program dąży do ciągu kroków i jednego skrętu. Wyłączenie lewego lub prawego na to nie wpływa
     - Instrukcje jednego skrętu i kroku na przód są niezbędne do przetrwania.

     Wszystkie obserwacje potwierdzają przypuszczenia/przemyślenia w trakcie kodowania :D