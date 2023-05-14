# IngKonkurs

[![CodeQL](https://github.com/FlafyKings/IngKonkurs/actions/workflows/codeql.yml/badge.svg)](https://github.com/FlafyKings/IngKonkurs/actions/workflows/codeql.yml)

# Wstęp
W świecie coraz bardziej skoncentrowanym na ekologii, koncepcja “green coding-u” stała się istotnym elementem tworzenia oprogramowania. Green coding odnosi się do praktyki tworzenia oprogramowania z silnym naciskiem na minimalizację zużycia energii, redukcję śladu węglowego i promowanie świadomości ekologicznej. Ponieważ pilna potrzeba rozwiązania problemu globalnego ocieplenia i ochrony zasobów rośnie, green coding staje się przyszłością rozwoju. Optymalizując kod, wdrażając wydajne algorytmy i wykorzystując odnawialne źródła energii, programiści mogą znacznie zmniejszyć wpływ tworzenia oprogramowania na środowisko. Przyjęcie praktyk zielonego oprogramowania nie tylko jest zgodne z globalnym dążeniem do zrównoważonego rozwoju ekologicznego, ale także zapewnia przewagę konkurencyjną na rynku, który wymaga rozwiązań przyjaznych dla środowiska. Zauważając znaczenie zielonego kodowania, ING podjęło proaktywne kroki - zorganizowali oni konkurs, którego celem było rozwiązanie trzech zadań z wykorzystaniem zasad green coding-u. Ten konkurs nie tylko podkreśla znaczenie odpowiedzialności ekologicznej w rozwoju oprogramowania, ale także pokazuje rosnące uznanie ideologii green coding-u jako kluczowej dla innowacji i zrównoważonych rozwiązań.

# Atm Service
Pierwsze zadanie polegało na stworzeniu algorytmu ustalania właściwej kolejności bankomatów, które zespół serwisowy musi odwiedzić na podstawie zgłoszeń w systemie zleceń serwisowych. Celem było opracowanie wydajnego rozwiązania uwzględniającego zarówno region, jak i typ żądania.

Aby rozwiązać to zadanie, na początku sortujemy listę według regionów, aby mieć pewność że bankomaty w tym samym obszarze są zgrupowane razem. Następnie w każdym regionie żądania były sortowane według rodzaju zgłoszenia. Do tego celu wykorzystałem algorytm “mergesort” w wersji równoległej w celu poprawienia wydajności i skrócenia całkowitego czasu potrzebnego do sortowania. Na samym końcu usuwamy z listy zduplikowane zlecenia, aby uniknąć zbędnych wizyt w tym samym bankomacie. 

# Online Game
Drugi problem polegał na określeniu kolejności, w jakiej gracze powinni wchodzić na plansze wydarzeń specjalnych popularnej gry online, biorąc pod uwagę ograniczenia wydajności platformy i określone zasady. Zasady te obejmowały przyjmowanie grup o maksymalnym rozmiarze, priorytetowe traktowanie najlepszych klanów oraz nadawanie pierwszeństwa grupom z mniejszą liczbą graczy.

W celu rozwiązania tego problemu, sortujemy malejąco listę klanów ze względu na ilość punktów, a w przypadku remisu - ze względu na mniejszą ilość graczy. Posortowaną listę dzielimy na grupy mając na uwadzę maksymalny rozmiar grupy oraz wolne miejsce w każdej grupie używając listy, która śledzi wolne miejsce dla każdej grupy. 

# Transactions
Trzeci problem polegał na przetwarzaniu tysięcy codziennych transakcji w dużym banku i określeniu, jak zmieni się saldo wszystkich rachunków po przetworzeniu tych transakcji. Dodatkowo wymaganiem było podanie posortowanej rosnąco listy rachunków wraz z liczbą uznań, obciążeń oraz saldem końcowym. Kluczowe było zaprojektowanie algorytmu, który poradziłby sobie z potencjalnym wzrostem liczby transakcji do 100 000 i zapewnił dokładne wyniki.

Aby sprostać temu zadaniu, opracowałem rozwiązanie wykorzystujące strukturę danych TreeMap. TreeMap automatycznie sortuje konta według ich numerów. Zadbałem również o to aby utrzymać unikalny zestaw kont, jednocześnie śledząc saldo każdego konta.


# Podsumowanie
Dzięki dwumiesiecznej pracy nad konkursem, zdałem sobie sprawę, jak bardzo green coding może wpłynąć na środowisko. Nauczyłem się, że stosując energooszczędne praktyki kodowania i używając programowania równoległego w Javie, możemy zmniejszyć zużycie zasobów i uczynić nasze oprogramowanie bardziej przyjaznym dla środowiska. Zrozumienie mocy green codingu zainspirowało mnie do włączenia tych zasad do mojej pracy programistycznej, co pozwoliło mi przyczynić się do bardziej ekologicznej przyszłości.
