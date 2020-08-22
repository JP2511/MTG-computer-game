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

#adds the rest of the link
def completLinks(data):
    completeLinks=[]
    for i in data:
        completeLinks.append("http://mythicspoiler.com/"+i)
    return completeLinks

#getting the commander decks
commanderDecksAndNoise = getContent(content,"<!---->")
commanderDeck = restrictNoise(commanderDecksAndNoise, "<!---->")
commanderDeckLinks = onlyLinks(commanderDeck)
commanderDecksCleanedLinks = links(commanderDeckLinks)
wholeCommanderDecksLinks = completLinks(commanderDecksCleanedLinks)
nameOfCommanderDeckExpansions = [i.split("/")[0]+".txt" for i in commanderDecksCleanedLinks]

#creates a number of text files each one with an URL   
def createFiles(urls, nameOfFile):
    for i in range(len(urls)):
        file = open(nameOfFile[i], "w+")
        file.write(urls[i])
        file.close()
        
#creates a text file with the name of all the files created with an URL in it
def createNameOfFilesFile(fileName, namesOfFiles):
    file = open(fileName, "w+")
    file.write("_".join(namesOfFiles)+"_n")
    file.close()

#creating the files with the commanderDeck links and a file with the name of all of the created files
createFiles(wholeCommanderDecksLinks, nameOfCommanderDeckExpansions)
createNameOfFilesFile("commanderDecksURL.txt", nameOfCommanderDeckExpansions)

#getting the expansions
expansionsWithNoise = getContent(commanderDecksAndNoise, "<!---->")
expansionsWithoutNoise = restrictNoise(expansionsWithNoise, "</table>")
expansionsLinksWNoise = onlyLinks(expansionsWithoutNoise)
expansionsCleanedLinks = links(expansionsLinksWNoise)


