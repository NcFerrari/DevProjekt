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

<h1>LOGOVÁNÍ</h1>
<ol>
<li>Přidat do pomka dependency</li>
<div style="color:yellow">
&lt;dependency&gt;<br>
<span style="padding-left: 20px">&lt;groupId&gt;org.apache.logging.log4j&lt;/groupId&gt;</span><br>
<span style="padding-left: 20px">&lt;artifactId&gt;log4j-core&lt;/artifactId&gt;</span><br>
<span style="padding-left: 20px">&lt;version&gt;2.19.0&lt;/version&gt;</span><br>
&lt;/dependency&gt;
</div>
<li>Přidat do resource adresáře buď XML nebo YAML file. Bylo by možno použít i properties file nebo JSON. V tomto
projektu jsou ukázky pro XML a pro YAML</li>
<h3>YAML FILE</h3>
<style>
.tab{
padding-left: 20px;
}
.green {
color:#618844;
}
.white {
color: #b8c4be;
}
</style>
<div style="color: #cb7832; padding:20px">
    Configuration:
        <div class="tab">
        Properties:
            <div class="tab">
            Property:
                <div class="tab">
                    name: <span class="green">log-path"</span><br>
                    value: <span class="white">logs</span>
                </div>
            </div>
        status: <span class="white">INFO</span><br>
        Appenders:
            <div class="tab">
            Console:
                <div class="tab">
                name: <span class="white">Console</span><br>
                target: <span class="white">SYSTEM_OUT</span><br>
                PatternLayout:
                    <div class="tab">
                    pattern: <span class="green">"[%d] %-8p (%-50c) : %m%n"</span>
                    </div>
                </div>
            RollingFile:
                <div class="tab">
                name: <span class="white">SaveToFile</span><br>
                filePattern: <span class="green">"${log-path}/%d{yyyy-MM-dd}/%d{HH}.log"</span><br>
                PatternLayout:<br>
                    <div class="tab">
                    pattern: <span class="green">"[%d] %-8p (%-50c) : %m%n"</span>                      
                    </div>
                Policies:
                    <div class="tab">
                    SizeBasedTriggeringPolicy:
                        <div class="tab">
                        size: <span class="green">"5 MB"</span>
                        </div>
                    </div>
                </div>
            </div>
        Loggers:
            <div class="tab">
            Root:
                <div class="tab">
                level: <span class="white">INFO</span><br>
                AppenderRef:
                    <div class="tab">
                        - ref: <span class="white">Console</span><br>
                        - ref: <span class="white">SaveToFile</span>
                    </div>
                </div>
            </div>
        </div>
</div>
<li>Potom je potřeba ve třídě, ve které chceme použít logování, tak vytvořit objekt a uložíme ho do atributu:
<div class="green">
<strong>
import org.apache.logging.log4j.LogManager;<br>
import org.apache.logging.log4j.Logger;<br><br>
private Logger log = LogManager.getLogger(<span style="color:orange">NázevTřídy.class</span>);</strong>
</div></li>
pozn.: v tomto projektu máme k dispozici servisu LoggerService, která dělá to samé, ale je tu už implementace servisy
<li>A potom už jen použijeme kdekoliv v kódu <strong><span class="green">log.info("text");</span></strong></li>
</ol>