# BioInformaticsLabs  
  
Чтобы собрать проект: mvn package, находясь в корне проекта;  

#Lab1
Чтобы запустить тесты: mvn -Dtest=lab1/TableTest test; (все остальные классы с тестами - функциональное тестирование)  
Чтобы запустить проект: mvn exec:java@lab1 -Dexec.args="<необходимые ключи>";  

Ключи:  
* -f,--file <arg> : File with two sequences in fasta format - обязательный ключ  
* -g,--gap <arg>  :  gap error, default is -2  
* -o <arg>        :  file output  
* -t,--type <arg> :  AA for amino acids, N for nucleotides - обязательный ключ  

#Lab2  
Чтобы запустить тесты: mvn -Dtest=lab2/TableTest test; (все остальные классы с тестами - функциональное тестирование)  
Чтобы запустить проект: mvn exec:java@lab2 -Dexec.args="<необходимые ключи>";    
  
Ключи:  
* -f,--file <arg>        : File with two sequences in fasta format - обязательный ключ  
* -go,--gapOpen <arg>    :  open gap error, default is -2  
* -ge,--gapExtend <arg>  :  extend gap error, default is -1  
* -o <arg>               :  file output  
* -t,--type <arg>        :  AA for amino acids, N for nucleotides - обязательный ключ  
