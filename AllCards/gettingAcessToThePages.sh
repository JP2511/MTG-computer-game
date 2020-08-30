#!/bin/bash

#get the main page that contains the links to all expansions
curl http://mythicspoiler.com/sets.html |
cat >>mainpage.txt
python creatingAListOfAllExpansions.py #creates two files in the temps folder: one contains all the URLs for all the expansions and the other contains all the names
rm mainpage.txt                        #of the files for each expansion                   

#get the pages from the links of the expansions
mkdir temps
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
rm $(ls | grep "^...\.txt$")
mv $(ls | grep "^Commander") ../commanderDecks/
mv ./* ../expansions/
cd ..
rmdir temps

# #creates text files inside the commanderDecks folder that contain URLs to the cards and a text file that contains the names of the files createds
# cd commanderDecks
# for i in $(seq $number) ; do
#     rm $(cat ../commanderDecksURL.txt | cut --delimiter=_ -f$i)
# done
# rm ../commanderDecksURL.txt

# #opens each of the files that contains the URLs of the cards for the commander decks and creates a file with the content of the html page for each card
# numberOfUnderscores=$(awk -F\_ '{ print NF - 1 }' commanderDecksCardsURLs.txt)
# for i in $(seq $numberOfUnderscores) ; do
#     numberForEachFile=$(awk -F\_ '{ print NF - 1 }' $(cat commanderDecksCardsURLs.txt | cut --delimiter=_ -f$i))
#     for j in $(seq $numberForEachFile) ; do
#         curl $(cat $(cat commanderDecksCardsURLs.txt | cut --delimiter=_ -f$i) | cut --delimiter=_ -f$j) >>$(cat commanderDecksCardsURLs.txt | cut --delimiter=_ -f$i | cut --delimiter=\. -f1)_$(cat $(cat commanderDecksCardsURLs.txt | cut --delimiter=_ -f$i) | cut --delimiter=_ -f$j | cut --delimiter=/ -f6 | cut --delimiter=\. -f1).txt
#     done
# done

# #removes the previous files with the html page of each commander Deck expansion
# for i in $(seq $numberOfUnderscores) ; do
#     rm $(cat commanderDecksCardsURLs.txt | cut --delimiter=_ -f$i)
# done
# rm commanderDecksCardsURLs.txt

# #creating a file that contains all the names of the cards
# ls | cat >>cardsHTMLcommander.txt
# sleep 6

# #creating files with the cards' information for each expansion
# cd ..
# python creatingFilesWithInformationAboutCards.py
# cd commanderDecks/
# rm *
# cd ..
# rm cardsHTMLcommander.txtCards.txt
# mv *Cards.txt commanderDecks/