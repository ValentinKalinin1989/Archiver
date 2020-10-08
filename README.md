[![Build Status](https://travis-ci.org/ValentinKalinin1989/Archiver.svg?branch=master)](https://travis-ci.org/ValentinKalinin1989/Archiver)
[![codecov](https://codecov.io/gh/ValentinKalinin1989/Archiver/branch/master/graph/badge.svg)](https://codecov.io/gh/ValentinKalinin1989/Archiver)
<h1>Archiver</h1>
    <h6>Консольный  архиватор, выполняющий две команды:</h6>
        <li> unpacking - распаковка выбранных архивов в директорию, из которой была запущена программа;
        <li> packing - архивирование файлов в архив с заданным именем.        
    <h3>Unpacking</h3>
        <h6>Команда для распаковки архива имеет следующую структуру</h6>
        <h6><code>java -jar путь-к-исполняемому-jar-файлу unpacking путь-к-1-ому-архиву путь-к-следущему-архиву</code></h6>
        <h6>Например(для Windows):</h6>
        <h6><code>java -jar D:\Archiver-1.0.jar unpacking D:\first.zip D:\next.zip</code></h6>    
    <h3>Packing</h3>
         <h6>Команда для архивирования имеет следующую структуру</h6>
         <h6><code>java -jar путь-к-исполняемому-jar-файлу packing имя-архива путь-к-1-ому-файлу-или-папке
          путь-к-следущему-файлу-или-архиву</code></h6>
         <h6>Например(для Windows):</h6>
         <h6><code>java -jar D:\Archiver-1.0.jar packing archiver-name D:\first D:\next.text</code></h6>
        
<h2>Сборка проекта</h2>
    <h6>Проект использует Maven для сборки приложения. В корне проекта  необходимо выполнить
    команду <code>mvn package</code> для сборки в исполняемый jar-файл</h6>