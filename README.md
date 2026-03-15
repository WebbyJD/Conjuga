Thank you for using:
~~~
     .---.  .----. .-. .-.   .-..-. .-. .---.   .--.  
    /  ___}/  {}  \|  `| |.-.| || { } |/   __} / {} \ 
    \     }\      /| |\  || {} || {_} |\  {_ }/  /\  \
     `---'  `----' `-' `-'`----'`-----' `---' `-'  `-'
~~~                                   
                 (CONJUGA)
      Ascii art from https://patorjk.com
Created(Originally) by Declan and Brendan Webb


//Instructions for setting up the jar and running it\\

  run the builder.bash file to build the project

  do this using the command "./builder.bash" when in its directory
  
  (Make sure your Java is compiling on the latest version)
  Run "java -jar Conjuga.jar" while in that directory


//Guidelines for option files\\

  Example forms.choice:
    1[Subjunctive]2[Optative]3[Infinitive] 
    #This line break indicates the end of a "stage" or "level", one element on this "level" will be chosen per instance
    #The option files are very sensitive to line breaks, do not put anything like these comments in between "levels"
    4[Present]5[Future]6[Perfect]
  Example forms.rules:
    [0]{1}[2]{2}
    #The 0 refers to 1[Subjunctive] in forms.choice and the 1 refers to 5[Future]
    #The 2 refers to 3[Infinitive] and the last 2 refers to 6[Perfect]
    #Every line here represents the "line" in between each line in forms.choice, 
    #Every pair selects a choice from the last line then the second, 
    #if the choice was made then the second of the pair is disallowed from appearing
  
