# Percentile Rank Calculator

Application calculates percentile rank (https://en.wikipedia.org/wiki/Percentile_rank) 
based on the GPA (=the score of interest).<br/>
Input data is provided in a comma-separated format where the columns are ID, name, and GPA,
in this order.<br/>
The program output should contain the student name, GPA and
calculated percentile rank.

##  Instructions to run application from sources 

1) Checkout code:
    
    git clone https://github.com/milos-savic/percentile-rank.git

2) Open project folder

	cd percentile_rank

3) Compile all the sources

	mvn clean install

4) Run application 
    
    java MainClass.java -input.csv
    

############################################################
## Instructions for development process

### Packaging to executable archive

    mvn clean package

To ensure everything worked, run:

    java -jar target/PercentileRankCalc-Fat.jar



