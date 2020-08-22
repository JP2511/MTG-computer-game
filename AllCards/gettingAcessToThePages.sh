#!/bin/bash

#get the main page that contains the links to all expansions
curl http://mythicspoiler.com/sets.html |
cat >>mainpage.txt
python creatingAListOfAllExpansions.py #returns a lot of text files each one with a link to webpage and two text files with the names of the created text files with URLs
rm mainpage.txt

#get the pages from the links of the commander Decks
number=$(awk -F\_ '{ print NF - 1}' commanderDecksURL.txt) #
for i in $(seq $number) ; do
    curl $(cat $(cat commanderDecksURL.txt | cut --delimiter=_ -f$i)) >>$(cat commanderDecksURL.txt | cut --delimiter=_ -f$i)
done
mv $(ls | grep "c..\.txt") commanderDecks/

#creates text files inside the commanderDecks folder that contain URLs to the cards and a text file that contains the names of the files createds
python creatingCommanderCardsURLs.py
cd commanderDecks
for i in $(seq $number) ; do
    rm $(cat ../commanderDecksURL.txt | cut --delimiter=_ -f$i)
done
rm ..commanderDecksURL.txt