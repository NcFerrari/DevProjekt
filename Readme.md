<h1>Vývojový projekt s ukázkou implementace EE prvků.</h1>
<hr>

Aplikace slouží pro zobrazení obyčejného formuláře, kde se zadává jméno, přijmení, adresa a telefoní číslo.
Ovšem postupným vývojem je tento jednoduchý formulář modifikován a jsou do projektu přidávany nejrůznější EE prvky jako
například unit testy, komentáře, lombok, rest api, web service, JPA architektura, a mnoho dalšího.

Veškeré nástroje, moduly a technologie, které budou použity budou zaznamenány v seznamu.

Aplikace bude jak desktopová (java Swing a java FX), tak na webu (od obyčejného HTML až po react framework)

<h2>Důležité specifikace</h2>
<ul>
    <li>Je použita <strong>Java 11</strong></li>
    <li>Nyní použit buildovací nástroj <strong>Maven</strong></li>
    <li>Použt <strong>Sonar lint</strong> pro clean code</li>
    <li>Pro Code Coverage použit plugin JaCoCo</li>
</ul>

<h1>Spuštění</h1>
<hr>
Aplikaci lze spustit klasickým způsobem přes main metodu ve třídě <strong>Manager</strong>

<h2>JaCoCo plugin</h2>
Pro použití stačí spustit v maven jobech job <strong>test</strong> (nebo install, který zahrnuje testy).
Potom, co se vygeneruje adresář <strong>target</strong>, tak v něm najít soubor:<br>
<i>site/jacoco/index.html</i><br>
Ten si klidně přetáhněte přímo z adresáře target do nějakého prohlížeče a tam už se v tom zorientujete.

Pozor! Abyste měli aktuální informace, je vždy potřeba spustit test job!

<h3>Lombok config</h3>
Aby se v JaCoCo ignorovaly zbytečné lombok generované přístupové metody (protože je zbytečné je testovat, pokud
nechceme zvyšovat počet otestovaných řádků), tak je potřeba vytvořit v root adresáři soubor:<br>
<i>lombok.config</i><br>
V tomto souboru se můžete podívat, jak je to nastavené.

<h4> PROPERTIES VS YAML</h4>

![properties vs yaml.png](src%2Fmain%2Fresources%2Freadmeresources%2Fproperties%20vs%20yaml.png)

