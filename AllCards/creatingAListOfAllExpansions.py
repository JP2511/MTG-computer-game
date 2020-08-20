#reads the main page of the website
with open("mainpage.txt") as datafile:
    data = datafile.read()

#gets the content after the first appearance of the introduced string
def getContent(data, x):
    j=0
    content=[]
    for i in range(len(data)):
        if(data[i].strip().startswith(x) and j == 0):
            j=i
        if(i>j and j != 0):
            content.append(data[i])
    return content

#gets the content of the website that is inside the body tag
mainNav = getContent(data.splitlines(), "<!--MAIN NAVIGATION-->")

#gets the content of the website that is inside of the outside center tags
content = getContent(mainNav, "<!--CONTENT-->")

#removes extra content from list
def restrictNoise(data, string):
    content=[]
    for i in data:
        if(i.startswith(string)):
            break
        content.append(i)
    return content

#return only lines which are links
def onlyLinks(data):
    content=[]
    for i in data:
        if(i.startswith("<a")):
            content.append(i)
    return content


#creates a list of the ending of the links to later fetch
def links(data):
    link=[]
    for i in data:
        link.append(i.split('"')[1])
    return link

#getting the commander decks
commanderDecksAndNoise = getContent(content,"<!---->")
commanderDeck = restrictNoise(commanderDecksAndNoise, "<!---->")
commanderDeckLinks = onlyLinks(commanderDeck)
commanderDecksCleanedLinks = links(commanderDeckLinks)

#creates a txt file and writes in it
def writeInTxt(nameOfFile, restrictions, usedList):
    file = open(nameOfFile,restrictions)
    for i in usedList:
        file.write(i+"\n")
    file.close()

#creating the commander deck text file
writeInTxt("commanderDeckLinks.txt", "w+", commanderDecksCleanedLinks)

#getting the expansions
expansionsWithNoise = getContent(commanderDecksAndNoise, "<!---->")
expansionsWithoutNoise = restrictNoise(expansionsWithNoise, "</table>")
expansionsLinksWNoise = onlyLinks(expansionsWithoutNoise)
expansionsCleanedLinks = links(expansionsLinksWNoise)

#creating the expansions text file
writeInTxt("expansionLinks.txt", "w+", expansionsCleanedLinks)
