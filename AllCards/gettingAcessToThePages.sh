#!/bin/bash

#get the main page that contains the links to all expansions
curl http://mythicspoiler.com/sets.html |
cat >>mainpage.txt
python creatingAListOfAllExpansions.py #creates two files in the temps folder: one contains all the URLs for all the expansions and the other contains all the names
rm mainpage.txt                        #of the files for each expansion
mkdir temps/ expansions/ commanderDecks/

#get the pages from the links of the expansions
cd temps/
numberOfExpansions=$(awk -F_ '{ print NF - 1}' allExpansionsFileNames.txt)
for i in $(seq $numberOfExpansions) ; do
    curl $(cat allExpansionsURLs.txt | cut --delimiter=_ -f$i) >>$(cat allExpansionsFileNames.txt | cut --delimiter=_ -f$i)
done
rm allExpansionsURLs.txt
cd ..

#creates text files named after their expansion containing the URLs to all cards of that expansion
python creatingExpansionFilesWithCardURLs.py
cd temps/
rm ccon.txt allExpansionsFileNames.txt
rm $(ls | grep "^...\.txt$")
mv $(ls | grep "^Commander") ../commanderDecks/
mv ./* ../expansions/
cd ..

#opens each of the files that contains the URLs of the cards for the commander decks and creates a file with the content of the html page for each card
numberOfUnderscores=$(awk -F\_ '{ print NF - 1 }' allExpansions.txt)
for i in $(seq $numberOfUnderscores) ; do
    numberForEachFile=$(awk -F\_ '{ print NF - 1 }' $(cat allExpansions.txt | cut --delimiter=_ -f$i))
    for j in $(seq $numberForEachFile) ; do
        curl $(cat $(cat allExpansions.txt | cut --delimiter=_ -f$i) | cut --delimiter=_ -f$j) >>temps/$(cat allExpansions.txt | cut --delimiter=_ -f$i | cut --delimiter=\. -f1 | cut --delimiter=/ -f2)_$(cat $(cat allExpansions.txt | cut --delimiter=_ -f$i) | cut --delimiter=_ -f$j | cut --delimiter=/ -f6 | cut --delimiter=\. -f1).txt
    done
done

#creates the files with the names of the expansions and cleans unnecessary files
ls expansions/ >>allExpansionNames.txt
ls commanderDecks >>allExpansionNames.txt
rm -r expansions/
rm -r commanderDecks/
ls temps/ >>cardsNames.txt
rm allExpansions.txt

#executes the python script to create the files that contain information of all cards for that particular expansion
python creatingFilesWithInformationAboutCards.py
rm -r temps/
rm allExpansionNames.txt cardsNames.txt