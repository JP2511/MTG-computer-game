#opens a file from its path
def openingAFile(filepath):
    with open(filepath) as datafile:
        data = datafile.read()
    return data

#opens the CommanderDecks file that contains the name of the other created files
files=openingAFile("commanderDecksURL.txt").split("_")
filesWithCompleteNames = ["commanderDecks\\" + i for i in files[:-1]]

#create a list of all the names of the expansions from th URLs
def getNames(filesWithCompleteNames):
    title = []
    for i in filesWithCompleteNames:
        data = openingAFile(i).splitlines()
        for j in data:
            if(j.startswith("<title")):
                title.append(j.split(">")[1].split(" |")[0].split(" Spoiler")[0].replace(" ",""))
                break
    return title

#gets list of all the names of the expansions of the commander Decks
titles = getNames(filesWithCompleteNames)

#create list of lists of URLS
def getListsOfURLs(filesWithCompleteNames):
    listOfLists = []
    for i in range(len(filesWithCompleteNames)):
        data = openingAFile(filesWithCompleteNames[i]).splitlines()
        listOfURLs = []
        for j in data:
            if(j.startswith('<a class="card"') or j.startswith('<a href="cards') or j.startswith('<a href="../' + files[i].split(".txt")[0] + "/cards") or j.startswith('<a class="' + files[i].split(".txt")[0])):
                links = "http://mythicspoiler.com/" + files[i].split(".txt")[0] + "/" +j.split('href="')[1].split('"')[0]
                listOfURLs.append(links)
        listOfLists.append(listOfURLs)
    return listOfLists

#creates a list of list of URLs of the cards from each expansion of the commander decks
listOfListOfURLs = getListsOfURLs(filesWithCompleteNames)

#create files with the urls of the cards of each expansion
def createFilesWithCardsURLs(titles, listOfListOfURLs):
    for i in range(len(titles)):
        file = open("commanderDecks\\" + titles[i]+".txt", "w+")
        file.write("_".join(listOfListOfURLs[i])+"_n")
        file.close()

#create files with the urls of the cards of each expansion of the commander Decks
createFilesWithCardsURLs(titles, listOfListOfURLs)

#create a text file with the names of the created text files
file = open("commanderDecks\\commanderDecksCardsURLs.txt", "w+")
file.write("_".join([i + '.txt' for i in titles]) + "_n")
file.close()