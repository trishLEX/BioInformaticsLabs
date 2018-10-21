# BioInformaticsLabs  
  
Чтобы собрать проект: mvn package, находясь в корне проекта;  
Чтобы запустить проект: mvn exec:java -Dexec.args="<необходимые ключи>;

#Lab1
  
Чтобы запустить тесты: mvn -Dtest=TableTest test; (все остальные классы с тестами - функциональное тестирование)  
  
Ключи:  
* -f,--file <arg> : File with two sequences in fasta format - обязательный ключ  
* -g,--gap <arg>  :  gap error, default is -2  
* -o <arg>        :  file output  
* -t,--type <arg> :  AA for amino acids, N for nucleotides - обязательный ключ  
