#opens a file from its path
def openingAFile(filepath, encodingt):
    with open("commanderDecks/" + filepath,encoding=encodingt, errors = "ignore") as datafile:
        data = datafile.read()
    return data

#reduce html context to between tags of <!--Content--> and <!--END CARD TEXT-->
def reduceHTMLPage(data):
    reducedHTMLpage = []
    j = 0
    for i in data:
        if(i.startswith("<!--CONTENT-->") or j != 0):
            j = 1
            reducedHTMLpage.append(i)
        if(i.startswith("<!--END CARD TEXT-->")):
            reducedHTMLpage.append(i)
            break
    return reducedHTMLpage

#get card Name
def cardName(data):
    reducedEvenMoreHTML = []
    j = 0
    for i in data:
        if(i.startswith("<!--CARD NAME-->")):
            j = 1
        if(i.startswith("</font") and j != 0):
            break
        if( j != 0 and not i.startswith("<!--CARD NAME-->")):
            reducedEvenMoreHTML.append(i)
    return reducedEvenMoreHTML[0]

#get manaCost
def getManaCost(data):
    reducedEvenMoreHTML = []
    j = 0
    for i in data:
        if(i.startswith("<!--MANA COST-->")):
            j = 1
        if(i.startswith("</td") and j != 0):
            break
        if( j != 0 and not i.startswith("<!--MANA COST-->")):
            reducedEvenMoreHTML.append(i)
    return reducedEvenMoreHTML[0]

#get type and subtype of card
def getTypeAndSubtype(data):
    reducedEvenMoreHTML = []
    j = 0
    for i in data:
        if(i.startswith("<!--TYPE-->")):
            j = 1
        if(i.startswith("</td") and j != 0):
            break
        if( j != 0 and not i.startswith("<!--TYPE-->")):
            reducedEvenMoreHTML.append(i)
    return reducedEvenMoreHTML[0]

#get type
def getType(data):
    typeAndSubtype = getTypeAndSubtype(data)
    if(typeAndSubtype.split(" - ")[0].startswith("Legendary ")):
        theType = typeAndSubtype.split(" - ")[0].split("Legendary ")[1]
    else:
        theType = typeAndSubtype.split(" - ")[0]
    return theType

#get subtype
def getSubtype(data):
    typeAndSubtype = getTypeAndSubtype(data)
    if(typeAndSubtype.count("-") == 0):
        subType = ""
    else:
        subType = typeAndSubtype.split(" - ")[1]
    return subType

#get the effects of the card
def cardText(data):
    reducedEvenMoreHTML = []
    j = 0
    for i in data:
        if(i.startswith("<!--TYPE-->")):
            j = 1
        if(i.startswith("<!--CARD TEXT-->") and j == 1):
            j = 2
        if( j == 2 and not i.startswith("<!--CARD TEXT-->")):
            reducedEvenMoreHTML.append(i)
        if(i.startswith("</td>") and j ==2 ):
            break
    text = []
    for i in reducedEvenMoreHTML:
        if(i.count("<") != 0):
            g = i.split("<")[0]
        else:
            g = i
        if(not(g.startswith("<")) and len(g) != 0):
            text.append(g)
    return text

#get card power and life/defense
def getPowerAndLife(data):
    reducedEvenMoreHTML = []
    j = 0
    for i in data:
        if(i.startswith("<!--P/T-->")):
            j = 1
        if(i.startswith("<!--END CARD TEXT-->")):
            break
        if(j == 1 and not i.startswith("<!--P/T-->")):
            reducedEvenMoreHTML.append(i)
    if(len(reducedEvenMoreHTML)==0):
        result = ""
    else:
        result = reducedEvenMoreHTML[0]
    return result

#get life/defense
def getLife(data):
    powerAndLife = getPowerAndLife(data)
    if(powerAndLife.count("/") != 0):
        life = powerAndLife.split("/")[1]
    else:
        life = powerAndLife
    return life

#get power
def getPower(data):
    powerAndLife = getPowerAndLife(data)
    if(powerAndLife.count("/") != 0):
        power = powerAndLife.split("/")[0]
    else:
        power = ""
    return power

#getting the expansions of the files
def namesOfExpansions(files):
    listOfNamesOfExpansions = []
    for i in files:
        if(i.split("_")[0] not in listOfNamesOfExpansions):
            listOfNamesOfExpansions.append(i.split("_")[0])
    return listOfNamesOfExpansions

#organizing the cards into various list separated by expansion
def listSeparatedByExpansions(files, namesOfExpansions):
    listOfListsOfExpansions = []
    for i in namesOfExpansions:
        listOfCards = []
        for j in files:
            if(j.split("_")[0]==i):
                listOfCards.append(j)
        listOfListsOfExpansions.append(listOfCards)
    return listOfListsOfExpansions       
 
#returns a list of lists with strings each string has all information regarding a card and each list has all cards of an expansion
def getListOfCardInformationPerExpansion(listOfListOfCommanderCards):
    listOfListOfCardsPerExpansion = []
    for i in listOfListOfCommanderCards:
        listOfCardsPerExpansion = []
        for j in i:
            card = ""
            cardInformation = []
            a = openingAFile(j,"utf8")
            b = a.splitlines()
            b = reduceHTMLPage(b)
            if(len(b)==0):
                next
            else:
                if(len(cardName(b))==0):
                    name = j.split("_")[1].split(".")[0]
                else:
                    name = cardName(b)
                cardInformation.append(name)
                cardInformation.append(getManaCost(b))
                cardInformation.append(getType(b))
                cardInformation.append(getSubtype(b))
                cardInformation.append("@".join(cardText(b)))
                cardInformation.append(getLife(b))
                cardInformation.append(getPower(b))
                card = "_".join(cardInformation)
                listOfCardsPerExpansion.append(card)
        listOfListOfCardsPerExpansion.append(listOfCardsPerExpansion)
    return listOfListOfCardsPerExpansion

#create a text file with the specified name and with the elements of list separated by lines
def writeFile(informationAboutCards, nameOfExpansion):
    file = open(nameOfExpansion + "Cards.txt", "w+")
    for i in informationAboutCards:
        file.write(i + "\n")
    file.close()

#creates a file for each expansion of the commander decks and fills it with their cards
files = openingAFile("cardsHTMLcommander.txt", "utf-8").splitlines()
namesOfCommanderExpansions = namesOfExpansions(files)
listOfListOfCommanderCards = listSeparatedByExpansions(files, namesOfCommanderExpansions)
listOfListOfCardsPerCommanderExpansion = getListOfCardInformationPerExpansion(listOfListOfCommanderCards)
for j in range(len(namesOfCommanderExpansions)):
    writeFile(listOfListOfCardsPerCommanderExpansion[j], namesOfCommanderExpansions[j])

